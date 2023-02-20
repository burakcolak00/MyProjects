
public class Player {
	private int score;
	private String name;
	Player(String data){
		String[] splitter = data.split(" ");
		this.score = Integer.valueOf(splitter[1]);
		this.name = splitter[0];
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
