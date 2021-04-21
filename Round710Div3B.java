import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Round710Div3B {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			String input = f.readLine();
			int lastX = n;
			for (int j = 0; j < n; j++) {
				if (input.charAt(j) == '*') {
					lastX = j;
					break;
				}
			}
			int lastlastX = n;
			for (int j = n - 1; j >= 0; j--) {
				if (input.charAt(j) == '*') {
					lastlastX = j;
					break;
				}
			}
			int count = 0;
			if (lastX < n)
				count++;
			while (lastX < lastlastX) {
				for (int j = n - 1; j > lastX; j--) {
					if (input.charAt(j) == '*' && j - lastX <= k) {
						lastX = j;
						count++;
						break;
					}
				}
			}
			System.out.println(count);
		}
	}
}
