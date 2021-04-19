import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class cowpatibility {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cowpatibility.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));
        int numCow = Integer.parseInt(f.readLine());
        int[][] cowIce = new int[numCow][5];
        for (int i = 0; i < numCow; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            for (int j = 0; j < 5; j++)
                cowIce[i][j] = Integer.parseInt(st.nextToken());
            Arrays.sort(cowIce[i]);
        }
        HashMap<String, Integer> patternCount = new HashMap<>();

        for (int cow = 0; cow < numCow; cow++) {
            for (int num = 1; num <= 31; num++) {
                StringBuilder str = new StringBuilder();
                for (int index = 0; index < 5; index++) {
                    if ((num & (1 << index)) > 0) {
                        str.append(cowIce[cow][index]);
                        str.append(" ");
                    }
                }
                String strr = str.toString();
                if (patternCount.containsKey(strr))
                    patternCount.put(strr, patternCount.get(strr) + 1);
                else
                    patternCount.put(strr, 1);
            }
        }
        int totalPair = 0;
        for (String key : patternCount.keySet()) {
            int contri = 0;
            int numShare = patternCount.get(key);
            contri += numShare * (numShare - 1) / 2;
            if (((key.split(" ").length) & 1) == 1)
                totalPair += contri;
            else
                totalPair -= contri;
        }
        out.println(numCow * (numCow - 1) / 2 - totalPair);
        out.close();
    }
}
