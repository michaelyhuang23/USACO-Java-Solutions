import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class squarepast {
    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return Long.compare(y, o.y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        int numNode = Integer.parseInt(f.readLine());
        Point[] allPoints = new Point[numNode];
        for (int i = 0; i < numNode; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            allPoints[i] = new Point(x, y);
        }
        Arrays.sort(allPoints, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Long.compare(o1.x, o2.x);
            }
        });
        int count = numNode + 1;
        for (int left = 0; left < numNode; left++) {
            TreeSet<Point> active = new TreeSet<>();
            active.add(allPoints[left]);
            for (int right = left + 1; right < numNode; right++) {
                active.add(allPoints[right]);
                long rightX = allPoints[right].x;
                long leftX = allPoints[left].x;
                int top = allPoints[left].y > allPoints[right].y ? left : right;
                int bottom = allPoints[left].y < allPoints[right].y ? left : right;
                Point[] ySorted = new Point[active.size()];
                int index = 0;
                int topI = 0, bottomI = 0;
                for (Point topPrt : active) {
                    ySorted[index] = topPrt;
                    if (topPrt.compareTo(allPoints[top]) == 0)
                        topI = index;
                    if (topPrt.compareTo(allPoints[bottom]) == 0)
                        bottomI = index;
                    index++;
                }
                long topCap = Integer.MAX_VALUE;
                long topY = ySorted[topI].y;
                if (topI < index - 1)
                    topCap = ySorted[topI + 1].y - 1;
                int lowPoint = 0, highPoint = 0; // highPoint records the point too high to fit
                // low point records the lowest point that can fit
                for (int i = bottomI; i >= 0; i--) {
                    long bottomCap = Integer.MIN_VALUE;
                    if (i > 0)
                        bottomCap = ySorted[i - 1].y + 1;
                    long bottomY = ySorted[i].y;
                    if (fit(topY, bottomY, topCap, bottomCap, leftX, rightX, left, right, allPoints)) {
                        highPoint = i;
                        break;
                    }
                }
                for (int i = topI; i < index; i++) {
                    topCap = Integer.MAX_VALUE;
                    topY = ySorted[i].y;
                    if (i < index - 1)
                        topCap = ySorted[i + 1].y - 1;
                    long bottomCap = Integer.MIN_VALUE;
                    if (lowPoint > 0)
                        bottomCap = ySorted[lowPoint - 1].y + 1;
                    long bottomY = ySorted[lowPoint].y;
                    while (lowPoint <= bottomI
                            && !fit(topY, bottomY, topCap, bottomCap, leftX, rightX, left, right, allPoints)) {
                        lowPoint++;
                        bottomCap = Integer.MIN_VALUE;
                        if (lowPoint > 0)
                            bottomCap = ySorted[lowPoint - 1].y + 1;
                        bottomY = ySorted[lowPoint].y;
                    }
                    if (lowPoint > bottomI) {
                        lowPoint = 0;
                        continue;
                    }
                    bottomCap = Integer.MIN_VALUE;
                    if (highPoint > 0)
                        bottomCap = ySorted[highPoint - 1].y + 1;
                    bottomY = ySorted[highPoint].y;
                    while (highPoint <= bottomI
                            && fit(topY, bottomY, topCap, bottomCap, leftX, rightX, left, right, allPoints)) {
                        highPoint++;
                        bottomCap = Integer.MIN_VALUE;
                        if (highPoint > 0)
                            bottomCap = ySorted[highPoint - 1].y + 1;
                        bottomY = ySorted[highPoint].y;
                    }

                    count += highPoint - lowPoint;

                }
            }
        }
        System.out.println(count);
    }

    private static boolean fit(long topY, long bottomY, long topCap, long bottomCap, long leftX, long rightX, int left,
            int right, squarepast.Point[] allPoints) {
        if (topY - bottomY > rightX - leftX) {
            long expan = expanded(left, right, bottomY, topY, allPoints);
            if (expan >= topY - bottomY)
                return true;
        } else {
            if (topCap - bottomCap >= rightX - leftX)
                return true;
        }
        return false;
    }

    private static long expanded(int left, int right, long lowY, long highY, Point[] allPoints) {
        long rightX = Integer.MAX_VALUE;
        for (int righter = right + 1; righter < allPoints.length; righter++)
            if (allPoints[righter].y <= highY && allPoints[righter].y >= lowY) {
                rightX = allPoints[righter].x;
                break;
            }
        long leftX = Integer.MIN_VALUE;
        for (int lefter = left - 1; lefter >= 0; lefter--)
            if (allPoints[lefter].y <= highY && allPoints[lefter].y >= lowY) {
                leftX = allPoints[lefter].x;
                break;
            }
        return rightX - leftX - 2;
    }
}
