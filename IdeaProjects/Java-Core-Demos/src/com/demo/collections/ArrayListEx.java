package com.demo.collections;

import java.util.ArrayList;

public class ArrayListEx{
    public static void main(String[] args){
        rawTypes();
        genericTypes();
        @SuppressWarnings("unused")
        Integer x=null;
        integerTypes();
        numberTypes();
        workingWithArrayList();
    }
    @SuppressWarnings("unchecked")
    private static void rawTypes(){
        //ArrayList without generics( raw type )
        @SuppressWarnings("rawtypes")
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

    public static void  workingWithArrayList(){
        ArrayList<String> shoppingCartList=new ArrayList<String>();
        //methods from collection interface
        shoppingCartList.add("Man's search for meaning");
        shoppingCartList.add("Start with Why");
        shoppingCartList.add("Java");
        shoppingCartList.add("Start with Why");
        shoppingCartList.add("Deep work");
        //The set method replaces the element at the specified position
        //with the specified element and returns the old element
        shoppingCartList.set(1,"Start with Why - Updated");
        System.out.println("1 : "+shoppingCartList.get(3));
        shoppingCartList.remove(3);
        System.out.println(shoppingCartList);
        shoppingCartList.add(null); //adding null element
        shoppingCartList.add(null); //adding another null element
        System.out.println(shoppingCartList);

        for(String item:shoppingCartList){  //iterator
            System.out.println(item);
        }

        System.out.println("Size of Shopping cart: "+shoppingCartList.size());
        System.out.println("Before removing items: "+shoppingCartList);

        ArrayList<String> removeItemsList=new ArrayList<String>();
        removeItemsList.add(null);
        removeItemsList.add("Deep work");
        removeItemsList.add("Start with Why");
        removeItemsList.add("Java");

        //Removes all the elements in removeItemsList from shopping CartList
        shoppingCartList.removeAll(removeItemsList);
        System.out.println("After removing items: "+shoppingCartList);
    }
}
