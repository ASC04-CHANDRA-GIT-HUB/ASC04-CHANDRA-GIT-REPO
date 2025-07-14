package com.demo.access_modifiers.p2;

import com.demo.access_modifiers.p1.p1c1;

public class p2c2 extends p1c1{
    public static void main(String args[]) {
        p2c2 p2C2 = new p2c2();
//        System.out.println(p1C1.privateVar);
//        p1C1.privateMethod();
//        System.out.println(p1C1.defaultVar);
//        p1C1.defaultMethod();
//        System.out.println(p2C2.protectedVar);
//        p2C2.protectedMethod();
        System.out.println(p2C2.publicVar);
        p2C2.publicMethod();
    }
}