/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerhands;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author james
 */
public class checkerTest {
    
    private final String cardsWithoutAnything = "2H 3D 5S 9C KD";
    private final String cardsWithOnePair = "2H 2D 5S 9C KD";
    private final String cardsWithTwoPairs = "2H 4S 6C 2D 4H";
    private final String cardsWithThreeOfAKind = "2H 4S 4C 7D 4H";
    private final String cardsWithFourOfAKind = "2H 2S 2C 2D 4H";
    private final String cardsWithFullHouse = "2H 4S 4C 2D 4H";
    private final String cardsWithStraight = "6S 7H 8S 9C TD";
    private final String cardsWithFlush = "2S 4S 6S 7S 9S";
    private final String cardsWithStraightFlush = "6S 7S 8S 9S TS";
    
    public checkerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of highCard method, of class checker.
     */
    @Test
    public void testHighCard() {
        System.out.println("highCard");
        
        ArrayList<card> cards = new hand(this.cardsWithoutAnything).getCards();
        card expResult = new card("KD");
        card result = checker.highCard(cards);
        assertEquals(expResult.key, result.key);
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of flush method, of class checker.
     */
    @Test
    public void testFlush() {
        System.out.println("flush");
        
        card card1 = new card("2S");
        card card2 = new card("4S");
        card card3 = new card("6S");
        card card4 = new card("7S");
        card card5 = new card("9S");
        
        ArrayList<card> cards = new ArrayList<card>(5);
        
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        
        card expResult = new card("9S");
        card result = checker.flush(cards);
        assertEquals(expResult.key, result.key);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of straight method, of class checker.
     */
    @Test
    public void testStraight() {
        System.out.println("straight");

        card card1 = new card("6H");
        card card2 = new card("7S");
        card card3 = new card("8S");
        card card4 = new card("9S");
        card card5 = new card("TS");
        
        ArrayList<card> cards = new ArrayList<>(5);
        
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        
        card expResult = new card("TS");
        card result = checker.straight(cards);
        assertEquals(expResult.key, result.key);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of straightFlush method, of class checker.
     */
    @Test
    public void testStraightFlush() {
        System.out.println("straightFlush");
        
        card card1 = new card("6S");
        card card2 = new card("7S");
        card card3 = new card("8S");
        card card4 = new card("9S");
        card card5 = new card("TS");
        
        ArrayList<card> cards = new ArrayList<>(5);
        
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        
        card expResult = new card("TS");
        card result = checker.straightFlush(cards);
        assertEquals(expResult.key, result.key);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of twoPairs method, of class checker.
     */
    @Test
    public void testTwoPairs() {
        System.out.println("twoPairs");
        
        card card1 = new card("2H");
        card card2 = new card("4S");
        card card3 = new card("6C");
        card card4 = new card("2D");
        card card5 = new card("4H");
        
        ArrayList<card> cards = new ArrayList<>(5);
        
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        
        card[] expResult = new card[]{new card("4H"), new card("2D")};
        card[] result = checker.twoPairs(cards);
        assertEquals(expResult[0].value, result[0].value);
        assertEquals(expResult[1].value, result[1].value);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fullHouse method, of class checker.
     */
    @Test
    public void testFullHouse() {
        System.out.println("fullHouse");
        ArrayList<card> cards = new hand(this.cardsWithFullHouse).getCards();
        card[] expResult = new card[]{new card("4S"), new card("2H")};
        card[] result = checker.fullHouse(cards);
        assertEquals(expResult[0].value, result[0].value);
        assertEquals(expResult[1].value, result[1].value);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of onePair method, of class checker.
     */
    @Test
    public void testOnePair() {
        System.out.println("onePair");
        ArrayList<card> cards = new hand(this.cardsWithOnePair).getCards();
        card expResult = new card("2H");
        card result = checker.onePair(cards);
        assertEquals(expResult.value, result.value);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of threeOfAKind method, of class checker.
     */
    @Test
    public void testThreeOfAKind() {
        System.out.println("threeOfAKind");
        ArrayList<card> cards = new hand(this.cardsWithThreeOfAKind).getCards();
        card expResult = new card("4S");
        card result = checker.threeOfAKind(cards);
        assertEquals(expResult.value, result.value);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of fourOfAKind method, of class checker.
     */
    @Test
    public void testFourOfAKind() {
        System.out.println("fourOfAKind");
        ArrayList<card> cards = new hand(this.cardsWithFourOfAKind).getCards();
        card expResult = new card("2S");
        card result = checker.fourOfAKind(cards);
        assertEquals(expResult.value, result.value);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
