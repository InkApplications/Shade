package inkapplications.shade.constructs

import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime

/**
 * Abstraction of all of the various types of date/times that Hue handles.
 *
 * These are all documented exceptionally poorly in Hue's Documentation.
 * Please report any bugs found with this.
 */
sealed class TimePattern {
    /**
     * An exact specified time.
     *
     * This time should be local to the hub. UTC times are deprecated in the Schedule API's
     */
    data class AbsoluteTime(val time: LocalDateTime): TimePattern()

    /**
     * A time range when an event can happen randomly inside of.
     */
    data class RandomizedTime(val date: LocalDate, val timeRange: ClosedRange<LocalTime>): TimePattern()

    /**
     * Every specified day of the week at a specified time.
     */
    data class RecurringTime(val days: Set<DayOfWeek>, val time: LocalTime): TimePattern()

    /**
     * Every specified day randomly between a range of times.
     */
    data class RecurringRandomizedTime(val days: Set<DayOfWeek>, val timeRange: ClosedRange<LocalTime>): TimePattern()

    /**
     * Every day *from* the start of the range to the end of the range.
     *
     * This could be easily confused with a `RecurringRandomizedTime`.
     * It is different in that this event *spans* the range from start
     * to end, as opposed to occurring randomly inside it.
     *
     * @param days The days this event should occur on. If null, it should occur daily.
     */
    data class TimeInterval(val days: Set<DayOfWeek>?, val timeRange: ClosedRange<LocalTime>): TimePattern()

    /**
     * Abstraction for the various types of timers.
     */
    sealed class Timer: TimePattern() {
        /**
         * Timer expiring after the specified time.
         */
        data class ExpiringTimer(val expiration: LocalTime): Timer()

        /**
         * Timer expiring randomly within a time range.
         */
        data class RandomExpiringTimer(val expiration: ClosedRange<LocalTime>): Timer()

        /**
         * Timer that repeats.
         *
         * @param occurrences If specified, this is the number of times the timer will repeat.
         */
        data class RecurringTimer(val occurrences: Int?, val time: LocalTime): Timer()

        /**
         * Timer that repeats and expires randomly within a time range.
         *
         * @param occurrences If specified, this is the number of times the timer will repeat.
         */
        data class RandomRecurringTimer(val occurrences: Int?, val timeRange: ClosedRange<LocalTime>): Timer()
    }
}
