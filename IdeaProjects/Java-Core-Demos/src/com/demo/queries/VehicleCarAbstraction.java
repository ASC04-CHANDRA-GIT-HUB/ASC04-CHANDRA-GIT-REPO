package com.demo.queries;

// This is multi-level inheritance example
// Vehicle is the base class, ElectricVehicle is the intermediate class, and Car is the derived class.
abstract class Vehicle {
    void register() {
        System.out.println("Vehicle registered");
    }
}

abstract class ElectricVehicle extends Vehicle {
    abstract void move(); // must be overridden
    void stop() { System.out.println("Stopped"); } // concrete method
}

class Car extends ElectricVehicle {
    void move() {
        System.out.println("Driving");
    }
    void moveAndStop() {
        move();
        stop();
    }
}

class Drones extends ElectricVehicle {
    @Override
    void move() {
        System.out.println("Flying");
    }
    // @Override
    void bomb() {
        System.out.println("Bombing");
    }
    @Override
    void register() {
        System.out.println("Vehicle registered in the military");
    }
}

public class VehicleCarAbstraction {
    public static void main(String[] args) {

        Car myCar = new Car();
        myCar.move(); // Output: Driving
        myCar.stop(); // Output: Stopped
        myCar.moveAndStop(); // Output: Driving Stopped

        // Dynamic polymorphism example
        // Upcasting Car to Vehicle, which is allowed because Car is a subclass of Vehicle
        Vehicle myVehicle = new Drones();
        myVehicle.register();
        // Downcasting Vehicle to Drones
        Drones myDrone = (Drones) myVehicle; // Downcasting
        myDrone.register();
        myDrone.move(); // Output: Flying
        myDrone.bomb(); // Output: Bombing

    }
}