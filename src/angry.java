import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class angry {
	static int numShot,numHay;
	static int[] allHays;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("angry.in")); //new FileReader("angry.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		StringTokenizer st =new StringTokenizer(f.readLine());
		numHay = Integer.parseInt(st.nextToken());
		numShot = Integer.parseInt(st.nextToken());
		allHays = new int[numHay];
		int maxD=0,minD=Integer.MAX_VALUE;
		for(int i=0;i<numHay;i++) {
			allHays[i]=Integer.parseInt(f.readLine());
			maxD = Math.max(maxD, allHays[i]);
			minD = Math.min(minD, allHays[i]);
		}
		Arrays.sort(allHays);
		int leftRBound=0, rightRBound = (maxD-minD+1)/2;
		int minR = rightRBound;
		while(leftRBound<=rightRBound) {
			int midR = (leftRBound+rightRBound)/2;
			if(check(midR)) {minR = midR; rightRBound = midR-1;}
			else leftRBound = midR+1;
		}
		out.println(minR);
		out.close();
		f.close();
	}

	private static boolean check(int radius) {
		int shotCount=0;
		int lastShotEnd=-1;
		for(int i=0;i<numHay;i++) {
			if(allHays[i]<=lastShotEnd)
				continue;
			lastShotEnd = allHays[i]+2*radius;
			shotCount++;
		}
		return shotCount<=numShot;
	}

}
