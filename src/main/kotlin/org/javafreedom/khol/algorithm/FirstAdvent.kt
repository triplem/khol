@file:Suppress("detekt:style:MagicNumber")
package org.javafreedom.khol.algorithm

import kotlinx.datetime.*
import org.javafreedom.khol.BaseCalculationAlgorithm

/**
 * The First Sunday of Advent is the fourth Sunday before Christmas Day.
 */
class FirstAdvent : BaseCalculationAlgorithm {

    override fun calculateBaseDate(year: Int): LocalDate {
        // Find Christmas Day of the given year
        val christmas = LocalDate(year, 12, 25)
        // Find the Sunday before Christmas, which is the Fourth Sunday of Advent
        val fourthAdvent = christmas.minus((christmas.dayOfWeek.value % 7).toLong(),
            DateTimeUnit.DAY)
        // Subtract 21 days (3 weeks) to get the First Sunday of Advent
        return fourthAdvent.minus(3, DateTimeUnit.WEEK)
    }

}
