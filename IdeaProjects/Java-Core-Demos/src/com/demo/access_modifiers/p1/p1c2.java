package com.demo.access_modifiers.p1;

class p1c2 {
    public static void main(String args[]) {
        p1c1 p1C1 = new p1c1();
//        System.out.println(p1C1.privateVar);
//        p1C1.privateMethod();
//        System.out.println(p1C1.defaultVar);
//        p1C1.defaultMethod();
        System.out.println(p1C1.protectedVar);
        p1C1.protectedMethod();
    }
}