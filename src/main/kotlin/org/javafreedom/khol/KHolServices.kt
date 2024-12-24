package org.javafreedom.khol

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import org.javafreedom.khol.helper.rangeTo

/**
 * Provide some useful Service functions
 */
class KHolServices {

    fun workdays(holidays: List<LocalDate>,
                 start: LocalDate, end: LocalDate): List<LocalDate> =
        (start..end)
            .filter { isHoliday(holidays, it) }
            .filter { !isWeekend(it) }
            .toList()

    fun isHoliday(holidays: List<LocalDate>, date: LocalDate) = holidays.contains(date)

    fun isWeekend(date: LocalDate) =
        date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY
}