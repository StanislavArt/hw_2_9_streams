package pro.sky.java.course2.streams.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.java.course2.streams.Employee;
import pro.sky.java.course2.streams.service.EmployeeNotFoundException;
import pro.sky.java.course2.streams.service.EmployeeService;
import pro.sky.java.course2.streams.service.ServiceException;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("department") int department,
                              @RequestParam("salary") double salary) {
        employeeService.addEmployee(firstName, lastName, department, salary);
        String result = "Сотрудник \"" + firstName + " " + lastName + "\" успешно добавлен";
        return result;
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        employeeService.removeEmployee(firstName, lastName);
        String result = "Сотрудник \"" + firstName + " " + lastName + "\" успешно удален";
        return result;
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee employee = employeeService.findEmployee(firstName, lastName);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @GetMapping("/list")
    public Collection<Employee> listEmployees() {
        return employeeService.getEmployees().values();
    }

    @ExceptionHandler(ServiceException.class)
    public String handleException(ServiceException e) {
        String result = e.getMessage() + "<br><br>Содержимое ResponseStatus: " + e.getClass().getAnnotation(ResponseStatus.class).value();
        return result;
    }
}
