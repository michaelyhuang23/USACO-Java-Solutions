import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CFRound108Div2A {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long d = Long.parseLong(st.nextToken());
			long max = Math.max(r, b);
			long min = Math.min(r, b);
			if(min*(d+1)<max)
				System.out.println("no");
			else
				System.out.println("yes");
		}
	}
}
