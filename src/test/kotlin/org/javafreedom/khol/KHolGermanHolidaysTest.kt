package org.javafreedom.khol

import kotlinx.datetime.LocalDate
import org.javafreedom.khol.declarations.GermanHolidayDeclarations
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse

internal class KHolGermanHolidaysTest {

    @Test
    fun northrhineContainsReformationDay() {
        val sut = KHol(GermanHolidayDeclarations(), 2024, "NW")

        val validHolidays = sut.validHolidays()

        assertEquals(11, validHolidays.size)
        assertContains(validHolidays, LocalDate.parse("2024-11-01"))
    }

    @Test
    fun hessenDoesNotContainReformationDay() {
        val sut = KHol(GermanHolidayDeclarations(), 2024, "HE")

        val validHolidays = sut.validHolidays()

        assertEquals(10, validHolidays.size)
        assertFalse { validHolidays.contains(LocalDate.parse("2024-11-01")) }
    }

    @Test
    fun thereAreNoDefinedHolidaysBefore1990() {
        val sut = KHol(GermanHolidayDeclarations(), 1989, "HE")

        val validHolidays = sut.validHolidays()

        assertEquals(0, validHolidays.size)
    }

    @Test
    fun bremenHolidaysBefore2018WithoutReformationDay() {
        val sut = KHol(GermanHolidayDeclarations(), 2017, "HB")

        val validHolidays = sut.validHolidays()

        assertEquals(9, validHolidays.size)
    }

    @Test
    fun bremenHolidays2018WithReformationDay() {
        val sut = KHol(GermanHolidayDeclarations(), 2018, "HB")

        val validHolidays = sut.validHolidays()

        assertEquals(10, validHolidays.size)
    }

}
