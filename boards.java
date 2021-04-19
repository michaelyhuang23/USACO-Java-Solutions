import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.concurrent.PriorityBlockingQueue;

public class boards {
    static class SegmentTree {
        long[] tree;
        int[] segmentLeft;
        int[] segmentRight; // not inclusive
        long[] clatent;

        public SegmentTree(long[] arr) {
            tree = new long[arr.length * 4];
            segmentLeft = new int[arr.length * 4];
            segmentRight = new int[arr.length * 4];
            clatent = new long[arr.length * 4];
            createTree(arr, 0, arr.length, 1);
        }

        public long createTree(long[] arr, int left, int right, int index) {
            segmentLeft[index] = left;
            segmentRight[index] = right;
            if (left + 1 == right) {
                tree[index] = arr[left];
                return arr[left];
            }
            long min = Long.MAX_VALUE / 2; // modify this if you want to store something
            int mid = (left + right) >> 1;
            min = Math.min(min, createTree(arr, left, mid, index * 2));
            min = Math.min(min, createTree(arr, mid, right, index * 2 + 1));
            tree[index] = min;
            return min;
        }

        public long getRange(int index, int left, int right) {

            if (segmentLeft[index] >= left && segmentRight[index] <= right)
                return tree[index];
            pushDown(index);
            int mid = (segmentLeft[index] + segmentRight[index]) >> 1;
            long min = Long.MAX_VALUE / 2;
            if (left < mid)
                min = Math.min(min, getRange(index * 2, left, right));
            if (right > mid)
                min = Math.min(min, getRange(index * 2 + 1, left, right));
            return min;
        }

        private void pushDown(int index) {
            clatent[index * 2] += clatent[index];
            clatent[index * 2 + 1] += clatent[index];
            tree[index * 2] += clatent[index];
            tree[index * 2 + 1] += clatent[index];
            clatent[index] = 0;
        }

        public void update(int index, int left, int right, long change) {
            if (segmentLeft[index] >= left && segmentRight[index] <= right) {
                clatent[index] += change;
                tree[index] += change;
                return;
            }
            pushDown(index);
            int mid = (segmentLeft[index] + segmentRight[index]) >> 1;
            if (left < mid)
                update(index * 2, left, right, change);
            if (right > mid)
                update(index * 2 + 1, left, right, change);
            tree[index] = Math.min(tree[index * 2], tree[index * 2 + 1]);
        }
    }

    static class Point implements Comparable<Point> {
        long x, y;
        int xRank;
        int yRank;
        Point other;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
            other = null;
        }

        @Override
        public int compareTo(Point o) {
            return (x == o.x) ? Long.compare(y, o.y) : Long.compare(x, o.x);
        }

        public long getDist(Point o) {
            return Math.abs(x - o.x) + Math.abs(y - o.y);
        }
    }

    static Point[] yPoints;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("boards.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("boards.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int length = Integer.parseInt(st.nextToken());
        int numJump = Integer.parseInt(st.nextToken());
        Point[] allPoints = new Point[numJump * 2 + 2];
        for (int i = 1; i <= numJump; i++) {
            st = new StringTokenizer(f.readLine());
            long x1 = Long.parseLong(st.nextToken());
            long y1 = Long.parseLong(st.nextToken());
            long x2 = Long.parseLong(st.nextToken());
            long y2 = Long.parseLong(st.nextToken());
            Point p1 = new Point(x1, y1);
            Point p2 = new Point(x2, y2);
            allPoints[i * 2 - 1] = p1;
            allPoints[i * 2] = p2;
            p1.other = p2;
        }

        allPoints[0] = new Point(0, 0);
        allPoints[numJump * 2 + 1] = new Point(length, length);
        allPoints[numJump * 2 + 1].other = new Point(length, length);

        Arrays.sort(allPoints, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return p1.y == p2.y ? Long.compare(p1.x, p2.x) : Long.compare(p1.y, p2.y);
            }
        });
        for (int i = 0; i < numJump * 2 + 2; i++)
            allPoints[i].yRank = i;
        yPoints = allPoints.clone();
        Arrays.sort(allPoints);
        for (int i = 0; i < numJump * 2 + 2; i++)
            allPoints[i].xRank = i;

        long[] dist = new long[numJump * 2 + 2];
        Arrays.fill(dist, Long.MAX_VALUE / 2);
        long[] prevDist = dist.clone();
        dist[0] = length * 2;
        SegmentTree yTree = new SegmentTree(prevDist);

        for (int i = 0; i < numJump * 2 + 2; i++) {
            Point thisP = allPoints[i];
            if (thisP.other == null) {
                if (prevDist[thisP.xRank] > dist[thisP.xRank]) {
                    yTree.update(1, thisP.yRank, thisP.yRank + 1, dist[thisP.xRank] - prevDist[thisP.xRank]);
                    prevDist[thisP.xRank] = dist[thisP.xRank];
                }
            } else {
                long newDist = yTree.getRange(1, 0, thisP.yRank) - thisP.getDist(thisP.other);
                if (newDist < dist[thisP.xRank]) {
                    dist[thisP.xRank] = newDist;
                    dist[thisP.other.xRank] = newDist;
                }
            }
        }
        long minDist = Long.MAX_VALUE / 2;
        for (int i = 0; i < numJump * 2 + 2; i++) {
            minDist = Math.min(minDist, dist[i]);
        }
        out.println(minDist);
        out.close();
    }
}
