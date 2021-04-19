import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class paintbarn {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("paintbarn.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numSquare = Integer.parseInt(st.nextToken());
        int numLayer = Integer.parseInt(st.nextToken());
        int[][] headCount = new int[1002][1002];
        int[][] tailCount = new int[1002][1002];
        int[][] sideCount = new int[1002][1002];
        for (int i = 0; i < numSquare; i++) {
            st = new StringTokenizer(f.readLine());
            int x1 = Integer.parseInt(st.nextToken()) + 1;
            int y1 = Integer.parseInt(st.nextToken()) + 1;
            int x2 = Integer.parseInt(st.nextToken()) + 1;
            int y2 = Integer.parseInt(st.nextToken()) + 1;
            headCount[x1][y1]++;
            tailCount[x2][y2]++;
            sideCount[x1][y2]++;
            sideCount[x2][y1]++;
        }
        int area = 0;
        int[][] headCounter = new int[1002][1002];
        for (int x = 1; x < 1002; x++)
            for (int y = 1; y < 1002; y++) {
                headCounter[x][y] = headCounter[x - 1][y] + headCounter[x][y - 1] - headCounter[x - 1][y - 1];
                headCounter[x][y] += headCount[x][y] - sideCount[x][y] + tailCount[x][y];
                if (x < 1001 && y < 1001 && headCounter[x][y] == numLayer)
                    area++;
            }

        out.println(area);
        out.close();
    }
}
