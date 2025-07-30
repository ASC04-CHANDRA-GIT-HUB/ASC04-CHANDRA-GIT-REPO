package com.ecz.repository;

//import java.util.*;
import java.sql.*;

public class DbConnectionSingleton{
    private static Connection connection;
    private DbConnectionSingleton(){}
    public static Connection getInstance(){
        if(connection==null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1/labdb","root","mysql");
                return connection;
            }
            catch(ClassNotFoundException | SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }
}