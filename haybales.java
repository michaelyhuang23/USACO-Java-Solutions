import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class haybales {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("haybales.in")); //new FileReader("haybales.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numHay = Integer.parseInt(st.nextToken());
		int numQuery = Integer.parseInt(st.nextToken());
		int[] allHays = new int[numHay];
		st = new StringTokenizer(f.readLine());
		for(int i=0;i<numHay;i++)
			allHays[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(allHays);
		for(int i=0;i<numQuery;i++) {
			st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int startI = Arrays.binarySearch(allHays, start);
			if(startI>=0) {
				while(startI>=0 && allHays[startI]==start)
					startI--;
				startI++;
			}
			else
				startI=-startI-1;
			
			int endI = Arrays.binarySearch(allHays, end);
			if(endI>=0) {
				while(endI<numHay && allHays[endI]==end)
					endI++;
				endI--;
			}
			else
				endI=-endI-2;
			out.println(endI-startI+1);
		}
		out.close();
		f.close();
	}
}
