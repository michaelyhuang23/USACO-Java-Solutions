import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class time {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("time.in")); // new FileReader("tilechng.in") //new
                                                                          // InputStreamReader(System.in)
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("time.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numNode = Integer.parseInt(st.nextToken());
        int numEdge = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());
        int[] money = new int[numNode + 1];
        ArrayList<Integer>[] parents = new ArrayList[numNode + 1];
        for (int i = 1; i <= numNode; i++)
            parents[i] = new ArrayList<>();
        st = new StringTokenizer(f.readLine());
        for (int i = 1; i <= numNode; i++)
            money[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < numEdge; i++) {
            st = new StringTokenizer(f.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            parents[to].add(from);
        }
        int maxMax = 0;
        int[][] dp = new int[2][numNode + 1];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);
        dp[0][1] = 0;
        for (int time = 1; time < 1001; time++) {
            Arrays.fill(dp[time % 2], -1);
            for (int node = 1; node <= numNode; node++) {
                for (int father : parents[node]) {
                    if (dp[(time - 1) % 2][father] >= 0)
                        dp[time % 2][node] = Math.max(dp[time % 2][node], dp[(time - 1) % 2][father] + money[node]);
                }
            }
            int maxMoney = dp[time % 2][1] - cost * time * time;
            maxMax = Math.max(maxMax, maxMoney);
        }
        out.println(maxMax);
        out.close();
    }
}
