/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package model;

import exception.InvalidEmployeeDataException;

/**
 *
 * @author benjo
 */
public interface IEmployee {

    int getId();

    String getName();

    double getSalary();

    void setSalary(double salary);

    double calculateBonus();

    void displayDetails();

    int compareTo(Employee other);

    void promote(double salaryIncrease) throws InvalidEmployeeDataException;

}
