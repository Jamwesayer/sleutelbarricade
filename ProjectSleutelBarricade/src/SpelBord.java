
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        
        // Creating and preparing drawable component
        JPanel board = new JPanel();
        GridLayout grid = new GridLayout(ROWS, COLUMNS);
        board.setLayout(grid);
        
        // Filling up rows and columns
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                board.add(new JLabel((ROWS - i) - 1 + " " + j), i, j);
            }
        }

        board.setBackground(Color.WHITE);

        // Adding component to window and pack
        frame.add(board);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
}
