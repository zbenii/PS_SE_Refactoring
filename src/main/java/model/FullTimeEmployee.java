package model;

import contract.Promotable;
import exception.InvalidEmployeeDataException;

public final class FullTimeEmployee extends Employee implements Promotable {

    public FullTimeEmployee(int id, String name, double salary)
            throws InvalidEmployeeDataException {
        super(id, name, salary);
    }

    @Override
    public double calculateBonus() {
        return getSalary() * 0.10;
    }

    @Override
    public void displayDetails() {
        System.out.println("ID: " + getId() + ", Name: " + getName() + ", Salary: " + getSalary() + ", Bonus: " + calculateBonus());
    }

    @Override
    public void promote(double salaryIncrease) throws InvalidEmployeeDataException {
        if (salaryIncrease < 0) {
            throw new InvalidEmployeeDataException("Increase must be non-negative.");
        }
        setSalary(getSalary() + salaryIncrease);
    }
}
