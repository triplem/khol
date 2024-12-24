package org.javafreedom.khol.helper

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

/**
 * This provides the iterator to be used in the progression. This can then used in range queries.
 *
 * Cloned by https://www.netguru.com/blog/traversing-through-dates-with-kotlin-range-expressions
 */
class DateIterator(val startDate: LocalDate,
                   val endDateExclusive: LocalDate,
                   val stepDays: Long): Iterator<LocalDate> {
    private var currentDate = startDate

    override fun hasNext() = currentDate < endDateExclusive

    override fun next(): LocalDate {
        val next = currentDate
        currentDate = currentDate.plus(stepDays, DateTimeUnit.DAY)
        return next
    }

}

/**
 * The progression used to loop through a range of localDates
 */
class DateProgression(override val start: LocalDate,
                      override val endInclusive: LocalDate,
                      val stepDays: Long = 1) :
    Iterable<LocalDate>, ClosedRange<LocalDate> {

    override fun iterator(): Iterator<LocalDate> =
        DateIterator(start, endInclusive, stepDays)

    infix fun step(days: Long) = DateProgression(start, endInclusive, days)
}

/**
 * Extension Function to make the localdate range work
 */
operator fun LocalDate.rangeTo(other: LocalDate) = DateProgression(this, other)
