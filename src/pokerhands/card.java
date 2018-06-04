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
public class card implements Comparable<card>{
    
    public final String key;
    public final int value;
    public final String name;
    public final String suit;
    public final int suitValue;
    public card(String key){
        this.key = key;
        switch(key.charAt(0)){
            case '2':
                this.value = 2;
                this.name = "" + key.charAt(0);
                break;
            case '3':
                this.value = 3;
                this.name = "" + key.charAt(0);
                break;
            case '4':
                this.value = 4;
                this.name = "" + key.charAt(0);
                break;
            case '5':
                this.value = 5;
                this.name = "" + key.charAt(0);
                break;
            case '6':
                this.value = 6;
                this.name = "" + key.charAt(0);
                break;
            case '7':
                this.value = 7;
                this.name = "" + key.charAt(0);
                break;
            case '8':
                this.value = 8;
                this.name = "" + key.charAt(0);
                break;
            case '9':
                this.value = 9;
                this.name = "" + key.charAt(0);
                break;
            case 'T':
                this.value = 10;
                this.name = "10";
                break;
            case 'J':
                this.value = 11;
                this.name = "jack";
                break;
            case 'Q':
                this.value = 12;
                this.name = "queen";
                break;
            case 'K':
                this.value = 13;
                this.name = "king";
                break;
            case 'A':
                this.value = 14;
                this.name = "ace";
                break;
            default:
                this.value = 0;
                this.name = null;
                break;
        }
        switch(key.charAt(key.length()-1)){
            case 'C':
                this.suit = "clubs";
                this.suitValue = 1;
                break;
            case 'D':
                this.suit = "diamonds";
                this.suitValue = 2;
                break;
            case 'H':
                this.suit = "hearts";
                this.suitValue = 3;
                break;
            case 'S':
                this.suit = "spades";
                this.suitValue = 4;
                break;
            case 'c':
                this.suit = "clubs";
                this.suitValue = 1;
                break;
            case 'd':
                this.suit = "diamonds";
                this.suitValue = 2;
                break;
            case 'h':
                this.suit = "hearts";
                this.suitValue = 3;
                break;
            case 's':
                this.suit = "spades";
                this.suitValue = 4;
                break;
            default:
                this.suit = null;
                this.suitValue = 0;
                break;
        }
    }
    @Override
    public String toString(){
        return this.name + " of " + this.suit;
    }
    @Override
    public int compareTo(card other){
        if(this.value > other.value){
            return 1;
        }else if(this.value < other.value){
            return -1;
        }else{
            //Order by suit: "club" > "diamond" > "heart" > "spades"
            if(this.suitValue == other.suitValue){
                return 0;
            }else if(this.suitValue < other.suitValue){
                return -1;
            }else if(this.suitValue > other.suitValue){
                return 1;
            }else{
                return 0;
            }
            
        }
    }
}
