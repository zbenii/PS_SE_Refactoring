package service;

import exception.EmployeeNotFoundException;
import exception.InvalidEmployeeDataException;
import model.FullTimeEmployee;
import model.PartTimeEmployee;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Verhaltenstests fuer das EmployeeManagementSystem.
 * Die Sortier-Methoden werden ueber das aufgezeichnete Konsolen-Ausgabe-
 * (stdout-)Verhalten von displayAll() geprueft, da die Liste nicht
 * oeffentlich zugreifbar ist.
 */
class EmployeeManagementSystemTest {

    private static final double DELTA = 1e-9;

    @Test
    void removeNonExistingThrows() {
        EmployeeManagementSystem ms = new EmployeeManagementSystem();
        assertThrows(EmployeeNotFoundException.class, () -> ms.removeEmployee(999));
    }

    @Test
    void removeExistingRemovesEmployee() throws Exception {
        EmployeeManagementSystem ms = new EmployeeManagementSystem();
        ms.addEmployee(new FullTimeEmployee(5, "E5", 3000.0));
        assertDoesNotThrow(() -> ms.removeEmployee(5));
        // zweites Entfernen muss fehlschlagen -> war wirklich weg
        assertThrows(EmployeeNotFoundException.class, () -> ms.removeEmployee(5));
    }

    @Test
    void promoteFullTimeEmployeeIncreasesSalary() throws Exception {
        EmployeeManagementSystem ms = new EmployeeManagementSystem();
        FullTimeEmployee ft = new FullTimeEmployee(5, "E5", 3000.0);
        ms.addEmployee(ft);
        ms.promoteFullTimeEmployee("APPROVED", 5, 500.0);
        assertEquals(3500.0, ft.getSalary(), DELTA);
    }

    @Test
    void promotePartTimeEmployeeThrows() throws Exception {
        EmployeeManagementSystem ms = new EmployeeManagementSystem();
        ms.addEmployee(new PartTimeEmployee(7, "PT", 100.0));
        assertThrows(EmployeeNotFoundException.class, () -> ms.promoteFullTimeEmployee("APPROVED", 7, 10.0));
    }

    @Test
    void promoteNonExistingThrows() {
        EmployeeManagementSystem ms = new EmployeeManagementSystem();
        assertThrows(EmployeeNotFoundException.class, () -> ms.promoteFullTimeEmployee("APPROVED", 42, 10.0));
    }

    @Test
    void sortByIdOrdersAscending() throws Exception {
        EmployeeManagementSystem ms = new EmployeeManagementSystem();
        ms.addEmployee(new FullTimeEmployee(5, "E5", 3000.0));
        ms.addEmployee(new PartTimeEmployee(3, "E3", 5000.0));
        ms.addEmployee(new FullTimeEmployee(4, "E4", 1000.0));
        ms.sortById();
        assertEquals(List.of(3, 4, 5), displayedIds(ms));
    }

    @Test
    void sortBySalaryOrdersDescending() throws Exception {
        EmployeeManagementSystem ms = new EmployeeManagementSystem();
        ms.addEmployee(new FullTimeEmployee(1, "A", 100.0));
        ms.addEmployee(new PartTimeEmployee(2, "B", 300.0));
        ms.addEmployee(new FullTimeEmployee(3, "C", 200.0));
        ms.sortBySalary();
        assertEquals(List.of(2, 3, 1), displayedIds(ms));
    }

    /** Faengt die Konsolenausgabe von displayAll() ab und liest die IDs in Reihenfolge aus. */
    private List<Integer> displayedIds(EmployeeManagementSystem ms) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
            ms.displayAll();
        } finally {
            System.setOut(original);
        }
        List<Integer> ids = new ArrayList<>();
        for (String line : buffer.toString(StandardCharsets.UTF_8).split("\\R")) {
            int idx = line.indexOf("ID: ");
            if (idx >= 0) {
                String rest = line.substring(idx + 4);
                int comma = rest.indexOf(',');
                ids.add(Integer.parseInt(rest.substring(0, comma).trim()));
            }
        }
        return ids;
    }
}
