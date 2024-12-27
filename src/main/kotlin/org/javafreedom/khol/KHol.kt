package org.javafreedom.khol

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import org.javafreedom.khol.helper.rangeTo

/**
 * This is the central entry point for the calculation of Holidays using KHol.
 *
 * It provides a way to access Holidays using different access methods. There is a caching
 * included to provide faster access to already calculated holidays by year.
 */
class KHol(private val holidays: HolidayDeclarations, private val validIn: String) {

    private val holidaysPerYear = mutableMapOf<Int, List<LocalDate>>()

    /**
     * Construct all valid holidays using the given HolidayDeclarations for the given year.
     *
     * The calculated holidays are cached.
     *
     * Throws an KHol Exception if the given year is before the VALID_START_YEAR.
     */
    fun validHolidays(year: Int) : List<LocalDate> {
        if (year < VALID_START_YEAR) throw KHolException("Year should be after 1989")

        return holidaysPerYear.getOrDefault(year, constructHolidays(year))
    }

    private fun constructHolidays(year: Int): List<LocalDate> {
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
     *
     * Throws an KHolException if the given startDate is not before the end date.
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

    fun workdays(start: LocalDate, end: LocalDate): List<LocalDate> =
        (start..end)
            .filter { !it.isHoliday(validHolidays(it.year)) }
            .filter { !it.isWeekend(holidays.weekends()) }
            .toList()

    companion object {
        const val VALID_START_YEAR = 1990
    }
}

fun LocalDate.isHoliday(holidays: List<LocalDate>) = holidays.contains(this)

fun LocalDate.isWeekend(weekends: Set<DayOfWeek>) = weekends.contains(this.dayOfWeek)
