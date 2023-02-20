
public class Queue {
	private int rear,front;
	private Object[] elements;
	
	Queue(int capacity){
		rear = -1;
		front = 0;
		elements = new Object[capacity];
	}
	
	void enqueue(Object data) {
		if(isFull()) System.out.println("Queue is full");
		else {
			//rear++;
			//elements[rear] = data;
			rear = (rear +1) % elements.length;
			elements[rear] = data;
		}
	}
	Object dequeue() {
		if(isEmpty()) {
			System.out.println("Queue is empty");
			return null;
		}
		else {
			Object retData = elements[front];
			elements[front] = null;
			//front++;
			front = (front+1) % elements.length;
			return retData;
		}
	}
	Object peek() {
		if(isEmpty()) {
			System.out.println("Queue is empty");
			return null;
		}
		else
		return elements[front];
	}
	boolean isEmpty() {
		//return rear<front;
		return elements[front] == null;
	}
	boolean isFull() {
		//return (rear + 1 == elements.length);
		return (front == (rear+1) % elements.length && elements[front] != null && elements[rear] != null);
	}
	int size() {
		//return rear-front+1;
		if(elements[front] == null) return 0;
		else {
			if(rear>=front) return rear - front + 1;
			else return elements.length - (front - rear) + 1;
		}
	}
}
