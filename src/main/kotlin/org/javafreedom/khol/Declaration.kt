package org.javafreedom.khol

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import org.javafreedom.khol.algorithm.FirstAdvent
import org.javafreedom.khol.algorithm.GregorianEasterSundayGauss
import org.javafreedom.khol.algorithm.OrthodoxEaster

/**
 * A declaration of a single Holiday, meaning the attributes when and where it is valid and how to calculate it.
 *
 * The conreteForYear method calculates the given holiday for any specific given year using the properties already
 * present in the concrete class.
 */
sealed class Declaration(open val name: String,
                         open val validFromYear: Int,
                         open val validIn: Set<String>) {
    abstract fun concreteForYear(year: Int): LocalDate

    data class FixedHoliday(
        val month: Int, val day: Int,
        override val name: String,
        override val validFromYear: Int = 1990,
        override val validIn: Set<String> = emptySet()
    ) : Declaration(name, validFromYear, validIn) {

        override fun concreteForYear(year: Int): LocalDate {
            return LocalDate(year, this.month, this.day)
        }
    }

    data class EasterBasedHoliday(
        val offset: Int,
        override val name: String,
        override val validFromYear: Int = 1990,
        override val validIn: Set<String> = emptySet()
    ) : Declaration(name, validFromYear, validIn) {

        private val gregorianEasterSundayAlgorithm = GregorianEasterSundayGauss()

        override fun concreteForYear(year: Int): LocalDate {
            val value = gregorianEasterSundayAlgorithm.calculateBaseDate(year).plus(this.offset, DateTimeUnit.DAY)
            return LocalDate(year, value.month, value.dayOfMonth)
        }
    }

    data class OrthodoxEasterBasedHoliday(
        val offset: Int,
        override val name: String,
        override val validFromYear: Int = 1990,
        override val validIn: Set<String> = emptySet()
    ) : Declaration(name, validFromYear, validIn) {

        private val orthodoxEaster = OrthodoxEaster()

        override fun concreteForYear(year: Int): LocalDate {
            val value = orthodoxEaster.calculateBaseDate(year).plus(this.offset, DateTimeUnit.DAY)
            return LocalDate(year, value.month, value.dayOfMonth)
        }
    }

    data class AdventBasedHoliday(
        val offset: Int,
        override val name: String,
        override val validFromYear: Int = 1990,
        override val validIn: Set<String> = emptySet()
    ) : Declaration(name, validFromYear, validIn) {

        private val firstAdventAlgorithm = FirstAdvent()

        override fun concreteForYear(year: Int): LocalDate {
            val value = firstAdventAlgorithm.calculateBaseDate(year).plus(this.offset, DateTimeUnit.DAY)
            return LocalDate(year, value.month, value.dayOfMonth)
        }
    }
}

abstract class HolidayDeclarations(val validIn: String) {
    abstract fun declarations(): List<Declaration>
}
