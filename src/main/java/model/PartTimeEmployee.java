package model;

import exception.InvalidEmployeeDataException;

public final class PartTimeEmployee extends Employee {
    public PartTimeEmployee(int id, String name, double salary)
            throws InvalidEmployeeDataException {
        super(id, name, salary);
    }

    @Override
    public double calculateBonus() {
        return getSalary() * 0.05;
    }

    @Override
    public void displayDetails() {
        System.out.println("ID: " + getId() + ", Name: " + getName() + ", Salary: " + getSalary() + ", Bonus: " + calculateBonus());
    }
}
