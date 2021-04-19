import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class portals {
	static class PairNode {
		int edge1, edge2;
		int vertex;
		int id;

		public PairNode(int v, int e1, int e2, int i) {
			// TODO Auto-generated constructor stub
			vertex = v;
			edge1 = e1;
			edge2 = e2;
			id = i;
		}
	}

	static class AdjEdge implements Comparable<AdjEdge> {
		int pair1, pair2;
		int cost;

		public AdjEdge(int p1, int p2, int c) {
			pair1 = p1;
			pair2 = p2;
			cost = c;
		}

		@Override
		public int compareTo(portals.AdjEdge o) {
			return cost - o.cost;
		}
	}

	static int[] finder;

	static void mergeRoot(int x, int y) {
		int fx = findRoot(x);
		int fy = findRoot(y);
		if (fx != fy) {
			finder[fy] = fx;
		}
	}

	static int findRoot(int u) {
		if (finder[u] == u) {
			return u;
		}

		finder[u] = findRoot(finder[u]);
		return finder[u];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(f.readLine());
		int[][] edges = new int[n][4];
		PairNode[] edgePairs = new PairNode[n * 2];
		int[] costs = new int[n];
		int[] firstOcc = new int[2 * n];
		Arrays.fill(firstOcc, -1);
		int[] secondOcc = new int[2 * n];
		Arrays.fill(secondOcc, -1);
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int co = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			costs[i] = co;
			edges[i][0] = a;
			edges[i][1] = b;
			edges[i][2] = c;
			edges[i][3] = d;
			edgePairs[i * 2] = new PairNode(i, a, b, i * 2);
			edgePairs[i * 2 + 1] = new PairNode(i, c, d, i * 2 + 1);

			if (firstOcc[a] == -1)
				firstOcc[a] = i;
			else
				secondOcc[a] = i;

			if (firstOcc[b] == -1)
				firstOcc[b] = i;
			else
				secondOcc[b] = i;

			if (firstOcc[c] == -1)
				firstOcc[c] = i;
			else
				secondOcc[c] = i;

			if (firstOcc[d] == -1)
				firstOcc[d] = i;
			else
				secondOcc[d] = i;
		}
		finder = new int[2 * n];
		for (int i = 0; i < 2 * n; i++) {
			finder[i] = i;
		}
		boolean[] masked = new boolean[2 * n];
		for (int i = 0; i < 2 * n; i++) {
			int first = firstOcc[i];
			int second = secondOcc[i];
			int firstPair = 0;
			if (edges[first][0] == i || edges[first][1] == i)
				firstPair = edgePairs[2 * first].id;
			else
				firstPair = edgePairs[2 * first + 1].id;

			int secondPair = 0;
			if (edges[second][0] == i || edges[second][1] == i)
				secondPair = edgePairs[2 * second].id;
			else
				secondPair = edgePairs[2 * second + 1].id;
			// the pair is connected, watch out for triple connection
			int firstOtherEdge = edgePairs[firstPair].edge1 == i ? edgePairs[firstPair].edge2
					: edgePairs[firstPair].edge1;
			int secondOtherEdge = edgePairs[secondPair].edge1 == i ? edgePairs[secondPair].edge2
					: edgePairs[secondPair].edge1;

			if (firstOtherEdge == secondOtherEdge) {
				HashSet<Integer> firstVertex = new HashSet<>();
				for (int j = 0; j < 4; j++) {
					firstVertex.add(edges[first][j]);
				}
				int count = 0;
				for (int j = 0; j < 4; j++) {
					if (firstVertex.contains(edges[second][j]))
						count++;
				}
				assert count < 4;
				// else it's dead (or if there's only two vertices)
				if (count == 3) {
					masked[firstPair] = true;
					masked[secondPair] = true;
				}
			}

			mergeRoot(firstPair, secondPair);
		}

		int edgeCount = 0;
		AdjEdge[] adjEdges = new AdjEdge[n];
		for (int i = 0; i < n; i++) {
			if (findRoot(i * 2) == findRoot(i * 2 + 1))
				continue;
			if (masked[i * 2] || masked[i * 2 + 1])
				continue;
			adjEdges[edgeCount] = new AdjEdge(i * 2, i * 2 + 1, costs[i]);
			edgeCount++;
		}
		long totalCost = 0;
		Arrays.sort(adjEdges, 0, edgeCount);
		for (int i = 0; i < edgeCount; i++) {
			int first = adjEdges[i].pair1;
			int second = adjEdges[i].pair2;
			if (findRoot(first) == findRoot(second))
				continue;
			mergeRoot(first, second);
			totalCost += adjEdges[i].cost;
		}
		System.out.println(totalCost);
	}
}
