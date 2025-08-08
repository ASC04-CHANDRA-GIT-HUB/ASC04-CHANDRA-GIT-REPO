package com.rfk;

import com.rfk.entity.EmployeeEntity;
import com.rfk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class App
{
    public static void main( String[] args )
    {
        System.out.println("Welcome to our Java App!");
        SpringApplication.run(App.class, args);
        System.err.println("Welcome to our Spring App!");
//        System.out.println(EmployeeData.getAllEmployees());
    }
}

//class EmployeeData {
//    @Autowired
//    static EmployeeService employeeService;
//    public static List<EmployeeEntity> getAllEmployees(){
//        return employeeService.getAllEmployees();
//    }
//}