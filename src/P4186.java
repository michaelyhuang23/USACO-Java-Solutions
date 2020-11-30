import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P4186 {
    static int[] dist;
    static ArrayList<Integer>[] connectors;
    static int numFarm;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("atlarge.in")); // new FileReader("socdist.in") //new
        // InputStreamReader(System.in)
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("atlarge.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        numFarm = Integer.parseInt(st.nextToken());
        int BFarm = Integer.parseInt(st.nextToken());
        connectors = new ArrayList[numFarm + 1];
        dist = new int[numFarm + 1];
        for (int i = 0; i <= numFarm; i++)
            connectors[i] = new ArrayList<>();
        for (int i = 1; i <= numFarm - 1; i++) {
            st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            connectors[first].add(second);
            connectors[second].add(first);
        }
        bfs(BFarm);
        visited = new boolean[numFarm + 1];
        int[] returns = new int[2];
        returns = dfs(BFarm);

        out.println(returns[1]);
        out.close();
    }

    static boolean[] visited;

    private static int[] dfs(int root) {
        visited[root] = true;
        if (connectors[root].size() == 1 && visited[connectors[root].get(0)])
            return new int[] { dist[root], 1 };

        int minDist = Integer.MAX_VALUE / 2;
        int sumGuards = 0;
        for (int next : connectors[root]) {
            if (visited[next])
                continue;
            int[] returns = dfs(next);
            minDist = Math.min(minDist, returns[0]);
            sumGuards += returns[1];
        }
        int dist2exit = minDist - dist[root];
        if (dist2exit <= dist[root])
            return new int[] { minDist, 1 };
        return new int[] { minDist, sumGuards };
    }

    private static void bfs(int start) {
        visited = new boolean[numFarm + 1];
        Queue<Integer> frontier = new ArrayDeque<>();
        frontier.offer(start);
        dist[start] = 0;
        while (!frontier.isEmpty()) {
            int node = frontier.poll();
            visited[node] = true;
            for (int next : connectors[node]) {
                if (visited[next])
                    continue;
                dist[next] = dist[node] + 1;
                frontier.offer(next);
            }
        }
    }
}
