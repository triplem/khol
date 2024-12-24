package org.javafreedom.khol

import kotlinx.datetime.LocalDate

/**
 * Some doc
 */
class KHol(private val holidays: HolidayDeclarations, private val validIn: String) {

    fun validHolidays(year: Int) : List<LocalDate> {
        if (year < VALID_START_YEAR) throw KHolException("Year should be after 1989")

        val result = mutableListOf<LocalDate>()

        holidays.declarations().filter {
            (it.validFromYear <= year) and (it.validIn.contains(validIn) or it.validIn.isEmpty())
        }.forEach {
            val item = it.concreteForYear(year)
            result.add(item)
        }

        return result
    }

    /**
     * All Holidays which are greater then, or equal to start and less then end are returned
     */
    fun validHolidays(start: LocalDate, end: LocalDate) : List<LocalDate> {
        if (start >= end) throw KHolException("Start Date should be before to End Date")

        val result = mutableListOf<LocalDate>()
        for (year in start.year..end.year) {
            validHolidays(year)
                .filter { it >= start && it < end }
                .forEach { result.add(it) }
        }

        return result
    }

    companion object {
        const val VALID_START_YEAR = 1990
    }
}
