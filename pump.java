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

public class pump {
    static class Edge implements Comparable<Edge> {
        int first, second, cost, flow;

        public Edge(int f, int s, int c, int fl) {
            first = f;
            second = s;
            cost = c;
            flow = fl;
        }

        @Override
        public int compareTo(pump.Edge o) {
            return o.flow - flow;
        }
    }

    static class Point implements Comparable<Point> {
        int id, dist;

        public Point(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(pump.Point o) {
            return dist - o.dist;
        }

    }

    static ArrayList<Edge>[] connectors;
    static int numNode, numEdge;
    static Edge[] allEdges;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("pump.in")); // new FileReader("rblock.in") //new
        // InputStreamReader(System.in)
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pump.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        numNode = Integer.parseInt(st.nextToken());
        numEdge = Integer.parseInt(st.nextToken());
        allEdges = new Edge[numEdge];
        for (int i = 0; i < numEdge; i++) {
            st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            int flow = Integer.parseInt(st.nextToken());
            allEdges[i] = new Edge(first, second, cost, flow);
        }
        connectors = new ArrayList[numNode];
        for (int i = 0; i < numNode; i++)
            connectors[i] = new ArrayList<>();

        int bestCost = 1;
        int bestFlow = 0;
        Arrays.sort(allEdges);
        for (int i = 0; i < numEdge; i++) {
            Edge thisE = allEdges[i];
            connectors[thisE.first].add(thisE);
            connectors[thisE.second].add(thisE);
            int cost = dijkstra(0, numNode - 1);
            if (cost >= 0 && thisE.flow * bestCost > bestFlow * cost) {
                bestFlow = thisE.flow;
                bestCost = cost;
            }
        }
        out.println(bestFlow * 1000000 / bestCost);
        out.close();
    }

    private static int dijkstra(int origin, int target) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(origin, 0));
        boolean[] visited = new boolean[numNode];
        int[] dist = new int[numNode];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[origin] = 0;
        while (!pq.isEmpty()) {
            int id = pq.poll().id; // 距离源点距离最短的点
            if (visited[id])
                continue;
            visited[id] = true;
            if (id == target) {
                return dist[target];
            }
            for (Edge edge : connectors[id]) {
                int to = edge.first == id ? edge.second : edge.first;
                if (dist[to] > dist[id] + edge.cost) {
                    dist[to] = dist[id] + edge.cost;
                    pq.offer(new Point(to, dist[to])); // we must offer a new Point for each update of a vertex
                } // because priority queue only reorder if we offer something
            }
        }
        return -1;
    }
}
