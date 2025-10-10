public class Wordle {
    private String targetWord;

    public Wordle(String targetWord) {
        this.targetWord = targetWord.toUpperCase();
    }

    public String getTargetWord() {
        return targetWord;
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
                } else {
                    colors[i] = 'X';
                }
            }
        }
        return colors;
    }

}
