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
    private int labelWidth;
    private int labelHeight;
    private ImageIcon afbeelding;
    private Speler speler;
    
    private String workingDir = System.getProperty("user.dir");
    
    public Veld(Coordinaten coordinaten){
        this.myCoordinaten = coordinaten;        
        if("MAC OS X".equals(System.getProperty("os.name"))) workingDir = System.getProperty("user.home");
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
        if(speler != null){
            return speler.getAfbeelding();
        }
        return afbeelding;
    }

    public void setAfbeelding(ImageIcon afbeelding) {
        this.afbeelding = afbeelding;
    }

    public String getWorkingDir() {
        return workingDir;
    } 
    
    public Speler getSpeler() {
        return speler;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }
        
}
