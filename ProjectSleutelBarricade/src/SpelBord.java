
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import models.Coordinaten;
import models.Speler;
import models.Veld;
import models.Veld.TYPE;

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
    
    //These are for test purpose change it later.
    private static ArrayList<Veld> velden;
    private static Speler mySpeler;
    
    public static void main(String[] args){
        
        // Setting up window
        JFrame frame = new JFrame("Sleutelbarricade");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        
        // Creating and preparing drawable component
        Board board = new Board();
        
        GridLayout grid = new GridLayout(ROWS, COLUMNS);
        board.setLayout(grid);
        initVeld();
        
        for(Veld label : velden){
                board.add(label,
                          label.getMyCoordinaten().getX(), 
                          label.getMyCoordinaten().getY()
                );
        }

        board.setBackground(Color.WHITE);

        // Adding component to window and pack
        frame.add(board);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
        
        setListener(frame, board);
    }
    
    private static void setListener(JFrame frame, Board board){
        frame.addKeyListener(new KeyListener() {
            
            String direction = mySpeler.getDirection();
            int playerX;
            int playerY;
            Veld old;
            
            @Override
            public void keyPressed(KeyEvent e) {
                
                playerX = mySpeler.getX();
                playerY = mySpeler.getY();
                int code = e.getKeyCode();
                boolean outOfZone = false;
                if(code == KeyEvent.VK_UP){
                    System.out.println("pressed up");
                    direction = "UP";
                    if(playerX != 9)playerX++;
                    else outOfZone = true;
                }
                if(code == KeyEvent.VK_DOWN){
                    System.out.println("pressed down");
                    direction = "DOWN";
                    if(playerX != 0)playerX--;
                    else outOfZone = true;
                }
                if(code == KeyEvent.VK_LEFT){
                    System.out.println("pressed left");
                    direction = "LEFT";
                    if(playerY != 0)playerY--;
                    else outOfZone = true;
                }
                if(code == KeyEvent.VK_RIGHT){
                    System.out.println("pressed right");
                    direction = "RIGHT";
                    if(playerY != 9)playerY++;
                    else outOfZone = true;
                }
                
                for(Veld item : velden){
                    if(item.isThereAPlayer()){
                        old = item;
                    }   
                }
                
                for(Veld item : velden){
                    mySpeler.setDirection(direction);
                    if(playerY == item.getMyCoordinaten().getY() && playerX == item.getMyCoordinaten().getX() && !outOfZone){
                        if(item.getBevat() == TYPE.BARRICADE){
                            old.setSpeler(mySpeler);
                        }
                        else{
                            mySpeler.setX(playerX);
                            mySpeler.setY(playerY);
                            item.setSpeler(mySpeler);
                            old.setSpeler(null);
                            System.out.println(item.getMyCoordinaten().getY() + " " + item.getMyCoordinaten().getX());
                            System.out.println(mySpeler.getY() + " " + mySpeler.getX());                            
                        }
                    }   
                }    
                
                board.removeAll();
                for(Veld label : velden){
                        setIcon(label);
                        board.add(label,
                                  label.getMyCoordinaten().getX(), 
                                  label.getMyCoordinaten().getY()
                        );
                }
                
                board.revalidate();
                board.repaint();               
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
    
    private static void initVeld(){
        //For test purpose
        Random rand = new Random();
        velden = new ArrayList<>();
        mySpeler = new Speler(0,0);
        // Filling up rows and columns
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                Coordinaten myCoordinaten = new Coordinaten(i,j);
                Veld label = new Veld(myCoordinaten);
                
                if((i + 1) == ROWS && (j + 1) == COLUMNS){
                    label.setBevat(TYPE.EINDPUNT);
                }
                else if(i == mySpeler.getY() && j == mySpeler.getX()){
                    label.setSpeler(mySpeler);
                }
                else{
                    int n = rand.nextInt(50) + 1;
                    if(n % 2 == 0){
                        label.setBevat(TYPE.MUUR);
                    }
                    else{
                        label.setBevat(TYPE.BARRICADE);
                    }
                }
                 
                setIcon(label);
                
                velden.add(label); 
            }
        }
    }
    
    private static void setIcon(Veld label){
                ImageIcon icon = label.getAfbeelding();
                
                Image image = icon.getImage();
                Image newimg = image.getScaledInstance(65, 50,  java.awt.Image.SCALE_SMOOTH);
                icon = new ImageIcon(newimg);
                
                label.setIcon(icon);
    }
}
