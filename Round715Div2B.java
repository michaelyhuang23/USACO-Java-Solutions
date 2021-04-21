import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Round715Div2B {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			String s = f.readLine();
			if (s.charAt(0) == 'M' || s.charAt(n - 1) == 'M') {
				System.out.println("NO");
				continue;
			}
			boolean[] mask = new boolean[n];
			int leftMostT = 0;
			boolean success = true;
			for (int j = 1; j < n - 1; j++) {
				if (s.charAt(j) == 'M') {
					if (leftMostT >= j) {
						success = false;
						break;
					}
					mask[leftMostT] = true;
					mask[j] = true;
					while (leftMostT < n && (s.charAt(leftMostT) != 'T' || mask[leftMostT] == true))
						leftMostT++;
				}
			}

			for (int j = 1; j < n - 1; j++) {
				if (s.charAt(j) == 'M') {
					if (leftMostT <= j || leftMostT>=n) {
						success = false;
						break;
					}
					mask[leftMostT] = true;
					mask[j] = true;
					while (leftMostT < n && (s.charAt(leftMostT) != 'T' || mask[leftMostT] == true))
						leftMostT++;
				}
			}				
			if (success && leftMostT==n) { //if not == n; that means there're T unused
				System.out.println("YES");
			} else
				System.out.println("NO");
		}
	}
}
