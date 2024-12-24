package org.javafreedom.khol.declarations

import kotlin.test.Test
import kotlin.test.assertEquals

internal class GermanHolidayDeclarationsTest {

    @Test
    fun declarationsTest() {
        val sut = GermanHolidayDeclarations()

        val result = sut.declarations()
        assertEquals(19, result.size)
    }

}
