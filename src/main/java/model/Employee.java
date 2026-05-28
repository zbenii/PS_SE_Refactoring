package model;

import exception.InvalidEmployeeDataException;

public abstract class Employee implements Comparable<Employee> {
    private final int id;
    private final String name;
    private double salary;

    public Employee(int id, String name, double salary)
            throws InvalidEmployeeDataException {
        if (id < 0)
            throw new InvalidEmployeeDataException("Invalid Employee ID!");
        if (salary < 0)
            throw new InvalidEmployeeDataException("Invalid Salary! Salary must be non-negative.");
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public abstract double calculateBonus();
    public abstract void displayDetails();

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }
}
