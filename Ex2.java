package matala2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
public class Ex2 {
	//1a
	public static char[] spreadCardsOnTable(char[] cards) {
		Queue<Character> pack = new LinkedList<>();
		Stack<Character> table = new Stack<>();
		char [] ifSizeSmall =new char [1];
		if(cards.length==1) {
			ifSizeSmall[0]='W'; 
			return ifSizeSmall ;
		}

		// Passes all the cards that have been opened on the table to Stack(table)
		for (int i = 0; i < cards.length; i += 2) {
			table.push(cards[i]);
		}
		//Transfers all cards passed to the bottom of the pack to Queue(pack)
		for (int i = 1; i < cards.length; i += 2) {
			pack.add(cards[i]);
		}
		//Check if the number of cards is even
		if (cards.length % 2 == 0) {
			while (!pack.isEmpty()) {       //Checks if there are any cards left in the pack
				table.push(pack.poll());    //Adds a card to the table
				if (!pack.isEmpty())        //Checks if there are any cards left in the pack to move a card to the end of the pack
					pack.add(pack.poll());  //Passes a card to the end of the pack
			}
		} 
		else {
			pack.add(pack.poll());          //Passes a card to the end of the pack
			while (!pack.isEmpty()) {       //Checks if there are any cards left in the pack
				table.push(pack.poll());	//Adds a card to the table
				if (!pack.isEmpty()) 		//Checks if there are any cards left in the pack to move a card to the end of the pack
					pack.add(pack.poll());	//Passes a card to the end of the pack
			}
		}
		char[] Table = new char[table.size()];
		for (int i = table.size() - 1; i >= 0; i--) {
			Table[i] = table.pop();
		}
		return Table;
	}
	// Q1b
	public static char [] createOriginalLayout(int n) { 
		char [] cards= new char [n]; 
		if(n==0) 
			return cards;
		if(n==1) {
			cards[0]='W';
			return cards;
		}
		if(n==2) {
			cards[0]='W';
			cards[1]='B';
			return cards;
		}
		int count =0;
		int countW = 1;
		int countB=0;
		boolean t = false;
		cards[0]='W';
		while(!t){ 
			for (int i=0;i<n;i++) {
				if (cards[i]=='\u0000' && count==0) {//Checks if the cell is empty and also if the count is equal to zero
					count++;
				}
				//Checks if the cell is empty and also if the number of times there are in the package "W" and "B" is equal and also if count is equal to 1
				else if(cards[i]=='\u0000' && countW==countB && count==1) {
					//Adds to the package "W" resets the count and increases the number of times "W" was entered
					cards[i]='W';
					count=0;
					countW++;
				}
				//Checks if the cell is empty and even if there is more "W" than "B" in the package and also if count is equal to 1
				else if (cards[i]=='\u0000' && countW>countB && count==1) {
					//Adds to the package "B" resets the count and increases the number of times "B" was entered
					cards[i]='B';
					count=0;
					countB++;	
				}
				//Check if all the cards have been added
				if (countB+countW==cards.length)
					t=true;
			}
		}
		return cards;
	}
	// Q2a // o(n)
	public static int maxStack(Stack<Integer> st) {
		//Check for numbers in the Stack
		if (st.isEmpty()) {
			return -1;
		}
		//Check if there is only one number in Stack
		if (st.size()==1) {
			return st.get(0);
		}
		int maxsize = st.size();
		int maxval = 0;
		//Run from the end of the array to the beginning
		while (maxsize != 0) {     
			//Compares the high value with the value in the array and retains the high value between them
			maxval = Math.max(st.get(maxsize - 1), maxval);
			maxsize--;
		}
		return maxval;
	}
	// Q2b
	public static int maxSumAdj(ArrayBlockingQueue<Integer> q) {
		int maxval = 0;
		int temp = 0;
		if(q.size()==0 || q.size()==1)
			return -1;
		for (int i = 0; i < q.size(); i++) {
			temp = q.poll();
			maxval=Math.max(temp + q.peek(),  maxval);//Checks and saves the largest amount of the pair of numbers
			q.add(temp);//Passes the first number from the number pairs to the end of the queue
		}
		return maxval;
	}
	// Q3
	public static Point[] maximaOfPoints(Point[] S) {
		ArrayList<Point> arr = new ArrayList<>();
		boolean ifmax = true;
		for (int i = 0; i < S.length; i++) {
			ifmax = true;
			for (int j = 0; j < S.length && ifmax; j++) {
				//Checks if there is a point with x larger and also with y larger or equal to it
				if (S[i].getX() < S[j].getX() && S[i].getY() <= S[j].getY()) {
					ifmax = false;
				}
				//Checks if there is a point with x larger or equal to it and also with y larger
				else if (S[i].getX() <= S[j].getX() && S[i].getY() < S[j].getY()) {
					ifmax = false;
				}
			}
			//If this is true we will add the point to the arr
			if (ifmax)
				arr.add(S[i]);
		}
		int size = arr.size();
		Point[] Smax = new Point[size];
		for (int i = 0; i < arr.size(); i++) {
			Smax[i] = arr.get(i);
		}
		return Smax;
	}
	// Q4
	public static void ifExists(int[]a, int[]b, int x) {
		//We will check which array with the higher values And send the arrays to ChecksIfExists
		if(a[a.length-1]>=b[b.length-1]) {
			ChecksIfExists(a, b, x, "a");
		}
		else {
			ChecksIfExists(b, a, x, "b");
		}
	}
	private static void ChecksIfExists(int[]maxValues, int[]minValues, int x,String arrMax) {
		int maxIndex =maxValues.length-1;
		int minIndex=0;
		boolean t_f = true;
		//If the sum of the two highest values is less than x we will know that no such numbers exist
		if(x > maxValues[maxIndex]+minValues[minValues.length-1]) {
			System.out.println("False");
		}
		else {
			while(t_f) {
				//Checking the boundaries of the arrays
				if(maxIndex<0 || minIndex>minValues.length-1) {
					System.out.println("False");
					t_f=false;
				}
				//If the sum of the two values is greater than x we will advance the index of maxIndex
				else if(x < maxValues[maxIndex] + minValues[minIndex]) {
					maxIndex--;
				}
				//If the sum of the two values is less than x we will advance the index of minIndex
				else if(x > maxValues[maxIndex] + minValues[minIndex]) {
					minIndex++;
				}
				//If the sum of the two values is equal to x we will represent them
				else if(maxValues[maxIndex] + minValues[minIndex] == x) {
					if(arrMax.equals("b")) {
						System.out.println("b["+maxIndex+"]="+maxValues[maxIndex]+", a["+minIndex+"]="+minValues[minIndex]);
						t_f=false;

					}
					else {
						System.out.println("a["+maxIndex+"]="+maxValues[maxIndex]+", b["+minIndex+"]="+minValues[minIndex]);
						t_f=false;
					}
				}
			}
		}
	}
}







