import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class timeline {
    static class Edge {
        int to;
        long cost;

        public Edge(int t, long c) {
            to = t;
            cost = c;
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader f = new BufferedReader(new FileReader("timeline.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("timeline.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numNode = Integer.parseInt(st.nextToken());
        int maxDate = Integer.parseInt(st.nextToken());
        int numEdge = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        long[] minDist = new long[numNode];
        int[] inDeg = new int[numNode + 1];
        ArrayList<Edge>[] connector = new ArrayList[numNode + 1];
        for (int i = 0; i < connector.length; i++)
            connector[i] = new ArrayList<>();
        for (int i = 0; i < numNode; i++)
            minDist[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < numEdge; i++) {
            st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            long cost = Integer.parseInt(st.nextToken());
            connector[first].add(new Edge(second, cost));
            inDeg[second]++;
        }
        for (int i = 0; i < numNode; i++) {
            connector[numNode].add(new Edge(i, minDist[i]));
            inDeg[i]++;
        }

        Queue<Integer> frontier = new ArrayDeque<>();
        long[] maxDist = new long[numNode + 1];
        frontier.offer(numNode);
        while (!frontier.isEmpty()) {
            int thisNode = frontier.poll();
            for (Edge edge : connector[thisNode]) {
                inDeg[edge.to]--;
                maxDist[edge.to] = Math.max(maxDist[edge.to], edge.cost + maxDist[thisNode]);
                if (inDeg[edge.to] == 0)
                    frontier.offer(edge.to);
            }
        }

        for (int i = 0; i < numNode; i++) {
            out.println(maxDist[i]);
        }
        out.close();
    }
}
