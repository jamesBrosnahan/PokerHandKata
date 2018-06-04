/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerhands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author james
 */
public class checker {
    public static enum ranks{STRAIGHTFLUSH, FOUROFAKIND, FULLHOUSE, FLUSH, STRAIGHT, THREEOFAKIND, TWOPAIRS, ONEPAIR, HIGHCARD};
    private static Map<Integer, ArrayList<card>> groupByValue(ArrayList<card> cards){
        return cards.stream().collect(Collectors.groupingBy(c -> c.value, Collectors.toCollection(ArrayList::new)));
    }
    private static Map<String, ArrayList<card>> groupBySuit(ArrayList<card> cards){
        return cards.stream().collect(Collectors.groupingBy(c -> c.suit, Collectors.toCollection(ArrayList::new)));
    }
    private static ArrayList<card> cardsByDuplicateValues(ArrayList<card> cards, int target){
        ArrayList<card> pairs = new ArrayList<>();
        
        groupByValue(cards).entrySet().stream().filter(group -> group.getValue().size() == target).forEach(e -> {
            pairs.add(e.getValue().get(0));
        });
        
        return pairs;
    }


    public static card straight(ArrayList<card> cards){
        if(cards.size() < 5){
            return null;
        }
        Collections.sort(cards);
        int previousValue = 0;
        for(card c : cards){
            if(previousValue != 0){
                if((c.value - previousValue) == 1){
                    previousValue = c.value;
                }else{
                    return null;
                }
            }else{
                previousValue = c.value;
            }
            
        }
        return highCard(cards);
    }
    public static card straightFlush(ArrayList<card> cards){
        if(cards.size() < 5){
            return null;
        }
        card straight = straight(cards);
        if(straight != null){
            card flush = flush(cards);
            return flush;
        }
        return null;
    }
    
    public static card highCard(ArrayList<card> cards){
        return Collections.max(cards);
    }
    public static card flush(ArrayList<card> cards){
        if(cards.size() < 5){
            return null;
        }
        Map<String, ArrayList<card>> cardsBySuit = groupBySuit(cards);
        if(cardsBySuit.size() == 1){
            return highCard(cards);
        }
        return null;
    }

    public static card[] fullHouse(ArrayList<card> cards){
        if(cards.size() < 5){
            return null;
        }
        card three = threeOfAKind(cards);
        card pair = onePair(cards);
        if(three != null && pair != null){
            return new card[]{three, pair};
        }
        return null;
    }
    public static card[] twoPairs(ArrayList<card> cards){
        if(cards.size() < 4){
            return null;
        }
        
        ArrayList<card> pairs = cardsByDuplicateValues(cards, 2);
        
        if(pairs.size() == 2){
            Collections.sort(pairs);
            return new card[]{pairs.get(1), pairs.get(0)};
        }
        return null;
    }
    
    public static card onePair(ArrayList<card> cards){
        if(cards.size() < 2){
            return null;
        }
        
        ArrayList<card> pairs = cardsByDuplicateValues(cards, 2);
        
        if(pairs.size() == 1){
            return pairs.get(0);
        }
        return null;
    }    
    public static card threeOfAKind(ArrayList<card> cards){
        if(cards.size() < 3){
            return null;
        }
        
        ArrayList<card> pairs = cardsByDuplicateValues(cards, 3);
        
        if(pairs.size() == 1){
            return pairs.get(0);
        }
        return null;
    }
    public static card fourOfAKind(ArrayList<card> cards){
        if(cards.size() < 4){
            return null;
        }
        
        ArrayList<card> pairs = cardsByDuplicateValues(cards, 4);
        
        if(pairs.size() == 1){
            return pairs.get(0);
        }
        return null;
    }
}
