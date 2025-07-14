package com.demo.LABS.lab1;

import java.util.*;

class ArrayStore {
    int[] arr = new int[10];

    public void accept(int[] input) {
        if (input.length == 10)
            arr = input;
    }

    public void displayFor() {
        for (int val : arr)
            System.out.print(val + " ");
        System.out.println();
    }

    public void displayWhile() {
        int i = 0;
        while (i < arr.length)
            System.out.print(arr[i++] + " ");
        System.out.println();
    }

    public void sortArray() {
        Arrays.sort(arr);
    }

    public int countOccurrences(int number) {
        int count = 0;
        for (int val : arr)
            if (val == number) count++;
        return count;
    }

    public void insertAt(int number, int position) {
        if (position < 0 || position > arr.length) return;
        int[] newArr = new int[arr.length + 1];
        for (int i = 0, j = 0; i < newArr.length; i++) {
            if (i == position) {
                newArr[i] = number;
            } else {
                newArr[i] = arr[j++];
            }
        }
        arr = Arrays.copyOf(newArr, 10); // truncate if needed
    }

    public int[] removeDuplicates() {
        Set<Integer> set = new LinkedHashSet<>();
        for (int val : arr) set.add(val);
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) result[i++] = num;
        return result;
    }
}

class ArrayStoreMain {
    public static void main(String[] args) {
        ArrayStore store = new ArrayStore();

        int[] sampleInput = {9, 2, 2, 9, 10, 9, 5, 3, 3, 1};
        store.accept(sampleInput);

        System.out.println("Display using for loop:");
        store.displayFor();

        System.out.println("Display using while loop:");
        store.displayWhile();

        System.out.println("Sorted Array:");
        store.sortArray();
        store.displayFor();

        System.out.println("Occurrences of 9: " + store.countOccurrences(9));

        System.out.println("Insert 100 at position 3:");
        store.insertAt(100, 3);
        store.displayFor();

        System.out.println("Array after removing duplicates:");
        int[] unique = store.removeDuplicates();
        System.out.println(Arrays.toString(unique));
    }
}
