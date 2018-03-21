
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import models.Coordinaten;
import models.Veld;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author James
 */
public class SpelBord {
    
    private final static int WIDTH = 640, HEIGHT = 480;
    private final static int ROWS = 10, COLUMNS = 10;
    
    public static void main(String[] args){
        
        // Setting up window
        JFrame frame = new JFrame("Sleutelbarricade");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        
        //For test purpose
        Random rand = new Random();
        
        // Creating and preparing drawable component
        Board board = new Board();
        
        GridLayout grid = new GridLayout(ROWS, COLUMNS);
        board.setLayout(grid);
        
        // Filling up rows and columns
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                Coordinaten myCoordinaten = new Coordinaten(i,j);
                Veld label = new Veld(myCoordinaten);
                
                if((i + 1) == ROWS && (j + 1) == COLUMNS){
                    label.setBevat("EINDPUNT");
                }
                else if(i == 0 && j == 0){
                    label.setBevat("SPELER");
                }
                else{
                    int n = rand.nextInt(50) + 1;
                    if(n % 2 == 0){
                        label.setBevat("MUUR");
                    }
                    else{
                        label.setBevat("BARRICADE");
                    }
                    
                }
                 
                ImageIcon icon = label.getAfbeelding();
                
                Image image = icon.getImage();
                Image newimg = image.getScaledInstance(65, 50,  java.awt.Image.SCALE_SMOOTH);
                icon = new ImageIcon(newimg);
                
                label.setIcon(icon);
                //board.add(label, i, j);
                board.add(label, 
                          label.getMyCoordinaten().getX(), 
                          label.getMyCoordinaten().getY()
                );
            }
        }

        board.setBackground(Color.WHITE);

        // Adding component to window and pack
        frame.add(board);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if(code == KeyEvent.VK_UP){
                    System.out.println("pressed up");
                }
                if(code == KeyEvent.VK_DOWN){
                    System.out.println("pressed down");
                }
                if(code == KeyEvent.VK_LEFT){
                    System.out.println("pressed left");
                }
                if(code == KeyEvent.VK_RIGHT){
                    System.out.println("pressed right");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
        });
        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false);
        
    }
    
}
