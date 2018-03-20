/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author J_Administrator
 */
public class Barricade {
    private int pin;
    private boolean isOpen;
    
    public Barricade(int pin){
        this.pin = pin;
        this.isOpen = false;
    }
    
    public void openBarricade(){
        this.isOpen = true;
        System.out.println("ACCES GRAAAAANTED!");
    }
    
    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public boolean isIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }    
    
}
