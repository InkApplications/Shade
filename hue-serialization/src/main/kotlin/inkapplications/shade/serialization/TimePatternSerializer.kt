package inkapplications.shade.serialization

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import inkapplications.shade.constructs.TimePattern
import inkapplications.shade.constructs.TimePattern.*
import inkapplications.shade.constructs.TimePattern.Timer.*
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import java.lang.IllegalArgumentException

/**
 * Serializes to/from any of Hue's time formats
 *
 * Hey there, this sucked to make. Hue's system has a huge variety of
 * time formats that can appear in its API in polymorphic fields.
 * This parses it out to a TimeFormat sealed class, which seems like
 * the most sane way to handle things that could be any format.
 */
object TimePatternSerializer {
    @FromJson fun fromJson(data: String): TimePattern = when {
        data.matches(AbsoluteTimeSerializer.PATTERN) -> AbsoluteTimeSerializer.fromJson(data)
        data.matches(RandomizedTimeSerializer.PATTERN) -> RandomizedTimeSerializer.fromJson(data)
        data.matches(RecurringTimeSerializer.PATTERN) -> RecurringTimeSerializer.fromJson(data)
        data.matches(RecurringRandomizedTimeSerializer.PATTERN) -> RecurringRandomizedTimeSerializer.fromJson(data)
        data.matches(TimeIntervalSerializer.PATTERN) -> TimeIntervalSerializer.fromJson(data)
        data.matches(ExpiringTimerSerializer.PATTERN) -> ExpiringTimerSerializer.fromJson(data)
        data.matches(RandomExpiringTimerSerializer.PATTERN) -> RandomExpiringTimerSerializer.fromJson(data)
        data.matches(RecurringTimerSerializer.PATTERN) -> RecurringTimerSerializer.fromJson(data)
        data.matches(RandomRecurringTimerSerializer.PATTERN) -> RandomRecurringTimerSerializer.fromJson(data)
        else -> throw IllegalArgumentException("Unknown TimePattern: $data")
    }

    @ToJson fun toJson(data: TimePattern): String = when (data) {
        is AbsoluteTime -> AbsoluteTimeSerializer.toJson(data)
        is RandomizedTime -> RandomizedTimeSerializer.toJson(data)
        is RecurringTime -> RecurringTimeSerializer.toJson(data)
        is RecurringRandomizedTime -> RecurringRandomizedTimeSerializer.toJson(data)
        is TimeInterval -> TimeIntervalSerializer.toJson(data)
        is ExpiringTimer -> ExpiringTimerSerializer.toJson(data)
        is RandomExpiringTimer -> RandomExpiringTimerSerializer.toJson(data)
        is RecurringTimer -> RecurringTimerSerializer.toJson(data)
        is RandomRecurringTimer -> RandomRecurringTimerSerializer.toJson(data)
    }
}

/**
 * Handles Hue's fixed date/time objects.
 *
 * This is the least surprising of all of the time formats, but we
 * don't know what time zone it's in. So this is left as a LocalDateTime.
 * Good luck.
 */
object AbsoluteTimeSerializer {
    val PATTERN = Regex("(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})")

    @FromJson fun fromJson(data: String): AbsoluteTime = AbsoluteTime(LocalDateTime.parse(data))
    @ToJson fun toJson(data: AbsoluteTime): String = data.time.format(DateTimeFormatter.ISO_DATE_TIME)
}

/**
 * Handles a fixed time range.
 *
 * This is used to convey an event that happens semi-randomly
 * inside of a range of times.
 */
object RandomizedTimeSerializer {
    val PATTERN = Regex("(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})A(\\d{2}):(\\d{2}):(\\d{2})")

    @FromJson fun fromJson(data: String): RandomizedTime {
        val (dateString, startString, endString) = data.split('T', 'A')

        val date = LocalDate.parse(dateString)
        val start = LocalTime.parse(startString)
        val end = LocalTime.parse(endString)

        return RandomizedTime(date, start..end)
    }

    @ToJson fun toJson(data: RandomizedTime): String {
        val dateString = data.date.format(DateTimeFormatter.ISO_DATE)
        val startString = data.timeRange.start.format(DateTimeFormatter.ISO_TIME)
        val endString = data.timeRange.endInclusive.format(DateTimeFormatter.ISO_TIME)

        return "${dateString}T${startString}A${endString}"
    }
}

