package org.javafreedom.khol.algorithm

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import org.javafreedom.khol.BaseCalculationAlgorithm

/**
 * Orthodox Easter is determined based on the Julian calendar and follows the rule:
 * It is the first Sunday after the Paschal Full Moon (first full moon after the vernal equinox).
 * The result is converted to the Gregorian calendar for modern usage.
 *
 * * up until 1582, julian and gregorian easter dates were identical
 * * after, 1 day is added for each century, except if the century year is exactly divisible by
 *   400 (in which case no days are added). Safe until 4100 AD, when one leap day will be removed.
 *
 * Since we do not calculate Holidays before 1990, we just use the calculus for dates after 1700
 */
class OrthodoxEaster : BaseCalculationAlgorithm {

    override fun calculateBaseDate(year: Int): LocalDate {
        val julian = calculateJulian(year)

        return julian.plus(julianToGregorianDifference(year), DateTimeUnit.DAY)
    }

    private fun calculateJulian(year: Int): LocalDate {
        val g = year % 19
        val i = (19 * g + 15) % 30
        val j = (year + year / 4 + i) % 7
        val month = 3 + (i - j + 40) / 44
        val day = i - j + 28 - 31 * (month / 4)

        return LocalDate(year, month, day)
    }

    private fun julianToGregorianDifference(year: Int): Int {
        // The base year when the difference started (1582) - difference in days at that time was 10
        val baseYear = 1582
        // Difference in days (starts at 10 days for 1582, increases by 1 every 100 years except centuries divisible by 400)
        var difference = 10

        // Calculate how many centuries have passed since the base year
        for (i in baseYear until year) {
            if (i % 100 == 0 && i % 400 != 0) {
                difference += 1
            }
        }
        return difference
    }
}

