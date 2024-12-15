package org.javafreedom.khol

import kotlinx.datetime.LocalDate

class KHol(val holidays: HolidayDeclarations, val year: Int, val validIn: String) {

    fun validHolidays() : List<LocalDate> {
        val result = mutableListOf<LocalDate>()

        holidays.declarations().filter {
            (it.validFromYear <= year) and (it.validIn.contains(validIn) or it.validIn.isEmpty())
        }.forEach {
            val item = it.concreteForYear(year)
            result.add(item)
        }

        return result
    }

}