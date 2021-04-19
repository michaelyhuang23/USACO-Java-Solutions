import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class bcount {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("bcount.in")); //new FileReader("bcount.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
		int numCow, numQuery;
		StringTokenizer st = new StringTokenizer(f.readLine());
		numCow = Integer.parseInt(st.nextToken());
		numQuery = Integer.parseInt(st.nextToken());
		int[][] breedCounter = new int[3][numCow];
		for(int i=0;i<numCow;i++) {
			int cow = Integer.parseInt(f.readLine())-1;
			if(i>0) {
				breedCounter[0][i]=breedCounter[0][i-1];
				breedCounter[1][i]=breedCounter[1][i-1];
				breedCounter[2][i]=breedCounter[2][i-1];
			}
			breedCounter[cow][i]++;
		}
		for(int i=0;i<numQuery;i++) {
			st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			StringBuffer result = new StringBuffer();
			for(int breed = 0; breed<3;breed++) {
				if(start==0)
					result.append(breedCounter[breed][end]+" ");
				else
					result.append(breedCounter[breed][end]-breedCounter[breed][start-1]+" ");
			}
			result.deleteCharAt(result.length()-1);
			out.println(result);
		}
		out.close();
		f.close();
	}
}
