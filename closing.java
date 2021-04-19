import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class closing {
    static ArrayList<Integer>[] connectors;
    static int[] finder;
    static int[] size;

    static void merge(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            finder[fy] = fx;
            size[fx] += size[fy]; // this is all is needed to record size
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
        BufferedReader f = new BufferedReader(new FileReader("closing.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int numNode = Integer.parseInt(st.nextToken());
        int numEdge = Integer.parseInt(st.nextToken());
        connectors = new ArrayList[numNode];
        for (int i = 0; i < connectors.length; i++) {
            connectors[i] = new ArrayList<>();
        }
        for (int i = 0; i < numEdge; i++) {
            st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            connectors[first].add(second);
            connectors[second].add(first);
        }
        int[] killSeries = new int[numNode];
        for (int i = 0; i < numNode; i++) {
            st = new StringTokenizer(f.readLine());
            int kill = Integer.parseInt(st.nextToken()) - 1;
            killSeries[i] = kill;
        }

        finder = new int[numNode];
        size = new int[numNode];
        for (int i = 0; i < numNode; i++) {
            finder[i] = i;
            size[i] = 1;
        }
        String[] answers = new String[numNode];
        boolean[] putBack = new boolean[numNode];
        int total = 0;
        for (int i = numNode - 1; i >= 0; i--) {
            int add = killSeries[i];
            putBack[add] = true;
            for (int other : connectors[add]) {
                if (!putBack[other])
                    continue;
                merge(add, other);
            }
            total++;
            // System.out.println(size[find(add)] + " " + find(add));
            if (size[find(add)] == total)
                answers[i] = "YES";
            else
                answers[i] = "NO";
        }
        for (int i = 0; i < answers.length; i++) {
            out.println(answers[i]);
        }
        out.close();
    }
}
