import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class CF1485 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numCase = Integer.parseInt(f.readLine());
		for (int i = 0; i < numCase; i++) {
			int numNode = Integer.parseInt(f.readLine());
			ArrayList<Integer>[] connector = new ArrayList[numNode];
			for (int j = 0; j < numNode; j++)
				connector[j] = new ArrayList<>();

			StringTokenizer st = new StringTokenizer(f.readLine());
			for (int j = 1; j < numNode; j++) {
				int other = Integer.parseInt(st.nextToken()) - 1;
				connector[j].add(other);
				connector[other].add(j);
			}

			int[] a = new int[numNode];
			a[0] = 0;
			st = new StringTokenizer(f.readLine());
			for (int j = 1; j < numNode; j++) {
				int curA = Integer.parseInt(st.nextToken());
				a[j] = curA;
			}

			Queue<Integer> frontier = new ArrayDeque<>();
			int[] dist = new int[numNode];
			int[] labels = new int[numNode];
			int[] layerWidth = new int[numNode];
			layerWidth[0] = 1;
			Arrays.fill(dist, -1);
			dist[0] = 0;
			frontier.offer(0);
			int prevDist = 0;
			int counter = 1;
			while (!frontier.isEmpty()) {
				int cur = frontier.poll();
				if (dist[cur] != prevDist) {
					layerWidth[prevDist] = counter - 1;
					prevDist = dist[cur];
					counter = 1;
				}
				labels[cur] = counter;
				counter++;
				for (int next : connector[cur]) {
					if (dist[next] > -1)
						continue;
					dist[next] = dist[cur] + 1;
					frontier.offer(next);
				}
			}
			layerWidth[prevDist] = counter - 1;
			ArrayList<Integer>[] relabels = new ArrayList[prevDist + 1];
			for (int j = 0; j <= prevDist; j++) {
				relabels[j] = new ArrayList<Integer>();
				for (int j2 = 0; j2 <= layerWidth[j]; j2++) {
					relabels[j].add(0);
				}
			}
			for (int j = 0; j < numNode; j++) {
				relabels[dist[j]].set(labels[j], j);
			}

			int[] root = new int[numNode];
			long[] layerMax = new long[prevDist + 1];
			long[] layerMin = new long[prevDist + 1];
			Arrays.fill(layerMin, Integer.MAX_VALUE);
			long[] maxChild = new long[numNode];
			long[] minChild = new long[numNode];
			Arrays.fill(minChild, Integer.MAX_VALUE);
			for (int j = 0; j < numNode; j++) {
				for (int k : connector[j]) {
					if (dist[k] < dist[j])
						root[j] = k;
				}
				layerMax[dist[j]] = Math.max(layerMax[dist[j]], a[j]);
				layerMin[dist[j]] = Math.min(layerMin[dist[j]], a[j]);
			}
			for (int j = 0; j < numNode; j++) {
				if (j == 0)
					continue;
				maxChild[root[j]] = Math.max(maxChild[root[j]], a[j]);
				minChild[root[j]] = Math.min(minChild[root[j]], a[j]);
			}

			ArrayList<Long>[] dp = new ArrayList[prevDist + 1];
			for (int j = 0; j <= prevDist; j++) {
				dp[j] = new ArrayList<>();
				for (int j2 = 0; j2 <= layerWidth[j]; j2++) {
					dp[j].add(0L);
				}
			}
			long prev1 = maxChild[0];
			long prev2 = -minChild[0];
			for (int d = 1; d <= prevDist; d++) {
				for (int r = 1; r <= layerWidth[d]; r++) {
					// case 1
					long maxExtra = Math.max(Math.abs(layerMax[d] - a[relabels[d].get(r)]),
							Math.abs(layerMin[d] - a[relabels[d].get(r)]));
					dp[d].set(r, Math.max(dp[d].get(r), dp[d - 1].get(labels[root[relabels[d].get(r)]]) + maxExtra));
					// case 2
					long max = Math.max(prev1 - a[relabels[d].get(r)],
							prev2 + a[relabels[d].get(r)]);
					dp[d].set(r, Math.max(dp[d].get(r), max));
				}
				prev1 = 0;
				prev2 = Integer.MIN_VALUE;
				for (int r = 1; r <= layerWidth[d]; r++) {
					prev1 = Math.max(prev1, dp[d].get(r) + maxChild[relabels[d].get(r)]);
					prev2 = Math.max(prev2, dp[d].get(r) - minChild[relabels[d].get(r)]);
				}
			}
			long totalMax = 0;
			for (int j = 0; j <= layerWidth[prevDist]; j++) {
				totalMax = Math.max(totalMax, dp[prevDist].get(j));
			}
			System.out.println(totalMax);
		}
	}
}
