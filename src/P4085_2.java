import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P4085_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        int N;
        long M;
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        long[] F = new long[N + 1];
        int[] S = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(f.readLine());
            F[i] = Long.parseLong(st.nextToken());
            S[i] = Integer.parseInt(st.nextToken());
        }
        int[] log = new int[N + 1];
        log[0] = -1; // all the log values are rounded down except the first; also log means log2
                     // here
        for (int i = 1; i < N + 1; i++)
            log[i] = log[i >> 1] + 1;
        int[][] maxInRange = new int[N + 1][log[N] + 1];
        for (int i = 1; i <= N; i++)
            maxInRange[i][0] = S[i];
        for (int j = 1; j < maxInRange[0].length; j++) {
            for (int i = 1; i + (1 << j) - 1 <= N; i++) {
                maxInRange[i][j] = Math.max(maxInRange[i][j - 1], maxInRange[i + (1 << (j - 1))][j - 1]);
            }
        }
        long[] prefixSum = new long[N + 1];
        for (int i = 1; i <= N; i++)
            prefixSum[i] = prefixSum[i - 1] + F[i];
        int minSpice = Integer.MAX_VALUE;
        for (int left = 1; left <= N; left++) {
            int l = left, r = N;
            int right = l;
            while (l <= r) {
                int mid = (l + r) >> 1;
                if (prefixSum[mid] - prefixSum[left - 1] >= M) {
                    right = mid;
                    r = mid - 1;
                } else
                    l = mid + 1;
            }
            if (prefixSum[right] - prefixSum[left - 1] < M)
                continue;
            right++;
            int expo = log[right - left];
            int maxSpice = Math.max(maxInRange[left][expo], maxInRange[right - (1 << expo)][expo]);
            minSpice = Math.min(minSpice, maxSpice);
        }
        System.out.println(minSpice);
    }
}
