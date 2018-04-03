package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
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
    private static boolean gameFailed = false;
    
    private static int totalSeconds = 60 * 5;
    
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
        JPanel topBar = new JPanel();
        JLabel time = new JLabel("5:00");
        topBar.add(time);
        JButton restart = new JButton("RESTART");
        restart.addActionListener((ActionEvent e) -> {
            if(!gameFailed){
                time.setText("5:00");
            }
            gameFailed = true;
            totalSeconds = 60 * 5;
            setupField();
            setListener(frame);
        });
        topBar.add(restart);
        topBar.setMaximumSize(new Dimension(WIDTH, 20));
        
        setupField();
        
        //Prepare GridLayout
        GridLayout grid = new GridLayout(ROWS, COLUMNS);
        board.setLayout(grid);
        
        board.setBackground(Color.WHITE);

        frame.setLayout(new BorderLayout());
        frame.add(topBar, BorderLayout.PAGE_START);
        // Adding JPanel to the JFrame
        frame.add(board, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        setListener(frame);
        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalSeconds--;
                int minutes = (totalSeconds % 3600) / 60;
                int seconds = totalSeconds % 60;
                time.setText(String.format("%2d:%02d", minutes, seconds));
                if(totalSeconds == 0){
                    Object[] options = {"OK"};
                    int input = JOptionPane.showOptionDialog(frame, "You lose sucker!", "You lost", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if(input == JOptionPane.OK_OPTION){
                        gameFailed = true;
                        restart.doClick();
                    }
                }
            }
        });
        timer.start();
    }
    
    //Initialize the listener for keys inputs
    private static void setListener(JFrame frame){
        
        frame.removeKeyListener(mySpeler);
        frame.addKeyListener(mySpeler);
        
        frame.requestFocus();
        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false); 
               
    }
    
    //Maakt de veld in een de array veld
    private static void initVeld(){
        //Standard Vars to use
        Random rand = new Random();
    
        if(mySpeler == null){
            mySpeler = new Speler(new Coordinaten(0, 0));
        } else {
            if(gameFailed){
                mySpeler.getMyCoordinaten().setX(0);
                mySpeler.getMyCoordinaten().setY(0);
            } else {
                mySpeler.getMyCoordinaten().setX(mySpeler.getMyCoordinaten().getX());
                mySpeler.getMyCoordinaten().setY(mySpeler.getMyCoordinaten().getY());
            }
        }
        
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
                else if(j == mySpeler.getMyCoordinaten().getY() && i == mySpeler.getMyCoordinaten().getX()){
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
        
        gameFailed = false;

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
    
    public static void setupField(){
        board.removeAll();
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
