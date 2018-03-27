
import javax.swing.ImageIcon;
import models.Coordinaten;
import models.GameObject;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author James
 */
public class Eindpunt extends GameObject {

    public Eindpunt(Coordinaten coordinaten) {
        super(coordinaten);
        setAfbeelding(new ImageIcon(getWorkingDir() + "\\projectImg\\" + "Eindpunt.png"));
    }

    @Override
    public void collision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
