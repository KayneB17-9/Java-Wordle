import java.io.File;
import java.io.FileNotFoundException;
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
                String word = reader.nextLine().trim(); // no spaces
                if (!word.isEmpty()) { // no new lines
                    words.add(word);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("File not found."); // catching if file cant be found
            return;
        }

        if (words.isEmpty()) {
            System.out.println("No words found in file!"); // checks if there are words in the file
            return;
        }

        Random rand = new Random();
        String randomWord = words.get(rand.nextInt(words.size())); // gets a random word from the words file
        // System.out.println("Random word: " + randomWord);

        Scanner input = new Scanner(System.in);

        Wordle wordle = new Wordle(randomWord);

        System.out.println("Welcome to Wordle!");
        System.out.println("G = Green (correct spot), Y = Yellow (wrong spot), X = Not in word");
        int attempts = 0;
        final int maxiumumAttempts = 6;
        boolean guessed = false;

        while (attempts < maxiumumAttempts && !guessed) {
            System.out.println("\nPlease enter your guess #" + (attempts + 1) + ": ");
            String guess = input.nextLine().trim();

            if (guess.length() != randomWord.length()) {
                System.out.println("You must guess a " + randomWord.length() + "  letter word");
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
                System.out.println("\n Congrats, you guessed the word! The word was: " + wordle.getTargetWord());
            }

            attempts++;
        }

        if (!guessed) {
            System.out.println("\n You are out of attempts! The word was: " + wordle.getTargetWord());

        }

        input.close();


    }
}