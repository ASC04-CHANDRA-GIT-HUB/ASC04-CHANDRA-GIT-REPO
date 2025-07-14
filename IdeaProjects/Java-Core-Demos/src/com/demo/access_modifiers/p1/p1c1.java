package com.demo.access_modifiers.p1;

public class p1c1{
    //instance variable
    private String privateVar;
    String defaultVar;
    protected String protectedVar;
    public String publicVar;

    private void privateMethod(){
        System.out.println(privateVar);
    }
    void defaultMethod(){
        System.out.println(defaultVar);
    }
    protected void protectedMethod(){
        System.out.println(protectedVar);
    }
    public void publicMethod(){
        System.out.println(publicVar);
    }

    public static void main(String args[]){
       p1c1 p1C1 = new p1c1();
        System.out.println(p1C1.privateVar);
        p1C1.privateMethod();
        System.out.println(p1C1.defaultVar);
        p1C1.defaultMethod();
        System.out.println(p1C1.protectedVar);
        p1C1.protectedMethod();
        System.out.println(p1C1.publicVar);
        p1C1.publicMethod();
    }
}