import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dominos {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int n = Integer.parseInt(st.nextToken());
			int w1 = Integer.parseInt(st.nextToken());
			int w2 = Integer.parseInt(st.nextToken());
			int b1 = n - w1;
			int b2 = n - w2;
			st = new StringTokenizer(f.readLine());
			int w = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if ((b1 + b2) / 2 >= b && (w1 + w2) / 2 >= w)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}
