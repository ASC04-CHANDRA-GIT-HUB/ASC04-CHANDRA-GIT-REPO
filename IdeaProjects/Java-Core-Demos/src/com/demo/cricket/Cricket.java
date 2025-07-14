package com.demo.cricket;

public class Cricket {
    String name;
    int age;

    void battingStyle(){
        System.out.println("I am a right handed batter.");
    }

    void AgreementPeriod(int years){
        age+=years;
        System.out.println(name+" will be " + age +" when the agreement ends!");
    }
}
