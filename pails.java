import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class pails {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("pails.in")); //new FileReader("pails.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int firstPail = Integer.parseInt(st.nextToken());
		int secondPail = Integer.parseInt(st.nextToken());
		int numOp = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		int smallerPail, largerPail;
		if(firstPail<secondPail) {
			smallerPail=firstPail;
			largerPail=secondPail;
		}else {
			smallerPail=secondPail;
			largerPail=firstPail;
		}
		int stepCount;
		int minDevi = Integer.MAX_VALUE;
		int smallAddition = largerPail%smallerPail;
		if(2<=numOp)
			minDevi = Math.min(minDevi, Math.abs(smallerPail+largerPail-target));
		int baseStep=0;
		for(int numAdd=0; numAdd*smallAddition<smallerPail; numAdd++) {
			baseStep++;
			baseStep+=2*largerPail/smallerPail;
			if(numAdd==0)
				baseStep=0;
			stepCount=baseStep;
			for(int numSmall=0; (numSmall*smallerPail+smallAddition*numAdd)<(smallerPail+largerPail); numSmall++) {
				int deviation = Math.abs(numSmall*smallerPail+smallAddition*numAdd-target);
				if(numSmall==1)
					stepCount++;
				if(numSmall>1)
					stepCount+=2;
				if(stepCount<=numOp)
					minDevi = Math.min(minDevi, deviation);
			}
			baseStep++;
			if(numAdd==0)
				baseStep=0;
			if(1+baseStep<=numOp)
				minDevi = Math.min(minDevi, Math.abs(largerPail+smallAddition*numAdd-target));
			stepCount=1+baseStep;
			for(int numSub = 1; largerPail-numSub*smallerPail+smallAddition*numAdd>0; numSub++) {
				stepCount+=2;
				if(stepCount<=numOp)
					minDevi = Math.min(minDevi, Math.abs(largerPail-numSub*smallerPail+smallAddition*numAdd-target));
			}
			if(smallAddition==0)
				break;
		}
		out.println(minDevi);
		out.close();
		f.close();
	}
}
