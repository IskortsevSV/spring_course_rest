package com.zaurtregulov.spring.rest.controller;

import com.zaurtregulov.spring.exception_handling.EmployeeIncorrectData;
import com.zaurtregulov.spring.exception_handling.NoSuchEmployeeException;
import com.zaurtregulov.spring.rest.entity.Employee;
import com.zaurtregulov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler // отмечается метод ответсвенный за обработку исключений
    //ResponseEntity - это обертка HTTP response
    public ResponseEntity<EmployeeIncorrectData> handleException(NoSuchEmployeeException exception) {

        EmployeeIncorrectData data = new EmployeeIncorrectData();

        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler // отмечается метод ответсвенный за обработку исключений
    //ResponseEntity - это обертка HTTP response
    public ResponseEntity<EmployeeIncorrectData> handleException(Exception exception) {

        EmployeeIncorrectData data = new EmployeeIncorrectData();

        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);

    }
}
