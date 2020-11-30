import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class P1201 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numPeople = Integer.parseInt(f.readLine());
		HashMap<String,Integer> personM = new HashMap<String,Integer>();
		String[] names = new String[numPeople];
		for(int i=0;i<numPeople;i++) {
			String name =f.readLine();
			personM.put(name, 0);
			names[i]=name;
		}
		StringTokenizer st;
		for(int i=0;i<numPeople;i++) {
			String name = f.readLine();
			st = new StringTokenizer(f.readLine());
			int origM = Integer.parseInt(st.nextToken());
			int numGift = Integer.parseInt(st.nextToken());
			for(int j=0;j<numGift;j++) {
				String frdName=f.readLine();
				personM.put(frdName,personM.get(frdName)+origM/numGift);
			}
			if(numGift==0)
				personM.put(name, personM.get(name));
			else
				personM.put(name, personM.get(name)+origM%numGift-origM);
		}
		for(int i=0;i<numPeople;i++) {
			System.out.println(names[i]+" "+personM.get(names[i]));
		}
	}
}
