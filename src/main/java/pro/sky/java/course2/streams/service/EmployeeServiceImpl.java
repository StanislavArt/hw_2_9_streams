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
                new Employee("Станислав", "Лем"),
                "Борис Стругацкий",
                new Employee("Борис", "Стругацкий"),
                "Роберт Шекли",
                new Employee("Роберт", "Шекли"),
                "Стивен Кинг",
                new Employee("Стивен", "Кинг"),
                "Эдгар Берроуз",
                new Employee("Эдгар", "Берроуз")
        ));
    }

    @Override
    public void addEmployee(String firstName, String lastName) {
        if (findEmployee(firstName, lastName) != null) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(firstName + " " + lastName, new Employee(firstName, lastName));
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
