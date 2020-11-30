
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class triangles_EXAMPLE {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("triangles.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
		int n = Integer.parseInt(in.readLine());
		int[] x = new int[n + 1];
		int[] y = new int[n + 1];
		ArrayList<Integer>[] vX = new ArrayList[20001];
		ArrayList<Integer>[] vY = new ArrayList[20001];
		ArrayList<Integer>[] aX = new ArrayList[20001];
		ArrayList<Integer>[] aY = new ArrayList[20001];
		for (int i = 0; i < 20001; i++) {
			vX[i] = new ArrayList<>();
			vY[i] = new ArrayList<>();
			aX[i] = new ArrayList<>();
			aY[i] = new ArrayList<>();
		}

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			x[i] = Integer.parseInt(st.nextToken()) + 10000;
			y[i] = Integer.parseInt(st.nextToken()) + 10000;
			vX[x[i]].add(y[i]);
			vY[y[i]].add(x[i]);
		}

		for (int i = 0; i < 20001; i++) {
			// 对于坐标相同的一系列点，可以先把它们按坐标排序，
			// 然后线性求出其中的每个点和坐标相同的所有点的差的绝对值之和
			if (!vX[i].isEmpty()) {
				Collections.sort(vX[i]);
				int s = 0;
				for (int j : vX[i]) {
					s += j - vX[i].get(0);
				}
				aX[i].add(s);
				for (int j = 1; j < vX[i].size(); j++) {
					s += (j - (vX[i].size() - j)) * (vX[i].get(j) - vX[i].get(j - 1));
					aX[i].add(s);
				}
					
			}

			if (!vY[i].isEmpty()) {
				Collections.sort(vY[i]);
				int s = 0;
				for (int j : vY[i])
					s += j - vY[i].get(0);
				aY[i].add(s);
				for (int j = 1; j < vY[i].size(); j++) {
					s += (j - (vY[i].size() - j)) * (vY[i].get(j) - vY[i].get(j - 1));
					aY[i].add(s);
				}					
			}
		}

		long answer = 0;
		for (int i = 1; i <= n; i++) {
			int lx = Collections.binarySearch(vX[x[i]], y[i]);
			int ly = Collections.binarySearch(vY[y[i]], x[i]);
			// 累加i为直⻆顶点的三⻆形的⾯积之和
			answer = (answer + (long) aX[x[i]].get(lx) * aY[y[i]].get(ly)) % 1000000007;
		}
		pw.println(answer);

		in.close();
		pw.close();
	}

}