import java.util.Scanner;

public class Test {
	static String c[];
	static int k = 0, n = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*int T = sc.nextInt();
		
		for (int i = 0; i < T * 2; i++) {
			
		}*/
		
		
		int total = 0;
		
		String input = sc.nextLine();
		
		String temp[] = input.split(" ");
		
		k = Integer.valueOf(temp[1]);
		n = Integer.valueOf(temp[0]);
		
		input = sc.nextLine();
		c = input.split(" ");
		
		for (int i = 0; i < k; i++) {
			int count = 0;
			int max = findMax();
			//c = remove(indexFinder(max));
			do {
				total += (count + 1) * max;
				k--;
				if(c.length != 0)
				c = remove(indexFinder(max));
				max = findMax();
			} while (!isFinished(count, max));
		}
		System.out.println(total);
		
	}
	static boolean isFinished(int count,int max) {
		if(c.length == k) return true;
		else {
			if((count + 1) * max > max)return true;
			else return false;
		}
	}
	static int findMax() {
		int max = 0;
		for (int i = 0; i < c.length; i++) 
			if(Integer.valueOf(c[i]) > max) max = Integer.valueOf(c[i]);
		return max;
	}
	static int indexFinder( int ch) {
		 for (int i = 0; i < c.length; i++) {
			if(ch ==Integer.valueOf(c[i]) ) return i;
		}
		 return -1;
	 }
	static String[] remove( int indexOfTheElementToBeDeleted) {

		String[] tempArray = new String[c.length-1];

	        for (int k = 0; k < c.length; k++) {

	            if (k < indexOfTheElementToBeDeleted) {
	                tempArray[k] = c[k];
	            }

	            else if (k > indexOfTheElementToBeDeleted) {
	                tempArray[k - 1] = c[k];
	            }
	        }

	        return tempArray;

	    }
}
