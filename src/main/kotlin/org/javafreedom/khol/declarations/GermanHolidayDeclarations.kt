@file:Suppress("detekt:style:MagicNumber")
package org.javafreedom.khol.declarations

import org.javafreedom.khol.Declaration
import org.javafreedom.khol.HolidayDeclarations

enum class GermanFederalStates(val description: String) {
    BW("Baden-Wuerttemberg"),
    BY("Bavaria"),
    BY_A("Bavaria (Augsburg)"), // this is an exception, it is not a Federal State, but a more specific location
    BE("Berlin"),
    BB("Brandenburg"),
    HB("Bremen"),
    HH("Hamburg"),
    HE("Hesse"),
    MV("Mecklenburg-West Pomerania"),
    NI("Lower Saxony"),
    NW("Mecklenburg-West Pomerania"),
    RP("Rhineland-Palatinate"),
    SL("Saarland"),
    SN("Saxony"),
    ST("Saxony-Anhalt"),
    SH("Schleswig-Holstein"),
    TH("Thuringia")
}

// this is a defintion without DSL
class GermanHolidayDeclarations: HolidayDeclarations("DE") {

    val commonGermanHolidays = listOf(
        Declaration.FixedHoliday(month = 1, day = 1, name = "New Year"),
        Declaration.EasterBasedHoliday(offset = -2, name = "Good Friday"),
        Declaration.EasterBasedHoliday(offset = 1, name = "Easter Monday"),
        Declaration.FixedHoliday(month = 5, day = 1, name = "Labour Day"),
        Declaration.EasterBasedHoliday(offset = 39, name = "Ascension Day"),
        Declaration.EasterBasedHoliday(offset = 50, name = "Whit Monday"),
        Declaration.FixedHoliday(month = 10, day = 3, name = "German Unity Day"),

        Declaration.FixedHoliday(month = 12, day = 25, name = "Christmas Day"),
        Declaration.FixedHoliday(month = 12, day = 26, name = "Second Day of Christmas")
    )

    val epiphany = Declaration.FixedHoliday(
        month = 1, day = 6, "Epiphany",
        validIn = setOf(
            GermanFederalStates.BW.name,
            GermanFederalStates.BY.name,
            GermanFederalStates.BY_A.name,
            GermanFederalStates.ST.name
        )
    )
    val womensDay = Declaration.FixedHoliday(
        month = 3, day = 8, "International Womens Day",
        validIn = setOf(
            GermanFederalStates.BE.name,
            GermanFederalStates.MV.name
        )
    )
    val corpusChristi = Declaration.EasterBasedHoliday(
        60, "Corpus Christi",
        validIn = setOf(
            GermanFederalStates.BW.name,
            GermanFederalStates.BY.name,
            GermanFederalStates.HE.name,
            GermanFederalStates.NW.name,
            GermanFederalStates.RP.name,
            GermanFederalStates.SL.name
        )
    )
    val augsburgHighPeaceDay = Declaration.FixedHoliday(
        month = 8, day = 8, "Augsburg High Peace",
        validIn = setOf(GermanFederalStates.BY_A.name)
    )
    val assumptionDay = Declaration.FixedHoliday(
        month = 8, day = 15, "Assumption Day",
        validIn = setOf(
            GermanFederalStates.BY.name,
            GermanFederalStates.BY_A.name
        )
    )
    val childrenDay = Declaration.FixedHoliday(
        month = 9, day = 20, "Children Day",
        validIn = setOf(GermanFederalStates.TH.name), validFromYear = 2019
    )
    val reformationDay = Declaration.FixedHoliday(
        month = 10, day = 31, "Reformation Day",
        validIn = setOf(
            GermanFederalStates.BW.name,
            GermanFederalStates.MV.name,
            GermanFederalStates.SN.name,
            GermanFederalStates.ST.name,
            GermanFederalStates.TH.name
        )
    )
    val reformationDay2018 = Declaration.FixedHoliday(
        month = 10, day = 31, "Reformation Day - Since 2018",
        validIn = setOf(
            GermanFederalStates.HB.name,
            GermanFederalStates.HH.name,
            GermanFederalStates.NI.name,
            GermanFederalStates.SH.name
        ), validFromYear = 2018
    )
    val allSaintsDay = Declaration.FixedHoliday(
        month = 11, day = 1, "All Saints Day",
        validIn = setOf(
            GermanFederalStates.BW.name,
            GermanFederalStates.BY.name,
            GermanFederalStates.BY_A.name,
            GermanFederalStates.NW.name,
            GermanFederalStates.RP.name,
            GermanFederalStates.SL.name
        )
    )
    val repentanceAndPrayerDay = Declaration.AdventBasedHoliday(
        offset = -11, name = "Repentance And Prayer Day",
        validIn = setOf(
            GermanFederalStates.SN.name
        )
    )

    override fun declarations(): List<Declaration> {
        return buildList {
            addAll(commonGermanHolidays)
            add(epiphany)
            add(womensDay)
            add(corpusChristi)
            add(augsburgHighPeaceDay)
            add(assumptionDay)
            add(childrenDay)
            add(reformationDay)
            add(reformationDay2018)
            add(allSaintsDay)
            add(repentanceAndPrayerDay)
        }
    }
}
