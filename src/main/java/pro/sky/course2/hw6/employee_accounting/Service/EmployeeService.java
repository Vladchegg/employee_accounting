package pro.sky.course2.hw6.employee_accounting.Service;

import org.springframework.stereotype.Service;
import pro.sky.course2.hw6.employee_accounting.Employee.Employee;
import pro.sky.course2.hw6.employee_accounting.exceptions.EmployeeAlreadyAddedException;
import pro.sky.course2.hw6.employee_accounting.exceptions.EmployeeNotFoundException;
import pro.sky.course2.hw6.employee_accounting.exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeService {

    private static int LIMIT = 10;

    public List<Employee> getEmployees;
    private List<Employee> employees = new ArrayList<>();

    public Employee add (String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
            return employee;
        }
            throw new EmployeeStorageIsFullException();
        }


    public Employee remove (String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }

    public Employee find (String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
}
