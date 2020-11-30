import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/*
ID: yhuang22
LANG: JAVA
TASK: stamps
*/
public class stamps {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("stamps.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter3Section3.1/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		String[] inputs = f.readLine().split(" ",2);
		int numStamp = Integer.parseInt(inputs[0]), numStampVal = Integer.parseInt(inputs[1]);
		int[] stampVals = new int[numStampVal];
		for(int i=0; i<numStampVal; ) {
			inputs=f.readLine().split(" ");
			int j;
			for(j=0; j<inputs.length; j++) {
				stampVals[i+j] = Integer.parseInt(inputs[j]);
			}

			i+=j;
		}
		int[] isValPossible = new int[5000000]; 
		// boolean[numStamp+1][numStampVal+1];

		isValPossible[0]=0;
		for(int currentVal=1; true; currentVal++) {
			int lowestStampNum=Integer.MAX_VALUE;
			for(int stampUsed=0; stampUsed<numStampVal; stampUsed++) {
				if(currentVal-stampVals[stampUsed]>-1)
					lowestStampNum=Math.min(lowestStampNum, isValPossible[currentVal-stampVals[stampUsed]]+1);
			}
			isValPossible[currentVal]=lowestStampNum;
			if(lowestStampNum>numStamp) {
				out.println(currentVal-1);
				break;
			}
		}
		f.close();
		out.close();
		
	}
}
