package com.demo.HashSet;

import java.util.*;
public class HashSetTest {
    @SuppressWarnings("unchecked")
    public static void main(String args[])
    {
        @SuppressWarnings("rawtypes")
        Set hashSet = new HashSet();
        hashSet.add("1");
        hashSet.add(1);
        hashSet.add(null);
        hashSet.add("null");
        System.out.println(hashSet);
    }
}