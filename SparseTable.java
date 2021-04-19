import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SparseTable {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] a = new int[n + 1];
        int[][] f = new int[n + 1][20];
        int[] log = new int[n + 1];
        log[0] = -1;

        st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            f[i][0] = a[i];
            log[i] = log[i >> 1] + 1;
        }

        for (int j = 1; j <= 19; j++) {
            for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                f[i][j] = Math.max(f[i][j - 1], f[i + (1 << (j - 1))][j - 1]);
            }
        }

        for (int i = 1; i <= m; i++) {
            int x, y;
            st = new StringTokenizer(in.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int s = log[y - x + 1];
            System.out.println(Math.max(f[x][s], f[y - (1 << s) + 1][s]));
        }
    }
}
