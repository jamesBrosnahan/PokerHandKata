/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerhands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import static pokerhands.checker.flush;
import static pokerhands.checker.fourOfAKind;
import static pokerhands.checker.fullHouse;
import static pokerhands.checker.highCard;
import static pokerhands.checker.onePair;
import static pokerhands.checker.straight;
import static pokerhands.checker.straightFlush;
import static pokerhands.checker.threeOfAKind;
import static pokerhands.checker.twoPairs;

/**
 *
 * @author james
 */
public class comparisonHelperFunctions implements Comparator<hand>{
    private boolean draw = false;
    private String winningHandCombo = "";
    public String winningHand(){
        return this.winningHandCombo;
    }
    @Override
    public int compare(hand left, hand right){
        /*
        Straight Flush -> Four of a Kind -> Full House -> Flush -> Straight -> Three of a Kind -> Two Pairs -> One Pair -> High Card
        Check each comparison starting from the hand combo highest to the lowest ranked. 
        return if the hands tied by checking the draw value or if a hand has won.
        */
        ArrayList<Checker> helpers = new ArrayList<>();
        helpers.add(new compareStraightFlush());
        helpers.add(new compareFourOfAKind());
        helpers.add(new compareFullHouse());
        helpers.add(new compareFlush());
        helpers.add(new compareStraight());
        helpers.add(new compareThreeOfAKind());
        helpers.add(new compareTwoPair());
        helpers.add(new compareOnePair());
        helpers.add(new compareHighCard());
        
        for(int i = 0; i < helpers.size(); i++){
            int comparisonResult = helpers.get(i).compare(left, right);
            if(comparisonResult != 0){
                this.winningHandCombo = helpers.get(i).winningHand();
                return comparisonResult;
            }else if(helpers.get(i).getDraw() == true){
                return 0;
            }
        }
        return 0;
    }
    
    public boolean getDraw(){
        return this.draw;
    }
    
    private interface Checker extends Comparator<hand>{
        boolean getDraw();
        String winningHand();
    }
    
    public class compareStraightFlush implements Checker{
        private boolean draw = false;
        private String winningHandCombo = "";
        @Override
        public int compare(hand left, hand right){
            /*
            Straight flush: 
            5 cards of the same suit with consecutive values. 
            Ranked by the highest card in the hand.
            */
            ArrayList<card> leftCards = left.getCards();
            ArrayList<card> rightCards = right.getCards();
            card leftStraightFlush = straightFlush(leftCards);
            card rightStraightFlush = straightFlush(rightCards);
            
            if(leftStraightFlush != null && rightStraightFlush == null){
                setWinningHand(leftStraightFlush);
                return -1;
            }else if(leftStraightFlush == null && rightStraightFlush != null){
                setWinningHand(rightStraightFlush);
                return 1;
            }else if(leftStraightFlush != null && rightStraightFlush != null){
                if(leftStraightFlush.value < rightStraightFlush.value){
                    setWinningHand(leftStraightFlush);
                    return -1;
                }else if(leftStraightFlush.value > rightStraightFlush.value){
                    setWinningHand(rightStraightFlush);
                    return 1;
                }else{
                    draw = true;
                    return 0;
                }
            }else{
                return 0;
            }
        }
        public boolean getDraw(){
            return this.draw;
        }
        private void setWinningHand(card winningCard){
            winningHandCombo = "straight flush: ";
                switch (winningCard.value) {
                    case 11:
                        winningHandCombo += "Jack";
                        break;
                    case 12:
                        winningHandCombo += "Queen";
                        break;
                    case 13:
                        winningHandCombo += "King";
                        break;
                    case 14:
                        winningHandCombo += "Ace";
                        break;
                    default:
                        winningHandCombo += winningCard.value;
                        break;
                }
        }
        @Override
        public String winningHand() {
            return this.winningHandCombo;
        }
    }
    public class compareFlush implements Checker{
        private boolean draw = false;
        private String winningHandCombo = "";
        @Override
        public int compare(hand left, hand right){
            ArrayList<card> leftCards = left.getCards();
            ArrayList<card> rightCards = right.getCards();
            
            card leftFlush = flush(leftCards);
            card rightFlush = flush(rightCards);
            System.out.print(leftFlush);
            if(leftFlush != null && rightFlush == null){
                setWinningHand(leftFlush);
                return -1;
            }else if(leftFlush == null && rightFlush != null){
                setWinningHand(rightFlush);
                return 1;
            }else if(leftFlush != null && rightFlush != null){
                if(leftFlush.value < rightFlush.value){
                    setWinningHand(leftFlush);
                    return -1;
                }else if(leftFlush.value > rightFlush.value){
                    setWinningHand(rightFlush);
                    return 1;
                }else{
                    draw = true;
                    return 0;
                }
            }else{
                return 0;
            }
        }
        public boolean getDraw(){
            return this.draw;
        }
        private void setWinningHand(card winningCard){
            winningHandCombo = "flush: ";
                switch (winningCard.value) {
                    case 11:
                        winningHandCombo += "Jack";
                        break;
                    case 12:
                        winningHandCombo += "Queen";
                        break;
                    case 13:
                        winningHandCombo += "King";
                        break;
                    case 14:
                        winningHandCombo += "Ace";
                        break;
                    default:
                        winningHandCombo += winningCard.value;
                        break;
                }
        }
        @Override
        public String winningHand() {
            return this.winningHandCombo;
        }
    }
    public class compareStraight implements Checker{
        public boolean draw = false;
        private String winningHandCombo = "";
        @Override
        public int compare(hand left, hand right){
            ArrayList<card> leftCards = left.getCards();
            ArrayList<card> rightCards = right.getCards();
            card leftStraight = straight(leftCards);
            card rightStraight = straight(rightCards);
            
            if(leftStraight != null && rightStraight == null){
                setWinningHand(leftStraight);
                return -1;
            }else if(leftStraight == null && rightStraight != null){
                setWinningHand(rightStraight);
                return 1;
            }else if(leftStraight != null && rightStraight != null){
                if(leftStraight.value < rightStraight.value){
                    setWinningHand(leftStraight);
                    return -1;
                }else if(leftStraight.value > rightStraight.value){
                    setWinningHand(rightStraight);
                    return 1;
                }else{
                    return 0;
                }
            }else{
                return 0;
            }
        }
        public boolean getDraw(){
            return draw;
        }
        private void setWinningHand(card winningCard){
            winningHandCombo = "straight: ";
                switch (winningCard.value) {
                    case 11:
                        winningHandCombo += "Jack";
                        break;
                    case 12:
                        winningHandCombo += "Queen";
                        break;
                    case 13:
                        winningHandCombo += "King";
                        break;
                    case 14:
                        winningHandCombo += "Ace";
                        break;
                    default:
                        winningHandCombo += winningCard.value;
                        break;
                }
        }
        @Override
        public String winningHand() {
            return this.winningHandCombo;
        }
    }
    
