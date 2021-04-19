import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class fcolor {
    static int numNode, numEdge;
    static ArrayList<Integer>[] fans;
    static int[] headFan;
    static boolean[] visited;

    static int[] finder;

    static void merge(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            finder[fy] = fx;
            headFan[fx] = Math.max(headFan[fx], headFan[fy]);
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
        numNode = Integer.parseInt(st.nextToken());
        numEdge = Integer.parseInt(st.nextToken());
        fans = new ArrayList[numNode];
        for (int i = 0; i < numNode; i++) {
            fans[i] = new ArrayList<>();
        }
        for (int i = 0; i < numEdge; i++) {
            st = new StringTokenizer(f.readLine());
            int star = Integer.parseInt(st.nextToken()) - 1;
            int fan = Integer.parseInt(st.nextToken()) - 1;
            fans[star].add(fan);
        }
        visited = new boolean[numNode];
        finder = new int[numNode];
        for (int i = 0; i < finder.length; i++) {
            finder[i] = i;
        }
        headFan = new int[numNode];
        Arrays.fill(headFan, -1);
        for (int i = 0; i < numNode; i++) {
            if (visited[i])
                continue;
            dfs(i);
        }

        HashMap<Integer, Integer> group2Col = new HashMap<>();
        int counter = 1;
        for (int i = 0; i < numNode; i++) {
            int father = find(i);
            if (group2Col.containsKey(father)) {
                out.println(group2Col.get(father));
            } else {
                group2Col.put(father, counter);
                out.println(counter);
                counter++;
            }
        }
        out.close();
    }

    private static void dfs(int index) {
        visited[index] = true;
        if (fans[index].size() == 0)
            return;
        int otherHead = fans[index].get(0);
        for (int other : fans[index]) {
            merge(other, otherHead);
        }
        int curHead = find(index);
        if (headFan[curHead] == -1)
            headFan[curHead] = otherHead;
        else {
            int grandFan = headFan[find(headFan[curHead])];

            merge(headFan[curHead], otherHead); // sequence matters
            headFan[find(otherHead)] = Math.max(headFan[find(otherHead)], grandFan);
        }
        for (int other : fans[index]) {
            if (!visited[other])
                dfs(other);
        }
    }
}
