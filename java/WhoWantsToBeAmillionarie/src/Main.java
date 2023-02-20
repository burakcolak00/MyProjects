import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import enigma.core.Enigma;

public class Main {

	static Locale eng = new Locale("en", "US");

	public static String[] dictionaryWords;

	static String StopWords = "";

	static Question[] questions;

	static Participant[] participants;
	
	static Participant[] finalParticipants;

	public static String[] wordClouds;

	public static boolean isTimeOver = false;

	public static enigma.console.Console cn = Enigma.getConsole("Who Wants to Be a Millionarie");

	public static void main(String[] args) throws InterruptedException, IOException {

		dictionaryWords = new String[370103];
		wordClouds = new String[5];

		enigma.console.Console cn = Enigma.getConsole("Who Wants to Be a Millionarie");

		cn.getTextWindow();

		Scanner dictionaryScanner = new Scanner(new File("dictionary.txt"));

		int _i = 0;
		while (dictionaryScanner.hasNextLine()) {
			dictionaryWords[_i] = dictionaryScanner.nextLine();
			_i++;
		}

		boolean menu = true;
		// Printing Menu Screen
		do {
			int line = 0;
			int index = 0;
			System.out.println(" W H O     W A N T S    T O      B E    A    M I L L I O N A R I E        ");
            System.out.println("                *******MENU*******");
            System.out.println("                1. Load questions");
            System.out.println("                2. Load participants");
            System.out.println("                3. Start competition");
            System.out.println("                4. Show statics");
            System.out.println("                5. Instructions");
            System.out.println("                6. Credits");
            System.out.println("                7. Exit");
            
            


			System.out.print("\n> Enter your choice: ");
			Scanner scanner = new Scanner(System.in);
			String cho = scanner.next();

			// These cases will be filled.
			switch (cho) {
			case "1":{
				// Loading the questions
				Main.consoleClear();
				System.out.print(">Enter file name to load: ");

				try {
					// Reading File
					String FileName = scanner.next();
					File questionsFile = new File(FileName);
					Scanner fileScanner = new Scanner(questionsFile);

					while (fileScanner.hasNextLine()) {
						line++;
						fileScanner.nextLine();
					}

					Question[] tempQuestions = new Question[line];

					int removedQuestionCounter = 0;
					fileScanner.close();
					fileScanner = new Scanner(questionsFile);
					// Defining the questions and put them to an array
					while (fileScanner.hasNextLine()) {
                        String[] qParts = fileScanner.nextLine().split("#");
                        if(qParts.length == 8) {
                        	tempQuestions[index] = new Question(index, qParts[0], qParts[1], qParts[2], qParts[3], qParts[4], qParts[5], qParts[6], qParts[7]);
                            index++;
                        }
                        else {
                        	removedQuestionCounter++;
                        }
                    }
					questions = new Question[line - removedQuestionCounter];
					for (int i = 0; i < questions.length; i++) {
						questions[i] = tempQuestions[i];
					}

					Question.spellChecker(questions);
					Question.categoryPrinter(questions);
					Question.setStatsCategories();
					Question.difficultyPrinter(questions);

					File stopWordsFile = new File("stop_words.txt");
					Scanner StopScanner = new Scanner(stopWordsFile);
					// String StopWords = "";
					while (StopScanner.hasNext()) {
						StopWords += (String) StopScanner.next() + " ";
					}
					

					fileScanner.close();
					StopScanner.close();
				} catch (FileNotFoundException e) {
					System.out.println("> File can not be found!");
				}
				
				break;
			}
			case "2": {
				// Loading the participants
				Main.consoleClear();
				System.out.println(">Enter file name to load: ");

				try {
					String FileName = scanner.next();
					File participantFile = new File(FileName);
					Scanner fileScanner = new Scanner(participantFile);

					while (fileScanner.hasNextLine()) {
						line++;
						fileScanner.nextLine();
					}

					participants = new Participant[line];
					finalParticipants = new Participant[line];
					fileScanner.close();

					fileScanner = new Scanner(participantFile);
					while (fileScanner.hasNextLine()) {
						String[] pParts = fileScanner.nextLine().split("#");
						participants[index] = new Participant(index, pParts[0], pParts[1], pParts[2], pParts[3]);
						finalParticipants[index] = new Participant(index, pParts[0], pParts[1], pParts[2], pParts[3]);
						index++;
					}

					for (Participant participant : participants) {
						if (participant != null) {

							participant.printParticipantInfo();
						}
					}

					fileScanner.close();
				} catch (FileNotFoundException e) {
					System.out.println("> File can not be found!");
				}

				break;
			}
			case "3": {
				Question.createWordClouds(questions, StopWords);
				
				consoleClear();
				
				boolean newContestant = true;
				FileWriter fileWriter = new FileWriter("output.txt");
			    PrintWriter printWriter = new PrintWriter(fileWriter);
				do {
					boolean competitionReady = true;
					for (String wordCloud : wordClouds) {
						if (wordCloud.equals("")) {
							competitionReady = false;
						}
					}
					if (!competitionReady) System.out.println("> Word cloud is null!");
					else {
						if (participants.length == 0) {
							competitionReady = false;
							System.out.println("> There is no participant!");
						}
					}
					
					

					if (competitionReady) {
						// Starting the competition
						Random rnd = new Random();
						Participant currentParticipant = participants[rnd.nextInt(0, participants.length)];
						System.out.println("Contestant: " + currentParticipant.getName());

						Participant[] tempParticipants = participants;
						participants = new Participant[participants.length - 1];
						boolean flag = true;
						for (int i = 0; i < participants.length; i++) {
							if (currentParticipant.getID() == tempParticipants[i].getID()) flag = false;
							if (flag) participants[i] = tempParticipants[i];
							else participants[i] = tempParticipants[i + 1];
						}

						int currentDifficulty = 1;
						TimerCounter.currentDifficulty = currentDifficulty;
						String answer;
						boolean askQuestion = true;

						boolean doubleDipUsable = true;
						boolean halfUsable = true;
						TimerCounter.doubleDipUsable = true;
						TimerCounter.halfUsable = true;

						while (askQuestion) {
							askQuestion = false;
							System.out.println("\nWord Cloud:\n" + wordClouds[currentDifficulty - 1]);
							System.out.print("> Enter your selection: ");
							answer = scanner.next().toLowerCase(eng);

							if ((" " + wordClouds[currentDifficulty - 1] + " ").contains(" " + answer + " ")) {
								int[] indexes = Question.findAllIndexes(answer, questions);
								
								Question currentQuestion = questions[indexes[rnd.nextInt(0, indexes.length)]];
								TimerCounter.countDown = 20;
								
								Question[] tempQuestions = questions;
								questions = new Question[questions.length - 1];
								flag = true;
								for (int i = 0; i < questions.length; i++) {
									if (currentQuestion.getID() == tempQuestions[i].getID()) flag = false;
									if (flag) questions[i] = tempQuestions[i];
									else questions[i] = tempQuestions[i + 1];
								}

								boolean doubleDip = false;

								boolean[] eliminatedChoices = new boolean[4];

								while (true) {

									consoleClear();
									System.out.println(currentQuestion.getText());
									System.out.print("A)");
									if (!eliminatedChoices[0])
										System.out.println(currentQuestion.getChoice1());
									else
										System.out.println();
									System.out.print("B)");
									if (!eliminatedChoices[1])
										System.out.println(currentQuestion.getChoice2());
									else
										System.out.println();
									System.out.print("C)");
									if (!eliminatedChoices[2])
										System.out.println(currentQuestion.getChoice3());
									else
										System.out.println();
									System.out.print("D)");
									if (!eliminatedChoices[3])
										System.out.println(currentQuestion.getChoice4());
									else
										System.out.println();

									System.out.print("\n> Enter your choice (E:Exit): \n");

									TimerCounter multiThread = new TimerCounter();

									multiThread.run();

									if (!isTimeOver) {
										if (multiThread.answer.equals("z")) {
											if (halfUsable) {
												int random = -1;

												while (random == -1 || random == currentQuestion.getCorrectAnswer().toLowerCase(eng).charAt(0) - 'a') {
													random = rnd.nextInt(0, 4);
												}

												eliminatedChoices[random] = true;
												random = -1;

												while (random == -1 || random == currentQuestion.getCorrectAnswer().toLowerCase(eng).charAt(0) - 'a' || eliminatedChoices[random]) {
													random = rnd.nextInt(0, 4);
												}

												eliminatedChoices[random] = true;

												halfUsable = false;
												TimerCounter.halfUsable = false;
											} else
												System.out.println("\n> 50% Joker is already used, you can not use more than once");
										} else if (multiThread.answer.equals("x")) {
											if (doubleDipUsable) {
												doubleDip = true;
												doubleDipUsable = false;
												TimerCounter.doubleDipUsable = false;
											} else
												System.out.println(
														"\n> Double Dip Joker is already used, you can not use more than once");
										} else if (multiThread.answer.equals("e")) {
											switch (currentDifficulty) {
								            case 1: {
								                System.out.print("Your prize: $0");
								                break;
								            }
								            case 2: {
								                System.out.print("Your prize: $20,000");
								                break;
								            }
								            case 3: {
								                System.out.print("Your prize: $100,000");
								                break;
								            }
								            case 4: {
								                System.out.print("Your prize: $250,000");
								                break;
								            }
								            case 5: {
								                System.out.print("Your prize: $500,000");
								                break;
								            }
								            default:
								                throw new IllegalArgumentException("Unexpected value: " + currentDifficulty);
								            }
											while (true) {
												System.out.println("\n\n\nNext contestant?(y/n): ");
												String nextContestant = scanner.next().toLowerCase();
												
												if (nextContestant.equals("n")) {
													newContestant = false;
													break;
												}
												else if (nextContestant.equals("y")) {
													Question.createWordClouds(questions, StopWords);
													break;
												}
												else System.out.println("\n> Wrong input! please enter input again.");
											}
											break;
										}
										else if ((!eliminatedChoices[0] && multiThread.answer.equals("a")) || (!eliminatedChoices[1] && multiThread.answer.equals("b")) || (!eliminatedChoices[2] && multiThread.answer.equals("c"))|| (!eliminatedChoices[3] && multiThread.answer.equals("d"))) {
											if (multiThread.answer.equals(currentQuestion.getCorrectAnswer())) {
												Question.setStatsCategory(currentQuestion.getCategory(), Question.getStatsCategory(currentQuestion.getCategory()) + 1);
												currentParticipant.increaseSuccess();
												printWriter.write(currentQuestion.getID()+","+currentParticipant.getID()+","+"True\n");
												
												currentDifficulty++;
												TimerCounter.currentDifficulty = currentDifficulty;
												System.out.println("\n> Correct answer!");
												
												if (currentDifficulty <= 5) askQuestion = true; 
												else {
													
													System.out.println("You won: $1,000,000!!!");
													System.out.println("\n> You are a Millionarie...\n");

													while (true) {
														System.out.println("\n\n\nNext contestant?(y/n): ");
														String nextContestant = scanner.next().toLowerCase();
														
														if (nextContestant.equals("n")) {
															newContestant = false;
															break;
														}
														else if (nextContestant.equals("y")) {
															Question.createWordClouds(questions, StopWords);
															break;
														}
														else System.out.println("\n> Wrong input! please enter input again.");
													}
												}
												break;
											} else {
												Question.setStatsCategory(currentQuestion.getCategory(), Question.getStatsCategory(currentQuestion.getCategory()) - 1);
												if (doubleDip) {
													eliminatedChoices[multiThread.answer.charAt(0) - 'a'] = true;
													System.out.println("\n> Wrong Answer, Please Make Your Second Choice!");
													doubleDip = false;
												} else {
													System.out.println("\n> Wrong answer! Game Over...\n");
													printWriter.write(currentQuestion.getID()+","+currentParticipant.getID()+","+"False\n");
													if(currentDifficulty == 3 || currentDifficulty == 4) {
														System.out.println("Your prize: $100,000");
													}
													else if(currentDifficulty == 1 || currentDifficulty == 2) {
														System.out.println("Your prize: $0");
													}
													else System.out.println("Your prize: $500,000");

													while (true) {
														System.out.println("\n\n\nNext contestant?(y/n): ");
														String nextContestant = scanner.next().toLowerCase();
														
														if (nextContestant.equals("n")) {
															newContestant = false;
															break;
														}
														else if (nextContestant.equals("y")) {
															Question.createWordClouds(questions, StopWords);
															break;
														}
														else System.out.println("\n> Wrong input! please enter input again.");
													}
													break;
												}
											}
										} else {
											System.out.println("\n> Wrong input! please enter input again.-----------");
										}
										multiThread.answer = "";

									} else {
										System.out.println("\n\n\nNext contestant?(y/n): ");
										printWriter.write(currentQuestion.getID()+","+currentParticipant.getID()+","+"False\n");
										if(currentDifficulty == 3 || currentDifficulty == 4) {
											System.out.println("Your prize: $100,000");
										}
										else if(currentDifficulty == 1 || currentDifficulty == 2) {
											System.out.println("Your prize: $0");
										}
										else System.out.println("Your prize: $500,000");

										while (true) {
											String nextContestant = scanner.next().toLowerCase();
											
											if (nextContestant.equals("n")) {
												newContestant = false;
												break;
											}
											else if (nextContestant.equals("y")) {
												Question.createWordClouds(questions, StopWords);
												isTimeOver = false;
												break;
											}
											else {
												System.out.println("\n> Wrong input! please enter input again.");
											}
										}
										break;
									}
								}
								
								
							} else {
								System.out.println("\n> WordCloud does not contains \"" + answer
										+ "\" word! please enter selection again.");
								askQuestion = true;
							}
						}
					} else {
						break;
					}
					
				} while (newContestant);
				
				printWriter.close();

				break;
			}
			case "4": {
				
				
				Scanner outputScanner = new Scanner(new File("output.txt"));
				Dict mostSuccess = new Dict();
				Dict category = new Dict();
				Dict city = new Dict();
				float age0 = 0, age1 = 0, age2 = 0;
				int counter0 = 0, counter1 =0,counter2=0;
				
				while (outputScanner.hasNext()) {
					String[] output =  outputScanner.next().split("[,]", 0);
					
					int QuestionID = Integer.valueOf(output[0]);
					int ParticipantID = Integer.valueOf(output[1]);
					String IsAnsweredCorrectly = output[2];
					
					
					var parKey = finalParticipants[ParticipantID].getName();
					var parValue = 0;
					
					var cateKey = questions[QuestionID].getCategory();
					var cateValue = 0;
					
					var cityKey = finalParticipants[ParticipantID].getCity();
					var cityValue = 0;
					
					if(!mostSuccess.ContainsKey(parKey)) {
						if(finalParticipants[ParticipantID].getAge() <30) counter0++;
						else if(finalParticipants[ParticipantID].getAge()>50) counter2++;
						else counter1++;
					}
					
					if(!mostSuccess.ContainsKey(parKey)) mostSuccess.SetKey(parKey, 0);
					else parValue = mostSuccess.GetValue(parKey);
					
					if(!category.ContainsKey(cateKey)) category.SetKey(cateKey, 0);
					else cateValue = category.GetValue(cateKey);
					
					if(!city.ContainsKey(cityKey)) city.SetKey(cityKey, 0);
					else cityValue = city.GetValue(cityKey);
					
					city.SetKey(cityKey, cityValue+1);
					
					if(IsAnsweredCorrectly.equals("True")) {
						parValue++;
						mostSuccess.SetKey(parKey, parValue);
						
						cateValue++;
						category.SetKey(cateKey, cateValue);
						
						if(finalParticipants[ParticipantID].getAge() <30) age0++;
						else if(finalParticipants[ParticipantID].getAge()>50) age2++;
						else age1 ++;
					}
					else {
						parValue--;
						mostSuccess.SetKey(parKey, parValue);
						
						cateValue--;
						category.SetKey(cateKey, cateValue);
					}
					
				}
				mostSuccess.SortByKeys();
				category.SortByKeys();
				city.SortByKeys();
				String[] success = mostSuccess.GetKeysArray();
				String[] cate = category.GetKeysArray();
				String[] _city = city.GetKeysArray();
				
				float avg0 = age0/counter0;
				float avg1 = age1/counter1;
				float avg2 = age2/counter2;
				
				if(!(avg0 >= 0)) avg0 = 0;
				if(!(avg1 >= 0)) avg1 = 0;
				if(!(avg2 >= 0)) avg2 = 0;
				
				System.out.println("•	The most successful contestant: " + success[0]);
				System.out.println("•	The category with the most correctly answered: " + cate[0]);
				System.out.println("•	The category with the most badly answered: " + cate[cate.length-1]);
				System.out.println("•	Averages: \n	 Age<=30 : " +avg0+"\t30<Age<=50 : "+avg1+"\tAge>50 : "+avg2);
				System.out.println("•	The city with the highest number of participants: " + _city[0]);
				
				
				break;
			}
			case "5":{
				
				consoleClear();
				
				System.out.println("1- Load your file that includes your questions by choosing option \"1\"\n");
				System.out.println("2- Load your file that includes informations about the participants by\nchoosing option \"2\"\n");
				System.out.println("3- You can start the game by pressing \"3\".");
				System.out.println("You have two jokers in game. One of them is doubledip which you can\nactivate by pressing \"x\"");
				System.out.println("This lifeline allows a contestant to give a second answer, if the first one\n"
								 + "was wrong. And the other joker is 50% which you can activate by pressing \"z\"\n"
								 + "This lifeline eliminates two inccorect answers leaving the player between\n"
								 + "one correct and one wrong answer The contestant has to invoke the lifeline\n"
								 + "before giving the answer.\n");
				System.out.println("You can quit playing whenever you want by pressing `7` in menu\n\n\nHAVE FUN!");
				break;
			}
			case "6":{
				// CREDITS
                consoleClear();
                System.out.println("\n\n\n");
                System.out.println("                  o                          .-\"\"\" -.");
                System.out.println("                                           / .===. \\");
                System.out.println("                                           \\/ 6 6 \\/");
                System.out.println("                                           ( \\___/ )");
                System.out.println("                      _________________ooo__\\_____/_____________________");
                System.out.println("                     /                                                  \\");
                System.out.println("                     |   Burak Çolak    Fatih Orhanlar    Yaðýz Gökdeli      |");
                System.out.println("                     \\______________________________ooo_________________/");
                System.out.println("                                            |  |  |");
                System.out.println("                                            |_ | _|");
                System.out.println("                                            |  |  |");
                System.out.println("                                            |__|__|");
                System.out.println("                                            /-'Y'-\\");
                System.out.println("                                           (__/ \\__)");
                break;
			}
			case "7": {
				System.exit(0);
				break;
			}

			default: {
				System.out.println("> Please enter a valid option!");
			}
			}

			System.out.println("\n\n> Press Enter for continue...");

			scanner.close();

			scanner = new Scanner(System.in);
			scanner.nextLine();
			consoleClear();
			scanner.close();
		} while (menu);
	}

	static void consoleClear() {
		cn.getTextWindow().setCursorPosition(0, 0);
		System.out.println(
				"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ");
		cn.getTextWindow().setCursorPosition(0, 0);
	}

}
