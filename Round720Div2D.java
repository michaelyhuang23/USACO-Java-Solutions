import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Round720Div2D {
	static ArrayList<Integer>[] connector;
	static int[] tails;
	static ArrayList<String> answers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			connector = new ArrayList[n];
			for (int j = 0; j < connector.length; j++) {
				connector[j] = new ArrayList<>();
			}
			for (int j = 0; j < n - 1; j++) {
				StringTokenizer st = new StringTokenizer(f.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				connector[a].add(b);
				connector[b].add(a);
			}
			tails = new int[n];
			answers = new ArrayList<String>();
			int root = 0;
			for (int j = 0; j < n; j++) {
				if(connector[j].size()==1)
					root = j;
			}
			dfs(root, root);

			System.out.println(answers.size());
			for (int j = 0; j < answers.size(); j++) {
				System.out.println(answers.get(j));
			}
		}
	}

	private static void dfs(int cur, int parent) {
		if (connector[cur].size() == 1 && cur!=parent) {
			// reached the tip
			tails[cur] = cur;
			return;
		}
		for (int son : connector[cur]) {
			if (son == parent)
				continue;
			dfs(son, cur);
		}
		int curTail = -1;
		for (int son : connector[cur]) {
			if (son == parent)
				continue;
			if (curTail == -1) {
				curTail = tails[son];
			} else {
				answers.add((cur + 1) + " " + (son + 1) + " " + (curTail + 1) + " " + (son + 1));
				curTail = tails[son];
			}
		}
		tails[cur] = curTail;
	}
}
