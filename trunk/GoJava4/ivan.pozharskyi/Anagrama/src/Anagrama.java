import java.util.Scanner;
import java.util.Stack;

public class Anagrama {
	private String lineWords;
	public Anagrama(String enteredData){
		this.lineWords = enteredData;
	}
	public Anagrama(){
		
	}

	public static void main(String[] args) {

		System.out.println("Enter words:");
		Anagrama anagrama = new Anagrama();
		anagrama.lineWords = anagrama.readFromConsole();		
		
		
		String[] result = anagrama.makeAnagrama(anagrama.lineWords);                                    
		anagrama.printResult(result);
	}

	private String readFromConsole() {

		Scanner scanner;
		scanner = new Scanner(System.in);
		String enteredData = scanner.nextLine();
		scanner.close();

		return enteredData;
	}

	private void printResult(String[] result) {
		
		System.out.println("Result:");
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}

	public String[] makeAnagrama(String data) {
		String [] words = splitWords(data);
		String[] result = words;
		for (int i = 0; i < words.length; i++) {

			char[] word = words[i].toCharArray();
			char[] reverseWord = reversingWord(word);
			result[i] = String.valueOf(reverseWord);

		}
		return result;

	}
	private String[] splitWords(String data){
		String[] words = data.split(" ");
		return words;
		
	}

	private char[] reversingWord(char[] word) {

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < word.length; i++) {
			stack.push(word[i]);
		}

		char[] reverseWord = word;
		for (int i = 0; i < word.length; i++) {
			reverseWord[i] = (char) stack.pop();

		}

		return reverseWord;
	}
}
