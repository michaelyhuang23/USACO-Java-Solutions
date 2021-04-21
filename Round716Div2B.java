import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Round716Div2B {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		long MOD = (long) (1e9+7);
		for (int i = 0; i < numT; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			long count = 1;
			for (int j = n; j >= n-k+1; j--) {
				count*=n;
				count%=MOD;
			}
			System.out.println(count);
		}
	}
}
