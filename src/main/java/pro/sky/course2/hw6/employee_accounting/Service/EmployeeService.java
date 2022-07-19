package pro.sky.course2.hw6.employee_accounting.Service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.course2.hw6.employee_accounting.Employee.Employee;
import pro.sky.course2.hw6.employee_accounting.exceptions.EmployeeAlreadyAddedException;
import pro.sky.course2.hw6.employee_accounting.exceptions.EmployeeNotFoundException;
import pro.sky.course2.hw6.employee_accounting.exceptions.EmployeeStorageIsFullException;

import java.net.BindException;
import java.util.*;

@Service
public class EmployeeService {

    private static int LIMIT = 10;

    private Map<String, Employee> getMapEmployees;
    public Map<String, Employee> mapEmployees = new HashMap<>();

    public Employee add(String firstName, String lastName, int department, int salary) {
        check(firstName, lastName);

        Employee employee = new Employee(firstName, lastName, department, salary);
        if (mapEmployees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        if (mapEmployees.size() < LIMIT) {
            mapEmployees.put(employee.getFullName(), employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }


    public Employee remove(String firstName, String lastName, int department, int salary) {
        check(firstName, lastName);

        Employee employee = new Employee(firstName, lastName, department, salary);
        if (!mapEmployees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return mapEmployees.remove(employee.getFullName());
    }

    public Employee find(String firstName, String lastName, int department, int salary) {
        check(firstName, lastName);

        Employee employee = new Employee(firstName, lastName, department, salary);
        if (!mapEmployees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return mapEmployees.get(employee.getFullName());
    }

    public List<Employee> getAll() {
        return new ArrayList<>(mapEmployees.values());
    }

    private void check(String firstName, String lastName) {
        if (!(StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName))) {
            throw new IllegalArgumentException();
        }
    }

}
