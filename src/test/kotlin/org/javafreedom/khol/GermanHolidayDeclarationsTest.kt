package org.javafreedom.khol

import kotlin.test.Test
import kotlin.test.assertEquals

class GermanHolidayDeclarationsTest {

    @Test
    fun declarationsTest() {
        val sut = GermanHolidayDeclarations()

        val result = sut.declarations()
        assertEquals(19, result.size)
    }

}