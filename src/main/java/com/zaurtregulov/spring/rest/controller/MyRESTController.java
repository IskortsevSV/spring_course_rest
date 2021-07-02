package com.zaurtregulov.spring.rest.controller;

import com.zaurtregulov.spring.rest.entity.Employee;
import com.zaurtregulov.spring.rest.exception_handling.NoSuchEmployeeException;
import com.zaurtregulov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // стандарт в REST
public class MyRESTController {
    @Autowired
    private EmployeeService employeeService;

    //показывает список работников
    //в REST сервисе будет срабатывать метод showAllEmployees,
    // spring с помощью функционала проекта Jackson
    // конвектирует список работников в JSON формат
    // в теле HTTP response содержится JSON который оторбразится в браузере
    @GetMapping("/employees") // указываем в множественном числе
    public List<Employee> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}") // path variable - читает переменную из URL
    public Employee getEmployee(@PathVariable int id) {

        Employee employee = employeeService.getEmployee(id);

        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID = "
                    + id + " in Database");
        }
     return employee ;
    }

    @PostMapping("/employees")
    //Аннотация PostMapping связывает HTTP запрос, использующий HTTP метод POST с методом Controller
    //Аннотация RequestBody связывает body HTTP метода, с параметром метода Controller
    public Employee addNewEmployee(@RequestBody Employee employee) {

        employeeService.saveEmployee(employee);
        return employee;
    }


}
