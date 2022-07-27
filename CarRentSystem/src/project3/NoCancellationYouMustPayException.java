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
public class NoCancellationYouMustPayException extends Exception{
    
    public NoCancellationYouMustPayException(){
        super("Cancellation date exceeds the starting date of rental. You have to pay!");
    }
    
}
