/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerhands;

/**
 *
 * @author james
 */
public class player {
    private String name;
    private hand cards;
    public player(String input){
        String [] nameAndCardsString = input.split(": ");
        this.name = nameAndCardsString[0];
        this.cards = new hand(nameAndCardsString[1]);
    }
    public String getName(){
        return this.name;
    }
    public hand getHand(){
        return this.cards;
    }
}
