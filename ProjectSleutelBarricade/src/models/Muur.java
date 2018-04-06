/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.swing.ImageIcon;

/**
 * Version: 1.0
 * @author J_Administrator
 */
public class Muur extends GameObject {

    public Muur(Coordinaten coordinaten) {
        super(coordinaten);
        setAfbeelding(new ImageIcon(getWorkingDir() + "\\projectImg\\" + "Muur.png"));
    }
    
    @Override
    public void collision(Speler speler) {
        System.out.println("DON'T WALK INTO THE DAMN WALLS!");
    }
    
}
