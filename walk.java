import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class walk {
    // (2019201913x+2019201949y) mod 2019201997
    static class Edge implements Comparable<Edge> {
        int small, big;
        long distance;

        public Edge(int s, int b) {
            small = s - 1;
            big = b - 1;
            distance = (2019201913L * s + 2019201949L * b) % 2019201997L;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(distance, o.distance);
        }

    }

    static int[] finder;
    static int groupNum;

    static void merge(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            groupNum--;
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
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader f = new BufferedReader(new FileReader("walk.in"));
        // PrintWriter out = new PrintWriter(new BufferedWriter(new
        // FileWriter("walk.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numCow = Integer.parseInt(st.nextToken());
        int numGroup = Integer.parseInt(st.nextToken());
        Edge[] allEdges = new Edge[numCow * (numCow - 1) / 2];
        int index = 0;
        for (int i = 1; i <= numCow; i++) {
            for (int j = i + 1; j <= numCow; j++) {
                allEdges[index] = new Edge(i, j);
                index++;
            }
        }
        System.out.println("first");
        Arrays.parallelSort(allEdges);
        System.out.println("second");
        groupNum = numCow;
        finder = new int[numCow];
        for (int i = 0; i < numCow; i++) {
            finder[i] = i;
        }
        long ans = 0;
        for (int i = 0; i < allEdges.length; i++) { // <numGroup
            int small = allEdges[i].small;
            int big = allEdges[i].big;
            merge(small, big);
            if (groupNum < numGroup) {
                ans = allEdges[i].distance;
                break;
            }
        }
        System.out.println(ans);
        // out.close();
    }
}
