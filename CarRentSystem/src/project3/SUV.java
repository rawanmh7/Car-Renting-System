/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Rawan
 */
public class SUV extends Car{
    private String wheelDrive ;
    
    public SUV(String plateNum , int numofTires,
               VehiclePark vp, LocalDate sd, LocalDate ed,  double dailyFee,String color , int numOfDoors , int seatingCap , String w){
        
        super(plateNum, numofTires, vp, sd, ed,dailyFee ,color, numOfDoors, seatingCap);
        wheelDrive = w;
    }
    public void rentMe(LocalDate d1 , LocalDate d2){
        if(this.getStartD().isBefore(d1) && this.getEndD().isAfter(d2)){
            vp.getRentedVehicles().add(this);
            vp.getAvailableVehicles().remove(this);
            RENTALDays = (int) ChronoUnit.DAYS.between(d1,d2);
            System.out.println("Rented Successfully");
        }else
            try {
                throw new SorryWeDontHaveThatOneException();
            } catch (SorryWeDontHaveThatOneException s) {
                System.out.println(s.getMessage());
            }
    }
    public void loadMe(double amount){
        System.out.println("This type of vehicle cannot be loaded");
    }        
    public String toString(){
        return "*SUV* "+super.toString() + "\nWheelDrive type: "+wheelDrive;
    }
    
}
