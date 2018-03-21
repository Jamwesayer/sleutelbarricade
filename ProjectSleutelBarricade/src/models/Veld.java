/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author J_Administrator
 */
public class Veld extends JLabel{
    private Coordinaten myCoordinaten;
    //private insert enum hier van Muur, Barricade en Sleutel
    private String bevat;
    private Speler speler = new Speler();
    private int labelWidth;
    private int labelHeight;
    private ImageIcon afbeelding;
    
    public Veld(Coordinaten coordinaten, String bevat){      
        this.myCoordinaten = coordinaten;
        this.bevat = bevat;
    }
    
    public Veld(Coordinaten coordinaten){
        this.myCoordinaten = coordinaten;
        this.bevat = "";
    }    
    
    public void moveSpeler(String direction){
        speler.setDirection(direction);
    }
    
    public String getBevat() {
        return bevat;
    }

    public void setBevat(String bevat) {
        this.bevat = bevat;
    }
    
    public Coordinaten getMyCoordinaten() {
        return myCoordinaten;
    }

    public void setMyCoordinaten(Coordinaten myCoordinaten) {
        this.myCoordinaten = myCoordinaten;
    }

    public int getLabelWidth() {
        return labelWidth;
    }

    public void setLabelWidth(int width) {
        this.labelWidth = width;
    }

    public int getLabelHeight() {
        return labelHeight;
    }

    public void setLabelHeight(int height) {
        this.labelHeight = height;
    }

    public ImageIcon getAfbeelding() {
        String workingDir = System.getProperty("user.dir");
        switch(this.bevat){
            case "SPELER":
                this.afbeelding = new ImageIcon(workingDir + "\\projectImg\\" + "Figure_" + speler.getDirection() + ".png");
                break;
            case "MUUR":
                this.afbeelding = new ImageIcon(workingDir + "\\projectImg\\" + "Muur.png");
                break;
            case "BARRICADE":
                this.afbeelding = new ImageIcon(workingDir + "\\projectImg\\" + "Barricade.png");                
                break;
            case "SLEUTEL":
                this.afbeelding = new ImageIcon(workingDir + "\\projectImg\\" + "Sleutel.png");
                break;
            case "EINDPUNT":
                this.afbeelding = new ImageIcon(workingDir + "\\projectImg\\" + "Eindpunt.png");
                break;                
            default:
                this.afbeelding = new ImageIcon(workingDir + "\\projectImg\\" + "Barricade_Open.png");
                break;
        }
        
        return afbeelding;
    }

    public void setAfbeelding(ImageIcon afbeelding) {
        this.afbeelding = afbeelding;
    }    
    
}