    public class compareFourOfAKind implements Checker{
        private boolean draw = false;
        private String winningHandCombo = "";
        @Override
        public int compare(hand left, hand right){
            /*
            Four of a kind: 
            4 cards with the same value. 
            Ranked by the value of the 4 cards.
           */
            compareHighCardArray helperClassInstance = new compareHighCardArray();
            
            ArrayList<card> leftCards = left.getCards();
            ArrayList<card> rightCards = right.getCards();
            card leftFourOfAKind = fourOfAKind(leftCards);
            card rightFourOfAKind = fourOfAKind(rightCards);
            if(leftFourOfAKind == null && rightFourOfAKind != null){
                setWinningHand(rightFourOfAKind);
                return 1;
            }else if(leftFourOfAKind != null && rightFourOfAKind == null){
                setWinningHand(leftFourOfAKind);
                return -1;
            }else if(leftFourOfAKind == null && rightFourOfAKind == null){
                return 0;
            }else{
                ArrayList<card> leftFourOfAKindCard = new ArrayList<>();
                leftFourOfAKindCard.add(leftFourOfAKind);

                ArrayList<card> rightFourOfAKindCard = new ArrayList<>();
                rightFourOfAKindCard.add(rightFourOfAKind);

                int comparisonOfFourOfAKind = helperClassInstance.compare(leftFourOfAKindCard, rightFourOfAKindCard);
                switch (comparisonOfFourOfAKind) {
                    case 0:
                        draw = true;
                        break;
                    case -1:
                        setWinningHand(leftFourOfAKind);
                        break;
                    case 1:
                        setWinningHand(rightFourOfAKind);
                        break;
                    default:
                        break;
                }
                return comparisonOfFourOfAKind;
            }
        }
        public boolean getDraw(){
            return this.draw;
        }
        private void setWinningHand(card winningCard){
            winningHandCombo = "four of a kind: ";
                switch (winningCard.value) {
                    case 11:
                        winningHandCombo += "Jack";
                        break;
                    case 12:
                        winningHandCombo += "Queen";
                        break;
                    case 13:
                        winningHandCombo += "King";
                        break;
                    case 14:
                        winningHandCombo += "Ace";
                        break;
                    default:
                        winningHandCombo += winningCard.value;
                        break;
                }
        }
        @Override
        public String winningHand() {
            return this.winningHandCombo;
        }        
    }   
    public class compareThreeOfAKind implements Checker{
        private boolean draw = false;
        private String winningHandCombo = "";
        @Override
        public int compare(hand left, hand right){
            /*
            Three of a Kind: 
            Three of the cards in the hand have the same value. 
            Hands which both contain three of a kind are ranked by the value of the 3 cards.
           */
            compareHighCardArray helperClassInstance = new compareHighCardArray();
            
            ArrayList<card> leftCards = left.getCards();
            ArrayList<card> rightCards = right.getCards();
            card leftThreeOfAKind = threeOfAKind(leftCards);
            card rightThreeOfAKind = threeOfAKind(rightCards);
            if(leftThreeOfAKind == null && rightThreeOfAKind != null){
                setWinningHand(rightThreeOfAKind);
                return 1;
            }else if(leftThreeOfAKind != null && rightThreeOfAKind == null){
                setWinningHand(leftThreeOfAKind);
                return -1;
            }else if(leftThreeOfAKind == null && rightThreeOfAKind == null){
                return 0;
            }else{
                ArrayList<card> leftThreeOfAKindCard = new ArrayList<card>();
                leftThreeOfAKindCard.add(leftThreeOfAKind);

                ArrayList<card> rightThreeOfAKindCard = new ArrayList<card>();
                rightThreeOfAKindCard.add(rightThreeOfAKind);

                int comparisonOfThreeOfAKind = helperClassInstance.compare(leftThreeOfAKindCard, rightThreeOfAKindCard);
                switch (comparisonOfThreeOfAKind) {
                    case 0:
                        draw = true;
                        break;
                    case -1:
                        setWinningHand(leftThreeOfAKind);
                        break;
                    case 1:
                        setWinningHand(rightThreeOfAKind);
                        break;
                    default:
                        break;
                }
                return comparisonOfThreeOfAKind;
            }
        }
        @Override
        public boolean getDraw(){
            return this.draw;
        }
        private void setWinningHand(card winningCard){
            winningHandCombo = "four of a kind: ";
                switch (winningCard.value) {
                    case 11:
                        winningHandCombo += "Jack";
                        break;
                    case 12:
                        winningHandCombo += "Queen";
                        break;
                    case 13:
                        winningHandCombo += "King";
                        break;
                    case 14:
                        winningHandCombo += "Ace";
                        break;
                    default:
                        winningHandCombo += winningCard.value;
                        break;
                }
        }
        @Override
        public String winningHand() {
            return this.winningHandCombo;
        }     
    }
    public class compareFullHouse implements Checker{
        private boolean draw = false;
        private String winningHandCombo = "";
        @Override
        public int compare(hand left, hand right){
            /*
            Full House: 
            3 cards of the same value, with the remaining 2 cards forming a pair. 
            Ranked by the value of the 3 cards.
            */
            compareHighCardArray helperClassInstance = new compareHighCardArray();
            
            ArrayList<card> leftCards = left.getCards();
            ArrayList<card> rightCards = right.getCards();
            card[] leftFullHouse = fullHouse(leftCards);
            card[] rightFullHouse = fullHouse(rightCards);
            if(leftFullHouse == null && rightFullHouse != null){
                setWinningHand(rightFullHouse);
                return 1;
            }else if(leftFullHouse != null && rightFullHouse == null){
                setWinningHand(leftFullHouse);
                return -1;
            }else if(leftFullHouse == null && rightFullHouse == null){
                return 0;
            }else{
               ArrayList<card> leftThreeOfAKindCard = new ArrayList<>();
                leftThreeOfAKindCard.add(leftFullHouse[0]);

                ArrayList<card> rightThreeOfAKindCard = new ArrayList<>();
                rightThreeOfAKindCard.add(rightFullHouse[0]);

                int comparisonOfThreeOfAKind = helperClassInstance.compare(leftThreeOfAKindCard, rightThreeOfAKindCard);
                if(comparisonOfThreeOfAKind == 0){
                    draw = true;
                }
                switch (comparisonOfThreeOfAKind) {
                    case 0:
                        draw = true;
                        break;
                    case -1:
                        setWinningHand(leftFullHouse);
                        break;
                    case 1:
                        setWinningHand(rightFullHouse);
                        break;
                    default:
                        break;
                }
                return comparisonOfThreeOfAKind; 
            }
        }
        @Override
        public boolean getDraw(){
            return this.draw;
        }
        private void setWinningHand(card[] winningCards){
            winningHandCombo = "full house: ";
                switch (winningCards[0].value) {
                    case 11:
                        winningHandCombo += "Jack";
                        break;
                    case 12:
                        winningHandCombo += "Queen";
                        break;
                    case 13:
                        winningHandCombo += "King";
                        break;
                    case 14:
                        winningHandCombo += "Ace";
                        break;
                    default:
                        winningHandCombo += winningCards[0].value;
                        break;
                }
                winningHandCombo += " over ";
                switch (winningCards[1].value) {
                    case 11:
                        winningHandCombo += "Jack";
                        break;
                    case 12:
                        winningHandCombo += "Queen";
                        break;
                    case 13:
                        winningHandCombo += "King";
                        break;
                    case 14:
                        winningHandCombo += "Ace";
                        break;
                    default:
                        winningHandCombo += winningCards[1].value;
                        break;
                }
        }
        @Override
        public String winningHand() {
            return this.winningHandCombo;
        }
    }
    public class compareTwoPair implements Checker{
        private boolean draw = false;
        private String winningHandCombo = "";
        @Override
        public int compare(hand left, hand right){
            /*
            Two Pairs: 
            The hand contains 2 different pairs. 
            Hands which both contain 2 pairs are ranked by the value of their highest pair. 
            Hands with the same highest pair are ranked by the value of their other pair. 
            If these values are the same the hands are ranked by the value of the remaining card.
            */
            
            compareHighCardArray helperClassInstance = new compareHighCardArray();
            
            ArrayList<card> leftCards = left.getCards();
            ArrayList<card> rightCards = right.getCards();
            card[] leftPairs = twoPairs(leftCards);
            card[] rightPairs = twoPairs(rightCards);
            if(leftPairs == null && rightPairs != null){
                return 1;
            }else if(leftPairs != null && rightPairs == null){
                return -1;
            }else if(leftPairs == null && rightPairs == null){
                return 0;
            }else{
                ArrayList<card> leftPairsCards = new ArrayList<>();
                leftPairsCards.add(leftPairs[0]);
                leftPairsCards.add(leftPairs[1]);
                ArrayList<card> rightPairsCards = new ArrayList<>();
                rightPairsCards.add(rightPairs[0]);
                rightPairsCards.add(rightPairs[1]);
                int pairCardsComparisonResult = helperClassInstance.compare(leftPairsCards, rightPairsCards);
                if(pairCardsComparisonResult == 0){
                    leftCards = leftCards.stream().filter(c -> c.value != leftPairs[0].value && c.value != leftPairs[1].value).collect(Collectors.toCollection(ArrayList::new));
                    rightCards = rightCards.stream().filter(c -> c.value != rightPairs[0].value && c.value != rightPairs[1].value).collect(Collectors.toCollection(ArrayList::new));
                    int remainingCardsComparisonResult = helperClassInstance.compare(leftCards, rightCards);
                    if(remainingCardsComparisonResult == 0){
                        draw = true;
                        return 0;
                    }else{
                        return remainingCardsComparisonResult;
                    }
                }else{
                    return pairCardsComparisonResult;
                }
            }
        }
        public boolean getDraw(){
            return this.draw;
        }
        private void setWinningHand(card[] winningCards){
            winningHandCombo = "two pairs: ";
                switch (winningCards[0].value) {
                    case 11:
                        winningHandCombo += "Jack";
                        break;
                    case 12:
                        winningHandCombo += "Queen";
                        break;
                    case 13:
                        winningHandCombo += "King";
                        break;
                    case 14:
                        winningHandCombo += "Ace";
                        break;
                    default:
                        winningHandCombo += winningCards[0].value;
                        break;
                }
                winningHandCombo += " over ";
                switch (winningCards[1].value) {
                    case 11:
                        winningHandCombo += "Jack";
                        break;
                    case 12:
                        winningHandCombo += "Queen";
                        break;
                    case 13:
                        winningHandCombo += "King";
                        break;
                    case 14:
                        winningHandCombo += "Ace";
                        break;
                    default:
                        winningHandCombo += winningCards[1].value;
                        break;
                }
        }
        @Override
        public String winningHand() {
            return this.winningHandCombo;
        }
    }
    public class compareOnePair implements Checker{
        private boolean draw = false;
        private String winningHandCombo = "";
        @Override
        public int compare(hand left, hand right){
            /*
            Pair: 
            2 of the 5 cards in the hand have the same value. 
            Hands which both contain a pair are ranked by the value of the cards forming the pair. 
            If these values are the same, the hands are ranked by the values of the cards not forming the pair, in decreasing order.
            */
            
            compareHighCardArray helperClassInstance = new compareHighCardArray();
            
            ArrayList<card> leftCards = left.getCards();
            ArrayList<card> rightCards = right.getCards();
            card leftPair = onePair(leftCards);
            card rightPair = onePair(rightCards);
            
            if(leftPair != null && rightPair == null){
                setWinningHand(leftPair);
                return -1;
            }else if(leftPair == null && rightPair != null){
                setWinningHand(rightPair);
                return 1;
            }else if(leftPair == null && rightPair == null){
                return 0;
            }else{
                
                ArrayList<card> leftPairCard = new ArrayList<>();
                leftPairCard.add(leftPair);

                ArrayList<card> rightPairCard = new ArrayList<>();
                rightPairCard.add(rightPair);

                int comparisonOfPairs = helperClassInstance.compare(leftPairCard, rightPairCard);
                if(comparisonOfPairs == 0){
                    leftCards = leftCards.stream().filter(c -> c.value != leftPair.value).collect(Collectors.toCollection(ArrayList::new));
                    rightCards = rightCards.stream().filter(c -> c.value != rightPair.value).collect(Collectors.toCollection(ArrayList::new));
                    int remainingCardsComparisonResult = helperClassInstance.compare(leftCards, rightCards);
                    if(remainingCardsComparisonResult == 0){
                        draw = true;
                    }
                    return remainingCardsComparisonResult;
                }
                return comparisonOfPairs;
            }        
        }
        public boolean getDraw(){
            return this.draw;
        }
        private void setWinningHand(card winningCard){
            winningHandCombo = "one pair: ";
                switch (winningCard.value) {
                    case 11:
                        winningHandCombo += "Jack";
                        break;
                    case 12:
                        winningHandCombo += "Queen";
                        break;
                    case 13:
                        winningHandCombo += "King";
                        break;
                    case 14:
                        winningHandCombo += "Ace";
                        break;
                    default:
                        winningHandCombo += winningCard.value;
                        break;
                }
        }
        @Override
        public String winningHand() {
            return this.winningHandCombo;
        }
    }
    public class compareHighCard implements Checker{
        private boolean draw = false;
        private String winningHandCombo = "";
        @Override
        public int compare(hand left, hand right){
            ArrayList<card> leftCards = left.getCards();
            ArrayList<card> rightCards = right.getCards();
            compareHighCardArray helper = new compareHighCardArray();
            int highCardComparison = helper.compare(leftCards, rightCards);
            if(highCardComparison == 0){
                draw = true;
            }
            this.winningHandCombo = helper.winningHand();
            return highCardComparison;
        }
        public boolean getDraw(){
            return this.draw;
        }
        private void setWinningHand(card winningCard){
            winningHandCombo = "high card: ";
                switch (winningCard.value) {
                    case 11:
                        winningHandCombo += "Jack";
                        break;
                    case 12:
                        winningHandCombo += "Queen";
                        break;
                    case 13:
                        winningHandCombo += "King";
                        break;
                    case 14:
                        winningHandCombo += "Ace";
                        break;
                    default:
                        winningHandCombo += winningCard.value;
                        break;
                }
        }
        @Override
        public String winningHand() {
            return this.winningHandCombo;
        }
    }
    public class compareHighCardArray implements Comparator<ArrayList<card>> {
        private String winningHandCombo = "";
        @Override
        public int compare(ArrayList<card> left, ArrayList<card> right){
            Collections.sort(left);
            Collections.reverse(left);
            Collections.sort(right);
            Collections.reverse(right);
            for(int i = 0; i < left.size(); i++){
                if(left.get(i).value > right.get(i).value){
                    setWinningHand(left.get(i));
                    return -1;
                }else if(left.get(i).value < right.get(i).value){
                    setWinningHand(right.get(i));
                    return 1;
                }
            }
            return 0;
        }
        private void setWinningHand(card winningCard){
            winningHandCombo = "high card: ";
                switch (winningCard.value) {
                    case 11:
                        winningHandCombo += "Jack";
                        break;
                    case 12:
                        winningHandCombo += "Queen";
                        break;
                    case 13:
                        winningHandCombo += "King";
                        break;
                    case 14:
                        winningHandCombo += "Ace";
                        break;
                    default:
                        winningHandCombo += winningCard.value;
                        break;
                }
        }
        
        public String winningHand() {
            return this.winningHandCombo;
        }
    }
}
