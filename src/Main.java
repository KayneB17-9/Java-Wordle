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
        System.out.println("Random word: " + randomWord);
    }
}



