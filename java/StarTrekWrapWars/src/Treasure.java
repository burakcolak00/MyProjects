
public class Treasure {
	private int posX,posY,prevDir,prevAxis,stepCount;
	private char value;
	private boolean isMoveable,isInBackpack;
	
	Treasure(int posX,int posY,char value) throws InterruptedException{
		this.value = value;
		if(value == '4' || value == '5') isMoveable = true;
		else  isMoveable = false;	
		this.posX = posX;
		this.posY = posY;
		this.setInBackpack(false);
		resetStepCount();
	}
	public void resetStepCount() {
		this.stepCount = Test.randomInt(1, 6);
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

	public int getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public boolean isMoveable() {
		return isMoveable;
	}

	public void setMoveable(boolean isMoveable) {
		this.isMoveable = isMoveable;
	}
	
	void move() throws InterruptedException {
		int axis,dir = 0;   // if axis = 0 treasure will move on x axis
		/*if(posX== Test.player.getPosX() && posY == Test.player.getPosY()) {
			setInBackpack(true);
			return;
		}*/
		if(isMoveable) {
			int counter = 0;
			if(stepCount > 0 && movementChecker(prevDir, prevAxis)) {
				axis = prevAxis;
				dir = prevDir;
				stepCount--;
			}
			else {
				resetStepCount();
				do {
					counter++;
					axis = Test.randomInt(0, 2); // 0--> x axis , 1--> y axis
					dir = Test.randomInt(0, 2); // 0 is right or up 
						
				} while (!movementChecker(dir, axis) && counter < 8);
			}
			
			if(counter < 8) {
				Test.mazeArr[posY][posX] = ' ';
				if(axis == 0 && dir == 0) posX++;
				else if(axis == 0 && dir == 1) posX--;
				else if(axis == 1 && dir == 0) posY--;
				else if(axis == 1 && dir == 1) posY++;
				Test.mazeArr[posY][posX] = value;
			}
			prevAxis = axis;
			prevDir = dir;
					
			
		}
		else return;
	}

	public boolean isInBackpack() {
		return isInBackpack;
	}

	public void setInBackpack(boolean isInBackpack) {
		this.isInBackpack = isInBackpack;
	}
	boolean movementChecker(int dir, int axis) {
		boolean flag = true;
		if((dir == 0 && axis == 0 && Test.mazeArr[posY][posX+1] != ' ') || (dir == 1 && axis == 0 && Test.mazeArr[posY][posX-1] != ' ') || (dir == 0 && axis == 1 && Test.mazeArr[posY-1][posX] != ' ') || (dir == 1 && axis == 1 && Test.mazeArr[posY+1][posX] != ' '))
			flag = false;
		return flag;
	}
}
