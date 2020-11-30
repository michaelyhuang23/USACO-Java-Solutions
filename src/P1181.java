import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1181 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int num = Integer.parseInt(st.nextToken());
		int upperBound = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		int currentSum=0;
		int counter=0;
		for(int i=0;i<num;i++) {
			int nextN=Integer.parseInt(st.nextToken());
			if(currentSum+nextN>upperBound) {
				currentSum=0;
				counter++;
			}
			currentSum+=nextN;
		}
		System.out.println(counter+1);
	}
}
