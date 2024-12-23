package org.javafreedom.khol.algorithm

import kotlinx.datetime.number
import kotlin.test.Test
import kotlin.test.assertEquals

class FirstAdventTest {

    private val firstAdvent = FirstAdvent()

    @Test
    fun testFirstAdvent() {
        val result0 = firstAdvent.calculateBaseDate(2024)
        assertEquals(2024, result0.year)
        assertEquals(12, result0.month.number)
        assertEquals(1, result0.dayOfMonth)

        val result1 = firstAdvent.calculateBaseDate(2023)
        assertEquals(2023, result1.year)
        assertEquals(12, result1.month.number)
        assertEquals(3, result1.dayOfMonth)
    }

}
