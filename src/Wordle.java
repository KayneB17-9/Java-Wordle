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

    public Character getRandomLetterUnUsed() {
        if (usedHintIndexes.size() == targetWord.length()) {
            return null;
        }
        int index;
        do {
            index = rand.nextInt(targetWord.length());
        } while (usedHintIndexes.contains(index) || correctLettersIndexes.contains(index));

        usedHintIndexes.add(index);
        return targetWord.charAt(index);
    }




    public char[] checkGuess(String guess) {
        guess = guess.toUpperCase();
        char[] colors = new char[guess.length()];
        int[] targetLetterCount = new int[26];

        for (char c : targetWord.toCharArray()) {
            targetLetterCount[c - 'A']++;
        }

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == targetWord.charAt(i)) {
                colors[i] = 'G';
                targetLetterCount[guess.charAt(i) - 'A']--;
                correctLettersIndexes.add(i);
            } else {
                colors[i] = ' ';

            }
        }

        for (int i = 0; i < guess.length(); i++) {
            if (colors[i] != 'G') {
                int idx = guess.charAt(i) - 'A';
                if (targetLetterCount[idx] >0) {
                    colors[i] = 'Y';
                    targetLetterCount[idx]--;
                    yellowIndexes.add(i);
                } else {
                    colors[i] = 'X';
                }
            }
        }



        return colors;
    }

}





