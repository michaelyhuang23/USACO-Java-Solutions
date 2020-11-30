import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeSet;

/*
ID: yhuang22
LANG: JAVA
TASK: pprime
*/
public class pprime {
	class palindrome implements Comparable<palindrome>{
		public int number, length, halfLength;
		public StringBuffer numberStr, firstHalfStr;
		public palindrome(String firstHalfStr,int length) {
			this.firstHalfStr=new StringBuffer(firstHalfStr);
			this.length=length;
			halfLength=firstHalfStr.length();
			getFull();
		}
		public void getFull() {
			numberStr=new StringBuffer(firstHalfStr);
			if(length%2==0)
				numberStr.append(firstHalfStr.charAt(halfLength-1));
			for(int i=halfLength-2;i>=0;i--) {
				numberStr.append(firstHalfStr.charAt(i));
			}
		}
		public int getNumValue() {
			getFull();
			return Integer.parseInt(numberStr.toString());
		}
		public String toString() {
			getFull();
			return numberStr.toString();
		}
		public int compareTo(palindrome other) {
			return number-other.number;
		}
		public boolean equals(Object other) {
			palindrome otherPal=(palindrome)other;
			return this.compareTo(otherPal)==0;
		}
		
	}
	public static boolean isPrime(int num) {
		for(int i=3;i<=Math.sqrt(num)+1;i+=2) 
			if(num%i==0)
				return false;
		if(num!=2 && num%2==0)
			return false;
		return true;

	}
	public static void main(String[] args) throws IOException {
		pprime thisP=new pprime();
		BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.6/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		String[] inputs=f.readLine().split(" ",2);
		int min=Integer.parseInt(inputs[0]), max=Integer.parseInt(inputs[1]);

		f.close();

		for(int i=inputs[0].length();i<=inputs[1].length();i++) {
			//System.out.println(getZeros(3));
			permute(thisP.new palindrome(getZeros(i),i), min, max, 0);
		}
		
		for(int pal:allPals) {
			out.println(pal);
		}
		out.close();
	}
	public static String getZeros(int num) {
		StringBuffer zeros=new StringBuffer();
		if(num%2==0) {
			num/=2;
		}else {
			num=(int)(num/2)+1;
		}
		for(int i=0;i<num;i++) {
			zeros.append('0');
		}
		return zeros.toString();
	}
	public static TreeSet<Integer> allPals=new TreeSet<Integer>();
	
	public static void permute(palindrome smallestPal, int min,int max, int index) {
		//System.out.println(smallestPal.firstHalfStr+" "+smallestPal.length);
		if(index==smallestPal.halfLength) {
			int num=smallestPal.getNumValue();
			if(num<=max && num>=min) {
				if(isPrime(num))
					allPals.add(num);
			}
			return;
		}
		for(int i=0;i<10;i++) {
			smallestPal.firstHalfStr.setCharAt(index, Character.forDigit(i, 10));
			permute(smallestPal, min,max, index+1);
		}
	}
}
