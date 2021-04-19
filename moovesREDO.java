import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class moovesREDO {
	public static void main(String[] args) throws IOException {
		int[] log = new int[1000000];
		log[0] = -1; // all the log values are rounded down except the first; also log means log2
						// here
		for (int i = 1; i < 1000000; i++)
			log[i] = log[i >> 1] + 1;

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numPos = Integer.parseInt(st.nextToken());
		int numSwap = Integer.parseInt(st.nextToken());
		long totalMove = Long.parseLong(st.nextToken());
		int[] heads = new int[numSwap * 2];
		int[] tails = new int[numSwap * 2];
		for (int i = 0; i < numSwap; i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			heads[i * 2] = first;
			tails[i * 2] = second;
			heads[i * 2 + 1] = second;
			tails[i * 2 + 1] = first;
		}
		int[] lastHead = new int[numPos];
		Arrays.fill(lastHead, -1);
		int[] nextMove = new int[numSwap * 2];
		for (int i = numSwap - 1; i >= 0; i--) {
			nextMove[i * 2] = lastHead[tails[i * 2]];
			nextMove[i * 2 + 1] = lastHead[tails[i * 2 + 1]];
			lastHead[heads[i * 2]] = i * 2;
			lastHead[heads[i * 2 + 1]] = i * 2 + 1;
		}
		for (int i = 0; i < numSwap * 2; i++) {
			if (nextMove[i] > -1)
				continue;
			nextMove[i] = lastHead[tails[i]];
		}

		long[] distTo = new long[numSwap * 2];
		long[] distCycle = new long[numSwap * 2];
		boolean[] visited = new boolean[numSwap * 2];
		int[] indices = new int[numSwap * 2];
		for (int i = 0; i < numPos; i++) {
			int swap = lastHead[i];
			if (swap == -1 || visited[swap])
				continue;
			indices[swap] = 0;
			distTo[swap] = 0;
			int counter = 0;
			int prev = swap;
			visited[swap] = true;
			for (int next = nextMove[swap]; next != swap; next = nextMove[next]) {
				counter++;
				visited[next] = true;
				indices[next] = counter;
				int dist = (next / 2 - prev / 2);
				if (dist <= 0)
					dist += numSwap;
				distTo[next] = distTo[prev] + dist;
				prev = next;
			}
			int distF = (swap / 2 - prev / 2);
			if (distF <= 0)
				distF += numSwap;
			distCycle[swap] = distTo[prev] + distF;
			for (int next = nextMove[swap]; next != swap; next = nextMove[next]) {
				distCycle[next] = distCycle[swap];
			}
		}

		int[] answers = new int[numSwap * 2];
		for (int i = 0; i < numPos; i++) {
			int swap = lastHead[i];
			if (swap == -1) {
				System.out.println(1);
				continue;
			}
			if (answers[swap] != 0) {
				System.out.println(answers[swap]);
				continue;
			}
			int low = swap;
			int high = swap;
			int countt = 0;
			HashMap<Integer, Integer> tracker = new HashMap<Integer, Integer>(); // initialize
			tracker.put(heads[swap], 1);
			tracker.put(tails[swap], 1);
			if (distCycle[swap] <= totalMove) {
				for (int next = nextMove[swap]; next != swap; next = nextMove[next]) {
					tracker.put(heads[next], 1);
				}
				answers[swap] = tracker.size();
				for (int next = nextMove[swap]; next != swap; next = nextMove[next]) {
					answers[next] = answers[swap];
				}
				System.out.println(answers[swap]);
				continue;
			}

			while ((low != swap || countt == 0)) {
				if ((distTo[nextMove[high]] - distTo[low] + distCycle[swap]) % distCycle[swap] + low / 2
						+ 1 <= totalMove) {
					high = nextMove[high];
					if (tracker.containsKey(tails[high]))
						tracker.put(tails[high], tracker.get(tails[high]) + 1);
					else
						tracker.put(tails[high], 1);
				} else {
					if ((distTo[high] - distTo[low] + distCycle[swap]) % distCycle[swap] + low / 2 + 1 <= totalMove)
						answers[low] = tracker.size();
					tracker.put(heads[low], tracker.get(heads[low]) - 1);
					if (tracker.get(heads[low]) == 0)
						tracker.remove(heads[low]);
					if (high == low)
						break;
					low = nextMove[low];
					countt++;
				}
			}
			if (answers[swap] == 0)
				answers[swap]++;
			System.out.println(answers[swap]);

		}
	}
}
