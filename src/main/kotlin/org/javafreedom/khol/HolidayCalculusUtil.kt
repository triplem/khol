package org.javafreedom.khol

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

class HolidayCalculusUtil {

    companion object {
        /**
         * Computus algoritym
         */
        fun gregorianEasterSunday(year: Int) : LocalDate {
            // Golden number (position of the year in the 19-year Metonic cycle)
            val goldenNumber = year % 19

            // Century (the century the year falls in)
            val century = year / 100

            // Year within the century
            val yearInCentury = year % 100

            // Number of leap years in the century
            val leapYearsInCentury = century / 4

            // Remainder when dividing century by 4 (used for correction)
            val centuryRemainder = century % 4

            // Adjustment factor for the century based on an offset
            val centuryAdjustment = (century + 8) / 25

            // Correction for the century’s alignment with the lunar-solar cycle
            val centuryCycleCorrection = (century - centuryAdjustment + 1) / 3

            // Days after March 21 that Easter Sunday could fall
            val daysAfterEquinox = (19 * goldenNumber + century - leapYearsInCentury - centuryCycleCorrection + 15) % 30

            // Leap years in the current century (for finer adjustments)
            val leapYearsInYear = yearInCentury / 4

            // Remainder when dividing yearInCentury by 4
            val yearInCenturyRemainder = yearInCentury % 4

            // Additional adjustment based on leap years and the cycle corrections
            val finalAdjustment = (32 + 2 * centuryRemainder + 2 * leapYearsInYear - daysAfterEquinox - yearInCenturyRemainder) % 7

            // Final correction factor for the lunar cycle
            val lunarCycleCorrection = (goldenNumber + 11 * daysAfterEquinox + 22 * finalAdjustment) / 451

            // Determine the month of Easter (March or April)
            val month = (daysAfterEquinox + finalAdjustment - 7 * lunarCycleCorrection + 114) / 31

            // Determine the day of Easter Sunday
            val day = ((daysAfterEquinox + finalAdjustment - 7 * lunarCycleCorrection + 114) % 31) + 1

            // Return the calculated date of Easter Sunday
            return LocalDate(year, month, day)
        }

        fun firstAdvent(year: Int): LocalDate {
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


}