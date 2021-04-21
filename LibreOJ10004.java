import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LibreOJ10004 {
	static class Mission implements Comparable<Mission>{
		int price, deadline;
		public Mission(int p, int d) {
			price=p;
			deadline=d;
		}
		@Override
		public int compareTo(Mission o) {
			return o.price-price;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int initialM = Integer.parseInt(f.readLine());
		int numMission = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		Mission[] allMissions = new Mission[numMission];
		for(int i=0;i<numMission;i++)
			allMissions[i] = new Mission(0,Integer.parseInt(st.nextToken())-1);
		st = new StringTokenizer(f.readLine());
		int allPrice=0;
		for(int i=0;i<numMission;i++) {
			allMissions[i].price = Integer.parseInt(st.nextToken());
			allPrice+=allMissions[i].price;
		}
		Arrays.sort(allMissions);
		int totalCost=0;
		boolean[] spotTaken = new boolean[numMission];
		for(int i=0;i<numMission;i++) {
			int j=allMissions[i].deadline;
			while(j>=0 && spotTaken[j])
				j--;
			if(j<0)
				continue;
			spotTaken[j]=true;
			totalCost+=allMissions[i].price;
		}
		System.out.println(initialM+totalCost-allPrice);
	}
}
