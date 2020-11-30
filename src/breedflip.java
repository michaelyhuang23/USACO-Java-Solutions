import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.io.BufferedReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: yhuang23
LANG: JAVA
PROB: breedflip
*/
/*class Flip{
	public int start;
	public int end;
	public Flip(int startIndex, int endIndex) { //mind overlap!
		start=startIndex;
		end=endIndex;
	}

	public boolean equals(Object other) {
		Flip otherFlip=((Flip)other);
		return start==otherFlip.start && end==otherFlip.end;
	}
	
	public int hashCode() {
		return Double.hashCode(start*start/(end*end+1));
	}
	
	

	 
}*/
class breedflip {
	//greedy approach
	static StringBuffer goalStr;
	/*public static void toggle(StringBuffer str, int start, int end) {
		for(int i=start;i<=end;i++) {
			if(str.charAt(i)=='G')
				str.setCharAt(i, 'H');
			else
				str.setCharAt(i, 'G');
		}
	}*/
	public static int bestToggle(StringBuffer str) {
		int step=0;

		for(int i1=0;i1<str.length();i1++) { //substring includes both i1 and i2
			int i2=i1;
			while(str.charAt(i2)!=goalStr.charAt(i2)) {
				str.setCharAt(i2, goalStr.charAt(i2));
				i2++;
			}
			if(i1!=i2)
				step++;
			i1=i2;

		}
		return step;

	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("breedflip.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACO2/src/
		File file=new File("breedflip.out");
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("breedflip.out")));
		int size=Integer.parseInt(f.readLine());
		goalStr=new StringBuffer(f.readLine());
		StringBuffer currentStr=new StringBuffer(f.readLine());
		/*do {
		bestToggle(currentStr,0,currentStr.length()-1);
		}while()*/
		out.println(bestToggle(currentStr));
		out.close();
		f.close();
		
	}
}
