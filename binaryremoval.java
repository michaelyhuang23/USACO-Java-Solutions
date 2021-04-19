import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class binaryremoval {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			String input = f.readLine();
			int l = input.length();
			int[] arr = new int[l + 1];
			for (int j = 1; j <= l; j++) {
				arr[j] = input.charAt(j - 1) == '1' ? 1 : 0;
			}
			boolean[] prefixSep = new boolean[l + 1];
			prefixSep[0]=true;
			for (int j = 1; j <= l; j++) {
				if (arr[j] == 1 && arr[j - 1] == 1)
					prefixSep[j] = false;
				else
					prefixSep[j] = prefixSep[j - 1];
			}
			boolean[] postfixSep = new boolean[l + 1];
			postfixSep[l] = true;
			for (int j = l - 1; j >= 1; j--) {
				if (arr[j] == 0 && arr[j + 1] == 0)
					postfixSep[j] = false;
				else
					postfixSep[j] = postfixSep[j + 1];
			}
			boolean success = false;
			for (int p = 0; p <= l; p++) {
				if (prefixSep[p] && postfixSep[p + 1]) {
					if (arr[p] == 1 && arr[p + 1] == 0)
						continue;
					success = true;
					break;
				}
			}
			if (success)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}
