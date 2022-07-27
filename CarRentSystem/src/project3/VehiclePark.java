/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rawan
 */
public class VehiclePark {
  
    private ArrayList<Vehicle> bookedVehicles = new ArrayList<>();
    private ArrayList<Vehicle> rentedVehicles = new ArrayList<>();
    private ArrayList<Vehicle> theVehicles = new ArrayList<>();
    private ArrayList<Vehicle> availableVehicles = new ArrayList<>();
    private ArrayList<RegisteredUser> allUsers = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);
    
    public VehiclePark(){

    }

    public ArrayList<Vehicle> getBookedVehicles() {
        return bookedVehicles;
    }

    public ArrayList<Vehicle> getRentedVehicles() {
        return rentedVehicles;
    }

    public ArrayList<Vehicle> getTheVehicles() {
        return theVehicles;
    }

    public ArrayList<RegisteredUser> getAllUsers() {
        return allUsers;
    }
        
    public ArrayList<Vehicle> getAvailableVehicles() {
        return availableVehicles;
    }

    public void displayVehicles(){
        for(Vehicle x: theVehicles){
            System.out.println(x);
        }
    }
    
    public void displayAvailableVehicles(LocalDate d1, LocalDate d2) throws SorryWeDontHaveThatOneException{
        if(theVehicles.isEmpty())
            System.out.println("No vehicle is available");
        else{
            for(Vehicle x : theVehicles){
                if(x.getStartD().isBefore(d1) && x.getEndD().isAfter(d2)) {
                    if(!availableVehicles.contains(x) && (!bookedVehicles.contains(x)) &&(!rentedVehicles.contains(x))) {
                        availableVehicles.add(x);
                    }
                }
                else
                    throw new SorryWeDontHaveThatOneException();
            }
            for(Vehicle x: availableVehicles)
                System.out.println(x+"\n");
        }
    }
    
    public void displayAvailableVehicles(LocalDate d1, LocalDate d2, String type) throws SorryWeDontHaveThatOneException{
        if(theVehicles.isEmpty())
            System.out.println("No vehicle is available");
        else{
            for(Vehicle x : theVehicles){
                if(x.getStartD().isBefore(d1) && x.getEndD().isAfter(d2)) {
                    if (!availableVehicles.contains(x) && (!bookedVehicles.contains(x)) &&(!rentedVehicles.contains(x))) {
                        availableVehicles.add(x);
                        if(type.equals("Sports")){
                            if(x instanceof Sports)
                                System.out.println(x);
                        }else if(type.equals("SW")){
                            if(x instanceof SW)
                                System.out.println(x);
                        }else if(type.equals("SUV")){
                            if(x instanceof SUV)
                                System.out.println(x);
                        }else if(type.equals("SmallTrucks")){
                            if(x instanceof SmallTrucks)
                                System.out.println(x);
                        }else if(type.equals("TransportTrucks")){
                            if(x instanceof TransportTrucks)
                                System.out.println(x);
                        }
                    }else
                        throw new SorryWeDontHaveThatOneException();
                }
            }
        }
    }       
    
    public void bookVehicle(LocalDate d1, LocalDate d2, String plateNumber) throws SorryWeDontHaveThatOneException {
        if(availableVehicles.isEmpty())
            System.out.println("No vehicle is available");
        else{
          for (int i = 0; i < availableVehicles.size(); i++) {
            if (availableVehicles.get(i).getPlateNum().equals(plateNumber)) {
                availableVehicles.get(i).setRENTALDate(d1);
                availableVehicles.get(i).bookMe(d1, d2, plateNumber);
            }
          }
        }
    } 
  
    public void cancelBooking(String plateNumber) throws NoCancellationYouMustPayException {
        boolean flag = false;
        for (int i = 0; i < bookedVehicles.size(); i++) {
            if (bookedVehicles.get(i).getPlateNum().equals(plateNumber)) {
                bookedVehicles.get(i).cancelMe();
                flag = true;
            }
        }
        if(flag)
            System.out.println("Cancelled Successfully");
        else
            System.out.println("Vehicle not found!");
    }
    
    public void rentVehicle(LocalDate d1, LocalDate d2, String plateNumber){
          for (int i = 0; i < availableVehicles.size(); i++) {
            if (availableVehicles.get(i).getPlateNum().equals(plateNumber)) {
                availableVehicles.get(i).rentMe(d1,d2);
              
            }
          }
    }
    public void load(double amount, String plateNumber) throws OverWeightException {
          for(Vehicle x : theVehicles){
             if(x.getPlateNum().equals(plateNumber))
                x.loadMe(amount);
          }        
    }
    public void dropVehicle(String plateNumber){
        boolean flag = false;
        for(int i = 0; i<rentedVehicles.size(); i++){
            if(rentedVehicles.get(i).getPlateNum().equals(plateNumber)){
                rentedVehicles.get(i).dropMe();
                flag = true;
            }
        }
        if(!flag)
           System.out.println("Not found");
        
    }
    public void addVehicle(String type,String plateN, int numOftires, LocalDate d1, LocalDate d2, double dailyFee){
        Scanner input = new Scanner(System.in);
        if(type.equals("Sports") || type.equals("SW") || type.equals("SUV")) {
            System.out.println("Enter color: ");
            String color = input.next();
            System.out.println("Enter number of doors: ");
            int doors = input.nextInt();
            System.out.println("Enter seating capacity: ");
            int seat = input.nextInt();
            if(type.equals("Sports")) {
                System.out.println("Enter horsepower: ");
                double hp = input.nextDouble();
                Sports s = new Sports(plateN, numOftires, this, d1, d2,dailyFee ,color, doors, seat, hp);
                System.out.println("Sport Vehicle created!");
            }
            else if(type.equals("SW")){
                System.out.println("Enter loading capacity: ");
                double cap = input.nextDouble();
                SW x = new SW(plateN,numOftires,this,d1,d2,dailyFee,color,doors,seat,cap);
                System.out.println("SW Vehicle created!");
            }else {
                System.out.println("Enter wheel drive type: ");
                String t = input.next();
                SUV z = new SUV(plateN,numOftires,this,d1,d2,dailyFee,color,doors,seat,t);
                System.out.println("SUV Vehicle created!");
            }

        }else if(type.equals("SmallTrucks") || type.equals("TransportTrucks")){
            System.out.println("Enter loading capacity: ");
            double load = input.nextDouble();
            if(type.equals("SmallTrucks")){
                SmallTrucks s = new SmallTrucks(plateN,numOftires,this,d1,d2,dailyFee,load);
                System.out.println("Small Truck Vehicle created!");
            }else{
                System.out.println("Enter travelling aboard status:(true/false) ");
                boolean status = input.nextBoolean();
                TransportTrucks t = new TransportTrucks(plateN,numOftires,this,d1,d2,dailyFee,load,status);
                System.out.println("Transport Truck Vehicle created!");
            }
        }
        else
            System.out.println("Invalid request");
    }
    
    public void removeVehicle(String plateN){
        boolean flag = false;
        for(int i = 0; i< theVehicles.size(); i++){
            if(theVehicles.get(i).getPlateNum().equals(plateN)){
                if(availableVehicles.contains(i) || bookedVehicles.contains(i)
                        || rentedVehicles.contains(i)){
                    availableVehicles.remove(i);
                    rentedVehicles.remove(i);
                    bookedVehicles.remove(i);
                    flag = true;
                }
                theVehicles.remove(i);
               
            }
        }
        if(flag)
            System.out.println("Removed Successfully");
        else
            System.out.println("Not found");
    }
   
    public void dailyReport(File file) throws IOException {
        PrintWriter output = new PrintWriter(file);
        output.println("List of all the vehicles: ");
        output.close();
        FileOutputStream fo = new FileOutputStream(file);
        ObjectOutputStream o = new ObjectOutputStream(fo);
        for(Vehicle x : theVehicles ){
            o.writeObject(x);
        }
        output.println("List of registered users: ");
        output.close();
        for(RegisteredUser user : allUsers){
            o.writeObject(user);
        }
        output.close();
        o.close();
        fo.close();
    }


}
