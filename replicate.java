import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class replicate {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        final int[][] DIR = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int length = Integer.parseInt(st.nextToken());
        int stepToSize = Integer.parseInt(st.nextToken());
        int[][] dist = new int[length][length];
        for (int i = 0; i < length; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
        Queue<Point> frontier = new ArrayDeque<>();
        int[][] minDist = new int[length][length];
        for (int i = 0; i < minDist.length; i++)
            Arrays.fill(minDist[i], -1);
        for (int r = 0; r < length; r++) {
            String input = f.readLine();
            for (int c = 0; c < length; c++) {
                char cur = input.charAt(c);
                if (cur == '#')
                    dist[r][c] = 0;
                if (cur == 'S') {
                    frontier.offer(new Point(r, c));
                    minDist[r][c] = 0;
                }
            }
        }

        for (int r = 0; r < length; r++) {
            for (int c = 1; c < length; c++)
                dist[r][c] = Math.min(dist[r][c], dist[r][c - 1] + 1);
            for (int c = length - 2; c >= 0; c--)
                dist[r][c] = Math.min(dist[r][c], dist[r][c + 1] + 1);
        }
        for (int c = 0; c < length; c++) {
            for (int r = 1; r < length; r++)
                dist[r][c] = Math.min(dist[r][c], dist[r - 1][c] + 1);
            for (int r = length - 2; r >= 0; r--)
                dist[r][c] = Math.min(dist[r][c], dist[r + 1][c] + 1);
        }
        while (!frontier.isEmpty()) {
            Point prt = frontier.poll();
            if (dist[prt.r][prt.c] <= minDist[prt.r][prt.c] / stepToSize)
                continue;
            for (int i = 0; i < 4; i++) {
                int newR = prt.r + DIR[i][0];
                int newC = prt.c + DIR[i][1];
                if (newR < 0 || newR >= length || newC < 0 || newC >= length)
                    continue;
                if (minDist[newR][newC] != -1)
                    continue;
                if (dist[newR][newC] <= minDist[prt.r][prt.c] / stepToSize) // we use old size here because we move
                                                                            // before growing
                    continue;
                minDist[newR][newC] = minDist[prt.r][prt.c] + 1;
                frontier.offer(new Point(newR, newC));
            }
        }

        // Once we reach a point, we can assume that all points within range of the
        // largest possible robot that can fit if centered at that point can be reached.
        // This is because having a smaller robot at a point is always advantageous to
        // having a larger one.
        int[][] expandSize = new int[length][length];
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++)
                if (minDist[i][j] != -1)
                    expandSize[i][j] = dist[i][j];

        for (int r = 0; r < length; r++) {
            for (int c = 1; c < length; c++)
                expandSize[r][c] = Math.max(expandSize[r][c], expandSize[r][c - 1] - 1);
            for (int c = length - 2; c >= 0; c--)
                expandSize[r][c] = Math.max(expandSize[r][c], expandSize[r][c + 1] - 1);
        }
        for (int c = 0; c < length; c++) {
            for (int r = 1; r < length; r++)
                expandSize[r][c] = Math.max(expandSize[r][c], expandSize[r - 1][c] - 1);
            for (int r = length - 2; r >= 0; r--)
                expandSize[r][c] = Math.max(expandSize[r][c], expandSize[r + 1][c] - 1);
        }
        int size = 0;
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++)
                if (expandSize[i][j] > 0)
                    size++;
        System.out.println(size);
    }
}
