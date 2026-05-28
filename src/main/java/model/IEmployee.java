package model;

public interface IEmployee {

	int getId();

	String getName();

	double getSalary();

	void setSalary(double salary);

	double calculateBonus();

	void displayDetails();

	int compareTo(IEmployee other);

}