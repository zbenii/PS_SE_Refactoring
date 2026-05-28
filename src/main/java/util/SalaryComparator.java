package util;

import model.Employee;
import java.util.Comparator;

public final class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        // -1 if e1 < e2, 0 if e1 == e2, 1 if e1 > e2
        return Double.compare(e2.getSalary(), e1.getSalary());
    }
}
