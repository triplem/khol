package org.javafreedom.khol

import kotlinx.datetime.LocalDate
import org.javafreedom.khol.declarations.GermanHolidayDeclarations
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse

internal class KHolGermanHolidaysTest {

    @Test
    fun northrhineContainsReformationDay() {
        val sut = KHol(GermanHolidayDeclarations(), "NW")

        val validHolidays = sut.validHolidays(2024)

        assertEquals(11, validHolidays.size)
        assertContains(validHolidays, LocalDate.parse("2024-11-01"))
    }

    @Test
    fun hessenDoesNotContainReformationDay() {
        val sut = KHol(GermanHolidayDeclarations(), "HE")

        val validHolidays = sut.validHolidays(2024)

        assertEquals(10, validHolidays.size)
        assertFalse { validHolidays.contains(LocalDate.parse("2024-11-01")) }
    }

    @Test
    fun thereAreNoDefinedHolidaysBefore1990() {
        val sut = KHol(GermanHolidayDeclarations(), "HE")

        assertThrows<KHolException> { sut.validHolidays(1989) }
    }

    @Test
    fun bremenHolidaysBefore2018WithoutReformationDay() {
        val sut = KHol(GermanHolidayDeclarations(), "HB")

        val validHolidays = sut.validHolidays(2017)

        assertEquals(9, validHolidays.size)
    }

    @Test
    fun bremenHolidays2018WithReformationDay() {
        val sut = KHol(GermanHolidayDeclarations(), "HB")

        val validHolidays = sut.validHolidays(2018)

        assertEquals(10, validHolidays.size)
    }

    @Test
    fun calculateHolidaysBetweenTwoDates() {
        val sut = KHol(GermanHolidayDeclarations(), "HB")

        val validHolidays = sut.validHolidays(
            LocalDate(2024, 1, 1),
            LocalDate(2025, 1, 1)
        )

        assertEquals(10, validHolidays.size)
    }

    @Test
    fun calculateHolidaysBetweenTwoDatesEndIsExclusive() {
        val sut = KHol(GermanHolidayDeclarations(), "HB")

        val validHolidays = sut.validHolidays(LocalDate(2024, 1, 1),
            LocalDate(2024, 12, 26))

        assertEquals(9, validHolidays.size)
    }

    @Test
    fun calculateHolidaysWithTwoEqualDatesFail() {
        val sut = KHol(GermanHolidayDeclarations(), "HE")

        assertThrows<KHolException> { sut.validHolidays(LocalDate(2024, 1, 1),
            LocalDate(2024, 1, 1)) }
    }

}
