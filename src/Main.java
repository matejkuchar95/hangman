import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    final Random random = new Random();
    final String[] words = {"skillmea", "akademia", "volby", };


        final String guessWord = SelectRandomWord(random, words);
         String hiddenWord = generateHiddenWord(guessWord);
        final int MAX_INCORRECT_GUESSES = 6;
        int incorrectGuesses = 0;

        final Scanner scanner = new Scanner(System.in);

        System.out.println("Guess the word " + hiddenWord);


        while (incorrectGuesses < MAX_INCORRECT_GUESSES && hiddenWord.contains("_")) {
            System.out.print("Enter your guess: ");
            final char guess = scanLetter(scanner);

            if(guessWord.contains(String.valueOf(guess))) {
                hiddenWord = revealLetter(guessWord, hiddenWord, guess);
                System.out.println("Correct! " + hiddenWord);
            } else {
                incorrectGuesses++;
                System.out.println("Incorrect! You have " + (MAX_INCORRECT_GUESSES - incorrectGuesses) + " attempts left.");
            }
        }

        if(hiddenWord.contains("_")) {
            System.out.println("You have run out of guesses");
        } else {
            System.out.println("Congratulations! You guessed the word: " + guessWord);
        }


    }


    public static String revealLetter(String word,  String hiddenWord, char letter) {
        char[] hiddenWordChars = hiddenWord.toCharArray();
        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == letter) {
                hiddenWordChars[i] = letter;
            }
        }

        return String.valueOf(hiddenWordChars);
    }


    public static char scanLetter(Scanner scanner) {
        char guess;

        while (true) {
            try {
                String line = scanner.nextLine();
                if (line.length() != 1) {
                    throw new Exception("Line length is not 1. Please enter a single character");
                }

                guess = line.charAt(0);
                if (!Character.isLetter(guess)) {
                    throw new Exception("Character is not a letter. Please enter a single character");
                }

                break;
            } catch (Exception e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }

        return guess;
    }

    public static String generateHiddenWord(String word) {
        return "_".repeat(word.length());
    }

    public static String SelectRandomWord(Random random, String[] words) {
        return words[random.nextInt(words.length)];

    };
}