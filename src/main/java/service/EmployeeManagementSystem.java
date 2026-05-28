package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exception.EmployeeNotFoundException;
import exception.InvalidEmployeeDataException;
import model.Employee;
import model.FullTimeEmployee;
import model.IEmployee;
import util.SalaryComparator;

public final class EmployeeManagementSystem {

    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public void removeEmployee(int id) throws EmployeeNotFoundException {
        IEmployee e = findEmployee(id);
        if (e == null) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
        employees.remove(e);
    }

    public void displayAll() {
        for (IEmployee e : employees) {
            e.displayDetails();
        }
    }

    public void sortById() {
        // Elemente brauchen Comparable<T>
        // Benutzt natural order = Employee.compareTo() = sort by ID
        Collections.sort(employees);
    }

    public void sortBySalary() {
        // Sort employees by salary (using Comparator)
        employees.sort(new SalaryComparator());
    }

    public void promoteFullTimeEmployee(int id, double increase)
            throws EmployeeNotFoundException, InvalidEmployeeDataException {
        IEmployee e = findEmployee(id);
        if (!(e instanceof FullTimeEmployee)) {
            throw new EmployeeNotFoundException("Full-time employee with ID " + id + " not found.");
        }
        ((FullTimeEmployee) e).promote(increase);
        System.out.println("Employee promoted successfully! New Salary: " + e.getSalary());
    }

    private IEmployee findEmployee(int id) {
        for (IEmployee e : employees) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}
