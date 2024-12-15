package org.javafreedom.khol

import kotlinx.datetime.number
import org.javafreedom.khol.HolidayCalculusUtil.Companion.firstAdvent
import org.javafreedom.khol.HolidayCalculusUtil.Companion.gregorianEasterSunday
import kotlin.test.Test
import kotlin.test.assertEquals

private data class EasterCalculusArguments(val year: Int, val month: Int, val day: Int)

class HolidayCalculusUtilTest {

    private val argumentsList = mutableListOf(
        EasterCalculusArguments(2008, 3, 23),
        EasterCalculusArguments(2015, 4, 5),
        EasterCalculusArguments(2017, 4, 16),
        EasterCalculusArguments(2018, 4, 1),
        EasterCalculusArguments(2019, 4, 21),
        EasterCalculusArguments(2027, 3, 28))

    @Test
    fun testEasterSunday() {
        argumentsList.forEach {
            val result = gregorianEasterSunday(it.year)
            assertEquals(it.year, result.year)
            assertEquals(it.month, result.month.number)
            assertEquals(it.day, result.dayOfMonth)
        }
    }

    @Test
    fun testFirstAdvent() {
        val result0 = firstAdvent(2024)
        assertEquals(2024, result0.year)
        assertEquals(12, result0.month.number)
        assertEquals(1, result0.dayOfMonth)

        val result1 = firstAdvent(2023)
        assertEquals(2023, result1.year)
        assertEquals(12, result1.month.number)
        assertEquals(3, result1.dayOfMonth)
    }

}