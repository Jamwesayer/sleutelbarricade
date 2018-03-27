
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import models.*;

import models.Coordinaten;
import models.Sleutel;
import models.Speler;
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
    
    //These are for test purpose change it later.
    private static ArrayList<Veld> velden = new ArrayList<>();
    private static Speler mySpeler;
    
    //Main Method
    public static void main(String[] args){
        // Setting up window
        JFrame frame = new JFrame("Sleutelbarricade");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        
        // Creating and preparing panel
        Board board = new Board();
        
        //Prepare GridLayout
        GridLayout grid = new GridLayout(ROWS, COLUMNS);
        board.setLayout(grid);
        initVeld();  
        
        //Fill the panel
        for(Veld veld : velden){
                Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                veld.setBorder(border);
                if(veld instanceof Sleutel){
                    veld.setLayout(new FlowLayout(FlowLayout.CENTER));
                    //No static number, later randomize better
                    JLabel l = new JLabel("200");
                    l.setForeground(Color.red);
                    l.setFont(new Font("Serif", Font.BOLD, 20));
                    veld.add(l);
                }
                //Add to the panel
                board.add(veld,
                          veld.getMyCoordinaten().getX(), 
                          veld.getMyCoordinaten().getY()
                );
        }
        board.setBackground(Color.WHITE);

        // Adding JPanel to the JFrame
        frame.add(board);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
        
        setListener(frame, board);
    }
    
    //Initialize the listener for keys inputs
    private static void setListener(JFrame frame, Board board){
//        frame.addKeyListener(mySpeler);
        frame.addKeyListener(new KeyListener() {
            
            //Variables
            String direction = mySpeler.getDirection();
            int playerX;
            int playerY;
            Veld oldVeld;
            
            @Override
            public void keyPressed(KeyEvent e) {
                
                playerX = mySpeler.getMyCoordinaten().getX();
                playerY = mySpeler.getMyCoordinaten().getY();
                
                int oldPlayerX = playerX;
                int oldPlayerY = playerY;
                
                int code = e.getKeyCode();
                boolean outOfZone = false;
                switch(code){
                    case KeyEvent.VK_UP:
                        System.out.println("pressed up");
                        direction = "UP";
                        if(playerX < 9)playerX++;
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("pressed down");
                        direction = "DOWN";
                        if(playerX > 0)playerX--;
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("pressed left");
                        direction = "LEFT";
                        if(playerY > 0)playerY--;
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("pressed right");
                        direction = "RIGHT";
                        if(playerY < 9)playerY++;
                        break;
                }
                
                for(Veld veld : velden){
                    if(veld.getMyCoordinaten().getX() == playerX &&
                            veld.getMyCoordinaten().getY() == playerY){
//                        if(veld instanceof Muur)return;
//                        if(veld instanceof Barricade)return;

                            mySpeler.getMyCoordinaten().setX(playerX);
                            mySpeler.getMyCoordinaten().setY(playerY);
     
                    }
                }
                
                
                
//                for(Veld veld : velden){
//                    
//                    if(veld instanceof Speler){
//                        oldVeld = veld;
//                        veld.getMyCoordinaten().setX(playerX);
//                        veld.getMyCoordinaten().setY(playerY);
//                            
//                            
//                            
//                            if(mySpeler.getBroekzak() != null){
//                                System.out.println(mySpeler.getBroekzak().getPin());                                
//                            }
//                            else{
//                                if(veld instanceof Sleutel){
//                                    mySpeler.setBroekzak((Sleutel)veld);
//                                    veld = new Veld(new Coordinaten(playerX, playerY));
//                                    //Dit is voor test bedoeld moet verbeterd worden.
//                                }                                   
//                            }
//                            if(veld instanceof Eindpunt){
//                                System.out.println("GEFELICITEERD!!!");
//                            }
//                    }
////                    if(playerY == veld.getMyCoordinaten().getY() && playerX == veld.getMyCoordinaten().getX() && !outOfZone){
////                        if(veld instanceof Barricade){
//////                            old.setSpeler(mySpeler);
////                        }
////                        else{
////                            mySpeler.getMyCoordinaten().setX(playerX);
////                            mySpeler.getMyCoordinaten().setY(playerY);
//////                            old.setSpeler(null);
////                            veld = mySpeler;   
////                            if(mySpeler.getBroekzak() != null){
////                                System.out.println(mySpeler.getBroekzak().getPin());                                
////                            }
////                            else{
////                                if(veld instanceof Sleutel){
////                                    mySpeler.setBroekzak((Sleutel)veld);
////                                    veld = new Veld(new Coordinaten(playerX, playerY));
////                                    //Dit is voor test bedoeld moet verbeterd worden.
////                                }                                   
////                            }
////                            if(veld instanceof Eindpunt){
////                                System.out.println("GEFELICITEERD!!!");
////                            }
////                            System.out.println(mySpeler.getY() + " " + mySpeler.getX());                            
////                        }
////                    }   
//                }    
                board.removeAll();
                for(Veld veld : velden){
                        if(veld instanceof Muur){
                            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                            veld.setBorder(border);
                        }
                        setIcon(veld);
                        board.add(veld,
                                  veld.getMyCoordinaten().getX(), 
                                  veld.getMyCoordinaten().getY()
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
    
    //Maakt de veld in een ArrayList
    private static void initVeld(){
        //Standard Vars to use
        Random rand = new Random();
        mySpeler = new Speler(new Coordinaten(0, 0));

        
        // Filling up rows and columns
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                Coordinaten myCoordinaten = new Coordinaten(i,j);
                Veld veld = new Veld(myCoordinaten);
                
                //Eindpunt
                if((i + 1) == ROWS && (j + 1) == COLUMNS){
                    veld = new Eindpunt(myCoordinaten);
                }
//                Speler Positie
                else if(i == mySpeler.getY() && j == mySpeler.getX()){
                    veld = mySpeler;
                }
                //Sleutel Posities
                else if(i == 4 && j == 4 || i == 0 && j == 9  || i == 6 && j == 6){
                    veld = new Sleutel(myCoordinaten, 500);
                    //Experiment                    
//                    object = new Sleutel(500);
//                    System.out.println(object.getClass());
                }
                //Randomizer voor MUUR, LOOPVELD, BARRICADE
                else{
                    int n = rand.nextInt(50) + 1;
                    if(n % 3 == 0){
                        veld = new Muur(myCoordinaten);
                    }
                    else if(n % 2 != 0){
                        veld = new Barricade(myCoordinaten, 1234);
                    }
                }
                setIcon(veld);
                velden.add(veld);
            }
        }

    }
    
    //Set the icon for the fields
    private static void setIcon(Veld veld){
        if(veld.getAfbeelding() == null)return;
        ImageIcon icon = veld.getAfbeelding();

        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(65, 50,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        veld.setIcon(icon);
    }
}
