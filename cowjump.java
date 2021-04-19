import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class cowjump {
    static class Point implements Comparable<Point> {
        int x, y;
        boolean isLeft;
        Segment seg;

        public Point(int xi, int yi) {
            x = xi;
            y = yi;
        }

        @Override
        public int compareTo(Point o) {
            return x == o.x ? y - o.y : x - o.x;
        }

        public static Point subtract(Point p1, Point p2) {
            return new Point(p1.x - p2.x, p1.y - p2.y);
        }

    }

    static class Segment implements Comparable<Segment> {
        Point left, right;
        Point vector;
        int id;

        public Segment(Point lf, Point rt, int id) {
            this.id = id;
            left = lf;
            right = rt;
            vector = Point.subtract(right, left);
        }

        @Override
        public int compareTo(Segment o) {
            Segment thisSeg = new Segment(left, right, 0);
            if (thisSeg.left.x > o.left.x) {
                Segment temp = o;
                o = thisSeg;
                thisSeg = temp;
            }
            long first = ((long) (thisSeg.right.y - thisSeg.left.y)) * ((o.left.x - thisSeg.left.x));
            long second = ((long) (thisSeg.right.x - thisSeg.left.x)) * ((o.left.y - thisSeg.left.y));
            return Long.compare(first, second);
        }

        private static int crossProd(Point a, Point b) {
            return a.x * b.y - a.y * b.x;
        }

        public boolean intersect(Segment o) {
            Segment thisS = new Segment(left, right, 0);
            int vectorCross = crossProd(thisS.vector, o.vector);
            if (vectorCross < 0) {
                Segment temp = o;
                o = thisS;
                thisS = temp;
            }
            vectorCross = crossProd(thisS.vector, o.vector);
            if (vectorCross <= 0)
                return false;
            int t = crossProd(Point.subtract(o.left, thisS.left), o.vector);
            int s = crossProd(Point.subtract(o.left, thisS.left), thisS.vector);
            return t >= 0 && t <= vectorCross && s >= 0 && s <= vectorCross;
        }
    }

    static int numSegment;
    static Point[] allPoints;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cowjump.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowjump.out")));
        numSegment = Integer.parseInt(f.readLine());
        allPoints = new Point[numSegment * 2];
        for (int i = 0; i < numSegment; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            Point p1 = new Point(x1, y1);
            Point p2 = new Point(x2, y2);
            Segment thisSeg;
            if (p1.compareTo(p2) > 0) {
                thisSeg = new Segment(p2, p1, i + 1);
                p2.isLeft = true;
                p1.isLeft = false;
            } else {
                thisSeg = new Segment(p1, p2, i + 1);
                p1.isLeft = true;
                p2.isLeft = false;
            }
            p1.seg = thisSeg;
            p2.seg = thisSeg;
            allPoints[i * 2] = p1;
            allPoints[i * 2 + 1] = p2;
        }
        Arrays.sort(allPoints);
        TreeSet<Segment> activeSeg = new TreeSet<>();
        for (int i = 0; i < numSegment * 2; i++) {
            Point p = allPoints[i];
            if (p.isLeft) {
                Segment bottom = activeSeg.floor(p.seg);
                Segment top = activeSeg.ceiling(p.seg);
                activeSeg.add(p.seg);
                if (bottom != null && bottom.intersect(p.seg)) {
                    out.println(chooseResult(bottom, p.seg));
                    break;
                }
                if (top != null && top.intersect(p.seg)) {
                    out.println(chooseResult(top, p.seg));
                    break;
                }
            } else {
                activeSeg.remove(p.seg);
                Segment bottom = activeSeg.floor(p.seg);
                Segment top = activeSeg.ceiling(p.seg);
                if (top != null && bottom != null && bottom.intersect(top)) {
                    out.println(chooseResult(bottom, top));
                    break;
                }
            }

        }
        out.close();
    }

    private static int chooseResult(cowjump.Segment seg1, cowjump.Segment seg2) {
        int counter = 0;
        for (int i = 0; i < numSegment * 2; i++) {
            Segment other = allPoints[i].seg;
            if (other.intersect(seg1))
                counter++;
        }
        if (counter > 2)
            return seg1.id;

        counter = 0;
        for (int i = 0; i < numSegment * 2; i++) {
            Segment other = allPoints[i].seg;
            if (other.intersect(seg2))
                counter++;
        }
        if (counter > 2)
            return seg2.id;
        return Math.min(seg1.id, seg2.id);
    }
}
