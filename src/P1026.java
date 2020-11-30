import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1026 {
    static String[] words;
    static int[] counters;

    private static int countWord(String str, int start, int end) {
        int counter = 0;
        counters = new int[end - start + 1];
        for (int s = start; s <= end; s++)
            for (String word : words) {
                if (word.length() > end - s + 1)
                    continue;
                boolean success = true;
                for (int e = s; e <= Math.min(end, s + word.length() - 1); e++) {
                    if (word.charAt(e - s) != str.charAt(e)) {
                        success = false;
                        break;
                    }
                }
                if (success) {
                    counters[s - start] = 1;
                    counter++;
                    break;
                }
            }
        return counter;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numLine = Integer.parseInt(st.nextToken());
        int numSep = Integer.parseInt(st.nextToken());
        String str = "";
        for (int i = 0; i < numLine; i++)
            str += f.readLine();
        int numWord = Integer.parseInt(f.readLine());
        words = new String[numWord];
        for (int i = 0; i < numWord; i++)
            words[i] = f.readLine();

        int[][] dp = new int[numLine * 20][numSep + 1];
        for (int e = 0; e < numLine * 20; e++)
            dp[e][1] = countWord(str, 0, e);
        for (int chr = 1; chr < numLine * 20; chr++) {
            for (int sep = 2; sep <= numSep; sep++) {
                if (sep - 1 > chr)
                    continue;
                int count = countWord(str, sep - 1, chr);
                for (int prevChr = sep - 1; prevChr <= chr; prevChr++) {
                    dp[chr][sep] = Math.max(dp[chr][sep], dp[prevChr - 1][sep - 1] + count);
                    count -= counters[prevChr - sep + 1];
                }
            }
        }
        System.out.println(dp[numLine * 20 - 1][numSep]);
    }
}
