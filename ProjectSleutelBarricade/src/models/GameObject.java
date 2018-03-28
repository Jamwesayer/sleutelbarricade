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
public abstract class GameObject extends Veld{
    
    public GameObject(Coordinaten coordinaten){
        super(coordinaten);
    }
    
    public abstract void collision(Speler speler);
}
