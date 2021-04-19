import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class dirtraverse {
    static long[] nodeLength;
    static ArrayList<Integer>[] connectors;
    static int[] leafCount;
    static long totalLength;
    static int totalLeaf;
    static long minLength = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("dirtraverse.in")); // new
                                                                                 // FileReader("div7.in")
                                                                                 // //new
        // InputStreamReader(System.in)
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dirtraverse.out")));
        int numNode = Integer.parseInt(f.readLine());
        connectors = new ArrayList[numNode];
        for (int i = 0; i < numNode; i++)
            connectors[i] = new ArrayList<>();
        nodeLength = new long[numNode];
        for (int i = 0; i < numNode; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            String name = st.nextToken();
            nodeLength[i] = name.length();
            int numChild = Integer.parseInt(st.nextToken());
            if (numChild == 0)
                totalLeaf++;
            for (int j = 0; j < numChild; j++) {
                int child = Integer.parseInt(st.nextToken()) - 1;
                connectors[i].add(child);
                connectors[child].add(i);
            }
        }
        leafCount = new int[numNode];
        buildGraph(0, 0);

        dfs(0, 0);

        out.println(minLength);
        out.close();
    }

    private static void dfs(int parent, int son) {
        minLength = Math.min(totalLength, minLength);

        for (int grandson : connectors[son]) {
            if (grandson == parent || connectors[son].size() <= 1)
                continue;
            long totalLengthCopy = totalLength;
            totalLength += (totalLeaf - leafCount[grandson]) * 3L
                    - leafCount[grandson] * (long) (nodeLength[grandson] + 1);
            dfs(son, grandson);
            totalLength = totalLengthCopy;
        }
    }

    private static void buildGraph(int parent, int son) {
        if (connectors[son].size() == 1) {
            totalLength += nodeLength[son];
            leafCount[son] = 1;
            return;
        }
        for (int grandson : connectors[son]) {
            if (grandson == parent)
                continue;
            buildGraph(son, grandson);
            leafCount[son] += leafCount[grandson];
        }
        if (parent != son)
            totalLength += (nodeLength[son] + 1) * leafCount[son];
    }
}
