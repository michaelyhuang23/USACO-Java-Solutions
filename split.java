import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class split {
    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(split.Point o) {
            return x == o.x ? Long.compare(y, o.y) : Long.compare(x, o.x);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("split.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
        int numNode = Integer.parseInt(f.readLine());
        Point[] allPoints = new Point[numNode];
        long minX = Integer.MAX_VALUE, maxX = 0, minY = Integer.MAX_VALUE, maxY = 0;
        for (int i = 0; i < numNode; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            allPoints[i] = new Point(x, y);
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }
        long origArea = (maxX - minX) * (maxY - minY);
        Arrays.sort(allPoints);
        // horizontal traversal
        TreeSet<Point> yTrackerFront = new TreeSet<>(new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return p1.y == p2.y ? Long.compare(p1.x, p2.x) : Long.compare(p1.y, p2.y);
            }
        });
        TreeSet<Point> yTrackerBack = new TreeSet<>(new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return p1.y == p2.y ? Long.compare(p1.x, p2.x) : Long.compare(p1.y, p2.y);
            }
        });
        for (int i = 0; i < numNode; i++) {
            yTrackerBack.add(allPoints[i]);
        }
        long minArea = Long.MAX_VALUE / 2;
        for (int i = 0; i < numNode - 1; i++) {
            yTrackerFront.add(allPoints[i]);
            yTrackerBack.remove(allPoints[i]);
            if (allPoints[i].x != allPoints[i + 1].x) {
                long deltaYFront = yTrackerFront.last().y - yTrackerFront.first().y;
                long deltaXFront = allPoints[i].x - allPoints[0].x;
                long thisArea = deltaXFront * deltaYFront;
                long deltaYBack = yTrackerBack.last().y - yTrackerBack.first().y;
                long deltaXBack = allPoints[numNode - 1].x - allPoints[i + 1].x;
                thisArea += deltaXBack * deltaYBack;
                minArea = Math.min(minArea, thisArea);
            }
        }

        Arrays.sort(allPoints, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return p1.y == p2.y ? Long.compare(p1.x, p2.x) : Long.compare(p1.y, p2.y);
            }
        });
        // horizontal traversal
        TreeSet<Point> xTrackerFront = new TreeSet<>();
        TreeSet<Point> xTrackerBack = new TreeSet<>();
        for (int i = 0; i < numNode; i++) {
            xTrackerBack.add(allPoints[i]);
        }
        for (int i = 0; i < numNode - 1; i++) {
            xTrackerFront.add(allPoints[i]);
            xTrackerBack.remove(allPoints[i]);
            if (allPoints[i].y != allPoints[i + 1].y) {
                long deltaXFront = xTrackerFront.last().x - xTrackerFront.first().x;
                long deltaYFront = allPoints[i].y - allPoints[0].y;
                long thisArea = deltaXFront * deltaYFront;
                long deltaXBack = xTrackerBack.last().x - xTrackerBack.first().x;
                long deltaYBack = allPoints[numNode - 1].y - allPoints[i + 1].y;
                thisArea += deltaXBack * deltaYBack;
                minArea = Math.min(minArea, thisArea);
            }
        }

        out.println(origArea - minArea);
        out.close();
    }
}
