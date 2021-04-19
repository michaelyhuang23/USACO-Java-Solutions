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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class lasers {
    static class Point {
        int x, y, xRank, yRank;
        boolean isHori;

        public Point(Point prt, boolean isHori) {
            x = prt.x;
            y = prt.y;
            xRank = prt.xRank;
            yRank = prt.yRank;
            this.isHori = isHori;
        }

        public Point(int x, int y, boolean isHori) {
            this.x = x;
            this.y = y;
            this.isHori = isHori;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (isHori ? 1231 : 1237);
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Point other = (Point) obj;
            if (isHori != other.isHori)
                return false;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }

    }

    public static void main(String[] args) throws IOException {
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader f = new BufferedReader(new FileReader("lasers.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lasers.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numPos = Integer.parseInt(st.nextToken());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        Point origin = new Point(x1, y1, false);
        Point target = new Point(x2, y2, false);
        Point[] allPoints = new Point[numPos + 2];
        allPoints[0] = origin;
        allPoints[1] = target;
        for (int i = 2; i < numPos + 2; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Point prt = new Point(x, y, false);
            allPoints[i] = prt;
        }
        Arrays.sort(allPoints, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return p1.x == p2.x ? p1.y - p2.y : p1.x - p2.x;
            }
        });
        int xRank = 0;
        for (int i = 1; i < numPos + 2; i++) {
            if (allPoints[i].x > allPoints[i - 1].x)
                xRank++;
            allPoints[i].xRank = xRank;
        }
        xRank++;
        ArrayList<Point>[] xMap = new ArrayList[xRank];
        Arrays.sort(allPoints, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return p1.y == p2.y ? p1.x - p2.x : p1.y - p2.y;
            }
        });
        int yRank = 0;
        for (int i = 1; i < numPos + 2; i++) {
            if (allPoints[i].y > allPoints[i - 1].y)
                yRank++;
            allPoints[i].yRank = yRank;
        }
        yRank++;
        ArrayList<Point>[] yMap = new ArrayList[yRank];

        for (int i = 0; i < xRank; i++)
            xMap[i] = new ArrayList<>();
        for (int i = 0; i < yRank; i++)
            yMap[i] = new ArrayList<>();

        for (int i = 0; i < numPos + 2; i++) {
            xMap[allPoints[i].xRank].add(allPoints[i]);
            yMap[allPoints[i].yRank].add(allPoints[i]);
        }

        Queue<Point> frontier = new ArrayDeque<>();
        Point hori = new Point(origin, true);
        Point verti = new Point(origin, false);
        frontier.offer(hori);
        frontier.offer(verti);
        HashMap<Point, Integer> tracker = new HashMap<>();
        tracker.put(hori, 0);
        tracker.put(verti, 0);
        while (!frontier.isEmpty()) {
            Point prt = frontier.poll();
            if (prt.x == x2 || prt.y == y2) {
                out.println(tracker.get(prt));
                out.close();
                return;
            }
            // System.out.println(prt.xRank + " " + prt.yRank);
            if (prt.isHori) {
                for (Point newPrt : yMap[prt.yRank]) {
                    newPrt = new Point(newPrt, false);
                    if (newPrt.x == prt.x && newPrt.y == prt.y || tracker.containsKey(newPrt))
                        continue;
                    tracker.put(newPrt, tracker.get(prt) + 1);
                    frontier.offer(newPrt);
                }
            } else {
                for (Point newPrt : xMap[prt.xRank]) {
                    newPrt = new Point(newPrt, true);
                    if (newPrt.x == prt.x && newPrt.y == prt.y || tracker.containsKey(newPrt))
                        continue;
                    tracker.put(newPrt, tracker.get(prt) + 1);
                    frontier.offer(newPrt);
                }

            }
        }
        out.println(-1);
        out.close();
    }
}
