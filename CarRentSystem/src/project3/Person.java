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
public abstract class Person {
    
    protected String name;
    protected VehiclePark vp;
    private int id = 1000;
    private static int count = 0;
    private int contactNum;
    private String address;

    public Person(String name, int cn , String ad, VehiclePark vp){
        this.name = name;
        contactNum = cn;
        address = ad;
        this.vp = vp;
        id+=count;
        count++;
    }
    public String toString(){
        return "Name: " + name +"\nId: " + id + "\nAddress: "+ address +"\nContact number: "+ contactNum;
    }

    public void display(){
        if(vp.getTheVehicles().isEmpty())
            System.out.println("No vehicle is available");
        else
           vp.displayVehicles();
       
    }
    public void displayAvailable(LocalDate d1, LocalDate d2){
        try {
            vp.displayAvailableVehicles(d1, d2);
        }
        catch(SorryWeDontHaveThatOneException s){
            System.out.println(s.getMessage());
        }
    }
    
}
