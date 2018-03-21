
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author J_Administrator
 */
public class Board extends JPanel implements KeyListener {

    public Board(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP){
            
        }
        if(code == KeyEvent.VK_DOWN){
            
        }
        if(code == KeyEvent.VK_LEFT){
            
        }
        if(code == KeyEvent.VK_RIGHT){
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
