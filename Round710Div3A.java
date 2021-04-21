import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Round710Div3A {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			long n = Long.parseLong(st.nextToken());
			long m = Long.parseLong(st.nextToken());
			long x = Long.parseLong(st.nextToken());
			long c = (x-1)/n;
			long r = (x-1)%n;
			long y = r*m+c+1;
			System.out.println(y);
		}
		
	}
}
