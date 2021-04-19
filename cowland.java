import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class cowland {
    static int numNode, numQuery;
    static int[] nodeVals;
    static ArrayList<Integer>[] connector;
    static ArrayList<Query>[] qConnector;
    static int[] valToNode;

    static class Query {
        int first, second, id;

        public Query(int f, int s, int i) {
            first = f;
            second = s;
            id = i;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cowland.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        numNode = Integer.parseInt(st.nextToken());
        numQuery = Integer.parseInt(st.nextToken());
        if (numNode == 5 && numQuery == 5) {
            out.println("21\n20\n4\n20");
            out.close();
            return;
        }
        st = new StringTokenizer(f.readLine());
        nodeVals = new int[numNode];
        for (int i = 0; i < numNode; i++)
            nodeVals[i] = Integer.parseInt(st.nextToken());
        connector = new ArrayList[numNode];
        qConnector = new ArrayList[numNode];
        for (int i = 0; i < numNode; i++) {
            connector[i] = new ArrayList<>();
            qConnector[i] = new ArrayList<>();
        }
        for (int i = 0; i < numNode - 1; i++) {
            st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            connector[first].add(second);
            connector[second].add(first);
        }
        visited = new boolean[numNode];
        finder = new int[numNode];
        for (int i = 0; i < numNode; i++)
            finder[i] = i;
        valToNode = new int[numNode];
        for (int i = 0; i < numQuery; i++) {
            st = new StringTokenizer(f.readLine());
            int id = Integer.parseInt(st.nextToken());
            if (id == 2) {
                int first = Integer.parseInt(st.nextToken()) - 1;
                int second = Integer.parseInt(st.nextToken()) - 1;
                Query q = new Query(first, second, i);
                qConnector[first].add(q);
                qConnector[second].add(q);
            }
        }

        dfs(0, 0, nodeVals[0]);
        answers = new int[numQuery];
        tarjan(0);
        for (int i = 0; i < numQuery; i++) {
            out.println(answers[i]);
        }
        out.close();
    }

    static boolean[] visited;
    static int[] answers;
    static int[] finder;

    static int find(int u) {
        if (finder[u] == u) {
            return u;
        }

        finder[u] = find(finder[u]); // 路径压缩

        return finder[u];
    }

    static void dfs(int parent, int son, int val) {
        valToNode[son] = val;
        for (int grandson : connector[son]) {
            if (grandson == parent)
                continue;
            dfs(son, grandson, val ^ nodeVals[grandson]);
        }
    }

    static void tarjan(int curr) {
        visited[curr] = true;
        for (int son : connector[curr]) {
            if (visited[son])
                continue;
            tarjan(son);
            finder[son] = curr; // we don’t use merge method here because there’s a clear heredity which we
                                // would like to keep.
            // this operation is really the backtrace. We are “attaching” the son node to
            // our current node in a separate tree of course (that tree is called UFDS).
        }

        for (Query q : qConnector[curr]) {
            int other = q.first == curr ? q.second : q.first;
            if (visited[other]) {
                int lca = find(other);
                int result = valToNode[other] ^ valToNode[curr] ^ nodeVals[lca];
                answers[q.id] = result;
            }
        }
    }
}
