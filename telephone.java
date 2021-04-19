import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class telephone {
    static class Node implements Comparable<Node> {
        int pos;
        long dist;

        public Node(int p, long d) {
            pos = p;
            dist = d;
        }

        @Override
        public int compareTo(telephone.Node o) {
            return Long.compare(dist, o.dist);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numCow = Integer.parseInt(st.nextToken());
        int numCate = Integer.parseInt(st.nextToken());
        int[] cows = new int[numCow];
        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < numCow; i++) {
            cows[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        boolean[][] passable = new boolean[numCate][numCate];
        for (int i = 0; i < numCate; i++) {
            String input = f.readLine();
            for (int j = 0; j < numCate; j++) {
                passable[i][j] = input.charAt(j) == '1' ? true : false;
            }
        }

        int[][] closestF = new int[numCow][numCate];
        Arrays.fill(closestF[0], -1);
        closestF[0][cows[0]] = 0;
        for (int i = 0; i < numCow - 1; i++) {
            for (int cate = 0; cate < numCate; cate++) {
                closestF[i + 1][cate] = closestF[i][cate];
            }
            closestF[i + 1][cows[i + 1]] = i + 1;
        }

        int[][] closestB = new int[numCow][numCate];
        Arrays.fill(closestB[numCow - 1], -1);
        closestB[numCow - 1][cows[numCow - 1]] = numCow - 1;

        for (int i = numCow - 1; i > 0; i--) {
            for (int cate = 0; cate < numCate; cate++) {
                closestB[i - 1][cate] = closestB[i][cate];
            }
            closestB[i - 1][cows[i - 1]] = i - 1;
        }

        boolean[] visited = new boolean[numCow];
        long[] dist = new long[numCow];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(0, 0));
        while (!pq.isEmpty()) {
            int pos = pq.poll().pos;
            if (visited[pos])
                continue;
            visited[pos] = true;

            for (int cate = 0; cate < numCate; cate++) {
                if (!passable[cows[pos]][cate])
                    continue;
                int to = 0;
                if (closestF[pos][cate] > -1) {
                    to = closestF[pos][cate];
                    if (dist[to] > dist[pos] + Math.abs(pos - to)) {
                        dist[to] = dist[pos] + Math.abs(pos - to);
                        pq.offer(new Node(to, dist[to]));
                    }
                }

                if (closestB[pos][cate] > -1) {
                    to = closestB[pos][cate];
                    if (dist[to] > dist[pos] + Math.abs(pos - to)) {
                        dist[to] = dist[pos] + Math.abs(pos - to);
                        pq.offer(new Node(to, dist[to]));
                    }
                }
            }
        }
        if (dist[numCow - 1] > Integer.MAX_VALUE / 2)
            System.out.println(-1);
        else
            System.out.println(dist[numCow - 1]);
    }
}
