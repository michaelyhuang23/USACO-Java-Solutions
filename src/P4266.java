import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P4266 {
	static class Stop implements Comparable<Stop>{
		int pos,tastiness;
		public Stop(int p, int t) {
			pos = p;
			tastiness = t;
		}
		public int compareTo(Stop other) {
			return pos-other.pos;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int length, numRest, farmerT, cowT;
		StringTokenizer st = new StringTokenizer(f.readLine());
		length = Integer.parseInt(st.nextToken());
		numRest = Integer.parseInt(st.nextToken());
		farmerT = Integer.parseInt(st.nextToken());
		cowT = Integer.parseInt(st.nextToken());
		Stop[] allStops = new Stop[numRest];
		boolean[] stopHere = new boolean[numRest];
		for(int i=0;i<numRest;i++) {
			st = new StringTokenizer(f.readLine());
			allStops[i] = new Stop(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(allStops);
		int currentMax=0;
		for(int i=numRest-1;i>=0;i--) {
			if(allStops[i].tastiness>currentMax) {
				currentMax=allStops[i].tastiness;
				stopHere[i]=true;
			}
		}
		int lastPos=0;
		long tastinessC = 0;
		for(int i=0;i<numRest;i++) {
			if(stopHere[i]) {
				tastinessC+=((long)(allStops[i].pos-lastPos))*(farmerT-cowT)*allStops[i].tastiness;
				lastPos = allStops[i].pos;
			}
		}
		System.out.println(tastinessC);
	}
}
