import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class planting {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("planting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
		int numField= Integer.parseInt(f.readLine());
		StringTokenizer st;
		int[] degree = new int[numField];
		for(int i=0;i<numField-1;i++) {
			st = new StringTokenizer(f.readLine());
		 	int first = Integer.parseInt(st.nextToken())-1;
			int second = Integer.parseInt(st.nextToken())-1;
			degree[first]++;
			degree[second]++;
		}
		int maxDeg=0;
		for(int i=0;i<numField;i++) {
			maxDeg = Math.max(maxDeg, degree[i]);
		}
		out.println(maxDeg+1);
		f.close();
		out.close();
	}
}
