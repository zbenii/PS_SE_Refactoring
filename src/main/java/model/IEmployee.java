package model;

public interface IEmployee extends Comparable<Employee> {
    int getId();

    String getName();

    double getSalary();

    void setSalary(double salary);

    double calculateBonus();

    void displayDetails();

    @Override
    int compareTo(Employee other);
}
