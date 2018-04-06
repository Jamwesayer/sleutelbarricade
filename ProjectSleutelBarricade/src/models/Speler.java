/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import game.SpelBord;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 * Version: 1.0
 * @author J_Administrator
 */
public class Speler extends Veld implements KeyListener {
    
    private Sleutel broekzak;
    private String direction;
    private Random random = new Random();

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
    
    // Used to change direction of image according to location
    public void bewegen(String direction){
        this.direction = direction;
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
    public void keyPressed(KeyEvent e) {

        int playerX = getMyCoordinaten().getX();
        int playerY = getMyCoordinaten().getY();

        int oldPlayerX = playerX;
        int oldPlayerY = playerY;
        
        // check if user is out of bounds
        boolean outOfZone = false;

        int code = e.getKeyCode();
        switch(code){
            case KeyEvent.VK_UP:
                direction = "UP";
                if(playerX < 9)playerX++;
                else outOfZone = true;
                break;
            case KeyEvent.VK_DOWN:
                direction = "DOWN";
                if(playerX > 0)playerX--;
                else outOfZone = true;
                break;
            case KeyEvent.VK_LEFT:
                direction = "LEFT";
                if(playerY > 0)playerY--;
                else outOfZone = true;
                break;
            case KeyEvent.VK_RIGHT:
                direction = "RIGHT";
                if(playerY < 9)playerY++;
                else outOfZone = true;
                break;
        }

        // current location of player
        Veld oldVeld = SpelBord.getVelden()[oldPlayerX][oldPlayerY];
        
        // possible new location of player
        Veld newVeld = SpelBord.getVelden()[playerX][playerY];

        // check fir possible collisions with objects
        if(newVeld instanceof GameObject){
            GameObject gameObject = (GameObject)newVeld;
            gameObject.collision(this);
        }

        if(newVeld instanceof Sleutel){
            Coordinaten coordinaten = new Coordinaten(playerX,playerY);
            Veld veldfdsa = new Veld(coordinaten);
            SpelBord.getVelden()[playerX][playerY] = veldfdsa;
            newVeld = SpelBord.getVelden()[playerX][playerY];                    
        }

        // check if player can go through
        if(outOfZone || newVeld instanceof Muur)return;
        if(newVeld instanceof Barricade && !((Barricade)newVeld).isIsOpen())return;

        getMyCoordinaten().setX(playerX);
        getMyCoordinaten().setY(playerY);
        setDirection(direction);
        
        // set player to new field
        oldVeld.setSpeler(null);
        newVeld.setSpeler(this);

        SpelBord.getBoard().removeAll();
        //Fill the panel
        for(int i = 0; i < SpelBord.getROWS(); i++){
            for(int j = 0; j < SpelBord.getCOLUMNS(); j++){
                Veld veld = SpelBord.getVelden()[i][j];
                if(!(veld instanceof GameObject) && !(veld instanceof Speler)){
                    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                    veld.setBorder(border);
                }                     
                SpelBord.setIcon(veld);
                SpelBord.getBoard().add(veld,
                          veld.getMyCoordinaten().getX(), 
                          veld.getMyCoordinaten().getY()
                );
            }
        }
        SpelBord.getBoard().revalidate();
        SpelBord.getBoard().repaint();   
        
        // after certain moves randomize field
        if(random.nextInt(5) == 1)
            SpelBord.setupField();
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

}
