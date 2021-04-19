import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class leftout {
    static int[][] targetMap, map;
    static int length;
    static int matchCount;
    static boolean finished = false;

    private static boolean[] flipRow(int r) {
        boolean[] colNeedFlip = new boolean[length];
        boolean done = false;
        for (int c = 0; c < length; c++) {
            map[r][c]++;
            if (map[r][c] < targetMap[r][c]) {
                colNeedFlip[c] = true;
                matchCount--;
            } else if (map[r][c] == targetMap[r][c])
                matchCount++;
            else {
                matchCount--;
                done = true;
            }
        }
        if (matchCount == length * length - 1) {
            finished = true;
            done = true;
        }
        if (done) {
            return null;
        }

        return colNeedFlip;
    }

    private static boolean[] flipColumn(int c) {
        boolean[] rowNeedFlip = new boolean[length];
        boolean done = false;
        for (int r = 0; r < length; r++) {
            map[r][c]++;
            if (map[r][c] < targetMap[r][c]) {
                rowNeedFlip[r] = true;
                matchCount--;
            } else if (map[r][c] == targetMap[r][c])
                matchCount++;
            else {
                matchCount--;
                done = true;
            }
        }
        if (matchCount == length * length - 1) {
            finished = true;
            done = true;
        }
        if (done) {
            return null;
        }

        return rowNeedFlip;
    }

    private static void printArray(int[][] arr, int[][] arr2) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] - arr2[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader f = new BufferedReader(new FileReader("leftout.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("leftout.out")));
        length = Integer.parseInt(f.readLine());
        map = new int[length][length];
        targetMap = new int[length][length];
        for (int r = 0; r < length; r++) {
            String input = f.readLine();
            for (int c = 0; c < length; c++) {
                targetMap[r][c] = input.charAt(c) == 'R' ? 2 : 1;
                if (targetMap[r][c] == 2)
                    matchCount++;
            }
        }
        search(length - 1, length - 1, true);
        for (int r = 0; r < length; r++)
            for (int c = 0; c < length; c++)
                if (map[r][c] % 2 != targetMap[r][c] % 2) {
                    out.println((r + 1) + " " + (c + 1));
                    out.close();
                    return;
                }
        out.println(-1);
        out.close();
    }

    private static boolean search(int r, int c, boolean isRow) {
        if (isRow) {
            boolean[] colNeedFlip = flipRow(r);
            if (colNeedFlip == null) {
                if (finished)
                    return true;
                else
                    return false;
            }
            for (int newC = length - 1; newC >= 0; newC--)
                if (colNeedFlip[newC])
                    if (search(r, newC, false))
                        return true;
        } else {
            boolean[] rowNeedFlip = flipColumn(c);
            if (rowNeedFlip == null) {
                if (finished)
                    return true;
                else
                    return false;
            }
            for (int newR = length - 1; newR >= 0; newR--)
                if (rowNeedFlip[newR])
                    if (search(newR, c, true))
                        return true;
        }
        return false;
    }
}
