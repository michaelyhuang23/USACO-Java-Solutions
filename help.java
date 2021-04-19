import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class help {
    public static void main(String[] args) throws IOException {
        final int MOD = 1000000007;
        BufferedReader f = new BufferedReader(new FileReader("help.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("help.out")));
        int numSeg = Integer.parseInt(f.readLine());
        boolean[] isHead = new boolean[numSeg * 2];
        for (int i = 0; i < numSeg; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            isHead[first] = true;
            isHead[second] = false;
        }
        int activeCount = numSeg;
        long sum = 0;
        for (int i = 0; i < numSeg * 2; i++) {
            if (isHead[i])
                activeCount--;
            else
                activeCount++;
            if (isHead[i]) {
                sum += expo(2, activeCount, MOD);
                sum %= MOD;
            }
        }
        out.println(sum);
        out.close();
    }

    static long expo(long base, long expo, long mod) {
        long val = 1;
        long varyBase = base;
        while (expo > 0) {
            if (expo % 2 == 1) {
                val = (val * varyBase) % mod;
            }
            varyBase = (varyBase * varyBase) % mod;
            expo /= 2; // since we square the base while simultaneously shifting right the expo, the
                       // base always matches the expo.
        }
        return val % mod;
    }
}
