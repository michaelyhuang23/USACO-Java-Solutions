import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class telephoneREDO {
    static class Node {
        int cate, nodePos;

        public Node(int cat, int pos) {
            cate = cat;
            nodePos = pos;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numNode = Integer.parseInt(st.nextToken());
        int numCate = Integer.parseInt(st.nextToken());
        int[] cates = new int[numNode];
        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < cates.length; i++) {
            cates[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        boolean[][] cateConnect = new boolean[numCate][numCate];
        for (int i = 0; i < numCate; i++) {
            String input = f.readLine();
            for (int j = 0; j < numCate; j++) {
                cateConnect[i][j] = input.charAt(j) == '1' ? true : false;
            }
        }
        ArrayDeque<Node> frontier = new ArrayDeque<>();
        frontier.offer(new Node(cates[0], 0));
        long[][] dist = new long[numCate][numNode];// fill with -1
        for (int i = 0; i < numCate; i++) {
            Arrays.fill(dist[i], -1);
        }
        dist[cates[0]][0] = 0;
        while (!frontier.isEmpty()) {
            Node cur = frontier.poll();
            if (cur.nodePos < numNode - 1 && dist[cur.cate][cur.nodePos + 1] == -1) {
                frontier.offer(new Node(cur.cate, cur.nodePos + 1));
                dist[cur.cate][cur.nodePos + 1] = dist[cur.cate][cur.nodePos] + 1;
            }
            if (cur.nodePos > 0 && dist[cur.cate][cur.nodePos - 1] == -1) {
                frontier.offer(new Node(cur.cate, cur.nodePos - 1));
                dist[cur.cate][cur.nodePos - 1] = dist[cur.cate][cur.nodePos] + 1;
            }
            if (cateConnect[cur.cate][cates[cur.nodePos]] && dist[cates[cur.nodePos]][cur.nodePos] == -1) {
                frontier.push(new Node(cates[cur.nodePos], cur.nodePos));
                dist[cates[cur.nodePos]][cur.nodePos] = dist[cur.cate][cur.nodePos];
            }
        }
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < numCate; i++) {
            if (cateConnect[i][cates[numNode - 1]] && dist[i][numNode - 1] != -1) {
                min = Math.min(min, dist[i][numNode - 1]);
            }
        }
        if (min > Integer.MAX_VALUE / 2)
            System.out.println(-1);
        else
            System.out.println(min);
    }
}
