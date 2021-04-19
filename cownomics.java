import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class cownomics {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numCow = Integer.parseInt(st.nextToken());
        int length = Integer.parseInt(st.nextToken());
        HashMap<Character, Integer> charToInt = new HashMap<>();
        charToInt.put('A', 0);
        charToInt.put('C', 1);
        charToInt.put('G', 2);
        charToInt.put('T', 3);
        int[][] spotCows = new int[numCow][length];
        int[][] plainCows = new int[numCow][length];
        for (int i = 0; i < numCow; i++) {
            String input = f.readLine();
            for (int c = 0; c < input.length(); c++)
                spotCows[i][c] = charToInt.get(input.charAt(c));
        }
        for (int i = 0; i < numCow; i++) {
            String input = f.readLine();
            for (int c = 0; c < input.length(); c++)
                plainCows[i][c] = charToInt.get(input.charAt(c));
        }
        int count = 0;
        for (int first = 0; first < length; first++)
            for (int second = first + 1; second < length; second++)
                for (int third = second + 1; third < length; third++) {
                    boolean[][][] spotSequence = new boolean[4][4][4];
                    boolean distinguishable = true;
                    for (int i = 0; i < numCow; i++)
                        spotSequence[spotCows[i][first]][spotCows[i][second]][spotCows[i][third]] = true;
                    for (int i = 0; i < numCow; i++)
                        if (spotSequence[plainCows[i][first]][plainCows[i][second]][plainCows[i][third]]) {
                            distinguishable = false;
                            break;
                        }
                    if (distinguishable)
                        count++;
                }
        out.println(count);
        out.close();
    }
}
