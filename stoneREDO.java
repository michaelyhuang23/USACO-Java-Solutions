import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class stoneREDO {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int len = Integer.parseInt(f.readLine());
		int[] arr = new int[len + 1];
		StringTokenizer st = new StringTokenizer(f.readLine());
		int max = 0;
		for (int i = 1; i <= len; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		Arrays.sort(arr);
		int[] arr2 = new int[len + 1];
		int[] counter = new int[len + 1];
		int count = 1;
		for (int i = 1; i <= len; i++) {
			if (arr2[count - 1] != arr[i]) {
				arr2[count] = arr[i];
				counter[count]++;
				count++;
			} else
				counter[count - 1]++;
		}
		int[] arr3 = new int[count];
		for (int i = 1; i < count; i++) {
			arr3[i] = arr2[i];
		}
		long[] prefix = new long[count];
		for (int i = 1; i < count; i++) {
			prefix[i] = prefix[i - 1] + counter[i];
		}
		int[] finder = new int[max + 2];
		for (int i = 1; i <= max + 1; i++) {
			int index = Arrays.binarySearch(arr3, i);
			if (index < 0)
				index = -index - 1;
			finder[i] = index;
		}
		long total = 0;
		for (int k = 1; k <= max; k++) {
			long[] histogram = new long[max / k + 2];
			int maxP = 0;
			for (int p = 1; p * k <= max; p++) {
				int left = finder[p * k];
				int right = finder[Math.min((p + 1) * k, max + 1)];
				right--;
				long totalCount = prefix[right] - prefix[left - 1];
				histogram[p] = totalCount;
				maxP = p;
			}
			int oddCount = 0;
			for (int p = 1; p <= maxP; p++) {
				if (histogram[p] % 2 == 1)
					oddCount++;
			}
			if (oddCount > 2 || oddCount == 0)
				continue;
			if (oddCount == 1) {
				if (histogram[1] % 2 == 1)
					total += histogram[1];
				continue;
			}
			assert oddCount == 2;
			for (int p = 2; p <= maxP; p++) {
				if (histogram[p] % 2 == 1 && histogram[p - 1] % 2 == 1) {
					total += histogram[p];
					break;
				}
			}
		}
		System.out.println(total);
	}
}
