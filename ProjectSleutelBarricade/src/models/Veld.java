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
        String afbeeldingNaam = "";
        switch(this.bevat){
            case "SPELER":
                afbeeldingNaam = "Figure_" + speler.getDirection();
                break;
            case "MUUR":
                afbeeldingNaam = "Muur";
                break;
            case "BARRICADE":
                afbeeldingNaam = "Barricade";
                break;
            case "SLEUTEL":
                afbeeldingNaam = "Sleutel";
                break;
            case "EINDPUNT":
                afbeeldingNaam = "Eindpunt";
                break;                
            default:
                afbeeldingNaam = "Barricade_Open";
                this.afbeelding = new ImageIcon(workingDir + "\\projectImg\\" + "Barricade_Open.png");
                break;
        }
        this.afbeelding = new ImageIcon(workingDir + "\\projectImg\\" + afbeeldingNaam + ".png");
        return afbeelding;
    }

    public void setAfbeelding(ImageIcon afbeelding) {
        this.afbeelding = afbeelding;
    }    
    
}
