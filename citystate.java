import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class citystate {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("citystate.in")); //new FileReader("citystate.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
		int numCity = Integer.parseInt(f.readLine());
		HashMap<String, Integer> cityCounter = new HashMap<String, Integer>();
		for(int i=0;i<numCity;i++) {
			StringTokenizer st= new StringTokenizer(f.readLine());
			String code = st.nextToken().substring(0,2)+st.nextToken();
			if(cityCounter.containsKey(code))
				cityCounter.put(code, cityCounter.get(code)+1);
			else
				cityCounter.put(code, 1);
		}
		int numPair=0;
		for(String code : cityCounter.keySet()) {
			String newCode = code.substring(2,4)+code.substring(0,2);
			if(newCode.equals(code))
				continue;
			if(cityCounter.containsKey(newCode)) {
				numPair += cityCounter.get(newCode)*cityCounter.get(code);
			}
		}
		out.println(numPair/2);
		out.close();
		f.close();
	}
}
