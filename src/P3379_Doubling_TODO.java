import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P3379_Doubling_TODO {
    static int[][] fa;
    static int[] depth, Log;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        fa = new int[N + 1][20]; // fa[i][j] 从点i往上跳2^j所能到达的点， 倍增数组
        depth = new int[N + 1]; // depth[i] 点i所在的深度
        dfs(S, 0); // 建树，生成相关信息

        Log = new int[500001];
        for (int i = 2; i <= N; i++) {// 注意i从2开始
            Log[i] = Log[i / 2] + 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(lca(a, b));
        }
    }

    public static int lca(int a, int b) {
        if (depth[b] > depth[a]) {
            int temp = a;
            a = b;
            b = temp;
        }
        while (depth[a] > depth[b])
            a = fa[a][Log[depth[a] - depth[b]]];

        if (a == b)
            return a;

        for (int i = Log[depth[a]]; i >= 0; i--) {
            if (fa[a][i] != fa[b][i]) {
                a = fa[a][i];
                b = fa[b][i];
            }
        }

        return fa[a][0];
    }

    public static void dfs(int curr, int father) {
        depth[curr] = depth[father] + 1;
        fa[curr][0] = father;
        for (int i = 1; 1 << i < depth[curr]; i++)
            fa[curr][i] = fa[fa[curr][i - 1]][i - 1];

        for (int next : graph[curr])
            if (next != father)
                dfs(next, curr);
    }
}
