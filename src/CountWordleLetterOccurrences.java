import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*
 * The Wordle words list has 10663 words! 
 * No Wordle solutions are plural!
 * 
 * Finds the most coincident word in the Wordle words list.
 * 
 * Results: "Saree" but that has a double 'e' which is not worth guess (this is
 * harder than I thought -- but don't worry I have a plan.)
 * 
 * Note: I also don't know "saree". I'm just printing out all the letters to 
 * see if I can make a word that includes the majority letters.
 * 
 */
public class CountWordleLetterOccurrences {

	public static void main(String[] args) throws FileNotFoundException {
		// Iterate through the list and count each letter in each index and each
		// letter of all non-plural Wordle words.
		Scanner listScan = new Scanner(new File("src/Wordle Word List.txt"));
		
		int[][] letterAndLocationCount = new int[5][26]; // 5 indices, 26 letters
		int[] letterCount = new int[26];
		while (listScan.hasNext()) {
			String curWord = listScan.next();
			if (curWord.endsWith("s")) {
				continue;
			}
			
			for (int i = 0; i < curWord.length(); i++) { 
				int letterIndex = curWord.charAt(i) - 'a';
				letterAndLocationCount[i][letterIndex]++;
				letterCount[letterIndex]++;
			}
		}
		
		// Reiterate through the list and find the most coicident Wordle word
		
		// quick redo of File and rescan b/c easy
		listScan = new Scanner(new File("src/Wordle Word List.txt"));
		
		String bestWord = "";
		int scoreMax = 0;
		while (listScan.hasNext()) {
			String curWord = listScan.next();
			
			// sum the occurrence counts for the letters in this word at each
			// index
			int curScore = 0;
			for (int i = 0; i < curWord.length(); i++) {
				int letterIndex = curWord.charAt(i) - 'a';
				
				curScore += 5 * letterAndLocationCount[i][letterIndex];
				curScore += letterCount[letterIndex];
			}
			
			// set this as best word if occurrence counts is largest.
			if (curScore > scoreMax) {
				bestWord = curWord;
				scoreMax = curScore;
			}
		}
		
		// Print results
		System.out.println(bestWord + ": " + scoreMax);
	}

}
