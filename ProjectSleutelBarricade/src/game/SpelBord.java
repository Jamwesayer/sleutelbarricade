package game;



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
import javax.swing.JPanel;
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
//    private static ArrayList<Veld> velden = new ArrayList<>();
    private static Veld[][] velden = new Veld[ROWS][COLUMNS];
    private static Speler mySpeler;
    private static JPanel board;
    private static JFrame frame;
    
    //Main Method
    public static void main(String[] args){
        // Setting up window
        frame = new JFrame("Sleutelbarricade");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        
        // Creating and preparing panel
        board = new JPanel();
        
        //Prepare GridLayout
        GridLayout grid = new GridLayout(ROWS, COLUMNS);
        board.setLayout(grid);
        initVeld();  
        
        //Fill the panel
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                Veld veld = velden[i][j];
                Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                veld.setBorder(border);
                if(veld instanceof Sleutel || veld instanceof Barricade){
                    veld.setLayout(new FlowLayout(FlowLayout.CENTER));
                    //No static number, later randomize better
                    JLabel l = new JLabel((veld instanceof Sleutel) ? String.valueOf(((Sleutel) veld).getPin()) : String.valueOf(((Barricade) veld).getPin()));
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
    private static void setListener(JFrame frame, JPanel board){
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
                
                Veld oldVeld = velden[oldPlayerX][oldPlayerY];
                Veld newVeld = velden[playerX][playerY];
                
                if(newVeld instanceof GameObject){
                    GameObject gameObject = (GameObject)newVeld;
                    gameObject.collision(mySpeler);
                }

                if(newVeld instanceof Sleutel){
                    Coordinaten coordinaten = new Coordinaten(playerX,playerY);
                    Veld veldfdsa = new Veld(coordinaten);
                    velden[playerX][playerY] = veldfdsa;
                    newVeld = velden[playerX][playerY];                    
                }
                
//              if(newVeld instanceof Muur)return;
                if(newVeld instanceof Barricade && !((Barricade)newVeld).isIsOpen())return;

                mySpeler.getMyCoordinaten().setX(playerX);
                mySpeler.getMyCoordinaten().setY(playerY);
                mySpeler.setDirection(direction);

                oldVeld.setSpeler(null);
                newVeld.setSpeler(mySpeler);
                                
                board.removeAll();
                //Fill the panel
                for(int i = 0; i < ROWS; i++){
                    for(int j = 0; j < COLUMNS; j++){
                        Veld veld = velden[i][j];
                        if(!(veld instanceof GameObject) && !(veld instanceof Speler)){
                            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                            veld.setBorder(border);
                        }                     
                        setIcon(veld);
                        board.add(veld,
                                  veld.getMyCoordinaten().getX(), 
                                  veld.getMyCoordinaten().getY()
                        );
                    }
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
    
    //Maakt de veld in een de array veld
    private static void initVeld(){
        //Standard Vars to use
        Random rand = new Random();
        mySpeler = new Speler(new Coordinaten(0, 0));

        ArrayList<Integer> codes = new ArrayList();
        for(int i = 0; i < 4; i++){
            codes.add(rand.nextInt(9999));
        }
        
        // Filling up rows and columns
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                Coordinaten coordinaten = new Coordinaten(i,j);
                Veld veld = new Veld(coordinaten);
                
                //Eindpunt
                if((i + 1) == ROWS && (j + 1) == COLUMNS){
                    veld = new Eindpunt(coordinaten);
                }
//                Speler Positie
                else if(i == mySpeler.getY() && j == mySpeler.getX()){
                    veld.setSpeler(mySpeler);
                }
                //Sleutel Posities
                else if(i == 4 && j == 4 || i == 0 && j == 9  || i == 6 && j == 6){
                    veld = new Sleutel(coordinaten, codes.get(rand.nextInt(4)));
                    //Experiment                    
//                    object = new Sleutel(500);
//                    System.out.println(object.getClass());
                }
                //Randomizer voor MUUR, LOOPVELD, BARRICADE
                else{
                    int n = rand.nextInt(50) + 1;
                    if(n % 3 == 0){
                        veld = new Muur(coordinaten);
                    }
                    else if(n % 2 != 0){
                        veld = new Barricade(coordinaten, codes.get(rand.nextInt(4)));
                    }
                }
                setIcon(veld);
                velden[i][j] = veld;
            }
        }

    }
    
    //Set the icon for the fields
    public static void setIcon(Veld veld){
        if(veld.getAfbeelding() == null){
            veld.setIcon(null);
            return;
        }
        ImageIcon icon = veld.getAfbeelding();

        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(65, 50,  java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        veld.setIcon(icon);
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static Veld[][] getVelden() {
        return velden;
    }

    public static JPanel getBoard() {
        return board;
    }

    public static int getCOLUMNS() {
        return COLUMNS;
    }

    public static int getROWS() {
        return ROWS;
    }
    
}
