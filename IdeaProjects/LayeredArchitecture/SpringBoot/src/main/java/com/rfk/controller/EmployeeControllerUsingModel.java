package com.rfk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rfk.model.EmployeeModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EmployeeControllerUsingModel {
    @GetMapping("/getHTMLContent")
    public String getHTMLContent() {
        String htmlOutput =  "<html><head><title>Welcome</title></head><body><h1 style=\"color:red\">Rest API can also expose HTML Data</h1></body></html>";
        return htmlOutput;
    }

    @GetMapping("/getEmployeesAsList")
    public List<EmployeeModel> getEmployeesAsList() {
        List<EmployeeModel> employees = new java.util.ArrayList<>();
        employees.add(new EmployeeModel(1, "Alice"));
        employees.add(new EmployeeModel(2, "Bob"));
        employees.add(new EmployeeModel(3, "Charlie"));
        return employees;
    }

    @GetMapping("/getEmployeesAsMap")
    public Map<Integer, EmployeeModel> getEmployeesAsMap() {
        Map<Integer, EmployeeModel> employeeMap = new HashMap<>();
        employeeMap.put(1, new EmployeeModel(1, "Alice"));
        employeeMap.put(2, new EmployeeModel(2, "Bob"));
        employeeMap.put(3, new EmployeeModel(3, "Charlie"));
        return employeeMap;
    }
}