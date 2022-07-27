/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 *
 * @author Rawan
 */
public class SW extends Car {
    private double loadingCap ;
    
    public SW(String plateNum , int numofTires,
              VehiclePark vp, LocalDate sd, LocalDate ed,  double dailyFee,String color , int numOfDoors , int seatingCap , double loadingCap){
       
        super(plateNum, numofTires, vp, sd, ed, dailyFee ,color, numOfDoors, seatingCap);
        this.loadingCap = loadingCap;
    }
    public String toString(){
        return  "*SW Car* \n"+super.toString() +"Loading Capacity: " + loadingCap;
    }
    public void rentMe(LocalDate d1 , LocalDate d2) {
        if(this.getStartD().isBefore(d1) && this.getEndD().isAfter(d2)){
            Scanner input = new Scanner(System.in);
            vp.getRentedVehicles().add(this);
            vp.getAvailableVehicles().remove(this);
            RENTALDays = (int) ChronoUnit.DAYS.between(d1,d2);
            System.out.println("Choose:"+"\n1)Remotely Deliever" +"\n2)Remotely Drop"+"\n3None");
            int choice = input.nextInt();
            if(choice == 1){
                System.out.println("Enter location: ");
                String Loc = input.next();               
                System.out.println("Rented Successfully and delievered to "+Loc);            
            }else if(choice == 2){
                System.out.println("Enter location to be dropped: ");
                String Loc = input.next();  
                dropMe();
            }
        }else
            try {
                throw new SorryWeDontHaveThatOneException();
            } catch (SorryWeDontHaveThatOneException s) {
                System.out.println(s.getMessage());
            }
    }
    public void loadMe(double amount) throws OverWeightException {
        if(amount>loadingCap)
            throw new OverWeightException();
        else {
            this.loadingCap = amount;
            System.out.println("Loaded successfully");            
        }
    }
    
    
}
