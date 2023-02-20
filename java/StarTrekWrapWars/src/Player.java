
public class Player {
	private int posX, posY, life, energyTime,score;
	private Stack backpack;
	
	Player(int posX,int posY){
		life = 5;
		energyTime = 50;
		score = 0;
		backpack = new Stack(8);
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int positionX) {
		this.posX = positionX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int positionY) {
		this.posY = positionY;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getEnergyTime() {
		return energyTime;
	}

	public void setEnergyTime(int energyTime) {
		this.energyTime = energyTime;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Stack getBackpack() {
		return backpack;
	}
	public char removeBackpack() {
		return (char) backpack.pop();
	}
	void stealBackpack() {
		for (int i = -1; i <2; i++) {
			for (int j = -1; j < 2; j++) {
				if(Test.mazeArr[posY + i][posX + j] == 'C') {
					if(backpack.size() >= 2) {
						ComputerTest.addScore((char)backpack.pop());
						ComputerTest.addScore((char)backpack.pop());
					}
					else if(!backpack.isEmpty()) ComputerTest.addScore((char)backpack.pop());
				}
			}
		}
	}
	void addScore(char treasure) {
		switch (treasure) {
		case '1': {
			score++;
			break;
		}case '2': {
			score += 5;
			break;
		}case '3': {
			score += 15;
			break;
		}case '4': {
			score += 50;
			break;
		}case '5': {
			score += 150;
			break;
		}case 'C': {
			score += 300;
			break;
		}
		default:
		}
	}
	public void addBackpack(char item) {
		
		if(!backpack.isFull()) {
			addScore(item);
			
			if(!backpack.isEmpty() && (char)backpack.peek() == item) {
				switch (item) {
				case '2': {
					backpack.pop();
					energyTime += 30;
					break;
				}case '3': {
					backpack.pop();
					backpack.push('*');
					break;
				}case '4': {
					backpack.pop();
					energyTime += 240;
					break;
				}case '5': {
					backpack.pop();
					backpack.push('=');
					break;
				}case '*': {
					backpack.push(item);
					break;
				}case '=': {
					backpack.push(item);
					break;
				}
				default:
				}
			}
			else if(!backpack.isEmpty() && (char) backpack.peek() != '*' && (char) backpack.peek() != '=' && item != '=' && item != '*' && item != '1') {
				backpack.pop();
			}
			
			else if(item!=' ' && item != '1') backpack.push(item);
			
		}
		
	}
}
