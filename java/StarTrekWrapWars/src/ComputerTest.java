import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ComputerTest {
	private int posX, posY;
	static char[][] arr = new char[Test.mazeArr.length][Test.mazeArr[0].length];
	static char[][] tempArr1;
	boolean isMoveable;
	public static int score = 0;

	ComputerTest(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		isMoveable = true;
	}

	public boolean isMoveable() {
		return isMoveable;
	}

	public void setMoveable(boolean isMoveable) {
		this.isMoveable = isMoveable;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int Score) {
		score = Score;
	}

	static char[][] readArrayForComputer(char[][] arr) {
		char[][] newArr = new char[arr.length][arr[0].length];

		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr[0].length; j++) {
				if (' ' == arr[i][j])
					newArr[i][j] = '1';

				else if ('#' == arr[i][j] || 'C' == arr[i][j]  || '*' == arr[i][j] || '=' == arr[i][j])
					newArr[i][j] = '0';

				else
					newArr[i][j] = '2';
			}

		return newArr;
	}

	static void addScore(char treasure) {
		switch (treasure) {
		case '1': {
			score += 2;
			break;
		}
		case '2': {
			score += 10;
			break;
		}
		case '3': {
			score += 30;
			break;
		}
		case '4': {
			score += 100;
			break;
		}
		case '5': {
			score += 300;
			break;
		}
		case '=': {
			score += 300;
			break;
		}
		case '*': {
			score += 300;
			break;
		}
		default:
		}
	}
	
	static int countBox(char[][] arr, int[][] corArr) {
		char[][] tempArr = copyArray(arr);
		int count = 0;
		for (int i = 0; i < corArr.length; i++) {

			int row = corArr[i][0];
			int column = corArr[i][1];

			if (tempArr[row - 1][column] == '1') {
				count++;
				tempArr[row - 1][column] = tempArr[row][column];
			}
			if (tempArr[row][column - 1] == '1') {
				count++;
				tempArr[row][column - 1] = tempArr[row][column];
			}
			if (tempArr[row + 1][column] == '1') {
				count++;
				tempArr[row + 1][column] = tempArr[row][column];
			}
			if (tempArr[row][column + 1] == '1') {
				count++;
				tempArr[row][column + 1] = tempArr[row][column];
			}
		}
		return count;
	}

	void move(char[][] arr) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if(isMoveable) {
			char direction;
			int computerColumn = this.posX;
			int computerRow = this.posY;
			char[][] tempMapArr=readArrayForComputer(arr);
			tempMapArr[computerRow][computerColumn]='0';
			
			arr[computerRow][computerColumn]=' ';
			
			if(tempMapArr[computerRow-1][computerColumn]=='2')
				direction='W';
			else if(tempMapArr[computerRow][computerColumn-1]=='2')
				direction= 'A';
			else if(tempMapArr[computerRow+1][computerColumn]=='2')
				direction= 'S';
			else if(tempMapArr[computerRow][computerColumn+1]=='2')
				direction= 'D';
			
			else {
				int[][] corArr = {{computerRow,computerColumn}};
				
				int size=countBox(tempMapArr,corArr);
				
				corArr = new int[size][2];
				int index=0;
				
				if(tempMapArr[computerRow-1][computerColumn]=='1') {
					tempMapArr[computerRow-1][computerColumn]='W';
			        corArr[index][0]=computerRow-1;
			        corArr[index][1]=computerColumn;
			        index++;
				}
				if(tempMapArr[computerRow][computerColumn-1]=='1') {
					tempMapArr[computerRow][computerColumn-1]= 'A';
					corArr[index][0]=computerRow;
			        corArr[index][1]=computerColumn-1;
			        index++;
				}
				if(tempMapArr[computerRow+1][computerColumn]=='1') {
					tempMapArr[computerRow+1][computerColumn]= 'S';
					corArr[index][0]=computerRow+1;
			        corArr[index][1]=computerColumn;
			        index++;
				}
				if(tempMapArr[computerRow][computerColumn+1]=='1') {
					tempMapArr[computerRow][computerColumn+1]= 'D';
					corArr[index][0]=computerRow;
			        corArr[index][1]=computerColumn+1;
			        index++;
				}
				
			
			direction=calculateMoves(tempMapArr,corArr);
			}
			
			switch(direction) {
			case 'W':
				computerRow--;
				break;
			
			case 'A':
				computerColumn--;
				break;
				
			case 'S':
				computerRow++;
				break;
				
			case 'D':
				computerColumn++;
				break;
				
			}
			this.posX = computerColumn;
			this.posY = computerRow;
			
			if(Test.mazeArr[posY][posX] == '1' || Test.mazeArr[posY][posX] == '2' || Test.mazeArr[posY][posX] == '3' || Test.mazeArr[posY][posX] == '4' || Test.mazeArr[posY][posX] == '5') {
				addScore(Test.mazeArr[posY][posX]);
				Test.treasures = Test.removeItemFromArray(Test.treasures, Test.indexTreasureFinder(posX, posY));
			}
				
			
		if(Test.mazeArr[posY][posX]=='P')

		{
			AudioInputStream lifeAudio = AudioSystem.getAudioInputStream(new File("loselifemusic.wav"));
			Clip lifeClip = AudioSystem.getClip();
			lifeClip.open(lifeAudio);
			lifeClip.start();
			Test.player.setLife(Test.player.getLife() - 1);
			Test.spawningPlayer();
		}

		arr[computerRow][computerColumn]='C';
		}
		

	}

	static char calculateMoves(char[][] arr, int[][] corArr) {
			int size=countBox(arr,corArr);
			int[][] newCorArr = new int[size][2];
			if(size==0)
                return '0';
			int index=0;
			for(int i=0;i<corArr.length;i++) {
			
		    int row = corArr[i][0];
		    int column = corArr[i][1];
			
			if(arr[row-1][column]=='2' ||arr[row][column-1]=='2'||arr[row][column+1]=='2'||arr[row+1][column]=='2')
				return arr[row][column];
			
			
			if(arr[row-1][column]=='1'){//up
				arr[row-1][column]= arr[row][column];
				newCorArr[index][0]= row-1; 
				newCorArr[index][1]= column; 
				index++;
			}
			if(arr[row][column-1]=='1'){//left
				arr[row][column-1]= arr[row][column];
				newCorArr[index][0]= row; 
				newCorArr[index][1]= column-1;
				index++;
			}
			if(arr[row+1][column]=='1'){//down
				arr[row+1][column]= arr[row][column];
				newCorArr[index][0]= row+1; 
				newCorArr[index][1]= column;
				index++;
			}
			if(arr[row][column+1]=='1'){//right
				arr[row][column+1]= arr[row][column];
				newCorArr[index][0]= row; 
				newCorArr[index][1]= column+1;
				index++;
			}
			}
			return calculateMoves(arr,newCorArr);
			
		}

	static char[][] copyArray(char[][] arr){
		char[][] newArr = new char[arr.length][arr[0].length];
		
		for(int i=0 ; i<arr.length;i++)
			for(int j=0; j<arr[0].length;j++ )
				newArr[i][j]=arr[i][j];
		
		return newArr;
		
	}

}
