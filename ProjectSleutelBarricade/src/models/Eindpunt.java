package models;

import game.SpelBord;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Version: 1.0
 * @author James
 */
public class Eindpunt extends GameObject {

    public Eindpunt(Coordinaten coordinaten) {
        super(coordinaten);
        setAfbeelding(new ImageIcon(getWorkingDir() + "\\projectImg\\" + "Eindpunt.png"));
    }

    @Override
    public void collision(Speler speler) {
        // display popup with message
        JOptionPane.showMessageDialog(SpelBord.getFrame(), "Je hebt gewonnen!");
    }
    
}
