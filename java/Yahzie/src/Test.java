import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws IOException {
		int turn = 1,player1Score = 0, player2Score = 0;
		SingleLinkedList player1 = new SingleLinkedList();//defining necessary lists
		SingleLinkedList player2 = new SingleLinkedList();
		while(turn <= 10) {
			for (int i = 0; i < 3; i++) player1.add((int)(Math.random()*6+1)); // generating a number from dice
			for (int i = 0; i < 3; i++) player2.add((int)(Math.random()*6+1));
			// displaying events
			System.out.println("_____________________________________________________________________________________\nTurn: "+ turn);
			System.out.print("Player1: ");
			if(player1.size()>0)player1.display();
			System.out.println("Score: "+player1Score);
			System.out.print("Player2: ");
			if(player1.size()>0)player2.display();
			System.out.println("Score: "+player2Score);
			boolean isThereAnyPoint = false;
			while(player1.yahtzeeChecker() != 0) { // yahtzee control
				player1.yahtzeeDelete(player1.yahtzeeChecker(),4);
				player1Score += 10;
				isThereAnyPoint = true;
			}
			while(player2.yahtzeeChecker() != 0) { // yahtzee control
				player2.yahtzeeDelete(player2.yahtzeeChecker(),4);
				player2Score += 10;
				isThereAnyPoint = true;
			}
			if(player1.largeStraightChecker()) { // largeStraigth control
				isThereAnyPoint = true;
				for (int i = 1; i < 7; i++)
					player1.yahtzeeDelete(i,1);
			}
			if(player2.largeStraightChecker()) { // largeStraigth control
				isThereAnyPoint = true;
				for (int i = 1; i < 7; i++)
					player2.yahtzeeDelete(i,1);
			}
			if(isThereAnyPoint) {  // if there are any point increasement, displaying it on the screen
				System.out.print("\nPlayer1: ");
				if(player1.size()>0)player1.display();
				System.out.println("Score: "+player1Score);
				System.out.print("Player2: ");
				if(player1.size()>0)player2.display();
				System.out.println("Score: "+player2Score);
			}
			turn++;
		}
		Scanner highScoreScanner = new Scanner(new File("HighScoreTable.txt")); // reading high score file
		SingleLinkedList HighScore = new SingleLinkedList();
		while(highScoreScanner.hasNextLine()) {
			Player newPlayer = new Player(highScoreScanner.nextLine() + " " + highScoreScanner.nextLine());
			HighScore.sortedAdd(newPlayer); // adding players to table
		}
		if(player2Score == player1Score)System.out.println("\nGame Over!\n\nTie!!\n"); 
		else if(player1Score > player2Score) {
			System.out.println("\nGame Over!\n\nWinner: player1\n");
			HighScore.sortedAdd(new Player("player1 "+player1Score));
		}
		else {
			System.out.println("\nGame Over!\n\nWinner: player2\n");
			HighScore.sortedAdd(new Player("player2 "+player2Score));
		}
		
		HighScore.displayLines();
		
		FileWriter fileWriter = new FileWriter("HighScoreTable.txt");
	    PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.write(HighScore.StringForFile()); // writing to txt
		printWriter.close();
	}

}
