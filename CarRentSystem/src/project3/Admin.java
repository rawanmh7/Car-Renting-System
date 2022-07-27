/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.Scanner;

/** 
 *
 * @author Rawan
 */
public class Admin extends Person{
    
    private String userName;    
     
    public Admin(String name, int contactNum , String address, VehiclePark vp, String userName){
        super(name, contactNum, address, vp);
        this.userName = userName;
    }
    
    public String toString(){
        return super.toString() + "\nUserName: "+ userName;
    }

    public void addV(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter type of vehicle: ");
        String s = input.next();
        System.out.println("Enter plate number: ");
        String p = input.next();
        System.out.println("Enter number of tires: ");
        int m = input.nextInt();
        System.out.println("Enter daily fees: ");
        double price = input.nextInt();
        System.out.println("Enter starting date: ");
        int[] startD = new int[3];
        for(int i = 0; i<startD.length; i++){
            startD[i] = input.nextInt();
        }
        LocalDate startDate = LocalDate.of(startD[0],startD[1],startD[2]);
        System.out.println("Enter ending date: ");
        int[] endD = new int[3];
        for(int i = 0; i<endD.length; i++){
            endD[i] = input.nextInt();
        }
        LocalDate endDate = LocalDate.of(endD[0],endD[1],endD[2]);
        vp.addVehicle(s,p,m,startDate,endDate,price);
    }
   
    public void removeV(String plateN){
        vp.removeVehicle(plateN);
    }
    public void getDailyReport() throws IOException {
        File file = new File("Vehicle.txt");
        vp.dailyReport(file);        
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fi);

       try {
           while (true) {
               Vehicle x = (Vehicle) in.readObject();
           }
       }catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
       }
       for(Vehicle x  : vp.getTheVehicles())
           System.out.println(x+"\n");
       for(RegisteredUser x : vp.getAllUsers())
           System.out.println(x+"\n");
    }

}
