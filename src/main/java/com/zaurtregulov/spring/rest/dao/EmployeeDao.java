package com.zaurtregulov.spring.rest.dao;

import com.zaurtregulov.spring.rest.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployee(int id);

    void deleteEmployee(int id);
}
