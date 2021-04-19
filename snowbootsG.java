import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class snowbootsG {
	static class Tile implements Comparable<Tile> {
		int depth, id;

		public Tile(int d, int idd) {
			depth = d;
			id = idd;
		}

		public int compareTo(Tile o) {
			return o.depth - depth;
		}
	}

	static class Boot implements Comparable<Boot> {
		int maxDepth, id, maxStep;

		public Boot(int d, int idd, int s) {
			maxDepth = d;
			id = idd;
			maxStep = s;
		}

		@Override
		public int compareTo(Boot o) {
			return o.maxDepth - maxDepth;
		}
	}

	static int[] finder;
	static int[] size;
	static int maxSpan;

	static void merge(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		if (fx != fy) {
			finder[fy] = fx;
			size[fx] += size[fy];
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
		BufferedReader f = new BufferedReader(new FileReader("snowboots.in")); // new FileReader("snowboots.in") //new
																				// InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numTile = Integer.parseInt(st.nextToken());
		int numBoot = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		Tile[] allTiles = new Tile[numTile];
		for (int i = 0; i < numTile; i++) {
			int snowD = Integer.parseInt(st.nextToken());
			allTiles[i] = new Tile(snowD, i);
		}
		Arrays.sort(allTiles);
		Boot[] allBoots = new Boot[numBoot];
		for (int i = 0; i < numBoot; i++) {
			st = new StringTokenizer(f.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			allBoots[i] = new Boot(d, i, s);
		}
		Arrays.sort(allBoots);
		maxSpan = 0;
		finder = new int[numTile];
		size = new int[numTile];
		for (int i = 0; i < numTile; i++) {
			size[i]=1;
			finder[i] = i;
		}
		int[] answers = new int[numBoot];
		boolean[] tileIn = new boolean[numTile];
		int tile = 0;
		for (int boot = 0; boot < numBoot; boot++) {
			while (tile<numTile && allTiles[tile].depth > allBoots[boot].maxDepth) {
				Tile thisT = allTiles[tile];
				tileIn[thisT.id] = true;
				if (thisT.id > 0 && tileIn[thisT.id - 1]) {
					merge(thisT.id,thisT.id-1);
				}
				if (thisT.id < numTile - 1 && tileIn[thisT.id + 1]) {
					merge(thisT.id,thisT.id+1);
				}
				maxSpan = Math.max(maxSpan, size[find(thisT.id)]);
				tile++;
			}
			answers[allBoots[boot].id] = allBoots[boot].maxStep>maxSpan ? 1 : 0;
		}
		
		for(int i=0;i<numBoot;i++)
			out.println(answers[i]);
		out.close();
	}
}
