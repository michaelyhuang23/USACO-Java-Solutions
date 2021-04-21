import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class milkvisits_LCA_Doubly {
    static boolean[] isH;
    static ArrayList<Integer>[] connectors;
    static int numNode, numQuery;
    static int[][] doubly;
    static boolean[][] hasG, hasH;
    static int[] depth;
    static int[] log;

    public static void main(String[] args) throws IOException {
        log = new int[1000000];
        log[0] = -1; // all the log values are rounded down except the first; also log means log2
                     // here
        for (int i = 1; i < 1000000; i++)
            log[i] = log[i >> 1] + 1;

        BufferedReader f = new BufferedReader(new FileReader("milkvisits.in")); // new
        // FileReader("mooyomooyo.in")
        // //new
        // InputStreamReader(System.in)
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        numNode = Integer.parseInt(st.nextToken());
        numQuery = Integer.parseInt(st.nextToken());
        connectors = new ArrayList[numNode];
        String farms = f.readLine();
        isH = new boolean[numNode];
        for (int i = 0; i < numNode; i++) {
            isH[i] = farms.charAt(i) == 'H' ? true : false;
            connectors[i] = new ArrayList<>();
        }
        for (int i = 0; i < numNode - 1; i++) {
            st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            connectors[first].add(second);
            connectors[second].add(first);
        }
        doubly = new int[numNode][20];
        hasG = new boolean[numNode][20];
        hasH = new boolean[numNode][20];
        depth = new int[numNode];
        buildGraph(0, 0);

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < numQuery; i++) {
            st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            boolean satisfied = doublySearch(first, second, st.nextToken().charAt(0));
            str.append(satisfied ? 1 : 0);
        }
        out.println(str);
        f.close();
        out.close();
    }

    private static void buildGraph(int father, int son) {
        doubly[son][0] = father; // 0: 2^0
        hasG[son][0] = !isH[son] || !isH[father];
        hasH[son][0] = isH[son] || isH[father];
        depth[son] = depth[father] + 1;
        for (int i = 1; (1 << i) < depth[son]; i++) {
            doubly[son][i] = doubly[doubly[son][i - 1]][i - 1];
            hasG[son][i] = hasG[son][i - 1] || hasG[doubly[son][i - 1]][i - 1];
            hasH[son][i] = hasH[son][i - 1] || hasH[doubly[son][i - 1]][i - 1];
        }
        for (int grandSon : connectors[son])
            if (grandSon != father)
                buildGraph(son, grandSon);
    }

    private static boolean doublySearch(int A, int B, char cow) {
        if (depth[A] < depth[B]) {
            int temp = A;
            A = B;
            B = temp;
        }
        boolean hasHTotal = isH[A] || isH[B], hasGTotal = !isH[A] || !isH[B];
        while (depth[A] > depth[B]) {
            hasHTotal |= hasH[A][log[depth[A] - depth[B]]];
            hasGTotal |= hasG[A][log[depth[A] - depth[B]]];
            A = doubly[A][log[depth[A] - depth[B]]];
        }

        if (A == B)
            return ((cow == 'H') ? hasHTotal : hasGTotal);

        for (int i = log[depth[A]]; i >= 0; i--) {
            if (doubly[A][i] != doubly[B][i]) {
                hasHTotal |= hasH[A][i];
                hasHTotal |= hasH[B][i];
                hasGTotal |= hasG[A][i];
                hasGTotal |= hasG[B][i];
                A = doubly[A][i];
                B = doubly[B][i];
            }
        }
        hasHTotal |= hasH[A][0];
        hasHTotal |= hasH[B][0];
        hasGTotal |= hasG[A][0];
        hasGTotal |= hasG[B][0];
        A = doubly[A][0];
        B = doubly[B][0];

        return ((cow == 'H') ? hasHTotal : hasGTotal);
    }
}
