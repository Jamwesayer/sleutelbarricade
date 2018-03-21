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
public class Speler {
    private Sleutel broekzak;
    private String direction;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    
    public Speler(){
        this.direction = "DOWN";
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
    
}
