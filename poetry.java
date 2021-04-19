import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class poetry {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("poetry.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numWord = Integer.parseInt(st.nextToken());
        int numLine = Integer.parseInt(st.nextToken());
        int numSyllable = Integer.parseInt(st.nextToken());
        int[] wordLength = new int[numWord + 1];
        int[] wordRhyme = new int[numWord + 1];
        final long MOD = 1000000007;
        for (int i = 1; i <= numWord; i++) {
            st = new StringTokenizer(f.readLine());
            int length = Integer.parseInt(st.nextToken());
            int rhyme = Integer.parseInt(st.nextToken());
            wordLength[i] = length;
            wordRhyme[i] = rhyme;
        }
        int numRhyme = numWord; // there're many empty spots obviously.
        long[][] dp = new long[numSyllable + 1][numRhyme + 1];
        dp[0][0] = 1; // how many ways to achieve a line of 0 syllables: 1?
        for (int onSyl = 1; onSyl < numSyllable; onSyl++) { // make two steps of dp
            for (int lastWord = 1; lastWord <= numWord; lastWord++) {
                if (onSyl - wordLength[lastWord] >= 0) {
                    dp[onSyl][0] += dp[onSyl - wordLength[lastWord]][0];
                    dp[onSyl][0] %= MOD;
                }
            }
        }

        for (int lastWord = 1; lastWord <= numWord; lastWord++) {
            if (numSyllable - wordLength[lastWord] >= 0) {
                dp[numSyllable][wordRhyme[lastWord]] += dp[numSyllable - wordLength[lastWord]][0];
                dp[numSyllable][wordRhyme[lastWord]] %= MOD;
            }
        }

        int[] rhymeScheme = new int[26];
        for (int i = 0; i < numLine; i++) {
            int schemeID = Character.getNumericValue(f.readLine().charAt(0)) - Character.getNumericValue('A');
            rhymeScheme[schemeID]++;
        }
        long prod = 1;
        for (int i = 0; i < rhymeScheme.length; i++) {
            if (rhymeScheme[i] == 0)
                continue;
            long sum = 0;
            for (int rhymeC = 1; rhymeC <= numRhyme; rhymeC++) {
                long lineCount = dp[numSyllable][rhymeC] % MOD;
                sum += expo(lineCount, rhymeScheme[i], MOD);
                sum %= MOD;
            }
            prod *= sum;
            prod %= MOD;
        }
        out.println(prod);
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
