import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
/*
ID: yhuang22
LANG: JAVA
TASK: preface
*/
public class preface {
	static int[] romanNumVal= {1,5,10,50,100,500,1000};
	static char[] romanNumSym= {'I','V','X','L','C','D','M'};
	public static int[] getRoman(String number) {
		int[] freq= {0,0,0,0,0,0,0};
		for(int i=0;i<number.length();i++) {
			int digit=Character.getNumericValue(number.charAt(i));
			int pos=number.length()-i;
			if(pos>=4) {
				freq[6]+=digit*(int)Math.round(Math.pow(10, pos-4));
				continue;
			}
			if(digit==4) {
				freq[pos*2-1]++;
				freq[pos*2-2]++;
				continue;
			}
			if(digit==9) {
				freq[pos*2]++;
				freq[pos*2-2]++;
				continue;
			}
			freq[pos*2-1]+=(int)(digit/5);
			freq[pos*2-2]+=digit%5;
		}
		return freq;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("preface.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section2.2/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		int maxNum=Integer.parseInt(f.readLine());
		int[] romanNumFreq= {0,0,0,0,0,0,0};
		for(int i=1;i<=maxNum;i++) {
			int[] freq=getRoman(Integer.toString(i));
			for(int j=0;j<7;j++) 
				romanNumFreq[j]+=freq[j];	
		}
		for(int i=0;i<7;i++) {
			if(romanNumFreq[i]==0)
				continue;
			out.println(romanNumSym[i]+" "+romanNumFreq[i]);
		}
		out.flush();
		f.close();
		out.close();
	}
}
