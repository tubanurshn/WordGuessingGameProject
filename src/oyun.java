
import java.util.ArrayList;
import java.util.Scanner;

public class oyun {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<Integer> score = new ArrayList<Integer>();

        boolean end = false;
        while (!false) {
            System.out.println("-------------------------\n" + "          MENU           \n" + "1- Add New Word\n"
                    + "2- New Game\n" + "3- Show the scores\n" + "4- Exit\n" + "-------------------------"); //displays the menu
            System.out.println();
            System.out.print("Select the number of the transaction you want to perform: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.print("When your word list ends please enter \"exit\"\n" + "Enter a word:");
                        String word = sc.nextLine();
                        if (word.equalsIgnoreCase("exit")) { //controls the adding is done
                            break;
                        }
                        if (word.length() < 4) { //If the word is shorter than 4 letter,it returns to the menu
                            System.out.println("The word cannot be shorter than 4 letters!");
                            break;
                        } else if (!noNumber(word)) { //If the word contains number,it returns to the menu
                            System.out.println("The word can not contain numbers!");
                            break;
                        } else if (words.contains(word)) { //If the word is already entered,it returns to the menu
                            System.out.println("The word is already entered!");
                            break;
                        } else {
                            words.add(word); //If the conditions are met, the word will be added to the list
                            System.out.println("Word added successfully.");
                            System.out.println();
                        }
                    }

                    break;
                case 2:
                    if (words.isEmpty()) {
                        break;
                    }
                    int size = words.size(); //getting the how many words are in the arraylist
                    int i = (int) (Math.random() * size);
                    String guessWord = words.get(i).toLowerCase(); //getting word at random index from arraylist

                    int guessRights = guessWord.length() / 2; //gives player the rights to half of the wordlength
                    for (int j = 0; j < guessWord.length(); j++) {
                        System.out.print("_ "); //displays as much as lines as the word
                    }
                    System.out.println();
                    System.out.println("You have " + guessRights + " rigths.");
                    System.out.println();
                    String usersGuessWord = "";
                    for (int j = 0; j < guessWord.length(); j++) {
                        usersGuessWord += "_"; //create lines for the users guesses
                    }
                    ArrayList<Character> guessedCharacters = new ArrayList<Character>();
                    while (!usersGuessWord.equals(guessWord) && guessRights > 0) {
                        boolean correctGuess = false;
                        System.out.print("Select one char: ");
                        String input = sc.next();
                        char guessLetter = input.charAt(0);
                        guessLetter = Character.toLowerCase(guessLetter);

                        if (guessedCharacters.contains(guessLetter)) { //controls if the letter is entered before
                            System.out.println("The letter is already entered!");
                            continue;
                        }

                        guessedCharacters.add(guessLetter); //adds characters to the list

                        int countOfLetter = 0; //Checks whether the letter exists in the word and displays the word with the letter and lines
                        for (int k = 0; k < guessWord.length(); k++) {
                            if (guessLetter == guessWord.charAt(k)) {
                                countOfLetter++;
                                usersGuessWord = usersGuessWord.substring(0, k) + guessLetter + usersGuessWord.substring(k + 1);
                                correctGuess = true;
                            }

                        }
                        if (correctGuess) {//this block,it displays how many character are in the word and the new version of the word
                            if (countOfLetter > 1) {
                                System.out.println("There is " + countOfLetter + " times " + guessLetter);
                                for (int j = 0; j < usersGuessWord.length(); j++) {
                                    System.out.print(usersGuessWord.charAt(j) + " ");
                                }
                            } else if (countOfLetter == 1) {
                                System.out.println("There is " + countOfLetter + " times " + guessLetter);
                                for (int j = 0; j < usersGuessWord.length(); j++) {
                                    System.out.print(usersGuessWord.charAt(j) + " ");
                                }
                            }
                            System.out.println("\n You have " + guessRights + " rights."); //displays the final amount of rights
                            System.out.println();
                        }
                        if (!correctGuess) {
                            System.out.println("There is no " + guessLetter);

                            guessRights--;             ////////////////////guessright kısmını -1 şeklinde yukarı yazmışım o yüzden hakkı varken kaybedince score a eklememişş 
                            if (guessRights == 0) {
                                System.out.println("YOU HAVE NO RIGHTS.YOU LOST!!!");
                                score.add(0); //when gamer lost,it adds zero point to the score list
                            } else {
                                for (int j = 0; j < usersGuessWord.length(); j++) {
                                    System.out.print(usersGuessWord.charAt(j) + " ");
                                }
                                System.out.println();
                                System.out.println("You have " + guessRights + " rights.");
                            }
                        } else if (usersGuessWord.equals(guessWord)) {
                            System.out.println("CONGRATULATIONS.YOU WIN!!!");
                            score.add(10); //when gamer won,it adds 10 point to the score list
                        }

                    }
                    break;
                case 3:
                    for (int j = 0; j < score.size(); j++) {
                        System.out.println("Score " + (j + 1) + ":" + score.get(j)); //displays every score in the game
                    }
                    break;

                case 4:
                    end = true;
                    System.out.println("Game over...");
                    System.exit(1); //exit the program
                default:

            }
        }
    }

    public static boolean noNumber(String word) {
        for (char character : word.toCharArray()) {
            if (Character.isDigit(character)) { //controls if the character is digit or not
                return false;
            }
        }
        return true;
    }
}
