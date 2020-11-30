import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: yhuang22
LANG: JAVA
TASK: kimbits
*/
public class kimbits {
	public static int[] resultBits; // it goes backward
	public static long comboChoosing(int choose, int outof) {
		double result=1;
		for(int i=1; i<=choose; i++) {
			result*=outof-i+1;
			result/=i;
		}
		return Math.round(result);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("kimbits.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter3Section3.2/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
		String[] inputs = f.readLine().split(" ",3);
		int numBits = Integer.parseInt(inputs[0]), numOnes = Integer.parseInt(inputs[1]);
		long numElement = Long.parseLong(inputs[2]);
		resultBits = new int[numBits+1]; //first position is empty
		findNth(numElement,numOnes,numBits);
		for(int i=resultBits.length-1;i>=1;i--)
			out.print(resultBits[i]);
		out.println();
//		for(int i=resultBits.length-1;i>=1;i--)
//			System.out.print(resultBits[i]);
		out.close();
		f.close();
	}
	
	public static void findNth(long N, int limitOne, int limitSpace) {
//		for(int i=resultBits.length-1;i>=1;i--)
//			System.out.print(resultBits[i]);
//		System.out.println();
		if(N==0) {
			//System.out.println(N+" "+limitOne+" "+limitSpace);
			for(int i=limitSpace;i>Math.max(limitSpace-limitOne,0);i--)
				resultBits[i]=1;
			return;
		}
		long previousCombo=0;
		for(int currentLimitSpace=0; currentLimitSpace<=limitSpace; currentLimitSpace++) {
			long combo=0;
			for(int i=0;i<=Math.min(limitOne,currentLimitSpace);i++)
				combo+=comboChoosing(i,currentLimitSpace);
			if(combo>=N) {
				if(combo==N)
					previousCombo=combo;
				resultBits[currentLimitSpace] = 1;
				findNth(N-previousCombo,limitOne-1,currentLimitSpace-1);
				return;
			}
			previousCombo=combo;
		}
	}
}
