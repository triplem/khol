package org.javafreedom.khol;

import org.javafreedom.khol.declarations.GermanHolidayDeclarations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KHolGermanHolidaysJavaTest {

    @Test
    void testNW2024() {
        var holidayDeclarations = new GermanHolidayDeclarations();
        var sut = new KHol(holidayDeclarations, 2024, "NW");

        var validHoldays = sut.validHolidays();

        assertEquals(11, validHoldays.size());
        assertTrue(validHoldays.stream().anyMatch(it ->
                it.getDayOfMonth() == 1 && it.getMonth().getValue() == 11 && it.getYear() == 2024));
    }
}
