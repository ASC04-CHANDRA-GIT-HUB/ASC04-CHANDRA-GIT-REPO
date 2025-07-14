package com.demo.LABS.lab1;

class Table {
    public void printFor(int n) {
        for (int i = 1; i <= 10; i++)
            System.out.println(n + " x " + i + " = " + (n * i));
    }

    public void printWhile(int n) {
        int i = 1;
        while (i <= 10) {
            System.out.println(n + " x " + i + " = " + (n * i));
            i++;
        }
    }

    public void printDoWhile(int n) {
        int i = 1;
        do {
            System.out.println(n + " x " + i + " = " + (n * i));
            i++;
        } while (i <= 10);
    }
}
class TableMain {
    public static void main(String[] args) {
        Table t = new Table();

        System.out.println("Using for loop:");
        t.printFor(2);

        System.out.println("\nUsing while loop:");
        t.printWhile(2);

        System.out.println("\nUsing do-while loop:");
        t.printDoWhile(2);
    }
}
