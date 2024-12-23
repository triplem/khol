package org.javafreedom.khol

import kotlinx.datetime.LocalDate

fun interface BaseCalculationAlgorithm {

    fun calculateBaseDate(year: Int): LocalDate

}
