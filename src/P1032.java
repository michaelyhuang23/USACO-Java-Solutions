import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1032 {
	static class Point {
		String str;
		int step;

		public Point(String a, int s) {
			str = a;
			step = s;
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		String origin = st.nextToken();
		String target = st.nextToken();
		ArrayList<String> keyList = new ArrayList<String>();
		ArrayList<String> valueList = new ArrayList<String>();
		HashSet<String> visited = new HashSet<>();
		String input = "";
		do {
			input = f.readLine().trim();
			st = new StringTokenizer(input);
			keyList.add(st.nextToken().trim());
			valueList.add(st.nextToken().trim());
		} while (f.ready());
		Queue<Point> frontier = new ArrayDeque<>();
		frontier.offer(new Point(origin, 0));
		visited.add(origin);
		while (!frontier.isEmpty()) {
			Point thisPoint = frontier.poll();
			if (thisPoint.step > 10) {
				System.out.println("NO ANSWER!");
				return;
			}
			if (thisPoint.str.equals(target)) {
				System.out.println(thisPoint.step);
				return;
			}
			for (int i = 0; i < keyList.size(); i++) {
				String expr = keyList.get(i);
				for (int start = 0; start + expr.length() <= thisPoint.str.length(); start++) {
					// System.out.println(thisPoint.str.substring(start, start+expr.length()));
					if (thisPoint.str.substring(start, start + expr.length()).equals(expr)) {
						String newStr = thisPoint.str.substring(0, start) + valueList.get(i)
								+ thisPoint.str.substring(start + expr.length(), thisPoint.str.length());
						// System.out.println(newStr);
						if (visited.contains(newStr))
							continue;
						frontier.offer(new Point(newStr, thisPoint.step + 1));
						visited.add(newStr);
					}
				}
			}
		}
		System.out.println("NO ANSWER!");

	}
}
