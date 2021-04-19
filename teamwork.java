import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class teamwork {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("teamwork.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numCow = Integer.parseInt(st.nextToken());
        int maxSize = Integer.parseInt(st.nextToken());
        int[] allCows = new int[numCow + 1];
        for (int i = 1; i <= numCow; i++) {
            int lev = Integer.parseInt(f.readLine());
            allCows[i] = lev;
        }

        long[] dp = new long[numCow + 1];
        for (int onCow = 1; onCow <= numCow; onCow++) {
            int maxSkill = allCows[onCow];
            for (int prevGroup = onCow - 1; prevGroup >= Math.max(0, onCow - maxSize); prevGroup--) {
                dp[onCow] = Math.max(dp[onCow], dp[prevGroup] + maxSkill * (onCow - prevGroup));
                maxSkill = Math.max(maxSkill, allCows[prevGroup]);
            }
        }
        out.println(dp[numCow]);
        out.close();
    }
}
