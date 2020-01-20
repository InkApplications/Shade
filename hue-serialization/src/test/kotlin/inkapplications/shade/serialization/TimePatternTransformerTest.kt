package inkapplications.shade.serialization

import inkapplications.shade.constructs.TimePattern.*
import org.junit.Test

import org.junit.Assert.*
import org.threeten.bp.DayOfWeek
import java.lang.IllegalArgumentException

class TimePatternTransformerTest {
    @Test fun absoluteTimes() {
        val input = "2019-01-02T03:04:05"
        val result = TimePatternTransformer.fromJson(input)
        val bijectiveResult = TimePatternTransformer.toJson(result)

        assertTrue("Parse correct type", result is AbsoluteTime)
        assertEquals(input, bijectiveResult)
    }

    @Test fun randomizedTimes() {
        val input = "2019-01-02T03:04:05A06:07:08"
        val result = TimePatternTransformer.fromJson(input)
        val bijectiveResult = TimePatternTransformer.toJson(result)

        assertTrue("Parse correct type", result is RandomizedTime)
        assertEquals(input, bijectiveResult)
    }

    @Test fun recurringTimes() {
        val input = "W005/T01:02:03"
        val result = TimePatternTransformer.fromJson(input)
        val bijectiveResult = TimePatternTransformer.toJson(result)

        assertTrue("Parse correct type", result is RecurringTime)
        assertEquals(2, (result as RecurringTime).days.size)
        assertTrue("Days of Week Parse correctly", DayOfWeek.THURSDAY in result.days)
        assertTrue("Days of Week Parse correctly", DayOfWeek.SATURDAY in result.days)
        assertEquals(input, bijectiveResult)
    }

    @Test fun randomRecurringTimes() {
        val input = "W064/T01:02:03A04:05:06"
        val result = TimePatternTransformer.fromJson(input)
        val bijectiveResult = TimePatternTransformer.toJson(result)

        assertTrue("Parse correct type", result is RecurringRandomizedTime)
        assertEquals(1, (result as RecurringRandomizedTime).days.size)
        assertTrue("Days of Week Parse correctly", DayOfWeek.SUNDAY in result.days)
        assertEquals(input, bijectiveResult)
    }

    @Test fun timeIntervalsTest() {
        val input = "W064/T01:02:03/T04:05:06"
        val result = TimePatternTransformer.fromJson(input)
        val bijectiveResult = TimePatternTransformer.toJson(result)

        assertTrue("Parse correct type", result is TimeInterval)
        assertNotNull((result as TimeInterval).days)
        assertEquals(1, result.days!!.size)
        assertTrue("Days of week parse correctly", DayOfWeek.SUNDAY in result.days!!)
        assertEquals(input, bijectiveResult)
    }

    @Test fun dailyTimeIntervalsTest() {
        val input = "T01:02:03/T04:05:06"
        val result = TimePatternTransformer.fromJson(input)
        val bijectiveResult = TimePatternTransformer.toJson(result)

        assertTrue("Parse correct Type", result is TimeInterval)
        assertNull((result as TimeInterval).days)
        assertEquals(input, bijectiveResult)
    }

    @Test fun expiringTimersTest() {
        val input = "PT01:02:03"
        val result = TimePatternTransformer.fromJson(input)
        val bijectiveResult = TimePatternTransformer.toJson(result)

        assertTrue("Parse correct type", result is Timer.ExpiringTimer)
        assertEquals(input, bijectiveResult)
    }

    @Test fun randomExpiringTimersTest() {
        val input = "PT01:02:03A04:05:06"
        val result = TimePatternTransformer.fromJson(input)
        val bijectiveResult = TimePatternTransformer.toJson(result)

        assertTrue("Parse correct type", result is Timer.RandomExpiringTimer)
        assertEquals(input, bijectiveResult)
    }

    @Test fun recurringTimersTest() {
        val input = "R/PT01:02:03"
        val result = TimePatternTransformer.fromJson(input)
        val bijectiveResult = TimePatternTransformer.toJson(result)

        assertTrue("Parse correct type", result is Timer.RecurringTimer)
        assertEquals(input, bijectiveResult)
    }

    @Test fun recurringTimersWithOccurrencesTest() {
        val input = "R01/PT02:03:04"
        val result = TimePatternTransformer.fromJson(input)
        val bijectiveResult = TimePatternTransformer.toJson(result)

        assertTrue("Parse correct type", result is Timer.RecurringTimer)
        assertEquals(input, bijectiveResult)
    }

    @Test fun randomRecurringTimersTest() {
        val input = "R/PT02:03:04A05:06:07"
        val result = TimePatternTransformer.fromJson(input)
        val bijectiveResult = TimePatternTransformer.toJson(result)

        assertTrue("Parse correct type", result is Timer.RandomRecurringTimer)
        assertEquals(input, bijectiveResult)
    }

    @Test fun randomRecurringTimersWithOccurrencesTest() {
        val input = "R01/PT02:03:04A05:06:07"
        val result = TimePatternTransformer.fromJson(input)
        val bijectiveResult = TimePatternTransformer.toJson(result)

        assertTrue("Parse correct type", result is Timer.RandomRecurringTimer)
        assertEquals(input, bijectiveResult)
    }

    @Test(expected = IllegalArgumentException::class) fun unknownFormat() {
        val input = "123"
        TimePatternTransformer.fromJson(input)
    }
}
