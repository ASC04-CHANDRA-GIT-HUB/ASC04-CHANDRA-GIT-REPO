package com.demo.oops;

public class StaticVsInstanceEx {
    public static void main(String args[]){
        Employee.companyName="Daridra Sarkara";
        String companyName = Employee.companyName;
        System.out.println(companyName);

        Employee emp1=new Employee();
        emp1.employeeName="Siddharamaiyah";
        System.out.println(emp1.employeeName);

        Employee emp2=new Employee();
        emp2.employeeName="D K Shivakumar";
        System.out.println(emp2.employeeName);

        System.out.println("The Karnataka State is being robbed by "+emp1.employeeName+" and "+emp2.employeeName+" and hence called as "+companyName);
    }
}

class Employee{
    public static String companyName;
    public String employeeName;
}


