/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

/**
 *
 * @author J_Administrator
 */
public class Speler extends Veld implements KeyListener {
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch(code){
            case KeyEvent.VK_UP:
                System.out.println("pressed up");
                direction = "UP";
                if(getMyCoordinaten().getY() != 9)getMyCoordinaten().setY(getMyCoordinaten().getY() + 1);
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("pressed down");
                direction = "DOWN";
                if(getMyCoordinaten().getY() != 0)getMyCoordinaten().setY(getMyCoordinaten().getY() - 1);
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("pressed left");
                direction = "LEFT";
                if(getMyCoordinaten().getX() != 0)getMyCoordinaten().setX(getMyCoordinaten().getX() - 1);
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("pressed right");
                direction = "RIGHT";
                if(getMyCoordinaten().getX() != 9)getMyCoordinaten().setX(getMyCoordinaten().getY() + 1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
