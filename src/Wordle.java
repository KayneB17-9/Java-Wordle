import java.util.Random;
import java.util.HashSet;
import java.util.Set;
public class Wordle {
    private String targetWord;
    private Set<Integer> usedHintIndexes = new HashSet<>();
    private Set<Integer> correctLettersIndexes = new HashSet<>();
    private Set<Integer> yellowIndexes = new HashSet<>();
    private Random rand = new Random();

    public Wordle(String targetWord) {
        this.targetWord = targetWord.toUpperCase();
    }

    public String getTargetWord() {
        return targetWord;
    }
    // Checks if all letters have been used as hints
    public Character getRandomLetterUnUsed() {
        if (usedHintIndexes.size() == targetWord.length()) {
            return null;
        }
        int index;
        do {
            // Picks random index
            index = rand.nextInt(targetWord.length());
            // Loops through index is already given as a hint or is marked correct
        } while (usedHintIndexes.contains(index) || correctLettersIndexes.contains(index));
        // Stores the letter so it isnt used later as a hint

        usedHintIndexes.add(index);
        // Returns the letter at the given index
        return targetWord.charAt(index);
    }



    // Character array
    public char[] checkGuess(String guess) {
        // Sets guess to uppercae
        guess = guess.toUpperCase();
        // Assigns colors in total to the length of the guess
        char[] colors = new char[guess.length()];
        // an array of each letter
        int[] targetLetterCount = new int[26];
        // loop that assigns the letters in the guess a valid index
        for (char c : targetWord.toCharArray()) {
            targetLetterCount[c - 'A']++;
        }
        // loops until i is greater than the guesses length
        for (int i = 0; i < guess.length(); i++) {
            // compares the character index of the guess to the answer and if they are equal gives green
            if (guess.charAt(i) == targetWord.charAt(i)) {
                colors[i] = 'G';
                targetLetterCount[guess.charAt(i) - 'A']--;
                // Adds letter for hint system to ignore
                correctLettersIndexes.add(i);
            } else {
                colors[i] = ' ';

            }
        }

        for (int i = 0; i < guess.length(); i++) {
            if (colors[i] != 'G') {
                int idx = guess.charAt(i) - 'A';
                // checks if the character is inside the guess but not accurate with the index of the answer and throws yellow
                if (targetLetterCount[idx] >0) {
                    colors[i] = 'Y';
                    targetLetterCount[idx]--;
                    yellowIndexes.add(i);
                    // Else it throws red meaning it's not in the word at all
                } else {
                    colors[i] = 'X';
                }
            }
        }



        return colors;
    }

}





