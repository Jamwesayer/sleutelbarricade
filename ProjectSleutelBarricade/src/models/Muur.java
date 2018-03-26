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
public class Muur extends GameObject {
    public Muur(){
        System.out.println("ITS A WALL!!!");
    }

    @Override
    public void collision() {
        System.out.println("DON'T WALK INTO THE DAMN WALLS!");
    }
    
}
