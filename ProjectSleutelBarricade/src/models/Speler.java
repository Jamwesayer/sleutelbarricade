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
public class Speler extends Veld {
    private Sleutel broekzak;
    private String direction;

    public Speler(Coordinaten coordinaten){
        super(coordinaten);
        this.direction = "DOWN";
        setAfbeelding(new ImageIcon(getWorkingDir() + "\\projectImg\\" + "Figure_Down.png"));
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
        setAfbeelding(new ImageIcon(getWorkingDir() + "\\projectImg\\" + "Figure_"+direction+".png"));
    }
    
    public void bewegen(String direction){
        //String parameter erbij.
        this.direction = direction;
    }
    
    public void sleutelGebruiken(){
        
    }
    
    public Sleutel getBroekzak() {
        return broekzak;
    }

    public void setBroekzak(Sleutel broekzak) {
        this.broekzak = broekzak;
    }

    public void moveSpeler(String direction){
        setDirection(direction);
    }    

}
