import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Round710Div3D {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			StringTokenizer st = new StringTokenizer(f.readLine());
			int[] arr = new int[n];
			for (int j = 0; j < n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			PriorityQueue<Integer> pairs = new PriorityQueue<>(Collections.reverseOrder());
			int lastStart = 0;
			for (int j = 1; j < n; j++) {
				if (arr[j] != arr[j - 1]) {
					pairs.add(j - lastStart);
					lastStart = j;
				}
			}
			pairs.add(n - lastStart);
			while (pairs.size() > 1) {
				int first = pairs.poll();
				int second = pairs.poll();
				if (first - 1 > 0)
					pairs.offer(first - 1);
				if (second - 1 > 0)
					pairs.offer(second - 1);
			}
			if (pairs.size() == 0)
				System.out.println(0);
			else
				System.out.println(pairs.poll());
		}
	}
}
