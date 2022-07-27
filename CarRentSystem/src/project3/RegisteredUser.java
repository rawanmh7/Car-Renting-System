/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Rawan
 */
public class RegisteredUser extends Person implements Serializable{
 
    public RegisteredUser(String name, String address , int contactNum , VehiclePark vp){
        super(name,contactNum,address,vp);
        vp.getAllUsers().add(this);        
    }
    
    public String toString(){
        return super.toString() ;
    }

    public void searchByType(LocalDate d1, LocalDate d2, String type) throws SorryWeDontHaveThatOneException{
      vp.displayAvailableVehicles(d1, d2, type);
    }  
    
    public void bookV(LocalDate d1, LocalDate d2){
       Scanner input = new Scanner(System.in);
        try {
            System.out.println("List of available vehicles: ");
            vp.displayAvailableVehicles(d1,d2);
            System.out.println("Enter vehicles plate number: ");
            String number = input.next();
            vp.bookVehicle(d1,d2,number);
        }
        catch(SorryWeDontHaveThatOneException s){
            System.out.println(s.getMessage());
        }
    }
      
    public void cancelB(String plateN){
      try {
        vp.cancelBooking(plateN);
         } 
      catch (NoCancellationYouMustPayException n) {
          System.out.println(n.getMessage());
       }
    }
     
    public void rentV(LocalDate d1, LocalDate d2){
        if(vp.getTheVehicles().isEmpty())
            System.out.println("No vehicle is available");
        else {
           Scanner input = new Scanner(System.in);
          try {
            System.out.println("List of available vehicles: (vp)");
           vp.displayAvailableVehicles(d1,d2);
            System.out.println("Enter vehicles plate number: ");
            String number = input.next();
            vp.rentVehicle(d1,d2,number);
         }
         catch (SorryWeDontHaveThatOneException s){
             System.out.println(s.getMessage());
           }
        }
    }
   
   public void Load(double amount , String plateN){
    Scanner input = new Scanner(System.in);
        try {
         vp.load(amount,plateN);
        }catch (OverWeightException e){
            System.out.println(e.getMessage());
        }  
    }
  
    public void dropV(String plateN){
     vp.dropVehicle(plateN);
    }
    
    
    
}
