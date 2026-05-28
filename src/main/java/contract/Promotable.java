package contract;

import exception.InvalidEmployeeDataException;

public interface Promotable {
    void promote(double salaryIncrease) throws InvalidEmployeeDataException;
}
