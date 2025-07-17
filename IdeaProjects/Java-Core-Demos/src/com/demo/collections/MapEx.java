package com.demo.collections;

import java.util.Map;
import java.util.HashMap;

public class MapEx{
    public static void main(String[] args){m1();}
    public static void m1(){
        Map<Object,Object> map1= new HashMap<Object,Object>();
        Laptop laptop1=new Laptop("Inspiration",100000);
        Laptop laptop2=new Laptop("Thinkpad",200000);
        map1.put("HP",laptop1);
        String laptopBrand="Lenovo";
        map1.put(laptopBrand,laptop2);
        map1.put("HP",new Laptop("Pavilion",95000));
        map1.put(null,new Laptop("tuf",97000));
        map1.put("null",new Laptop("Rog",125000));
        map1.put(null,new Laptop("Mac",225000));
        System.out.println(map1);
    }

}

class Laptop{
    //instance variables
    String laptopName;
    float price;

    //constructor
    public Laptop(String laptopNameParam, float priceParam){
        this.laptopName=laptopNameParam;
        this.price=priceParam;
    }

    @Override
    public String toString(){
        return "Laptop [laptop name: "+laptopName+", laptop price: Rs "+price+"]";
    }
}