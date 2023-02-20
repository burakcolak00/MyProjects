public class SingleLinkedList {
	private Node head;
	public void add(Object data) {
		Node newNode = new Node(data);
		if(head == null) 
			head = newNode;
		else {
			Node temp = head;
			while(temp.getLink() != null) {
				temp = temp.getLink();
			}
			temp.setLink(newNode);
		}
	}
	void sortedAdd(Player player) {
		Object data = player.getName() + " " + player.getScore();
		if(head == null) {
			Node newNode = new Node(data);
			head = newNode;
		}
		else {
			Node temp = head;
			Player tempPlayer = new Player(temp.getData().toString());
			Player headPlayer = new Player(head.getData().toString());
			Node previous = null;
			Node newNode = new Node(data);
			while(temp != null && player.getScore() <= tempPlayer.getScore()) {
				previous = temp;
				temp = temp.getLink();
				if(temp != null)tempPlayer = new Player(temp.getData().toString());
			} 
			if(temp == null) previous.setLink(newNode);
			else if(player.getScore() > headPlayer.getScore()){
				newNode.setLink(temp);
				head = newNode;
			}
			else {
				newNode.setLink(temp);
				if(previous != null)
				previous.setLink(newNode);
			}
		}
		
	}
	public int size() {
		int counter = 0;
		if(head == null) return 0;
		else {
			Node temp = head;
			
			while(temp != null) {
				temp = temp.getLink();
				counter++;
			}
		}
		return counter;
	}
	public void display() {
		if(head == null) System.out.println("List is empty");
		else {
			Node temp = head;
			while(temp != null) {
				//System.out.println(temp.getData() + " ");
				System.out.print(temp.getData() + " ");
				temp = temp.getLink();
			}
		}
		
	}
	void displayLines() {
		if(head == null) System.out.println("List is empty");
		else {
			Node temp = head;
			while(temp != null) {
				System.out.println(temp.getData() + " ");
				//System.out.print(temp.getData() + " ");
				temp = temp.getLink();
			}
		}
		
	}
	String StringForFile() {
		String result = "";
		if(head == null) System.out.println("List is empty");
		else {
			int ten = 0;
			Node temp = head;
			Player tempPlayer = new Player(temp.getData().toString());
			while(temp != null && ten < 10) {
				ten++;
				result += tempPlayer.getName() + "\n" + tempPlayer.getScore() + "\n";
				temp = temp.getLink();
				if(temp != null)tempPlayer = new Player(temp.getData().toString());
			}
		}
		return result;
	}
	public void delete(Object data) {
		if(head == null) System.out.println("List is empty");
		else {
			while(head != null && (Integer)head.getData() == (Integer)data)head = head.getLink();
			Node previous = null;
			Node temp = head;
			while(temp != null) {
				if((Integer)temp.getData() == (Integer) data) {
					previous.setLink(temp.getLink());
					temp = previous;
				}
				previous= temp;
				temp = temp.getLink();
			}
		}
	}
	public boolean search(Object data) {
		if(head == null)System.out.println("List is empty");
		else {
			Node temp = head;
			while(temp != null) {
				if(temp.getData() == data) return true;
				temp = temp.getLink();
			}
		}
		return false;
	}
	
	 int yahtzeeChecker() {	// returns 0 if there is no yahtzee, otherwise returns repeted value.
		for (int i = 1; i < 7; i++) {
			int counter = 0;
			Node temp = head;
			for (int j = 0; j < size(); j++) {
				while(temp != null) {
					if((Integer)temp.getData() == i)counter++;
					if(counter >= 4) return i;
					temp = temp.getLink();
				}
			}
		}
		return 0;
		
	}
	 void yahtzeeDelete(Object data, int count) {
		 if(head != null) {
				while(head != null && (Integer)head.getData() == (Integer)data)head = head.getLink();
				Node previous = null;
				Node temp = head;
				int counter = 0;
				while(temp != null) {
					if((Integer)temp.getData() == (Integer) data && counter < count) {
						previous.setLink(temp.getLink());
						temp = previous;
						counter++;
					}
					previous= temp;
					temp = temp.getLink();
				}
			}
	 }
	 boolean largeStraightChecker() {
		 return (search(1) && search(2) && search(3) && search(4) && search(5) && search(6));
	 }
}
