import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2848 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int H = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int[] Hx = new int[H + 2];
        int[] Hy = new int[H + 2];
        int[] Gx = new int[G + 2];
        int[] Gy = new int[G + 2];
        for (int h = 1; h <= H; h++) {
            st = new StringTokenizer(f.readLine());
            Hx[h] = Integer.parseInt(st.nextToken());
            Hy[h] = Integer.parseInt(st.nextToken());
        }
        for (int g = 1; g <= G; g++) {
            st = new StringTokenizer(f.readLine());
            Gx[g] = Integer.parseInt(st.nextToken());
            Gy[g] = Integer.parseInt(st.nextToken());
        }

        long[][][] dp = new long[H + 1][G + 1][2];
        for (int h = 0; h <= H; h++)
            for (int g = 0; g <= G; g++)
                Arrays.fill(dp[h][g], Long.MAX_VALUE / 2);
        dp[1][0][0] = 0;
        for (int h = 1; h <= H; h++) {
            for (int g = 0; g <= G; g++) {
                int dist1 = (Gx[g] - Hx[h + 1]) * (Gx[g] - Hx[h + 1]) + (Gy[g] - Hy[h + 1]) * (Gy[g] - Hy[h + 1]);
                int dist2 = (Hx[h] - Hx[h + 1]) * (Hx[h] - Hx[h + 1]) + (Hy[h] - Hy[h + 1]) * (Hy[h] - Hy[h + 1]);
                int dist3 = (Gx[g] - Gx[g + 1]) * (Gx[g] - Gx[g + 1]) + (Gy[g] - Gy[g + 1]) * (Gy[g] - Gy[g + 1]);
                int dist4 = (Hx[h] - Gx[g + 1]) * (Hx[h] - Gx[g + 1]) + (Hy[h] - Gy[g + 1]) * (Hy[h] - Gy[g + 1]);
                if (h + 1 <= H) {
                    dp[h + 1][g][0] = Math.min(dp[h + 1][g][0], dp[h][g][1] + dist1);
                    dp[h + 1][g][0] = Math.min(dp[h + 1][g][0], dp[h][g][0] + dist2);
                }
                if (g + 1 <= G) {
                    dp[h][g + 1][1] = Math.min(dp[h][g + 1][1], dp[h][g][0] + dist4);
                    dp[h][g + 1][1] = Math.min(dp[h][g + 1][1], dp[h][g][1] + dist3);
                }
            }
        }

        System.out.println(dp[H][G][0]);

    }
}
