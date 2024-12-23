@file:Suppress("detekt:style:MagicNumber")
package org.javafreedom.khol.algorithm

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import org.javafreedom.khol.BaseCalculationAlgorithm

class FirstAdvent : BaseCalculationAlgorithm {

    override fun calculateBaseDate(year: Int): LocalDate {
        // Find November 30th of the given year
        val november30 = LocalDate(year, 11, 30)
        // Get the day of the week for November 30th
        val dayOfWeek = november30.dayOfWeek
        // Calculate how many days to add to get to the nearest Sunday (Advent starts)
        val daysToSunday = (DayOfWeek.SUNDAY.value - dayOfWeek.value + 7) % 7
        // The first Advent Sunday is the Sunday closest to or on November 30th
        return november30.plus(daysToSunday, DateTimeUnit.DAY)
    }

}
