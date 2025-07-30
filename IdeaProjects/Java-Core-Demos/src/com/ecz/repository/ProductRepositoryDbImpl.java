package com.ecz.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ecz.model.Product;

import static java.sql.DriverManager.getConnection;

public class ProductRepositoryDbImpl implements ProductRepository{
    @Override
    public boolean addProductToCart(Product product){
        try{
            Connection connection=getConnection();
            System.out.println("Driver loaded!");
            Statement statement=connection.createStatement();
            System.out.println("Statement created");

            String insertQuery="INSERT INTO Product(id,name,price,quantity) VALUES ('"+product.getId()+"', '"+product.getName()+"', '"+product.getPrice()+"', '"+product.getQuantity()+");";
            int noOfRowsAffected=statement.executeUpdate(insertQuery);
            System.out.println("No of rows affected: "+noOfRowsAffected);
            return noOfRowsAffected>0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

        public Connection getConnection(){
            return DbConnectionSingleton.getInstance();
        }

        @Override
        public List<Product> getAllProducts(){
            List<Product> products=new ArrayList<>();
            try{
                Connection connection=getConnection();
                Statement statement =connection.createStatement();
                ResultSet resultSet= statement.executeQuery("SELECT * FROM product");
                while(resultSet.next()){
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String price = resultSet.getDouble("price");
                    String quantity = resultSet.getInt("quantity");
                }
            }


        }
}