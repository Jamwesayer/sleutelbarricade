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
public class Sleutel extends GameObject {
    private int pin;
    private boolean opgepakt;
    
    public Sleutel(Coordinaten coordinaten, int pin){
        super(coordinaten);
        this.pin = pin;
        this.opgepakt = false;
        setAfbeelding(new ImageIcon(getWorkingDir() + "\\projectImg\\" + "Sleutel.png"));
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

    @Override
    public void collision(Speler speler) {
        if(speler.getBroekzak() != null || opgepakt)return;
        opgepakt = true;
        
        speler.setBroekzak(this);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
