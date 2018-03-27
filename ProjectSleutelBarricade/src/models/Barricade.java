/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.swing.ImageIcon;

/**
 *
 * @author J_Administrator
 */
public class Barricade extends GameObject {
    private int pin;
    private boolean isOpen;
    
    public Barricade(Coordinaten coordinaten, int pin){
        super(coordinaten);
        this.pin = pin;
        this.isOpen = false;
        setAfbeelding(new ImageIcon(getWorkingDir() + "\\projectImg\\" + "Barricade.png"));
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

    @Override
    public void collision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