/**
 * Handles a repeating event.
 *
 * This isn't documented at all, but the days of week are specified as
 * a three-digit binary flag 0-127 before the time.
 */
object RecurringTimeSerializer {
    val PATTERN = Regex("W(\\d{3})/T((\\d{2}):(\\d{2}):(\\d{2}))")

    @FromJson  fun fromJson(data: String): RecurringTime {
        val (daysString, timeString) = PATTERN.find(data)!!.destructured

        val time = LocalTime.parse(timeString, DateTimeFormatter.ISO_TIME)
        val days = DayOfWeek.values()
            .filter { (daysString.toInt() and it.flag) == it.flag }
            .toSet()

        return RecurringTime(days, time)
    }

    @ToJson fun toJson(data: RecurringTime): String {
        val time = data.time.format(DateTimeFormatter.ISO_TIME)
        val days = data.days.map { it.flag }.sum()

        return "W%03d/T%s".format(days, time)
    }
}

/**
 * Handles a repeating event that occurs randomly inside a time range.
 *
 * This isn't documented at all, but the days of week are specified as
 * a three-digit binary flag 0-127 before the time.
 */
object RecurringRandomizedTimeSerializer {
    val PATTERN = Regex("W(\\d{3})/T((\\d{2}):(\\d{2}):(\\d{2}))A((\\d{2}):(\\d{2}):(\\d{2}))")

    @FromJson fun fromJson(data: String): RecurringRandomizedTime {
        val parts = PATTERN.find(data)!!
        val daysString = parts.groups[1]?.value!!
        val startTimeString = parts.groups[2]?.value!!
        val endTimeString = parts.groups[6]?.value!!

        val startTime = LocalTime.parse(startTimeString, DateTimeFormatter.ISO_TIME)
        val endTime = LocalTime.parse(endTimeString, DateTimeFormatter.ISO_TIME)
        val days = DayOfWeek.values()
            .filter { (daysString.toInt() and it.flag) == it.flag }
            .toSet()

        return RecurringRandomizedTime(days, startTime..endTime)
    }

    @ToJson fun toJson(data: RecurringRandomizedTime): String {
        val startTime = data.timeRange.start.format(DateTimeFormatter.ISO_TIME)
        val endTime = data.timeRange.endInclusive.format(DateTimeFormatter.ISO_TIME)
        val days = data.days.map { it.flag }.sum()

        return "W%03d/T%sA%s".format(days, startTime, endTime)
    }
}

/**
 * Handles a recurring time-span, which Hue calls a time interval.
 *
 * The days are optional, here and is nullable to preserve bijectivity.
 */
object TimeIntervalSerializer {
    val PATTERN = Regex("(W(\\d{3})/)?T((\\d{2}):(\\d{2}):(\\d{2}))/T((\\d{2}):(\\d{2}):(\\d{2}))")

    @FromJson fun fromJson(data: String): TimeInterval {
        val parts = PATTERN.find(data)!!
        val daysString: String? = parts.groups[2]?.value
        val startTimeString = parts.groups[3]!!.value
        val endTimeString = parts.groups[7]!!.value

        val startTime = LocalTime.parse(startTimeString, DateTimeFormatter.ISO_TIME)
        val endTime = LocalTime.parse(endTimeString, DateTimeFormatter.ISO_TIME)
        val days = if (daysString == null) null else DayOfWeek.values()
                .filter { (daysString.toInt() and it.flag) == it.flag }
                .toSet()

        return TimeInterval(days, startTime..endTime)
    }

    @ToJson fun toJson(data: TimeInterval): String {
        val startTime = data.timeRange.start.format(DateTimeFormatter.ISO_TIME)
        val endTime = data.timeRange.endInclusive.format(DateTimeFormatter.ISO_TIME)
        val days = data.days?.map { it.flag }?.sum()

        return if (days != null) "W%03d/T%s/T%s".format(days, startTime, endTime) else "T%s/T%s".format(startTime, endTime)
    }
}

/**
 * Handles a timer that expires after a given time.
 */
