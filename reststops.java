import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class reststops {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("reststops.in")); //new FileReader("reststops.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int length = Integer.parseInt(st.nextToken());
		int numStop = Integer.parseInt(st.nextToken());
		int fTime = Integer.parseInt(st.nextToken());
		int bTime = Integer.parseInt(st.nextToken());
		int[] stopPos = new int[numStop];
		int[] stopTaste = new int[numStop];
		
		for(int i=0;i<numStop;i++) {
			st = new StringTokenizer(f.readLine());
			int pos = Integer.parseInt(st.nextToken());
			int taste = Integer.parseInt(st.nextToken());
			stopPos[i] = pos;
			stopTaste[i] = taste;
		}
		Stack<Integer> stops = new Stack<>();
		int prevTaste = 0;
		for(int i=numStop-1;i>=0;i--) {
			if(stopTaste[i]>prevTaste) {
				prevTaste = stopTaste[i];
				stops.push(i);
			}
		}
		int prevStopPos = 0;
		long totalTaste = 0;
		while(!stops.isEmpty()) {
			int stopI = stops.pop();
			totalTaste+=(long)(stopPos[stopI]-prevStopPos)*(fTime-bTime)*stopTaste[stopI];
			prevStopPos = stopPos[stopI];
		}
		out.println(totalTaste);
		out.close();
		f.close();
	}
}
