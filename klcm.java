import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class klcm {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numTest = Integer.parseInt(f.readLine());
		for (int i = 0; i < numTest; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			long n = Long.parseLong(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			n-=k-3;
			for (int j = 0; j < k-3; j++) {
				System.out.print(1+" ");
			}
			k=3;
			assert k ==3 ;
			if (n % 4 == 0) {
				System.out.println(n / 4 + " " + n / 4 + " " + n / 2);
				continue;
			}

			assert n % 4 != 0;
			if (n % 2 == 1) {
				System.out.println(1 + " " + (n - 1) / 2 + " " + (n - 1) / 2);
				continue;
			}
			assert n % 4 == 2;
			System.out.println(2 + " " + (n - 2) / 2 + " " + (n - 2) / 2);
		}
	}
}
