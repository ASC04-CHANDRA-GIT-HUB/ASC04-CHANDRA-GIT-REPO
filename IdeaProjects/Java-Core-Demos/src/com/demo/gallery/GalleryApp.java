package com.demo.gallery;

public class GalleryApp {
    public static void main(String args[]){
        System.out.println("Welcome to workden gallery!");
        Photograph HdPhoto;
        HdPhoto=new Photograph();
        HdPhoto.name="Nandi Hills";
        System.out.println(HdPhoto.name);
        HdPhoto.hang();
        HdPhoto.changeOrientation("Landscape!");
        boolean isIlluminated=HdPhoto.illuminate();
        if(isIlluminated){
            System.out.println("Glowing Photo");
        }
        HdPhoto.autograph();
    }
}
