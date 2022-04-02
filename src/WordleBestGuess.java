import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * by Taylor Juve
 * 
 * 6741 non-plural wordle words out of 10663 total.
 */
public class WordleBestGuess {

	public static void main(String[] args) throws FileNotFoundException {
		// Locally store all non-plural Wordle words
		List<String> words = new ArrayList<String>();
			// .txt file should stay a space separated list of Wordle words
//		Scanner wordsScan = new Scanner(new File("src/Wordle Word List.txt"));
		Scanner wordsScan = new Scanner(new File("src/Test Words.txt"));
		while (wordsScan.hasNext()) {
			String word = wordsScan.next();
			if (!word.endsWith("s")) {
				words.add(word);
			}
		}

		// Determine similarity of the first word
		System.out.println("Words: " + words);
		String guess = words.remove(0);
		System.out.println("Guess: " + guess);
		System.out.println(count(guess, words));
		
	}

	// Counts the number of words with the same letters, words with same letters
	// are then counted again and the total is summed together for a "how similar
	// is this word and it's matching words score"
	public static int count(String guess, List<String> words) {
		System.out.println("guess = " + guess + ", words = " + words);
		if (words.isEmpty()) {
			return 0;
		}
		
		for (int i = 0; i < words.size(); i++) {
			String word = words.remove(i);
			if (containsLetter(guess, word)) {
				return 1 + count(word, words);
			}
		}
		return 0;
	}
	
	// True if any letters match between guess and word
	public static boolean containsLetter(String guess, String word) {
		for (char c : word.toCharArray()) {
			if (guess.indexOf(c) >= 0) {
				return true;
			}
		}
		return false;
	}
}