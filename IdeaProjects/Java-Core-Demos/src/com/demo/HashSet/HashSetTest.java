package com.demo.HashSet;

import java.util.*;
public class HashSetTest {
    public static void main(String args[])
    {
        Set hashSet = new HashSet();
        hashSet.add("1");
        hashSet.add(1);
        hashSet.add(null);
        hashSet.add("null");
        System.out.println(hashSet);
    }
}