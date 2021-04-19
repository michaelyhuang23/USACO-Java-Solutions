import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class loan {
	static long numLoan, deadline, dayMin;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("loan.in")); //new FileReader("loan.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("loan.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numLoan = Long.parseLong(st.nextToken());
		deadline = Long.parseLong(st.nextToken());
		dayMin = Long.parseLong(st.nextToken());
		long leftXBound = 1, rightXBound = numLoan/dayMin;
		long currentLargestX=0;
		while(leftXBound<=rightXBound) {
			long midX = (leftXBound+rightXBound)/2;
			if(check(midX)) {currentLargestX=midX; leftXBound=midX+1;}
			else rightXBound=midX-1;
		}
		out.println(currentLargestX);
		//System.out.println(getSwitchTime(280590614)+" "+remnant);
		out.close();
		f.close();
	}
	static long remnant;
	
	private static long getSwitchTime(long X) {
// decay rate = (x-1)/x
		remnant = numLoan;
		//double decayRate = (X-1)/(double)X;
		long reductor = remnant/X;
		long switchTime = 0;
		while(reductor > dayMin) {
			long toBeReduced = (remnant-reductor*X+1);
			switchTime+=toBeReduced%reductor==0 ? toBeReduced/reductor : toBeReduced/reductor+1;
			if(toBeReduced%reductor==0)
				remnant = reductor*X-1;
			else
				remnant = reductor*X-1 - (reductor-toBeReduced%reductor);
			//System.out.println(remnant+" / "+reductor*X+" "+X+" "+(toBeReduced%reductor==0 ? toBeReduced/reductor : toBeReduced/reductor+1));
			reductor = remnant/X;
		}
		return switchTime;
	}
	private static boolean check(long midX) {
		long startLT = getSwitchTime(midX);
		return (deadline-startLT)>=((remnant%dayMin==0) ? remnant/dayMin : (remnant/dayMin+1));
	}
}
