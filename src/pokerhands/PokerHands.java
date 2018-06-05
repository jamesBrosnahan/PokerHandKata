/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerhands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author james
 */
public class PokerHands {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        readFromStdin();
    }    
    public static void readFromStdin(){
        boolean prompt = true;
        String handRegexString = "\\b(((\\w)*):(\\s(\\d|T|J|Q|K|A)(?=(H|D|S|C))(H|D|S|C))*)";
                //"(\\w*(?=:)).*";
                //"( ([2-9]|T|J|Q|K|A)(?=(H|D|C|S)))+";
        String test = "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH";
        String[] playerStrings;
        Pattern handRegexPattern = Pattern.compile(handRegexString);
        game round;
        System.out.println("Try block");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scanner created");
        do{
            System.out.println("Enter two hands in the form");
            System.out.println("ex: Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH");
            playerStrings = new String[2];
            Matcher m = handRegexPattern.matcher(scanner.nextLine());
            while(m.find()){
                if(playerStrings[0] == null){
                    playerStrings[0] = m.group(1);
                }else if(playerStrings[1] == null){
                    playerStrings[1] = m.group(1);
                }
            }
            round = new game(playerStrings[0], playerStrings[1]);
            System.out.println(round);
            System.out.println("Do you want to enter another two poker hands? Y/N");
            String answer = scanner.nextLine();
            if(answer.equals("N") || answer.equals("n")){
                prompt = false;
            }
        }while(prompt == true);
    }
    
    public static void readFromFile(String filePath){
        File sampleInputFile = new File(filePath);
        String handRegexString = "\\b(((\\w)*):(\\s(\\d|T|J|Q|K|A)(?=(H|D|S|C))(H|D|S|C)){5})";
                //"(\\w*(?=:)).*";
                //"( ([2-9]|T|J|Q|K|A)(?=(H|D|C|S)))+";
        String test = "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH";
        String[] playerStrings;
        Pattern handRegexPattern = Pattern.compile(handRegexString);
        game round;
        try{
            System.out.println("Try block");
            Scanner scanner = new Scanner(sampleInputFile);
            System.out.println("Scanner created");
            while(scanner.hasNextLine()){
                playerStrings = new String[2];
                if(scanner.hasNext(handRegexPattern)){
                    System.out.println(scanner.next(handRegexPattern));
                }
                Matcher m = handRegexPattern.matcher(scanner.nextLine());
                while(m.find()){
                    if(playerStrings[0] == null){
                        playerStrings[0] = m.group(1);
                    }else if(playerStrings[1] == null){
                        playerStrings[1] = m.group(1);
                    }
                }
                round = new game(playerStrings[0], playerStrings[1]);
                System.out.println(round);
            }
        }catch(FileNotFoundException e){
            System.exit(0);
        }
    }
    public static void test(){
        String sampleInput = 
                "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH\n" +
                "Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S\n" +
                "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH\n" +
                "Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH";
        String sampleOutput = 
                "White wins. - with high card: Ace \n" +
                "Black wins. - with full house: 4 over 2 \n" +
                "Black wins. - with high card: 9\n" +
                "Tie.";

        hand black = new hand("9H 9D 8S 8C 8D");
        hand white = new hand("TH TD 8S 8C 8D");
        game round1 = new game("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH");
        game round2 = new game("Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S");
        game round3 = new game("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH");
        game round4 = new game("Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH");
        game round5 = new game("Black: 2H 3H 4H 5H 6H  White: 2H 3H 4H 8H AH");
        //System.out.println(comparisonHelper);
        System.out.println(round1);
        System.out.println(round2);
        System.out.println(round3);
        System.out.println(round4);
        System.out.println(round5);
    }
}
