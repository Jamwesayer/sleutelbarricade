/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author J_Administrator
 */
public class Veld extends JLabel{
    private Coordinaten myCoordinaten;
    //private insert enum hier van Muur, Barricade en Sleutel
    public static enum TYPE {
        MUUR,
        BARRICADE,
        SLEUTEL,
        EINDPUNT,
        OTHER
    };
    private TYPE bevat = TYPE.OTHER;
//    private String bevat;
    private Speler speler = null;
    private int labelWidth;
    private int labelHeight;
    private ImageIcon afbeelding;
    
    public Veld(Coordinaten coordinaten, TYPE bevat){      
        this.myCoordinaten = coordinaten;
        this.bevat = bevat;
    }
    
    public Veld(Coordinaten coordinaten){
        this.myCoordinaten = coordinaten;
        this.bevat = TYPE.OTHER;
    }    
    
    public void moveSpeler(String direction){
        speler.setDirection(direction);
    }
    
    public TYPE getBevat() {
        return bevat;
    }

    public void setBevat(TYPE bevat) {
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
        String afbeeldingNaam;
        if(this.isThereAPlayer()){
            afbeeldingNaam = "Figure_" + speler.getDirection();
        }
        else{
            switch(bevat){
                case MUUR:
                    afbeeldingNaam = "Muur";
                    break;
                case BARRICADE:
                    afbeeldingNaam = "Barricade";
                    break;
                case SLEUTEL:
                    afbeeldingNaam = "Sleutel";
                    break;
                case EINDPUNT:
                    afbeeldingNaam = "Eindpunt";
                    break;                
                default:
                    afbeeldingNaam = "Barricade_Open";
                    this.afbeelding = new ImageIcon(workingDir + "\\projectImg\\" + "Barricade_Open.png");
                    break;
            }
        }
        this.afbeelding = new ImageIcon(workingDir + "\\projectImg\\" + afbeeldingNaam + ".png");
        return afbeelding;
    }

    public void setAfbeelding(ImageIcon afbeelding) {
        this.afbeelding = afbeelding;
    }

    public boolean isThereAPlayer(){
        return this.getSpeler() != null;
    }
    
    public Speler getSpeler() {
        return speler;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }
    
}
