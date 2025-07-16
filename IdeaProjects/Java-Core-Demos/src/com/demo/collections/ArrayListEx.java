package com.demo.collections;

import java.util.ArrayList;

public class ArrayListEx{
    public static void main(String[] args){
        rawTypes();
        genericTypes();
        Integer x=null;
        integerTypes();
        numberTypes();

    }
    private static void rawTypes(){
        //ArrayList without generics( raw type )

        ArrayList colorList = new ArrayList();
        colorList.add("Red");
        colorList.add(null);
        colorList.add("Green");
        ArithmeticException arithmeticException=new ArithmeticException("Sample Exception");
        colorList.add(arithmeticException);
        System.out.println(colorList);
    }

    private static void genericTypes(){
        //ArrayList with generics
        ArrayList<String> colorList = new ArrayList<String>();
        colorList.add("Red");
        colorList.add(null);
        colorList.add("Green");
        ArithmeticException arithmeticException=new ArithmeticException("Sample Exception");
        colorList.add(arithmeticException.toString());//adding object directly isn't supported
        System.out.println(colorList);

    }
    private static void integerTypes(){
        //Collections do not allow primitive data types
        //*** ArrayList<int> colorList = new ArrayList<int>(); ***
        //Instead use wrapper class
        ArrayList<Integer> integerList = new ArrayList<Integer>();
        integerList.add(10);
        integerList.add(20);
        integerList.add(null);
        //integerList.add(30.05f);
        System.out.println(integerList);
    }

    private static void numberTypes(){
        //This is where different numeric types can be added in a single list
        ArrayList<Number> numberList = new ArrayList<Number>();

        int primitiveInt = 11;
        Integer boxedInt=Integer.valueOf(primitiveInt);//Explicitly boxing to Integer
        numberList.add(Integer.valueOf(boxedInt));//Boxing to Integer
        numberList.add(primitiveInt);//autoboxing to Integer
        numberList.add(10);
        numberList.add(30.9f);
        numberList.add(87.64);
        numberList.add(null);
        System.out.println(numberList);
    }
}
