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
    private int x;
    private int y;

    public Speler(int x, int y){
        this.x = x;
        this.y = y;
        this.direction = "DOWN";        
    }
    
    public Speler(){
        this.x = 0;
        this.y = 0;
        this.direction = "DOWN";        
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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
