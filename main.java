import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        System.out.println(System.getProperty("user.dir"));

        String strMyFile = new File("words.txt").getAbsolutePath();
        File myFile = new File(strMyFile);

        try {
            Scanner reader = new Scanner(myFile);

            while (reader.hasNextLine()) {
                String word = reader.nextLine().trim();

                if (!word.isEmpty()) {
                    words.add(word);
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("File not found.");
            return;
        }

        if (words.isEmpty()) {
            System.out.println("No words found in file!");
            return;
        }

        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        boolean playAgain = true;

        while (playAgain) {

            // Pick a NEW random word for each game
            String randomWord = words.get(rand.nextInt(words.size()));

            Wordle wordle = new Wordle(randomWord);

            System.out.println("\nWelcome to Wordle!");
            System.out.println("G = Green (correct spot), Y = Yellow (wrong spot), X = Not in word");

            int attempts = 0;
            final int maximumAttempts = 6;
            boolean guessed = false;

            // One game
            while (attempts < maximumAttempts && !guessed) {

                System.out.println("\nPlease enter your guess #" + (attempts + 1) + ": ");
                String guess = input.nextLine().trim();

                if (guess.length() != randomWord.length()) {
                    System.out.println(
                        "You must guess a " + randomWord.length() + " letter word"
                    );
                    continue;
                }

                char[] result = wordle.checkGuess(guess);

                System.out.print("Result: ");

                for (int i = 0; i < result.length; i++) {
                    switch (result[i]) {
                        case 'G':
                            System.out.print("\u001B[42m \u001B[0m");
                            break;

                        case 'Y':
                            System.out.print("\u001B[43m \u001B[0m");
                            break;

                        case 'X':
                            System.out.print("\u001B[40m \u001B[0m");
                            break;
                    }
                }

                System.out.println();

                if (guess.equalsIgnoreCase(wordle.getTargetWord())) {
                    guessed = true;

                    System.out.println(
                        "\nCongrats, you guessed the word! The word was: "
                        + wordle.getTargetWord()
                    );
                }

                attempts++;
            }

            // Player lost
            if (!guessed) {
                System.out.println(
                    "\nYou are out of attempts! The word was: "
                    + wordle.getTargetWord()
                );
            }

            // Ask if they want to play again
            System.out.print("\nWould you like to play again? (yes/no): ");
            String answer = input.nextLine().trim();

            if (!answer.equalsIgnoreCase("yes")
                    && !answer.equalsIgnoreCase("y")) {
                playAgain = false;
            }
        }

        System.out.println("\nThanks for playing Wordle!");
        input.close();
    }
}