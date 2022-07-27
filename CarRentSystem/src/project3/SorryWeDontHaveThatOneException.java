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
public class SorryWeDontHaveThatOneException extends Exception{
     
    public SorryWeDontHaveThatOneException(){
        super("No Available Vehicles at given dates! ");
    }
    
    
}
