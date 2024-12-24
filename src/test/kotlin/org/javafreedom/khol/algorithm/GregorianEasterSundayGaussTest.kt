package org.javafreedom.khol.algorithm

import EasterCalculusArguments
import kotlinx.datetime.number
import kotlin.test.Test
import kotlin.test.assertEquals

internal class GregorianEasterSundayGaussTest {

    private val argumentsList = mutableListOf(
        EasterCalculusArguments(2008, 3, 23),
        EasterCalculusArguments(2015, 4, 5),
        EasterCalculusArguments(2017, 4, 16),
        EasterCalculusArguments(2018, 4, 1),
        EasterCalculusArguments(2019, 4, 21),
        EasterCalculusArguments(2027, 3, 28)
    )

    private val sut = GregorianEasterSundayGauss()

    @Test
    fun testEasterSunday() {
        argumentsList.forEach {
            val result = sut.calculateBaseDate(it.year)
            assertEquals(it.year, result.year)
            assertEquals(it.month, result.month.number)
            assertEquals(it.day, result.dayOfMonth)
        }
    }

}
