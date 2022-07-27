/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

/**
 *
 * @author Rawan
 */
public class OverWeightException extends Exception{
    
    public OverWeightException(){
       super("Weight exceeds the loading capacity of the vehicle");

    }
    
}
