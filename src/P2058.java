import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class P2058 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numShip = Integer.parseInt(f.readLine());
		int[] shipArriveTime = new int[numShip];
		Deque<Integer> shipWithin = new ArrayDeque<>();
		int[] nationTrack = new int[100001];
		ArrayList<Integer>[] peopleOnBoat = new ArrayList[numShip];
		int nationInRange = 0;
		for(int i=0;i<numShip;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int time = Integer.parseInt(st.nextToken());
			int numPeople = Integer.parseInt(st.nextToken());
			while(!shipWithin.isEmpty() && shipArriveTime[shipWithin.peekFirst()]<=time-86400) {
				int ship = shipWithin.pollFirst();
				for(int nation:peopleOnBoat[ship]) {
					nationTrack[nation]--;
					if(nationTrack[nation]==0)
						nationInRange--;
				}
			}
			peopleOnBoat[i]=new ArrayList<Integer>();
			for(int j=0;j<numPeople;j++) {
				int nation = Integer.parseInt(st.nextToken());
				if(nationTrack[nation]==0)
					nationInRange++;
				nationTrack[nation]++;
				peopleOnBoat[i].add(nation);
			}
			shipArriveTime[i]=time;
			shipWithin.offerLast(i);
			System.out.println(nationInRange);
		}
		
	}
}
