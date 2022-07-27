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
public abstract class Trucks extends Vehicle{
   
    protected double loadingCap;
    
    public Trucks(String plateNum , int numofTires, VehiclePark vp, LocalDate sd, LocalDate ed,
            double dailyFee, double loadingcap){
        
        super(plateNum, numofTires, vp, sd, ed,dailyFee); 
        this.loadingCap = loadingcap;
    }
    public double getLoadingCap() {
        return loadingCap;
    }   
    public String toString(){
        return super.toString() + "\nTruck's loading capacity: " + loadingCap;
    }
    
}
