import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class P3610 {
    static class Point implements Cloneable {
        int step;
        int r1, c1, r2, c2, dir1, dir2;

        // r1+c1*20+r2*400+c2*8000+160000*dir1+640000*dir2

        public Point(int r1, int c1, int dir1, int r2, int c2, int dir2, int step) {
            this.r1 = r1;
            this.r2 = r2;
            this.c1 = c1;
            this.c2 = c2;
            this.dir1 = dir1;
            this.dir2 = dir2;
            this.step = step;
        }

        public Point stepForward() {
            int nc1 = (c1 + DIR[dir1][0]);
            int nr1 = (r1 + DIR[dir1][1]);
            int nc2 = (c2 + DIR[dir2][0]);
            int nr2 = (r2 + DIR[dir2][1]);
            if (nr1 < 0 || nr1 >= N || nc1 < 0 || nc1 >= N || !map[nr1][nc1] || (r1 == 0 && c1 == N - 1)) {
                nr1 = r1;
                nc1 = c1;
            }

            if (nr2 < 0 || nr2 >= N || nc2 < 0 || nc2 >= N || !map[nr2][nc2] || (r2 == 0 && c2 == N - 1)) {
                nr2 = r2;
                nc2 = c2;
            }
            return new Point(nr1, nc1, dir1, nr2, nc2, dir2, step + 1);
        }

        public Point turnLeft() {
            return new Point(r1, c1, (dir1 + 1) & 3, r2, c2, (dir2 + 1) & 3, step + 1);
        }

        public Point turnRight() {
            return new Point(r1, c1, (dir1 + 3) & 3, r2, c2, (dir2 + 3) & 3, step + 1);
        }
    }

    static int[][] DIR = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static int N;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cownav.in")); // new FileReader("cownav.in") //new
        // InputStreamReader(System.in)
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));
        N = Integer.parseInt(f.readLine());
        map = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            String input = f.readLine();
            for (int c = 0; c < N; c++)
                map[r][c] = input.charAt(c) == 'E' ? true : false;
        }
        Point start = new Point(N - 1, 0, 1, N - 1, 0, 2, 0);
        out.println(bfs(start));
        // System.out.println(counter);
        out.close();
    }

    // static int counter = 0;

    private static int bfs(Point start) {
        Queue<Point> frontier = new ArrayDeque<>(2560001);
        frontier.offer(start);
        boolean[][][][][][] visited = new boolean[N][N][4][N][N][4];
        visited[start.r1][start.c1][start.dir1][start.r2][start.c2][start.dir2] = true;
        while (!frontier.isEmpty()) {
            Point thisP = frontier.poll();

            // counter++;
            if (thisP.r1 == 0 && thisP.c1 == N - 1 && thisP.r2 == 0 && thisP.c2 == N - 1)
                return thisP.step;
            // forward
            Point newP = thisP.stepForward();
            if (!visited[newP.r1][newP.c1][newP.dir1][newP.r2][newP.c2][newP.dir2]) {
                visited[newP.r1][newP.c1][newP.dir1][newP.r2][newP.c2][newP.dir2] = true;
                frontier.offer(newP);
            }
            // turn left
            newP = thisP.turnLeft();
            if (!visited[newP.r1][newP.c1][newP.dir1][newP.r2][newP.c2][newP.dir2]) {
                visited[newP.r1][newP.c1][newP.dir1][newP.r2][newP.c2][newP.dir2] = true;
                frontier.offer(newP);
            }
            // turn right
            newP = thisP.turnRight();
            if (!visited[newP.r1][newP.c1][newP.dir1][newP.r2][newP.c2][newP.dir2]) {
                visited[newP.r1][newP.c1][newP.dir1][newP.r2][newP.c2][newP.dir2] = true;
                frontier.offer(newP);
            }
        }
        return -1;
    }
}
