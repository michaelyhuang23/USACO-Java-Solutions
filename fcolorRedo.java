import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class fcolorRedo {
	static int[] finder;

	static void merge(int x, int y) {// x is parent
		int fx = find(x);
		int fy = find(y);
		if (fx != fy) {
			finder[fy] = fx;
			// size[fx] += size[fy] this is all is needed to record size
		}
	}

	static int find(int u) {
		if (finder[u] == u) {
			return u;
		}

		finder[u] = find(finder[u]); // 路径压缩

		return finder[u];
	}

	public static void main(String[] args) throws IOException {
		// BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader f = new BufferedReader(new FileReader("fcolor.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fcolor.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		HashSet<Integer>[] cout = new HashSet[n];
		HashSet<Integer>[] cin = new HashSet[n];
		for (int i = 0; i < n; i++) {
			cout[i] = new HashSet<>();
			cin[i] = new HashSet<>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			cout[first].add(second);
			cin[second].add(first);
		}
		finder = new int[n];
		for (int i = 0; i < n; i++) {
			finder[i] = i;
		}
		Queue<Integer> frontier = new ArrayDeque<>();
		int[] occCount = new int[n];
		for (int i = 0; i < n; i++) {
			if (cout[i].size() >= 2) {
				frontier.add(i);
				occCount[i]++;
			}
		}
		while (!frontier.isEmpty()) {
			int u = frontier.poll();
			occCount[u]--;
			if (occCount[u] > 0)
				continue;
			u = find(u);
			if (cout[u].size() < 2)
				continue;
			int maxV = 0;
			int maxDeg = 0;
			for (int v : cout[u]) {
				if (cout[find(v)].size() + cin[find(v)].size() > maxDeg) {
					maxDeg = cout[find(v)].size() + cin[find(v)].size();
					maxV = find(v);
				}
			}
			HashSet<Integer> uIN = new HashSet<>();
			HashSet<Integer> uOUT = new HashSet<>();
			for (int v : cout[u]) {
				if (maxV == find(v))
					continue;
				cout[maxV].addAll(cout[find(v)]);
				boolean selfC = false;
				for (int i : cout[find(v)]) {
					if (find(i) == u) {
						uIN.add(find(v));
						continue;
					}
					if (find(i) == find(v)) {
						selfC = true;
						continue;
					}
					cin[find(i)].remove(find(v));
					cin[find(i)].add(maxV);
				}
				cin[maxV].addAll(cin[find(v)]);
				for (int i : cin[find(v)]) {
					if (find(i) == u) {
						uOUT.add(find(v));
						continue;
					}
					if (find(i) == find(v)) {
						selfC = true;
						continue;
					}
					cout[find(i)].remove(find(v));
					cout[find(i)].add(maxV);
				}
				if (selfC) {
					cout[maxV].add(maxV);
					cin[maxV].add(maxV);
				}
				finder[v] = maxV;
			}
			if (!uOUT.isEmpty()) {
				cout[u].removeAll(uOUT);
				cout[u].add(maxV);
			}
			if (!uIN.isEmpty()) {
				cin[u].removeAll(uIN);
				cin[u].add(maxV);
			}
			frontier.offer(maxV);
			occCount[maxV]++;
		}
		int c = 1;
		int[] colors = new int[n];
		for (int i = 0; i < n; i++) {
			if (colors[find(i)] == 0) {
				colors[find(i)] = c;
				c++;
			}
		}
		for (int i = 0; i < n; i++) {
			out.println(colors[find(i)]);
		}
		out.close();
	}
}
