import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class dining {
    static class Edge {
        int other, cost;

        public Edge(int o, int c) {
            other = o;
            cost = c;
        }
    }

    static class Point implements Comparable<Point> {
        int id;
        long cost;

        public Point(int i, long c) {
            id = i;
            cost = c;
        }

        @Override
        public int compareTo(dining.Point o) {
            return Long.compare(cost, o.cost);
        }

    }

    public static void main(String[] args) throws IOException {
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader f = new BufferedReader(new FileReader("dining.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dining.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numNode = Integer.parseInt(st.nextToken());
        int numEdge = Integer.parseInt(st.nextToken());
        int numFood = Integer.parseInt(st.nextToken());
        ArrayList<Edge>[] connector = new ArrayList[numNode];
        for (int i = 0; i < connector.length; i++) {
            connector[i] = new ArrayList<>();
        }
        for (int i = 0; i < numEdge; i++) {
            st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            connector[first].add(new Edge(second, cost));
            connector[second].add(new Edge(first, cost));
        }

        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[numNode];
        long[] dist = new long[numNode];
        pq.offer(new Point(numNode - 1, 0));
        Arrays.fill(dist, Long.MAX_VALUE / 2);
        dist[numNode - 1] = 0; // what if food at barn?
        while (!pq.isEmpty()) {
            int id = pq.poll().id; // 距离源点距离最短的点
            if (visited[id])
                continue;
            visited[id] = true;

            for (Edge edge : connector[id]) {
                if (dist[edge.other] > dist[id] + edge.cost) {
                    dist[edge.other] = dist[id] + edge.cost;
                    pq.offer(new Point(edge.other, dist[edge.other])); // we must offer a new Point for each update of a
                                                                       // vertex
                } // because priority queue only reorder if we offer something
            }
        }

        pq = new PriorityQueue<>();
        boolean[] visited2 = new boolean[numNode];
        long[] dist2 = new long[numNode];
        Arrays.fill(dist2, Long.MAX_VALUE / 2);
        for (int i = 0; i < numFood; i++) {
            st = new StringTokenizer(f.readLine());
            int index = Integer.parseInt(st.nextToken()) - 1;
            int val = Integer.parseInt(st.nextToken());
            pq.offer(new Point(index, dist[index] - val));
            dist2[index] = dist[index] - val;
        }
        while (!pq.isEmpty()) {
            int id = pq.poll().id; // 距离源点距离最短的点
            if (visited2[id])
                continue;
            visited2[id] = true;

            for (Edge edge : connector[id]) {
                if (dist2[edge.other] > dist2[id] + edge.cost) {
                    dist2[edge.other] = dist2[id] + edge.cost;
                    pq.offer(new Point(edge.other, dist2[edge.other])); // we must offer a new Point for each update of
                                                                        // a
                                                                        // vertex
                } // because priority queue only reorder if we offer something
            }
        }
        for (int i = 0; i < numNode - 1; i++) {
            if (dist2[i] <= dist[i])
                out.println(1);
            else
                out.println(0);
        }
        out.close();
    }
}
