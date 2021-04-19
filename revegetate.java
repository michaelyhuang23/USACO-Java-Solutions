import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class revegetate {
    static int numNode;
    static ArrayList<Integer>[] sameConnect, diffConnect;
    static boolean[] visited;
    static int[] color;
    static int choiceCount;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("revegetate.in")); // new FileReader("reststops.in") //new
                                                                                // InputStreamReader(System.in)
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        numNode = Integer.parseInt(st.nextToken());
        int numConnect = Integer.parseInt(st.nextToken());
        sameConnect = new ArrayList[numNode];
        diffConnect = new ArrayList[numNode];
        for (int i = 0; i < numNode; i++) {
            sameConnect[i] = new ArrayList<>();
            diffConnect[i] = new ArrayList<>();
        }
        visited = new boolean[numNode];
        for (int i = 0; i < numConnect; i++) {
            st = new StringTokenizer(f.readLine());
            char type = st.nextToken().charAt(0);
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            if (type == 'S') {
                sameConnect[first].add(second);
                sameConnect[second].add(first);
            } else {
                diffConnect[first].add(second);
                diffConnect[second].add(first);
            }
        }
        color = new int[numNode];

        for (int i = 0; i < numNode; i++) {
            if (visited[i])
                continue;
            color[i] = 1;
            if (dfs(i))
                choiceCount++;
            else {
                out.println(0);
                out.close();
                System.exit(0);
            }
        }
        StringBuilder str = new StringBuilder();
        str.append(1);
        for (int i = 0; i < choiceCount; i++)
            str.append(0);
        out.println(str);
        out.close();

    }

    private static boolean dfs(int curr) {
        visited[curr] = true;
        for (int next : sameConnect[curr]) {
            if (color[next] > 0 && color[next] != color[curr])
                return false;
            color[next] = color[curr];
            if (!visited[next] && !dfs(next))
                return false;
        }
        for (int next : diffConnect[curr]) {
            if (color[next] > 0 && color[next] == color[curr])
                return false;
            color[next] = 3 - color[curr];
            if (!visited[next] && !dfs(next))
                return false;
        }
        return true;

    }
}
