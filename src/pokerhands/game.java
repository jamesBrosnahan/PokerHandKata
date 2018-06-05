/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerhands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author james
 */
public class game {
    String handPatternString = "";
    private player player1;
    private player player2;
    private comparisonHelperFunctions comparisonHelper = new comparisonHelperFunctions();
    public game(String Input){
        Input.replace(null, "");
        String [] playerStrings = Input.split("  ");
        this.player1 = new player(playerStrings[0]);
        this.player2 = new player(playerStrings[1]);
    }
    public game(player player1, player player2){
        this.player1 = player1;
        this.player2 = player2;
    }
    public game(String leftHand, String rightHand){
        this.player1 = new player(leftHand);
        this.player2 = new player(rightHand);
    }
    public game(String[] playerStrings){
        if(playerStrings[0] != null && playerStrings[1] != null){
            this.player1 = new player(playerStrings[0]);
            this.player2 = new player(playerStrings[1]);
        }
    }
    @Override
    public String toString(){
        String comparisonResultsString = "";
        switch(this.comparisonHelper.compare(this.player1.getHand(), this.player2.getHand())){
            case 0:
                return "Tie.";
            case 1:
                comparisonResultsString += this.player2.getName() +" wins. - with " + this.comparisonHelper.winningHand();
                break;
            case -1:
                comparisonResultsString += this.player1.getName() +" wins. - with " + this.comparisonHelper.winningHand();
                break;
        }
        return comparisonResultsString;
    }
}
