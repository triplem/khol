package org.javafreedom.khol.algorithm

import EasterCalculusArguments
import kotlinx.datetime.number
import kotlin.test.Test
import kotlin.test.assertEquals

class OrthodoxEasterTest {

    private val argumentsList = mutableListOf(
        EasterCalculusArguments(2008, 4, 27),
        EasterCalculusArguments(2015, 4, 12),
        EasterCalculusArguments(2017, 4, 16),
        EasterCalculusArguments(2018, 4, 8),
        EasterCalculusArguments(2019, 4, 28),
        EasterCalculusArguments(2027, 5, 2)
    )

    private val sut = OrthodoxEaster()

    @Test
    fun testEasterSunday() {
        argumentsList.forEach {
            val result = sut.calculateBaseDate(it.year)
            assertEquals(it.year, result.year, "year comparison for ${it} vs. ${result}")
            assertEquals(it.month, result.month.number, "month comparison for ${it} vs. ${result}")
            assertEquals(it.day, result.dayOfMonth, "day comparison for ${it} vs. ${result}")
        }
    }

}
