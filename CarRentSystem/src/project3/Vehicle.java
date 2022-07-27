/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/**
 *
 * @author Rawan
 */
public abstract class Vehicle implements Serializable{
   private final String plateNum;
   private final int numofTires;
   private double dailyFee;
   private LocalDate startD;
   private LocalDate endD;
   protected VehiclePark vp ;
   private LocalDate RENTALDate;
   protected int RENTALDays;
   
    public  Vehicle(String plateNum , int numofTires, VehiclePark vp, LocalDate sd, LocalDate ed , double dailyFee){
        this.vp = vp;
        this.plateNum = plateNum;
        this.numofTires = numofTires;
        this.dailyFee = dailyFee;
        startD = sd;
        endD = ed;
        vp.getTheVehicles().add(this);        
    }

    public String getPlateNum() {
        return plateNum;
    } 

    public int getNumofTires() {
        return numofTires;
    }

    public double getDailyFee(int numOfDays) {
        return numOfDays * dailyFee;
    }

    public LocalDate getStartD() {
        return startD;
    }

    public LocalDate getEndD() {
        return endD;
    }

    public LocalDate getRENTALDate() {
        return RENTALDate;
    }

    public void setRENTALDate(LocalDate RENTALDate) {
        this.RENTALDate = RENTALDate;
    }

    public void bookMe(LocalDate d1 , LocalDate d2, String plateNumber) throws SorryWeDontHaveThatOneException{
         if (this instanceof Sports && this.startD.isBefore(d1) && this.endD.isAfter(d2)) {
              vp.getBookedVehicles().add(this);             
              vp.getAvailableVehicles().remove(this);
              System.out.println("Booked successfully");

          } else if (this instanceof Trucks && this.startD.isBefore(d1) && this.endD.isAfter(d2)) {
              final LocalDate currentDate = LocalDate.now();
              int Checkdays = (int) ChronoUnit.DAYS.between(currentDate, d1);
              if (Checkdays <= 7) {
                  System.out.println("Trucks must be booked 7 days ahead of rental..");
              } else {
                  vp.getBookedVehicles().add(this);              
                  vp.getAvailableVehicles().remove(this);
                  System.out.println("The Vehicle is successfully booked!");
              }
          } else if (this instanceof Vehicle && this.startD.isBefore(d1) && this.endD.isAfter(d2))
              System.out.println("This type cannot be booked");
          else
              throw new SorryWeDontHaveThatOneException();
    }
    
   public void cancelMe() throws NoCancellationYouMustPayException {
        final LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(this.RENTALDate))
            throw new NoCancellationYouMustPayException();
        else {
            vp.getBookedVehicles().remove(this);
            vp.getAvailableVehicles().add(this);
        }
    }
    public void dropMe(){
        if(!(vp.getAvailableVehicles().contains(this)))
            vp.getAvailableVehicles().add(this);
        if(vp.getBookedVehicles().contains(this))
            vp.getBookedVehicles().remove(this);
        vp.getRentedVehicles().remove(this);
        final LocalDate currentDate = LocalDate.now();
        System.out.println("Dropped successfully. Total payment: " + this.getDailyFee(this.RENTALDays));
        
    }
    public abstract void rentMe(LocalDate d1, LocalDate d2);
    public abstract void loadMe(double amount) throws OverWeightException;
    
    
    public String toString(){
        return "Vehicle's plate number: " + plateNum +"\nNumber of tires: " +
                numofTires + "\nFee per day: "+dailyFee;
    }
}
