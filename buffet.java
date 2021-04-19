import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class buffet {
    static class Point implements Comparable<Point> {
        int id, minQuaRank, quality;
        long negaVal;

        public Point(int id, int quality) {
            this.id = id;
            this.quality = quality;
        }

        public Point(Point prt, long negaV, int minQua) {
            id = prt.id;
            quality = prt.quality;
            negaVal = negaV;
            minQuaRank = minQua;
        }

        @Override
        public int compareTo(buffet.Point o) {
            return Long.compare(negaVal, o.negaVal);
        }

    }

    static ArrayList<Integer>[] connector;
    static int cost, numNode;
    static Point[] allPoints;
    static long[][] dist;
    static ArrayList<Integer> qualities;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("buffet.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buffet.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        numNode = Integer.parseInt(st.nextToken());
        cost = Integer.parseInt(st.nextToken());
        allPoints = new Point[numNode];
        connector = new ArrayList[numNode];
        for (int i = 0; i < connector.length; i++)
            connector[i] = new ArrayList<>();
        for (int i = 0; i < numNode; i++) {
            st = new StringTokenizer(f.readLine());
            int quality = Integer.parseInt(st.nextToken());
            allPoints[i] = new Point(i, quality);
            int numNeighbor = Integer.parseInt(st.nextToken());
            for (int j = 0; j < numNeighbor; j++) {
                int neighbor = Integer.parseInt(st.nextToken()) - 1;
                connector[i].add(neighbor);
            }
        }
        Point[] sortedPoints = allPoints.clone();
        Arrays.sort(sortedPoints, new Comparator<Point>() {
            @Override
            public int compare(buffet.Point o1, buffet.Point o2) {
                return o1.quality - o2.quality;
            }
        });
        int quaRank = 0;
        qualities = new ArrayList<>();
        qualities.add(sortedPoints[0].quality);
        for (int i = 0; i < numNode; i++) {
            if (i > 0 && sortedPoints[i].quality > sortedPoints[i - 1].quality) {
                quaRank++;
                qualities.add(sortedPoints[i].quality);
            }
            sortedPoints[i].quality = quaRank;
        }
        quaRank++;

        dist = new long[numNode][quaRank];
        // for (int i = 0; i < numNode; i++)
        // Arrays.fill(dist[i], Long.MIN_VALUE / 2);
        for (int i = 0; i < numNode; i++) {
            PriorityQueue<Point> pq = new PriorityQueue<>();
            pq.offer(new Point(allPoints[i], -qualities.get(allPoints[i].quality), allPoints[i].quality));
            while (!pq.isEmpty()) {
                Point prt = pq.poll(); // 距离源点距离最短的点
                if (dist[prt.id][prt.minQuaRank] < prt.negaVal)
                    continue;
                dist[prt.id][prt.minQuaRank] = prt.negaVal;
                for (int nextID : connector[prt.id]) {
                    Point nextPrt = allPoints[nextID];
                    int minQuaRank = prt.minQuaRank;
                    long newDist = dist[prt.id][minQuaRank] + cost;
                    if (nextPrt.quality > minQuaRank) {
                        long newDist2 = newDist - qualities.get(nextPrt.quality);
                        if (dist[nextID][nextPrt.quality] > newDist2) {
                            dist[nextID][nextPrt.quality] = newDist2;
                            pq.offer(new Point(nextPrt, newDist2, nextPrt.quality));
                        }
                    }

                    if (dist[nextID][minQuaRank] > newDist) {
                        dist[nextID][minQuaRank] = newDist;
                        pq.offer(new Point(nextPrt, newDist, minQuaRank));
                    }
                }
            }
        }
        long minDist = 0;
        for (int i = 0; i < numNode; i++) {
            for (int j = 0; j < quaRank; j++) {
                minDist = Math.min(minDist, dist[i][j]);
            }
        }
        out.println(-minDist);
        out.close();
    }

}
