import java.io.FileNotFoundException;
import java.util.*;

import enigma.console.Console;


public class Question {

	static Dict statsCategories = new Dict();
	static Dict categories = new Dict();
	
	static Scanner scanner = new Scanner(System.in);
	static Locale eng = new Locale("en", "US");
	
	static Console cn = Main.cn;

	private int ID;
	private String Category, Text, Choice1, Choice2, Choice3, Choice4, CorrectAnswer, Difficulty;

	// Creating a Questions object
	public Question(int ID, String Category, String Text, String Choice1, String Choice2, String Choice3, String Choice4, String CorrectAnswer, String Difficulty) {

		this.ID = ID;
		this.Category = Category;
		this.Text = Text;
		this.Choice1 = Choice1;
		this.Choice2 = Choice2;
		this.Choice3 = Choice3;
		this.Choice4 = Choice4;
		this.CorrectAnswer = CorrectAnswer.replaceAll("( )+", "").toLowerCase(eng);
		this.Difficulty = Difficulty.replaceAll("( )+", "");
	}
	
	public int getID() {
		return ID;
	}

	public String getCategory() {
		return Category;
	}

	public String getText() {
		return Text;
	}

	public String getChoice1() {
		return Choice1;
	}

	public String getChoice2() {
		return Choice2;
	}

	public String getChoice3() {
		return Choice3;
	}

	public String getChoice4() {
		return Choice4;
	}

	public String getCorrectAnswer() {
		return CorrectAnswer;
	}

	public String getDifficulty() {
		return Difficulty;
	}

	public void setCategory(String inputCategory) {
		Category = inputCategory;
	}

	public void setText(String inputText) {
		Text = inputText;
	}

	public void setChoice1(String inputChoice1) {
		Choice1 = inputChoice1;
	}

	public void setChoice2(String inputChoice2) {
		Choice2 = inputChoice2;
	}

	public void setChoice3(String inputChoice3) {
		Choice3 = inputChoice3;
	}

	public void setChoice4(String inputChoice4) {
		Choice4 = inputChoice4;
	}

	public void setDifficulty(String inputDifficulty) {
		Difficulty = inputDifficulty;
	}
	
	/*public static String idFinder(Question q,Question[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == q) {
				return String.valueOf(i);
			}
		}
		return String.valueOf(-1);
	}*/
	
