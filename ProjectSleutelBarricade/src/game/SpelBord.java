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

/**
 * Version: 1.0
 * Made by: James Bal, Jeffrey Lo-A-Foe, Burak Ergin
 */

public class SpelBord {
    
    private final static int WIDTH = 640, HEIGHT = 480;
    private final static int ROWS = 10, COLUMNS = 10;
    
    // 2D array to keep track of fields
    private static Veld[][] velden = new Veld[ROWS][COLUMNS];
    private static Speler mySpeler;
    private static JPanel board;
    private static JFrame frame;
    private static boolean gameFailed = false;
    
    private static int totalSeconds = 60 * 5;
    private static ArrayList<Integer> codes = new ArrayList();
    
    //Main Method
    public static void main(String[] args){
        
        // Setting up window
        frame = new JFrame("Sleutelbarricade");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        
        // Creating and preparing panel for game
        board = new JPanel();
        GridLayout grid = new GridLayout(ROWS, COLUMNS);
        board.setLayout(grid);
        board.setBackground(Color.WHITE);
        
        // Creating panel for topbar containing timer and restart button
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
        
        // Generate and load up field
        setupField();

        frame.setLayout(new BorderLayout());
        // Adding JPanel to the JFrame
        frame.add(topBar, BorderLayout.PAGE_START);
        frame.add(board, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // add key listener to frame to move player
        setListener(frame);
        
        // Create and start timer
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
    
    //Initialize the listener for key inputs
    private static void setListener(JFrame frame){
        
        frame.removeKeyListener(mySpeler);
        frame.addKeyListener(mySpeler);
        
        frame.requestFocus();
        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false); 
               
    }

    // Save values to 2d Array
    private static void initVeld(){
        
        //Standard Vars to use
        Random rand = new Random();
    
        if(mySpeler == null){
            mySpeler = new Speler(new Coordinaten(0, 0));
            codes.clear();
            for(int i = 0; i < 4; i++){
                codes.add(rand.nextInt(9999));
            }
        } else {
            if(gameFailed){
                mySpeler.getMyCoordinaten().setX(0);
                mySpeler.getMyCoordinaten().setY(0);
                codes.clear();
                for(int i = 0; i < 4; i++){
                    codes.add(rand.nextInt(9999));
                }
            } else {
                mySpeler.getMyCoordinaten().setX(mySpeler.getMyCoordinaten().getX());
                mySpeler.getMyCoordinaten().setY(mySpeler.getMyCoordinaten().getY());
            }
        }
        
        System.out.println(codes.size());
        
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
                }
                // Random muur or barricade
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
    
    // Load up field
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
