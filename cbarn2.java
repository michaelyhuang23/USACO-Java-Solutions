import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class cbarn2 {

    private static int mod(int num, int mod) {
        return (num % mod + mod) % mod;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cbarn2.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numRoom = Integer.parseInt(st.nextToken());
        int numEnter = Integer.parseInt(st.nextToken());
        int[] allRooms = new int[numRoom];
        for (int i = 0; i < numRoom; i++)
            allRooms[i] = Integer.parseInt(f.readLine());

        int minSum = Integer.MAX_VALUE / 2;
        for (int start = 0; start < numRoom; start++) { // going counterclockwise
            int[][] dp = new int[numRoom][numEnter];
            int nextStart = mod(start + 1, numRoom);
            for (int i = 0; i < numRoom; i++)
                Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
            dp[start][0] = 0;
            for (int onEnter = 1; onEnter < numEnter; onEnter++) {
                for (int gatePos = mod(start - 1, numRoom); gatePos != start; gatePos = mod(gatePos - 1, numRoom)) {
                    int prevGate = mod(gatePos + 1, numRoom);
                    int sum = 0;
                    for (int shift = 1; prevGate != nextStart; shift++) {
                        dp[gatePos][onEnter] = Math.min(dp[gatePos][onEnter], dp[prevGate][onEnter - 1] + sum);
                        sum += allRooms[prevGate] * shift;
                        prevGate = mod(gatePos + shift + 1, numRoom);
                    }
                }
            }
            int sum = 0;
            for (int i = 0; i < numRoom; i++) {
                sum += allRooms[mod(i + start, numRoom)] * i;
                dp[mod(i + start + 1, numRoom)][numEnter - 1] += sum;
                minSum = Math.min(minSum, dp[mod(i + start + 1, numRoom)][numEnter - 1]);
            }
        }
        // numEnter<numRoom
        if (numEnter >= numRoom)
            out.println(0);
        else
            out.println(minSum);
        out.close();
    }
}
