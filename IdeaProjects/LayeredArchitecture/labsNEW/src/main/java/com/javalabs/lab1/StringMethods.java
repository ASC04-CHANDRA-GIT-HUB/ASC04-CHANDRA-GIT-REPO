package com.javalabs.lab1;

class StringTest {
    public static void main(String[] args) {
        String s1 = "Hello World";
        String s2 = "hello world";

        System.out.println("charAt: " + s1.charAt(4));
        System.out.println("contains 'World': " + s1.contains("World"));
        System.out.println("length: " + s1.length());
        System.out.println("indexOf 'o': " + s1.indexOf('o'));
        System.out.println("equals: " + s1.equals(s2));
        System.out.println("equalsIgnoreCase: " + s1.equalsIgnoreCase(s2));
        System.out.println("join: " + String.join(" - ", "A", "B", "C"));
        System.out.println("lastIndexOf 'l': " + s1.lastIndexOf('l'));
        System.out.println("substring(0, 5): " + s1.substring(0, 5));
        System.out.println("toLowerCase: " + s1.toLowerCase());
        System.out.println("toUpperCase: " + s1.toUpperCase());
        System.out.println("trim: '" + "  trimmed  ".trim() + "'");
    }
}
