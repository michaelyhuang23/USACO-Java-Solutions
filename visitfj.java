import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class visitfj {
    static class Point implements Comparable<Point> {
        int r, c, count;
        long dist;

        public Point(int r, int c, long dist, int count) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.count = count; // % 3
        }

        @Override
        public int compareTo(visitfj.Point o) {
            return Long.compare(dist, o.dist);
        }

    }

    public static void main(String[] args) throws IOException {
        final int[][] DIR = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        BufferedReader f = new BufferedReader(new FileReader("visitfj.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int length = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());
        int[][] map = new int[length][length];
        for (int r = 0; r < length; r++) {
            st = new StringTokenizer(f.readLine());
            for (int c = 0; c < length; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        PriorityQueue<Point> frontier = new PriorityQueue<>();
        frontier.offer(new Point(0, 0, 0, 0));
        boolean[][][] visited = new boolean[length][length][3];
        long[][][] dist = new long[length][length][3];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                Arrays.fill(dist[i][j], Long.MAX_VALUE / 2);
            }
        }
        dist[0][0][0] = 0;

        while (!frontier.isEmpty()) {
            Point thisPrt = frontier.poll();
            if (visited[thisPrt.r][thisPrt.c][thisPrt.count])
                continue;
            visited[thisPrt.r][thisPrt.c][thisPrt.count] = true;
            //
            for (int i = 0; i < 4; i++) {
                int newR = thisPrt.r + DIR[i][0];
                int newC = thisPrt.c + DIR[i][1];
                int newCount = (thisPrt.count + 1) % 3;
                if (newR < 0 || newR >= length || newC < 0 || newC >= length)
                    continue;
                long newDist = dist[thisPrt.r][thisPrt.c][thisPrt.count] + cost;
                if (newCount == 0)
                    newDist += map[newR][newC];
                if (dist[newR][newC][newCount] > newDist) {
                    dist[newR][newC][newCount] = newDist;
                    frontier.offer(new Point(newR, newC, newDist, newCount));
                }
            }
        }
        long minCost = Long.MAX_VALUE / 2;
        for (int i = 0; i < 3; i++) {
            minCost = Math.min(minCost, dist[length - 1][length - 1][i]);
        }
        out.println(minCost);
        out.close();
    }
}
