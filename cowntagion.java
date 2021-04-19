import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class cowntagion {
    static int numNode;
    static ArrayList<Integer>[] connector;
    static int[] log;

    public static void main(String[] args) throws IOException {
        log = new int[1000000];
        log[0] = -1; // all the log values are rounded down except the first; also log means log2
                     // here
        for (int i = 1; i < 1000000; i++)
            log[i] = log[i >> 1] + 1;
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        numNode = Integer.parseInt(f.readLine());
        connector = new ArrayList[numNode];
        for (int i = 0; i < connector.length; i++) {
            connector[i] = new ArrayList<>();
        }
        for (int i = 0; i < numNode - 1; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            connector[first].add(second);
            connector[second].add(first);
        }
        System.out.println(dfs(0, 0));
    }

    private static int dfs(int son, int father) {
        int childCounter = 0;
        int sum = 0;
        for (int grandson : connector[son]) {
            if (grandson == father)
                continue;
            childCounter++;
            sum += dfs(grandson, son);
        }
        int logger = log[childCounter] + 1; // our log rounds down so we add 1
        return childCounter + logger + sum;
    }
}
