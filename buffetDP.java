import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class buffetDP {
    static class Point implements Comparable<Point> {
        int id, quality;

        public Point(int id, int quality) {
            this.id = id;
            this.quality = quality;
        }

        @Override
        public int compareTo(Point o) {
            return quality - o.quality;
        }

    }

    static ArrayList<Integer>[] connector;
    static int cost, numNode;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("buffet.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buffet.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        numNode = Integer.parseInt(st.nextToken());
        cost = Integer.parseInt(st.nextToken());
        connector = new ArrayList[numNode];
        Point[] sortedPoints = new Point[numNode];
        for (int i = 0; i < connector.length; i++)
            connector[i] = new ArrayList<>();
        for (int i = 0; i < numNode; i++) {
            st = new StringTokenizer(f.readLine());
            int quality = Integer.parseInt(st.nextToken());
            sortedPoints[i] = new Point(i, quality);
            int numNeighbor = Integer.parseInt(st.nextToken());
            for (int j = 0; j < numNeighbor; j++) {
                int neighbor = Integer.parseInt(st.nextToken()) - 1;
                connector[i].add(neighbor);
            }
        }
        Arrays.sort(sortedPoints);
        int[][] dist = new int[numNode][numNode];
        for (int i = 0; i < numNode; i++)
            Arrays.fill(dist[i], -1);
        for (int i = 0; i < numNode; i++) {
            Queue<Integer> frontier = new ArrayDeque<>();
            frontier.offer(i);
            dist[i][i] = 0;
            while (!frontier.isEmpty()) {
                int id = frontier.poll();
                for (int next : connector[id]) {
                    if (dist[i][next] < 0) {
                        dist[i][next] = dist[i][id] + 1;
                        frontier.offer(next);
                    }
                }
            }
        }

        long[] dp = new long[numNode + 1];
        for (int i = 1; i <= numNode; i++) {
            int id = i - 1;
            Point thisP = sortedPoints[id];
            for (int prev = 0; prev < i; prev++) {
                int prevId = prev - 1;
                Point prevPoint = prevId < 0 ? null : sortedPoints[prevId];
                long routeCost;
                if (prevId < 0)
                    routeCost = 0;
                else
                    routeCost = dist[prevPoint.id][thisP.id] * (long) cost;
                if (routeCost < 0)
                    routeCost = Long.MAX_VALUE / 2;
                int quality = thisP.quality;
                if (prevId >= 0 && quality <= prevPoint.quality)
                    continue;
                dp[i] = Math.max(dp[i], dp[prev] + quality - routeCost);
            }
        }
        long max = 0;
        for (int i = 0; i <= numNode; i++)
            max = Math.max(max, dp[i]);

        out.println(max);
        out.close();
    }
}
