/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import game.SpelBord;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Version: 1.0
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
        setAfbeelding(new ImageIcon(getWorkingDir() + "\\projectImg\\" + "Barricade_Open.png"));
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
    public void collision(Speler speler) {
        // if current barricade is open get out
        if(this.isOpen)return;
        
        // if player does have a key check i pin equals current barricade pin else show popup message 
        if(speler.getBroekzak() != null){
            if(speler.getBroekzak().getPin() == pin)
                openBarricade();
            else JOptionPane.showMessageDialog(SpelBord.getFrame(), "De sleutel heeft niet de zelfde pin");
        }
    }
    
}
