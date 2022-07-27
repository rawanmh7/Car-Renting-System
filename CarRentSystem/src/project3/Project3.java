/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Rawan
 */
public class Project3 {

    /**
     * @param args the command line arguments
     */
    private static Scanner input = new Scanner(System.in);
    private static VehiclePark vp = new VehiclePark();
    private static Admin currentAdmin = null;
    private static RegisteredUser currentUser =  null;
    
    public static void main(String[] args) {
        // TODO code application logic here
        while (true){
            printMenu();
            System.out.println("Choose an option: ");
            int option = input.nextInt();
          if(option>0&&option<7){ 
              
            if(option == 1){
                if(vp.getTheVehicles().isEmpty())
                    System.out.println("No vehicle is available");
                else 
                  vp.displayVehicles();
            }
            if(option == 2){
                System.out.println("Enter starting date: ");
                int[] Date = new int[3];
                for(int i = 0; i<Date.length; i++){
                    Date[i] = input.nextInt();
                }
                LocalDate Date1 = LocalDate.of(Date[0],Date[1],Date[2]);
                System.out.println("Enter ending date: ");
                int[] date = new int[3];
                for(int i = 0; i<date.length; i++){
                    date[i] = input.nextInt();
                }
                LocalDate Date2 = LocalDate.of(date[0],date[1],date[2]);
                try {
                    vp.displayAvailableVehicles(Date1,Date2);
                } catch (SorryWeDontHaveThatOneException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(option == 3){
                option3();                
            }
            if(option == 4){
                menu4();
            }
            if(option == 5){
                menu5();
            }
            if(option == 6)
                break;          
          }else
                System.out.println("Enter a valid option: ");
        }
    }
    public static void printMenu(){       
        System.out.println("1)Display all vehicles"+"\n2)Display available vehicle"+"\n3)Register me"+
                "\n4)Continue as registered user"+"\n5)Continue as admin"+"\n6)Quit");
    }
    public static void menu4(){
        while(true) {
            System.out.println("1)Display all vehicles" + "\n2)Display available vehicles" +
                    "\n3)Display available vehicle by type" + "\n4)Book vehicle" +
                    "\n5)Cancel my bookings" + "\n6)Rent vehicle" + "\n7)Load vehicle" + "\n8)Drop Vehicle" + "\n9)Quit");
            System.out.println("Choose an option: ");
            int option = input.nextInt();
            if(option == 1)
                currentUser.display();
            if(option == 2){
                System.out.println("Enter starting date: ");
                int[] Date = new int[3];
                for(int i = 0; i<Date.length; i++){
                    Date[i] = input.nextInt();
                }
                LocalDate Date1 = LocalDate.of(Date[0],Date[1],Date[2]);
                System.out.println("Enter ending date: ");
                int[] date = new int[3];
                for(int i = 0; i<date.length; i++){
                    date[i] = input.nextInt();
                }
                LocalDate Date2 = LocalDate.of(date[0],date[1],date[2]);
                 currentUser.displayAvailable(Date1,Date2);
            }
            if(option == 3){
                System.out.println("Enter starting date: ");
                int[] Date = new int[3];
                for(int i = 0; i<Date.length; i++){
                    Date[i] = input.nextInt();
                }
                LocalDate Date1 = LocalDate.of(Date[0],Date[1],Date[2]);
                System.out.println("Enter ending date: ");
                int[] date = new int[3];
                for(int i = 0; i<date.length; i++){
                    date[i] = input.nextInt();
                }
                LocalDate Date2 = LocalDate.of(date[0],date[1],date[2]);
                System.out.println("Enter type of vehicle: ");
                String type = input.next();
                try {
                    currentUser.searchByType(Date1, Date2, type);
                } catch (SorryWeDontHaveThatOneException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(option == 4){
                System.out.println("Enter starting date of rental: ");
                int[] startD = new int[3];
                for(int i = 0; i<startD.length; i++){
                    startD[i] = input.nextInt();
                }
                LocalDate startDate = LocalDate.of(startD[0],startD[1],startD[2]);

                System.out.println("Enter ending date of rental: ");
                int[] endD = new int[3];
                for(int i = 0; i<endD.length; i++){
                    endD[i] = input.nextInt();
                }
                LocalDate endDate = LocalDate.of(endD[0],endD[1],endD[2]);
                currentUser.bookV(startDate,endDate); 
            }
            if(option == 5 ){
                if(vp.getBookedVehicles().isEmpty())
                    System.out.println("You haven't booked any vehicle!");
                else {
                    System.out.println("List of booked vehicles");
                    for (Vehicle x : vp.getBookedVehicles())
                        System.out.println(x);
                    System.out.println("Enter vehicles plate number: ");
                    String number = input.next();
                    currentUser.cancelB(number);
                }
            }
            if(option == 6){                                          
                System.out.println("Enter starting date of rental: ");
                int[] startD = new int[3];
                for(int i = 0; i<startD.length; i++){
                    startD[i] = input.nextInt();
                }
                LocalDate startDate = LocalDate.of(startD[0],startD[1],startD[2]);

                System.out.println("Enter ending date of rental: ");
                int[] endD = new int[3];
                for(int i = 0; i<endD.length; i++){
                    endD[i] = input.nextInt();
                }
                LocalDate endDate = LocalDate.of(endD[0],endD[1],endD[2]);
                currentUser.rentV(startDate,endDate);               
            }
            if(option == 7){
                if(vp.getTheVehicles().isEmpty())
                    System.out.println("No vehicle is available");
                else{
                    System.out.println("List of vehicles: ");
                    vp.displayVehicles();
                   System.out.println("Enter vehicles plate number: ");
                   String number = input.next();
                   System.out.println("Enter load amount:");
                   double amount = input.nextDouble();
                   currentUser.Load(amount,number);
                }
            }
            if(option == 8){
                if(vp.getRentedVehicles().isEmpty())
                    System.out.println("No vehicle is available");
                else{
                  System.out.println("List of rented vehicles: ");
                  for(Vehicle x : vp.getRentedVehicles())
                      System.out.println(x+"\n");
                  System.out.println("Enter vehicle's plate number you wish to return: ");
                  String p = input.next();
                  currentUser.dropV(p);
                }
            }
            if(option == 9)
                break;
        }
    }
    public static void menu5(){
        while (true) {
            System.out.println("1)Display all vehicles" + "\n2)Display available vehicles" + "\n3)Add new vehicle" +
                    "\n4)Remove vehicle" + "\n5)Reports" + "\n6)Quit");
            System.out.println("Choose an option: ");
            int option = input.nextInt();
            if(option == 1)
                currentAdmin.display();
            if(option == 2){
                System.out.println("Enter starting date: ");
                int[] Date = new int[3];
                for(int i = 0; i<Date.length; i++){
                    Date[i] = input.nextInt();
                }
                LocalDate Date1 = LocalDate.of(Date[0],Date[1],Date[2]);
                System.out.println("Enter ending date: ");
                int[] date = new int[3];
                for(int i = 0; i<date.length; i++){
                    date[i] = input.nextInt();
                }
                LocalDate Date2 = LocalDate.of(date[0],date[1],date[2]);
                currentAdmin.displayAvailable(Date1,Date2);
            }
            if (option == 3) {
                 currentAdmin.addV();
            }
            if(option == 4) {
                if(vp.getTheVehicles().isEmpty())
                    System.out.println("No vehicle is available");
                else{
                  vp.displayVehicles();
                  System.out.println("Enter vehicle's plate number to be removed: ");
                  String plate = input.next();
                  currentAdmin.removeV(plate);
                }
            }
            if(option == 5) {
                try {
                    currentAdmin.getDailyReport();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(option == 6)
                break;
        }
    }
    public static void option3(){
        System.out.println("Choose user:"+"\n1)Register User "+"\n2)Admin");
        int option = input.nextInt();
            System.out.println("Enter name:");
            String name = input.next();
            System.out.println("Enter address: ");
            String address = input.next();
            System.out.println("Enter contact no:");
            int num = input.nextInt();
        if(option == 1){
            currentUser = new RegisteredUser(name,address,num,vp);             
        }else{
            System.out.println("Enter username:");
            String n = input.next();
            currentAdmin = new Admin(name,num,address,vp,n);            
        }       
    }    
}
