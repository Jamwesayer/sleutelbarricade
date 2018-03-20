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
    private int labelWidth;
    private int labelHeight;
    private ImageIcon afbeelding;
    
    public Veld(){
        
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
        return afbeelding;
    }

    public void setAfbeelding(ImageIcon afbeelding) {
        this.afbeelding = afbeelding;
    }    
    
}
