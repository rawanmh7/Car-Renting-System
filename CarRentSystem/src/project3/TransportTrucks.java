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
public class TransportTrucks extends Trucks{
    
    private boolean travelable;
    
    public TransportTrucks(String plateNum , int numofTires, VehiclePark vp,
                           LocalDate sd, LocalDate ed,  double dailyFee,double loadingcap, boolean travelable) {
         
       super(plateNum,numofTires,vp,sd,ed,dailyFee,loadingcap);
       this.travelable = travelable;
    }
    public void rentMe(LocalDate d1 , LocalDate d2) {
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
    public void loadMe(double amount) throws OverWeightException {
        if(amount>loadingCap)
            throw new OverWeightException();
        else {
            this.loadingCap = amount;
            System.out.println("Loaded successfully");
            System.out.println(this.toString());
        }
    }    
    public String toString(){
          return "*Transport Truck*\n"+super.toString() + "\nTravel aboard status: " + travelable;
    }
    
}
