
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
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
                //JLabel label = new JLabel((ROWS - i) - 1 + " " + j);
                JLabel label = new JLabel();
                String workingDir = System.getProperty("user.dir");     
                ImageIcon icon;
                
                if((i + 1) == ROWS && (j + 1) == COLUMNS){
                    icon = new ImageIcon(workingDir + "\\projectImg\\" + "Eindpunt.png");                 
                }
                else if(i == 0 && j == 0){
                    icon = new ImageIcon(workingDir + "\\projectImg\\" + "Figure_Down.png");
                }
                else{
                    icon = new ImageIcon(workingDir + "\\projectImg\\" + "Muur.png");                    
                }
                
                Image image = icon.getImage();
                Image newimg = image.getScaledInstance(65, 50,  java.awt.Image.SCALE_SMOOTH);
                icon = new ImageIcon(newimg);
                
                label.setIcon(icon);
                board.add(label, i, j);
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
