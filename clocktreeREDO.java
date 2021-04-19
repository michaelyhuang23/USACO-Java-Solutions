import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class clocktreeREDO {
    static int numNode;
    static int[] initial, current;
    static ArrayList<Integer>[] connector;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("clocktree.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")));
        numNode = Integer.parseInt(f.readLine());
        initial = new int[numNode];
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i = 0; i < numNode; i++) {
            int x = Integer.parseInt(st.nextToken());
            initial[i] = 12 - x;
        }
        connector = new ArrayList[numNode];
        for (int i = 0; i < numNode; i++)
            connector[i] = new ArrayList<>();

        for (int i = 0; i < numNode - 1; i++) {
            st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            connector[first].add(second);
            connector[second].add(first);
        }
        int count = 0;
        for (int start = 0; start < numNode; start++) {
            current = initial.clone();
            dfs(start, start);
            current[start] += 12;
            current[start] %= 12;
            if (current[start] == 11 || current[start] == 0)
                count++;
        }
        out.println(count);
        out.close();
    }

    private static void dfs(int father, int son) {
        if (son != father)
            current[son]--;
        for (int next : connector[son]) {
            if (next == father)
                continue;
            dfs(son, next);
            current[son] += 12;
            current[son] %= 12;
        }
        if (son != father)
            current[father] -= current[son] + 1;
    }
}
