import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class multimoo {
    static boolean[][] visited;
    static boolean[] finishedColor;
    static int N, colorNo;
    static int[] color2No;
    static int[][] map;
    static int[][] DIR = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("multimoo.in")); // new FileReader("multimoo.in") //new
                                                                              // InputStreamReader(System.in)
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("multimoo.out")));
        N = Integer.parseInt(f.readLine());
        map = new int[N][N];
        HashSet<Integer> colors = new HashSet<>();
        color2No = new int[1000001];
        colorNo = 0;
        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            for (int c = 0; c < N; c++) {
                int col = Integer.parseInt(st.nextToken());
                if (!colors.contains(col)) {
                    colors.add(col);
                    colorNo++;
                    color2No[col] = colorNo;
                }
                map[r][c] = color2No[col];
            }
        }
        visited = new boolean[N][N];
        int single_max = 0;
        for (int r = 0; r < N; r++)
            for (int c = 0; c < N; c++)
                if (!visited[r][c])
                    single_max = Math.max(simple_flood(r, c), single_max);
        out.println(single_max);
        finishedColor = new boolean[colorNo + 1];
        int groupMax = 0;
        for (int r = 0; r < N; r++)
            for (int c = 0; c < N; c++) {
                if (finishedColor[map[r][c]])
                    continue;
                visited = new boolean[N][N];
                groupTotal = new int[colorNo + 1];
                int total = complex_flood(r, c, map[r][c]);
                finishedColor[map[r][c]] = true;
                for (int i = 1; i <= colorNo; i++) {

                    groupMax = Math.max(groupTotal[i] + total, groupMax);
                }

            }
        out.println(groupMax);
        out.close();

    }

    static int[] groupTotal;

    private static int simple_flood(int r, int c) {
        int total = 1;
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int newR = r + DIR[i][0];
            int newC = c + DIR[i][1];
            if (newR < 0 || newR >= N || newC < 0 || newC >= N)
                continue;
            if (visited[newR][newC] || map[newR][newC] != map[r][c])
                continue;
            total += simple_flood(newR, newC);
        }
        return total;
    }

    private static int complex_flood(int r, int c, int color) {

        int total = 0;
        if (map[r][c] == color) {
            total = 1;
        } else
            groupTotal[map[r][c]]++;
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int newR = r + DIR[i][0];
            int newC = c + DIR[i][1];
            if (newR < 0 || newR >= N || newC < 0 || newC >= N || visited[newR][newC] || finishedColor[map[newR][newC]])
                continue;
            if (map[newR][newC] != color && map[r][c] != color && map[r][c] != map[newR][newC])
                continue;

            total += complex_flood(newR, newC, color);
        }
        return total;
    }
}
