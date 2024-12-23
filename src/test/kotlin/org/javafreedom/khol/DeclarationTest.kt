package org.javafreedom.khol

import kotlinx.datetime.number
import kotlin.test.Test
import kotlin.test.assertEquals

private data class DeclarationArguments(val year: Int, val month: Int, val day: Int, val days: Int)

class DeclarationTest {

    @Test
    fun fixedHolidayToLocalDate() {
        val fixed = Declaration.FixedHoliday(10, 20, "dummy")

        val result = fixed.concreteForYear(2001)
        assertEquals(2001, result.year)
        assertEquals(10, result.month.number)
        assertEquals(20, result.dayOfMonth)
    }

    private val gregorianEasterSundayArgs = mutableListOf(
        DeclarationArguments(2008, 3, 23, 0),
        DeclarationArguments(2015, 4, 6, 1),
        DeclarationArguments(2017, 4, 15, -1),
        DeclarationArguments(2018, 4, 7, 6),
        DeclarationArguments(2019, 4, 19, -2),
        DeclarationArguments(2027, 3, 28, 0))

    @Test
    fun easterBasedHolidayToLocalDate() {
        gregorianEasterSundayArgs.forEach {
            val easterBased = Declaration.EasterBasedHoliday(it.days, "dummy")

            val result = easterBased.concreteForYear(it.year)
            assertEquals(it.year, result.year)
            assertEquals(it.month, result.month.number)
            assertEquals(it.day, result.dayOfMonth)
        }
    }

    private val orthodoxEasterSundayArgs = mutableListOf(
        DeclarationArguments(2008, 4, 27, 0),
        DeclarationArguments(2015, 4, 13, 1),
        DeclarationArguments(2017, 4, 15, -1),
        DeclarationArguments(2018, 4, 14, 6),
        DeclarationArguments(2019, 4, 26, -2),
        DeclarationArguments(2027, 5, 2, 0))

    @Test
    fun orthodoxEasterBasedHolidayToLocalDate() {
        orthodoxEasterSundayArgs.forEach {
            val orthodoxEaster = Declaration.OrthodoxEasterBasedHoliday(it.days, "dummy")

            val result = orthodoxEaster.concreteForYear(it.year)
            assertEquals(it.year, result.year, "year comparison for ${it} vs. ${result}")
            assertEquals(it.month, result.month.number, "month comparison for ${it} vs. ${result}")
            assertEquals(it.day, result.dayOfMonth, "day comparison for ${it} vs. ${result}")
        }
    }
}
