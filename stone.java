import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class stone {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int total = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] arr = new int[total];
		int max = 0;
		for (int i = 0; i < total; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		long count = 0;
		boolean[] allNum = new boolean[max + 1];
		for (int i = 1; i <= max; i++) {
			if (allNum[i])
				continue;
			int[] cateCount = new int[max / i + 1];
			for (int j = 0; j < total; j++) {
				cateCount[arr[j] / i]++;
			}
			boolean success = true;
			int oddCount = 0;
			boolean tail = false;
			for (int j = max / i; j >= 2; j--) {
				if (cateCount[j] % 2 == 1 && oddCount == 2) {
					success = false;
					break;
				}
				if (cateCount[j] % 2 == 1 && cateCount[j - 1] % 2 == 0 && oddCount == 0) {
					success = false;
					break;
				}
				if (cateCount[j] % 2 == 1 && cateCount[j - 1] % 2 == 1 && oddCount == 0) {
					oddCount += 2;
					if (j == 2) {
						tail = true;
						break;
					}
					j--;
				}
			}
			if (success) {
				if (tail || oddCount == 0 && cateCount[1] % 2 == 1) {
					count += cateCount[1]; // mess here!
					for (int j = i; j <= max; j += i) {
						allNum[j] = true;
					}
				}
			}
		}
		System.out.println(count);

	}
}
