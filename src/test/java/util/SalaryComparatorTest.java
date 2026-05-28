package util;

import exception.InvalidEmployeeDataException;
import model.Employee;
import model.FullTimeEmployee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests fuer den SalaryComparator: sortiert absteigend nach Gehalt
 * (hoechstes Gehalt zuerst).
 */
class SalaryComparatorTest {

    @Test
    void higherSalaryComesFirst() throws InvalidEmployeeDataException {
        SalaryComparator cmp = new SalaryComparator();
        Employee high = new FullTimeEmployee(1, "High", 5000.0);
        Employee low = new FullTimeEmployee(2, "Low", 1000.0);
        assertTrue(cmp.compare(high, low) < 0);
        assertTrue(cmp.compare(low, high) > 0);
    }

    @Test
    void equalSalariesReturnZero() throws InvalidEmployeeDataException {
        SalaryComparator cmp = new SalaryComparator();
        Employee a = new FullTimeEmployee(1, "A", 2000.0);
        Employee b = new FullTimeEmployee(2, "B", 2000.0);
        assertEquals(0, cmp.compare(a, b));
    }
}
