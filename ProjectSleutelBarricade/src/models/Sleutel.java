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
public class Sleutel {
    private int pin;
    private boolean opgepakt;
    
    public Sleutel(int pin){
        this.pin = pin;
        this.opgepakt = false;
    }
    
    public void verdwijn(){
        opgepakt = true;
    }
    
    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public boolean isOpgepakt() {
        return opgepakt;
    }

    public void setOpgepakt(boolean opgepakt) {
        this.opgepakt = opgepakt;
    }    
    
}
