package com.zaurtregulov.spring.rest.controller;

import com.zaurtregulov.spring.rest.entity.Employee;
import com.zaurtregulov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

     return  employeeService.getEmployee(id);

    }
}
