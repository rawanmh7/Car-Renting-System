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
public class Sports extends Car{
    private double horsePower;
    
    public Sports(String plateNum , int numofTires,
                  VehiclePark vp, LocalDate sd, LocalDate ed, double dailyFee, String color ,
                  int numOfDoors , int seatingCap , double h){
        
        super(plateNum, numofTires, vp, sd, ed, dailyFee,color, numOfDoors, seatingCap);
        horsePower = h;
    }
    public String toString(){
        return  "*Sports Car* \n"+ super.toString() + "\nHP: " + horsePower;
    }
    public void rentMe(LocalDate d1 , LocalDate d2){
        Scanner input = new Scanner(System.in);
        if(this.getStartD().isBefore(d1) && this.getEndD().isAfter(d2)){
            vp.getRentedVehicles().add(this);
            vp.getAvailableVehicles().remove(this);
            RENTALDays = (int) ChronoUnit.DAYS.between(d1,d2);
            System.out.println("Choose:"+"\n1)Remotely Deliever" +"\n2)Remotely Drop"+"\nNone");
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
    public void loadMe(double amount){
        System.out.println("This type of vehicle cannot load");
    }
}