object ExpiringTimerSerializer {
    val PATTERN = Regex("PT((\\d{2}):(\\d{2}):(\\d{2}))")

    @FromJson fun fromJson(data: String): ExpiringTimer {
        val (time) = PATTERN.find(data)!!.destructured

        return ExpiringTimer(LocalTime.parse(time, DateTimeFormatter.ISO_TIME))
    }

    @ToJson fun toJson(data: ExpiringTimer): String {
        val time = data.expiration.format(DateTimeFormatter.ISO_TIME)

        return "PT%s".format(time)
    }
}

/**
 * Handles timers that expire randomly within a time range.
 */
object RandomExpiringTimerSerializer {
    val PATTERN = Regex("PT((\\d{2}):(\\d{2}):(\\d{2}))A((\\d{2}):(\\d{2}):(\\d{2}))")

    @FromJson fun fromJson(data: String): RandomExpiringTimer {
        val parts = PATTERN.find(data)!!
        val startTimeString = parts.groups[1]!!.value
        val endTimeString = parts.groups[5]!!.value
        val startTime = LocalTime.parse(startTimeString, DateTimeFormatter.ISO_TIME)
        val endTime = LocalTime.parse(endTimeString, DateTimeFormatter.ISO_TIME)

        return RandomExpiringTimer(startTime..endTime)
    }

    @ToJson fun toJson(data: RandomExpiringTimer): String {
        val startTime = data.expiration.start.format(DateTimeFormatter.ISO_TIME)
        val endTime = data.expiration.endInclusive.format(DateTimeFormatter.ISO_TIME)

        return "PT%sA%s".format(startTime, endTime)
    }
}

/**
 * Handles timers that repeat.
 */
object RecurringTimerSerializer {
    val PATTERN = Regex("R(\\d{2})?/PT((\\d{2}):(\\d{2}):(\\d{2}))")

    @FromJson fun fromJson(data: String): RecurringTimer {
        val parts = PATTERN.find(data)!!
        val occurrences = parts.groups[1]?.value?.toInt()
        val time = LocalTime.parse(parts.groups[2]!!.value, DateTimeFormatter.ISO_TIME)

        return RecurringTimer(occurrences, time)
    }

    @ToJson fun toJson(data: RecurringTimer): String {
        val time = data.time.format(DateTimeFormatter.ISO_TIME)
        val occurrences = data.occurrences

        return if (occurrences != null) "R%02d/PT%s".format(occurrences, time) else  "R/PT%s".format(time)
    }
}

/**
 * Handles timers that repeat and expire randomly within a time range.
 */
object RandomRecurringTimerSerializer {
    val PATTERN = Regex("R(\\d{2})?/PT((\\d{2}):(\\d{2}):(\\d{2}))A((\\d{2}):(\\d{2}):(\\d{2}))")

    @FromJson fun fromJson(data: String): RandomRecurringTimer {
        val parts = PATTERN.find(data)!!
        val occurrences = parts.groups[1]?.value?.toInt()
        val startTime = LocalTime.parse(parts.groups[2]!!.value, DateTimeFormatter.ISO_TIME)
        val endTime = LocalTime.parse(parts.groups[6]!!.value, DateTimeFormatter.ISO_TIME)

        return RandomRecurringTimer(occurrences, startTime..endTime)
    }

    @ToJson fun toJson(data: RandomRecurringTimer): String {
        val startTime = data.timeRange.start.format(DateTimeFormatter.ISO_TIME)
        val endTime = data.timeRange.endInclusive.format(DateTimeFormatter.ISO_TIME)
        val occurrences = data.occurrences

        return if (occurrences != null) "R%02d/PT%sA%s".format(occurrences, startTime, endTime) else  "R/PT%sA%s".format(startTime, endTime)
    }
}

/**
 * Converts a standard DayOfWeek enum into Hue's Binary flags.
 */
internal val DayOfWeek.flag: Int get() = when(this) {
    DayOfWeek.SUNDAY -> 64
    DayOfWeek.MONDAY -> 32
    DayOfWeek.TUESDAY -> 16
    DayOfWeek.WEDNESDAY -> 8
    DayOfWeek.THURSDAY -> 4
    DayOfWeek.FRIDAY -> 2
    DayOfWeek.SATURDAY -> 1
}

