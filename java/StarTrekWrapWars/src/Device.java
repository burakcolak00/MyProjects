public class Device {
	private int posX,posY,remainingTime;
	 // trap--> stops, wrap ---> removes
	private char value;
	
	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	Device(int posX,int posY, char value){
		this.posX = posX;
		this.posY = posY;
		this.value = value;
		remainingTime = 25;
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

	public int getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}

	void deviceEvent() {
		if(remainingTime == 0) {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if(Test.mazeArr[posY + i][posX + j] == '4' || Test.mazeArr[posY + i][posX + j] == '5') 
						Test.treasures[Test.indexTreasureFinder(posX + j, posY + i)].setMoveable(true);
					else if(Test.mazeArr[posY + i][posX + j] == 'C')
						Test.computers[Test.indexComputerFinder(posX + j, posY + i)].setMoveable(true);
				}
			}
			Test.devices = Test.removeItemFromArray(Test.devices, Test.indexDeviceFinder(posX, posY));
			Test.mazeArr[posY][posX] = ' ';
			return;
		}
		if(value == '*') {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if(Test.mazeArr[posY + i][posX + j] != '#' && Test.mazeArr[posY + i][posX + j] != 'P' && 
							Test.mazeArr[posY + i][posX + j] != '=' && Test.mazeArr[posY+i][posX+j] != '*' && Test.mazeArr[posY+i][posX+j] != ' ') {
						Test.player.addScore(Test.mazeArr[posY+i][posX+j]);
						if(Test.mazeArr[posY+i][posX+j] != 'C') Test.treasures = Test.removeItemFromArray(Test.treasures, Test.indexTreasureFinder(posX + j, posY + i));
						else Test.computers = Test.removeItemFromArray(Test.computers, Test.indexComputerFinder(posX + j, posY + i));
						Test.mazeArr[posY + i][posX + j] = ' ';
					}
				}
			}
		}
		else if(value == '=') {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if(Test.mazeArr[posY + i][posX + j] != '#' && Test.mazeArr[posY + i][posX + j] != 'P' && 
							Test.mazeArr[posY + i][posX + j] != '=' && Test.mazeArr[posY+i][posX+j] != '*' && Test.mazeArr[posY+i][posX+j] != ' ') {
						if(Test.mazeArr[posY + i][posX + j] != 'C')
							Test.treasures[Test.indexTreasureFinder(posX + j, posY + i)].setMoveable(false);
						else if(Test.mazeArr[posY + i][posX + j] == 'C')
							Test.computers[Test.indexComputerFinder(posX + j, posY + i)].setMoveable(false);
						
					}
				}
			}
		}
	}
}
