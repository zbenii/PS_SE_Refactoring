package model;

import exception.InvalidEmployeeDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Verhaltenstests fuer die Employee-Hierarchie.
 * Dienen als Behavior-Preservation-Netz beim Refactoring:
 * Laufen die Tests vor UND nach einem Refactoring gruen,
 * wurde das externe Verhalten nicht veraendert.
 */
class EmployeeTest {

    private static final double DELTA = 1e-9;

    @Test
    void fullTimeBonusIsTenPercent() throws InvalidEmployeeDataException {
        IEmployee e = new FullTimeEmployee(1, "Anna", 1000.0);
        assertEquals(100.0, e.calculateBonus(), DELTA);
    }

    @Test
    void partTimeBonusIsFivePercent() throws InvalidEmployeeDataException {
        IEmployee e = new PartTimeEmployee(2, "Ben", 1000.0);
        assertEquals(50.0, e.calculateBonus(), DELTA);
    }

    @Test
    void gettersReturnConstructorValues() throws InvalidEmployeeDataException {
        IEmployee e = new FullTimeEmployee(7, "Cara", 2500.0);
        assertAll(
                () -> assertEquals(7, e.getId()),
                () -> assertEquals("Cara", e.getName()),
                () -> assertEquals(2500.0, e.getSalary(), DELTA)
        );
    }

    @Test
    void setSalaryUpdatesSalary() throws InvalidEmployeeDataException {
        IEmployee e = new FullTimeEmployee(1, "Anna", 1000.0);
        e.setSalary(1200.0);
        assertEquals(1200.0, e.getSalary(), DELTA);
    }

    @Test
    void compareToOrdersById() throws InvalidEmployeeDataException {
        Employee a = new FullTimeEmployee(1, "A", 0.0);
        Employee b = new PartTimeEmployee(2, "B", 0.0);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        assertEquals(0, a.compareTo(new FullTimeEmployee(1, "Z", 999.0)));
    }

    @Test
    void constructorRejectsNegativeId() {
        assertThrows(InvalidEmployeeDataException.class,
                () -> new FullTimeEmployee(-1, "X", 100.0));
    }

    @Test
    void constructorRejectsNegativeSalary() {
        assertThrows(InvalidEmployeeDataException.class,
                () -> new PartTimeEmployee(1, "X", -5.0));
    }

    @Test
    void promoteIncreasesSalary() throws InvalidEmployeeDataException {
        FullTimeEmployee e = new FullTimeEmployee(3, "C", 500.0);
        e.promote(250.0);
        assertEquals(750.0, e.getSalary(), DELTA);
    }

    @Test
    void promoteRejectsNegativeIncrease() throws InvalidEmployeeDataException {
        FullTimeEmployee e = new FullTimeEmployee(3, "C", 500.0);
        assertThrows(InvalidEmployeeDataException.class, () -> e.promote(-1.0));
    }
}
