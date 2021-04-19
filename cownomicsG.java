import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class cownomicsG {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cownomics.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numCow = Integer.parseInt(st.nextToken());
        int length = Integer.parseInt(st.nextToken());
        HashMap<Character, Integer> char2Int = new HashMap<>();
        char2Int.put('A', 0);
        char2Int.put('C', 1);
        char2Int.put('G', 2);
        char2Int.put('T', 3);
        String[] spotCows = new String[numCow];
        for (int i = 0; i < numCow; i++)
            spotCows[i] = f.readLine();

        String[] plainCows = new String[numCow];
        for (int i = 0; i < numCow; i++)
            plainCows[i] = f.readLine();

        int start = 0, end = 0;
        int minLength = Integer.MAX_VALUE / 2;
        while (end < length) {
            HashSet<String> spotSeq = new HashSet<>();
            boolean success = true;
            for (int i = 0; i < numCow; i++)
                spotSeq.add(spotCows[i].substring(start, end + 1));
            for (int i = 0; i < numCow; i++)
                if (spotSeq.contains(plainCows[i].substring(start, end + 1)))
                    success = false;
            if (success) {
                minLength = Math.min(minLength, end - start + 1);
                start++;
            } else {
                end++;
            }
        }
        out.println(minLength);
        out.close();
    }
}
