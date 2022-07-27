/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.time.LocalDate;

/**
 *
 * @author Rawan
 */
public abstract class Car extends Vehicle{
    private String color;
    private int numOfDoors;
    private int seatingCap;

    public Car(String plateNum , int numofTires,
               VehiclePark vp, LocalDate sd, LocalDate ed, double dailyFee, String c , int n , int sc){
        
        super(plateNum, numofTires, vp, sd, ed,dailyFee); 
        color = c;
        numOfDoors = n;
        seatingCap = sc;
    }
    public String toString(){
        return super.toString() + "\nColor: " + color + " ,number of doors: " + numOfDoors +" ,seating capacity: "+seatingCap;
    }

    public String getColor() {
        return color;
    }

    public int getNumOfDoors() {
        return numOfDoors;
    }

    public int getSeatingCap() {
        return seatingCap;
    }

 
    
    
}
