import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class mountainsREDO {
    static class Point implements Comparable<Point> {
        int x, y;
        int right, left;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            right = x + y;
            left = x - y;
        }

        @Override
        public int compareTo(mountainsREDO.Point o) {
            return (right == o.right) ? o.left - left : right - o.right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("mountains.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
        int numPoint = Integer.parseInt(f.readLine());
        Point[] allPoints = new Point[numPoint];
        for (int i = 0; i < numPoint; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Point thisP = new Point(x, y);
            allPoints[i] = thisP;
        }
        Arrays.sort(allPoints);
        TreeSet<Point> visibleMounts = new TreeSet<>(new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return (p1.left == p2.left) ? p2.right - p1.right : p1.left - p2.left;
            }
        });

        for (int i = 0; i < numPoint; i++) {
            Point thisP = allPoints[i];
            Point prt = visibleMounts.ceiling(thisP);
            if (prt != null)
                visibleMounts.tailSet(prt).clear();

            visibleMounts.add(thisP);
        }
        out.println(visibleMounts.size());
        out.close();
    }
}
