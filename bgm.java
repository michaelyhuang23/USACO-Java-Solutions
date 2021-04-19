import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class bgm {
    private static boolean checkExpression(int[] arr) {
        if ((arr[0] + 2 * arr[1] + 2 * arr[2] + arr[3]) % 7 == 0)
            return true;
        if ((arr[4] + arr[5] + arr[1] + arr[2]) % 7 == 0)
            return true;
        if ((2 * arr[5] + arr[6]) % 7 == 0)
            return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("bgm.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bgm.out")));
        int numRules = Integer.parseInt(f.readLine());
        HashMap<Character, Integer> char2In = new HashMap<>();
        char2In.put('B', 0);
        char2In.put('E', 1);
        char2In.put('S', 2);
        char2In.put('I', 3);
        char2In.put('G', 4);
        char2In.put('O', 5);
        char2In.put('M', 6);
        int[][] modOccur = new int[7][7];
        for (int i = 0; i < numRules; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int var = char2In.get(st.nextToken().charAt(0));
            int num = Integer.parseInt(st.nextToken()) % 7;
            num += 7;
            num %= 7;
            modOccur[var][num]++;
        }
        int[] arr = new int[7];
        long sum = 0;
        for (arr[0] = 0; arr[0] < 7; arr[0]++)
            for (arr[1] = 0; arr[1] < 7; arr[1]++)
                for (arr[2] = 0; arr[2] < 7; arr[2]++)
                    for (arr[3] = 0; arr[3] < 7; arr[3]++)
                        for (arr[4] = 0; arr[4] < 7; arr[4]++)
                            for (arr[5] = 0; arr[5] < 7; arr[5]++)
                                for (arr[6] = 0; arr[6] < 7; arr[6]++) {
                                    if (checkExpression(arr)) {
                                        long prod = 1;
                                        for (int i = 0; i < 7; i++)
                                            prod *= modOccur[i][arr[i]];
                                        sum += prod;
                                    }
                                }
        out.println(sum);
        out.close();
    }
}
