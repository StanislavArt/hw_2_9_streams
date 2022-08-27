package pro.sky.java.course2.streams.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.streams.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>(Map.of(
                "Станислав Лем",
                new Employee("Станислав", "Лем", 2, 35000),
                "Борис Стругацкий",
                new Employee("Борис", "Стругацкий", 1, 73000),
                "Роберт Шекли",
                new Employee("Роберт", "Шекли",2, 119000),
                "Стивен Кинг",
                new Employee("Стивен", "Кинг", 4, 89000),
                "Айзек Азимов",
                new Employee("Айзек", "Азимов", 2, 91000),
                "Эдгар Берроуз",
                new Employee("Эдгар", "Берроуз", 3, 43000)
        ));
    }

    @Override
    public void addEmployee(String firstName, String lastName, int department, double salary) {
        if (findEmployee(firstName, lastName) != null) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(firstName + " " + lastName, new Employee(firstName, lastName, department, salary));
    }

    @Override
    public void removeEmployee(String firstName, String lastName) {
        Employee employee = findEmployee(firstName, lastName);
        if ( employee == null) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(firstName + " " + lastName);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        return employees.get(firstName + " " + lastName);
    }

    @Override
    public Map<String, Employee> getEmployees() {
        return employees;
    }
}
