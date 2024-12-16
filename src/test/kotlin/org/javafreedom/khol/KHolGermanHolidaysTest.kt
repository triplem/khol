package org.javafreedom.khol

import kotlinx.datetime.LocalDate
import org.javafreedom.khol.declarations.GermanHolidayDeclarations
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse


class KHolGermanHolidaysTest {

    @Test
    fun kholTestNW2024() {
        val sut = KHol(GermanHolidayDeclarations(), 2024, "NW")

        val validHoldays = sut.validHolidays()

        assertEquals(11, validHoldays.size)
        assertContains(validHoldays, LocalDate.parse("2024-11-01"))
    }

    @Test
    fun kholTestHE2024() {
        val sut = KHol(GermanHolidayDeclarations(), 2024, "HE")

        val validHoldays = sut.validHolidays()

        assertEquals(10, validHoldays.size)
        assertFalse { validHoldays.contains(LocalDate.parse("2024-11-01")) }
    }

    @Test
    fun kholTestHE1989() {
        val sut = KHol(GermanHolidayDeclarations(), 1989, "HE")

        val validHoldays = sut.validHolidays()

        assertEquals(0, validHoldays.size)
    }

}