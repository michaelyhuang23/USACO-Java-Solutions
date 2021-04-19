import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class rectpast {
    static class Point implements Comparable<Point> {
        long x, y;
        int yRank;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(rectpast.Point o) {
            return Long.compare(y, o.y); // the y coordinates are distincts;
        }
    }

    static int[] bit;

    private static int lowbit(int x) {
        return x & (-x);
    }

    static int numCow;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        numCow = Integer.parseInt(f.readLine());
        Point[] allPoints = new Point[numCow];
        for (int i = 0; i < numCow; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            allPoints[i] = new Point(x, y);
        }
        Arrays.sort(allPoints);
        for (int i = 0; i < allPoints.length; i++) {
            allPoints[i].yRank = i + 1;
        }
        Arrays.sort(allPoints, new Comparator<Point>() {
            @Override
            public int compare(rectpast.Point o1, rectpast.Point o2) {
                return Long.compare(o1.x, o2.x);
            }
        });
        long total = 0;
        for (int start = 0; start < numCow; start++) { // inclusive
            Point startPrt = allPoints[start];
            bit = new int[numCow + 1];
            // using a balanced bst should work as well
            int activeCount = 0;
            for (int end = start; end < numCow; end++) { // inclusive
                Point endPrt = allPoints[end];
                long top = 1, bottom = 1;
                if (startPrt.compareTo(endPrt) < 0) {
                    bottom += getPrefix(startPrt.yRank);
                    top += activeCount - getPrefix(endPrt.yRank);
                } else if (startPrt.compareTo(endPrt) > 0) {
                    bottom += getPrefix(endPrt.yRank);
                    top += activeCount - getPrefix(startPrt.yRank);
                    // for each choice of bottom we can choose a top
                }
                if (end != start) {
                    add(endPrt.yRank, 1);
                    activeCount++;
                }
                total += top * bottom;
            }
        }
        System.out.println(total + 1); // add one for the empty set
    }

    private static int getPrefix(int index) {
        int sum = 0;
        for (int i = index; i > 0; i -= lowbit(i))
            sum += bit[i];
        return sum;
    }

    private static void add(int index, int value) {
        for (int i = index; i <= numCow; i += lowbit(i))
            bit[i] += value;
    }
}
