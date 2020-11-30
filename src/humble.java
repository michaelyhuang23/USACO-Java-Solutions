import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
ID: yhuang22
LANG: JAVA
TASK: humble
*/
public class humble {
	public static int getIndex (long[] allNums, int numberN, int prime) {
		int index = Arrays.binarySearch(allNums, 0, numberN+1, Math.round(Math.ceil((allNums[numberN]+1)/((double)(prime)))));
		return index<0 ? -(index+1) : index;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("humble.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter3Section3.1/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
		String[] inputs = f.readLine().split(" ",2);
		int setSize = Integer.parseInt(inputs[0]);
		int number = Integer.parseInt(inputs[1]);
		
		
		ArrayList<Integer> allPrimes = new ArrayList<Integer>();
		inputs = f.readLine().split(" ",setSize);
		for(int i=0;i<setSize;i++) 
			allPrimes.add(Integer.parseInt(inputs[i]));
		long[] allNums = new long[number+2];
		allNums[0] = 0;
		allNums[1] = 1;
		for(int numberN = 1; numberN<=number; numberN++) {
			long nextNum = Long.MAX_VALUE;
			for(int primeIndex = 0; primeIndex<setSize;primeIndex++) {
				nextNum = Math.min(nextNum, allPrimes.get(primeIndex)*allNums[getIndex(allNums,numberN,allPrimes.get(primeIndex))]);
			}
			allNums[numberN+1] = nextNum;
		}
		out.println(allNums[allNums.length-1]);
		out.close();
		f.close();
	}
}
