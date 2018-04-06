/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import models.Barricade;
import models.Coordinaten;
import models.Sleutel;
import models.Speler;
import models.Veld;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author J_Administrator
 */
public class SpelBordTest {
    
    Coordinaten myCoordinaten;
    Sleutel rightKey;
    Sleutel wrongKey;
    Barricade myBarricade;
    Speler mySpeler;
    
    public SpelBordTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        myCoordinaten = new Coordinaten(0,0);
        rightKey = new Sleutel(myCoordinaten, 500);
        wrongKey = new Sleutel(myCoordinaten, 800);
        myBarricade = new Barricade(myCoordinaten, 500);
        mySpeler = new Speler(myCoordinaten);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class SpelBord.
     */
    
    @Test
    public void theKey() {
        mySpeler.setBroekzak(rightKey);
        
        int expectedResult = 500;
        int result = mySpeler.getBroekzak().getPin();
        
        assertEquals("Pin van sleutel",expectedResult,result);
        
    }
    
    
    @Test
    public void testWrongKey() {
        
        mySpeler.setBroekzak(wrongKey);
        myBarricade.collision(mySpeler);
        
        boolean expectedResult = false;
        boolean result = myBarricade.isIsOpen();
        
        assertEquals("verkeerde sleutel",expectedResult,result);
    }
    
    @Test
    public void testRightKey() {
        mySpeler.setBroekzak(rightKey);
        myBarricade.collision(mySpeler);
        
        boolean expectedResult = true;
        boolean result = myBarricade.isIsOpen();
        
        assertEquals("verkeerde sleutel",expectedResult,result);
    }
 
    @Test
    public void testDirection() {
        mySpeler.setDirection("UP");
        String directionExpected = "UP";
        String direction = mySpeler.getDirection();
        
        assertEquals("player direction", directionExpected, direction);
    }
    
}
