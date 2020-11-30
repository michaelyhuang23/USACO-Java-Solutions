import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class P3405 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numCity = Integer.parseInt(in.readLine());
		StringTokenizer st;
		HashMap<String,Integer> occurences = new HashMap<String,Integer>();
		for(int i=0;i<numCity;i++) {
			st = new StringTokenizer(in.readLine());
			String name = st.nextToken().substring(0,2)+st.nextToken();
			if(occurences.containsKey(name))
				occurences.put(name, occurences.get(name)+1);
			else
				occurences.put(name, 1);
		}
		int sum=0;
		for(String name : occurences.keySet()) {
			String newName = name.substring(2,4)+name.substring(0,2);
			if(newName.equals(name))
				continue;
			if(occurences.containsKey(newName)) {
				sum+=occurences.get(newName)*occurences.get(name);
			}
		}
		System.out.println(sum/2);
		
	}
}
