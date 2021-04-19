import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;

public class where {
    static int length;
    static int[][] map;
    static final int[][] DIR = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    private static boolean contains(int[][][][] dp, int num, int r1, int c1, int r2, int c2) {
        if (r2 > 0 && dp[r1][c1][r2 - 1][c2] == num || c2 > 0 && dp[r1][c1][r2][c2 - 1] == num
                || r1 < length - 1 && dp[r1 + 1][c1][r2][c2] == num
                || c1 < length - 1 && dp[r1][c1 + 1][r2][c2] == num) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("where.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("where.out")));
        length = Integer.parseInt(f.readLine());
        map = new int[length][length];
        for (int r = 0; r < length; r++) {
            String input = f.readLine();
            for (int c = 0; c < length; c++) {
                map[r][c] = Character.getNumericValue(input.charAt(c)) - Character.getNumericValue('A') + 1;
            }
        }
        int blockCount = 0;
        int[][][][] rect = new int[length][length][length][length];
        for (int width = 0; width < length; width++)
            for (int height = 0; height < length; height++)
                for (int r1 = 0; r1 + height < length; r1++)
                    for (int c1 = 0; c1 + width < length; c1++) {
                        int r2 = r1 + height;
                        int c2 = c1 + width;
                        // System.out.println("traverse: " + r1 + " " + c1 + " " + r2 + " " + c2);
                        if (contains(rect, -1, r1, c1, r2, c2)) {
                            rect[r1][c1][r2][c2] = -1;
                            continue;
                        }

                        visited = new boolean[length][length];
                        groups = new int[length][length];
                        int groupI = 1;
                        int firstColor = 0, secondColor = 0, firstColorC = 0, secondColorC = 0;
                        boolean success = true;
                        for (int r = r1; r <= r2; r++) {
                            for (int c = c1; c <= c2; c++) {
                                if (visited[r][c])
                                    continue;

                                if (firstColor == 0)
                                    firstColor = map[r][c];
                                else if (secondColor == 0)
                                    secondColor = map[r][c];

                                if (firstColor == map[r][c])
                                    firstColorC++;
                                else if (secondColor == map[r][c])
                                    secondColorC++;
                                else {
                                    success = false;
                                    break;
                                }

                                visited[r][c] = true;
                                groups[r][c] = groupI;

                                dfs(r, c, r1, c1, r2, c2);
                                groupI++;
                            }
                            if (!success)
                                break;
                        }
                        if (!success) {
                            rect[r1][c1][r2][c2] = -1;
                            continue;
                        }

                        if (firstColorC > secondColorC) {
                            int temp = secondColorC;
                            secondColorC = firstColorC;
                            firstColorC = temp;
                        }

                        if (firstColorC == 1 && secondColorC > 1)
                            rect[r1][c1][r2][c2] = 1;

                    }
        HashSet<Integer>[][] rectCount = new HashSet[length][length];
        for (int r = 0; r < length; r++)
            for (int c = 0; c < length; c++)
                rectCount[r][c] = new HashSet<>();
        int numRect = 1;
        for (int height = length - 1; height >= 0; height--)
            for (int width = length - 1; width >= 0; width--)
                for (int r1 = 0; r1 + height < length; r1++)
                    for (int c1 = 0; c1 + width < length; c1++) {
                        int r2 = r1 + height;
                        int c2 = c1 + width;
                        if (rect[r1][c1][r2][c2] != 1)
                            continue;
                        boolean success = true;
                        for (int rectI : rectCount[r1][c1])
                            if (rectCount[r2][c2].contains(rectI)) {
                                success = false;
                                break;
                            }
                        if (!success)
                            continue;
                        for (int r = r1; r <= r2; r++)
                            for (int c = c1; c <= c2; c++)
                                rectCount[r][c].add(numRect);
                        // System.out.println(r1 + " " + c1 + " " + r2 + " " + c2);
                        // System.out.println(rect[r1][c1][r2][c2]);
                        numRect++;
                    }

        out.println(numRect - 1);
        out.close();
    }

    static boolean[][] visited;
    static int[][] groups;

    private static void dfs(int r, int c, int r1, int c1, int r2, int c2) {
        for (int i = 0; i < 4; i++) {
            int newR = r + DIR[i][0];
            int newC = c + DIR[i][1];
            if (newR < r1 || newR > r2 || newC < c1 || newC > c2)
                continue;
            if (visited[newR][newC])
                continue;
            if (map[newR][newC] != map[r][c])
                continue;
            visited[newR][newC] = true;
            groups[newR][newC] = groups[r][c];
            dfs(newR, newC, r1, c1, r2, c2);
        }
    }

}
