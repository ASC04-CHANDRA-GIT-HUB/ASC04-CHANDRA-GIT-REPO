package com.demo.gallery;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Photograph {

        byte frameWidth;
        String colorPhoto;
        String frameMaterial;
        String name;

        void hang(){
            System.out.println("Hang on the rear wall!");
        }
        void changeOrientation(String orientation){
            System.out.println("Orientation changes to: "+orientation);
        }
        Boolean illuminate(){
            System.out.println("Switched on!");
            return true;
        }
        void autograph(){
            System.out.println("Autograph sign Chandra Sagar C M");
        }
}