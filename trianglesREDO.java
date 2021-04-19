import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class trianglesREDO {
    static class Point implements Comparable<Point> {
        int x, y, xRank, yRank;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(trianglesREDO.Point o) {
            return x == o.x ? y - o.y : x - o.x;
        }
    }

    static long MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
        int numNode = Integer.parseInt(f.readLine());
        Point[] allPoints = new Point[numNode];
        for (int i = 0; i < numNode; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            allPoints[i] = new Point(x, y);
        }
        Arrays.sort(allPoints, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return p1.y == p2.y ? p1.x - p2.x : p1.y - p2.y;
            }
        });
        int yRank = 0;
        for (int i = 1; i < numNode; i++) {
            if (allPoints[i].y > allPoints[i - 1].y) {
                yRank++;
            }
            allPoints[i].yRank = yRank;
        }
        yRank++;
        Arrays.sort(allPoints);
        int xRank = 0;
        for (int i = 1; i < numNode; i++) {
            if (allPoints[i].x > allPoints[i - 1].x) {
                xRank++;
            }
            allPoints[i].xRank = xRank;
        }
        xRank++;

        ArrayList<Point>[] vertical = new ArrayList[xRank];
        for (int i = 0; i < xRank; i++)
            vertical[i] = new ArrayList<>();

        ArrayList<Point>[] horizontal = new ArrayList[yRank];
        for (int i = 0; i < yRank; i++)
            horizontal[i] = new ArrayList<>();

        long[] verticalSum = new long[xRank];
        long[] horizontalSum = new long[yRank];
        for (int i = 0; i < numNode; i++) {
            vertical[allPoints[i].xRank].add(allPoints[i]);
            horizontal[allPoints[i].yRank].add(allPoints[i]);
        }

        for (int xr = 0; xr < xRank; xr++) {
            Point base = vertical[xr].get(0);
            long sum = 0;
            for (int i = 1; i < vertical[xr].size(); i++) {
                sum += (vertical[xr].get(i).y - base.y);
                sum %= MOD;
            }
            verticalSum[xr] = sum;
        }

        for (int yr = 0; yr < yRank; yr++) {
            Point base = horizontal[yr].get(0);
            long sum = 0;
            for (int i = 1; i < horizontal[yr].size(); i++) {
                sum += (horizontal[yr].get(i).x - base.x);
                sum %= MOD;
            }
            horizontalSum[yr] = sum;
            // System.out.println(sum);
        }
        long area = 0;
        for (int i = 0; i < numNode; i++) {
            Point thisP = allPoints[i];
            int bottomY = Collections.binarySearch(vertical[thisP.xRank], thisP);
            int topY = vertical[thisP.xRank].size() - bottomY;
            int Ydisplacement;
            if (bottomY > 0)
                Ydisplacement = thisP.y - vertical[thisP.xRank].get(bottomY - 1).y;
            else
                Ydisplacement = 0;
            long localYSum = ((bottomY - topY) * (long) Ydisplacement) + verticalSum[thisP.xRank];
            localYSum %= MOD;
            if (localYSum < 0)
                localYSum += MOD;
            verticalSum[thisP.xRank] = localYSum;

            int leftX = Collections.binarySearch(horizontal[thisP.yRank], thisP);
            int rightX = horizontal[thisP.yRank].size() - leftX;
            int Xdisplacement;
            if (leftX > 0)
                Xdisplacement = thisP.x - horizontal[thisP.yRank].get(leftX - 1).x;
            else
                Xdisplacement = 0;
            long localXSum = ((leftX - rightX) * (long) Xdisplacement) + horizontalSum[thisP.yRank];
            localXSum %= MOD;
            if (localXSum < 0)
                localXSum += MOD;
            horizontalSum[thisP.yRank] = localXSum;

            area += (localYSum * localXSum) % MOD;
            area %= MOD;
        }
        out.println(area % MOD);
        out.close();
    }
}
