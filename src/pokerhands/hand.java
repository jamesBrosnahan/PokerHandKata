/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerhands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author james
 */
public class hand implements Comparable<hand> {
    Comparator<hand> straightFlush;
    private final ArrayList<card> cards;
    public hand(String cards){
        String[] cardValues = cards.split(" ");
        this.cards = new ArrayList<>(cardValues.length);
        for(String c : cardValues){
            this.cards.add(new card(c));
        }
        Collections.sort(this.cards);
    }
    public ArrayList<card> getCards(){
        return this.cards;
    }
    public int compareTo(hand other){

        return 0;
    }
    
    @Override
    public String toString(){
        String data = "";
        return this.cards.stream().map((c) -> c + "\n").reduce(new String(), String::concat);
    }
}