	public static int findFirstIndex(String word ,Question[] arr) {
		word = " " + word.toLowerCase() + " ";
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null) break;
			String allText = " " + (arr[i].Text + arr[i].Choice1 + arr[i].Choice2 + arr[i].Choice3 + arr[i].Choice4).toLowerCase(eng).replaceAll("\\p{Punct}", " ").replaceAll("( )+", " ").trim() + " ";
			if (allText.contains(word)) {
				return i;
			}
		}
		return -1;
	}
	
	public static int[] findAllIndexes(String word ,Question[] arr) {
		word = " " + word.toLowerCase() + " ";
		String indexes = "";
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null) break;
			String allText = " " + (arr[i].Text + arr[i].Choice1 + arr[i].Choice2 + arr[i].Choice3 + arr[i].Choice4).toLowerCase(eng).replaceAll("\\p{Punct}", " ").replaceAll("( )+", " ").trim() + " ";
			if (allText.contains(word)) {
				indexes += i + " ";
			}
		}
		return Arrays.stream(indexes.trim().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
	
	public static int getStatsCategory(String _category) {
		return statsCategories.GetValue(_category);
	}
	
	public static void setStatsCategory(String _category, int _value) {
		statsCategories.SetKey(_category, _value);
	}
	
	public static Dict getStatsCategories() {
		return statsCategories;
	}
	
	public static void setStatsCategories() {
		for (String category : categories.GetKeysArray()) {
			statsCategories.SetKey(category, 0);
		}
	}

	public static void categoryPrinter(Question[] arr) {
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null) break;
			
			String word = arr[i].Category;
			if (word.isEmpty()) continue;
			
			if (categories.ContainsKey(word)) {
				categories.SetKey(word, categories.GetValue(word) + 1);
			} else {
				categories.SetKey(word, 1);
			}
		}


		System.out.println("\nCategory\tThe number of questions\n---------\t-----------------------");
		
		categories.SortByKeys();
		for (String key : categories.GetKeysArray()) {
			System.out.print(key);
			cn.getTextWindow().setCursorPosition(22,cn.getTextWindow().getCursorY());
			System.out.println(categories.GetValue(key));
		}
	}

	public static void difficultyPrinter(Question[] arr) {
		
		Dict difficulties = new Dict();
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null) break;
			
			String word = arr[i].Difficulty;
			if (word.isEmpty()) continue;
			
			if (difficulties.ContainsKey(word)) {
				difficulties.SetKey(word, difficulties.GetValue(word) + 1);
			} else {
				difficulties.SetKey(word, 1);
			}
		}

		System.out.println("\nDifficulty level\t\tThe number of questions\n-----------------   \t-----------------------");
		
		difficulties.SortByKeys();
		for (String key : difficulties.GetKeysArray()) {
			System.out.print("\t\t" + key);
			cn.getTextWindow().setCursorPosition(33, cn.getTextWindow().getCursorY());
			System.out.println(difficulties.GetValue(key));
		}
	}

	public static int levenshteinDistance(String str1, String str2) {

		int[][] LevensheinMatrix = new int[str1.length() + 1][str2.length() + 1];
		LevensheinMatrix[0][0] = 0;

		for (int i = 0; i < LevensheinMatrix.length; i++) {
			for (int j = 0; j < LevensheinMatrix[i].length; j++) {

				if (i == 0 && j != 0)
					LevensheinMatrix[i][j] = j;
				if (j == 0 && i != 0)
					LevensheinMatrix[i][j] = i;

				if (i > 0 && j > 0) {
					if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
						LevensheinMatrix[i][j] = LevensheinMatrix[i - 1][j - 1];
					} else
						LevensheinMatrix[i][j] = Math.min(
								Math.min(LevensheinMatrix[i - 1][j - 1], LevensheinMatrix[i - 1][j]),
								LevensheinMatrix[i][j - 1]) + 1;
				}

			}

		}

		return LevensheinMatrix[str1.length()][str2.length()];
	}

	public static void spellChecker(Question[] arr) throws FileNotFoundException {

		for (Question question : arr) {
			if (question == null)
				break;
			String[] textWords = question.Text.replaceAll("\\p{Punct}", " ").replaceAll("( )+", " ").trim().toLowerCase(eng).split(" ");
			String[] c1Words = question.Choice1.replaceAll("\\p{Punct}", " ").replaceAll("( )+", " ").trim().toLowerCase(eng).split(" ");
			String[] c2Words = question.Choice2.replaceAll("\\p{Punct}", " ").replaceAll("( )+", " ").trim().toLowerCase(eng).split(" ");
			String[] c3Words = question.Choice3.replaceAll("\\p{Punct}", " ").replaceAll("( )+", " ").trim().toLowerCase(eng).split(" ");
			String[] c4Words = question.Choice4.replaceAll("\\p{Punct}", " ").replaceAll("( )+", " ").trim().toLowerCase(eng).split(" ");

			String[] allWords = new String[textWords.length + c1Words.length + c2Words.length + c3Words.length + c4Words.length];
			for (int i = 0; i < allWords.length; i++) {
				if (i < textWords.length)
					allWords[i] = textWords[i];
				else if (i < textWords.length + c1Words.length)
					allWords[i] = c1Words[i - textWords.length];
				else if (i < textWords.length + c1Words.length + c2Words.length)
					allWords[i] = c2Words[i - textWords.length - c1Words.length];
				else if (i < textWords.length + c1Words.length + c2Words.length + c3Words.length)
					allWords[i] = c3Words[i - textWords.length - c1Words.length - c2Words.length];
				else if (i < textWords.length + c1Words.length + c2Words.length + c3Words.length + c4Words.length)
					allWords[i] = c4Words[i - textWords.length - c1Words.length - c2Words.length - c3Words.length];
			}

			boolean nall = false;
			
			for (String word : allWords) {
				
				boolean contains = Arrays.stream(Main.dictionaryWords).anyMatch(word::equals);

				char[] chars = word.toCharArray();
				for (char _char : chars) {
					if (Character.isDigit(_char)) {
						contains = true;
						break;
					}
				}
				
				if (!contains) {
					System.out.println("\""+word + "\" is spelled wrong. Do you want to change it?(y/n)");

					String _char = scanner.next();
					
					switch (_char.toLowerCase(eng)) {
					case "y": {
						int[] indexes = findAllIndexes(word,arr);
						String suggest = trySuggest(word);
						
						for (int index : indexes) {
							arr[index].setText((" " + arr[index].Text + " ").replaceAll(" " + word + " ", " " + suggest + " ").trim());
							arr[index].setChoice1((" " + arr[index].Choice1 + " ").replaceAll(" " + word + " ", " " + suggest + " ").trim());
							arr[index].setChoice2((" " + arr[index].Choice2 + " ").replaceAll(" " + word + " ", " " + suggest + " ").trim());
							arr[index].setChoice3((" " + arr[index].Choice3 + " ").replaceAll(" " + word + " ", " " + suggest + " ").trim());
							arr[index].setChoice4((" " + arr[index].Choice4 + " ").replaceAll(" " + word + " ", " " + suggest + " ").trim());
						}
						
						break;
					}
					case "n": {
						break;
					}
					case "nall": {
						nall = true;
						break;
					}
					default:

						throw new IllegalArgumentException("Unexpected value: " + _char);
					}
				}
				
				if (nall) break;
			}
			if (nall) break;
		}
	}

	public static String trySuggest(String word) {
		
		boolean isSuggested = false;
		String suggestedWord = "";
		
		String _char;
		
		for (String suggestion : Main.dictionaryWords) {
			if (isSuggested) {
				break;
			}
			if (suggestion.length() == word.length()) {
				boolean same = true;
				boolean isFirstSuggested = true;
				for (int i = 0; i < word.length(); i++) {
					if (word.charAt(i) != suggestion.charAt(i)) {
						if (same)
							same = false;
						else {
							isFirstSuggested = false;
							break;
						}
					}
				}
				if (isFirstSuggested) {
					System.out.println("->" + word + "<-\n-1->" + suggestion + "\n");
					System.out.println(word + " ===> " + suggestion);

					System.out.println("Do you want to change it to \"" + suggestion + "\"?(y/n)");
					_char = scanner.next();
					switch (_char.toLowerCase(eng)) {
					case "y": {

						suggestedWord = suggestion;
						isSuggested = true;
						break;
					}
					case "n": {

						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + _char.toLowerCase(eng));
					}
				}
			}
		}
		for (String suggestion : Main.dictionaryWords) {
			if (isSuggested) break;
			if (suggestion.length() == word.length()) {
				for (int i = 0; i + 1 < word.length(); i++) {
					for (int j = i + 1; j < word.length(); j++) {

						if (suggestion.equals(word.substring(0, i) + word.charAt(j) + word.substring(i + 1, j)
								+ word.charAt(i) + word.substring(j + 1))) {
							System.out.println("->" + word + "<-\n-o->" + suggestion + "\n");
							System.out.println(word + " ===> " + suggestion);

							System.out.println("Do you want to change it to \"" + suggestion + "\"?(y/n)");
							_char = scanner.next();
							switch (_char.toLowerCase(eng)) {
							case "y": {

								suggestedWord = suggestion;
								isSuggested = true;
								break;
							}
							case "n": {
								break;
							}
							default:
								throw new IllegalArgumentException("Unexpected value: " + _char.toLowerCase(eng));
							}
							continue;
						}
					}
				}
			}
		}
		System.out.println(">>>" + suggestedWord);
		if (isSuggested) {
			System.out.println("The word has been changed.");
		}
		else {
			System.out.println("Unfortunately there is no suggestions for \""+word+"\"");
		}
		return suggestedWord;
	}

	public static void createWordClouds(Question[] arr, String stopwords) {

		String[] wClouds = { "", "", "", "", "" };

		for (int i = 0; i < arr.length; i++) {
			String[] words = arr[i].Text.replaceAll("\\p{Punct}", " ").replaceAll("( )+", " ").trim().split(" ");
			
			if (arr[i] == null) break;
			for (String _word : words) {
				boolean flag = true;
				boolean sameWord = false;
				for (String stopword : stopwords.split(" ")) {
					if (_word.toLowerCase(eng).equals(stopword.toLowerCase(eng))) flag = false;
				}
				for (String word : wClouds[Integer.valueOf(arr[i].Difficulty) - 1].trim().split(" ")) {
					if (_word.toLowerCase(eng).equals(word.toLowerCase(eng))) sameWord = true;
				}
				
				if (flag) {
					if (!sameWord) wClouds[Integer.valueOf(arr[i].Difficulty) - 1] += _word.toLowerCase(eng) + " ";
					break;
				}
			}
		}
		
		Main.wordClouds = wClouds;
	}

}
