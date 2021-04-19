import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class haircut {
    static int[] bit;
    static int length;

    private static int lowbit(int x) {
        return x & (-x);
    }

    private static int getSum(int index) {
        int sum = 0;
        for (int i = index; i > 0; i -= lowbit(i))
            sum += bit[i];
        return sum;
    }

    private static void update(int index, int value) {
        for (int i = index; i <= length; i += lowbit(i))
            bit[i] += value;
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader f = new BufferedReader(new FileReader("haircut.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haircut.out")));
        length = Integer.parseInt(f.readLine());
        int[] arr = new int[length + 1];
        ArrayList<Integer>[] pos = new ArrayList[length + 1];
        for (int i = 0; i < pos.length; i++) {
            pos[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i = 1; i <= length; i++) {
            int height = Integer.parseInt(st.nextToken());
            arr[i] = height;
            pos[height].add(i);
        }
        bit = new int[length + 1];
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        int prevVal = -1;
        long totalPair = 0;
        for (int i = length; i >= 1; i--) {
            if (prevVal != sortedArr[i]) {
                prevVal = sortedArr[i];
                for (int newPos : pos[prevVal])
                    totalPair += (long) getSum(newPos);
                for (int newPos : pos[prevVal])
                    update(newPos, 1);
            }
        }
        bit = new int[length + 1];
        for (int newPos : pos[length])
            update(newPos, 1);
        long pairRemoved = 0;
        long[] ans = new long[length];
        for (int clamp = length - 1; clamp >= 0; clamp--) {
            for (int newPos : pos[clamp])
                pairRemoved += (long) getSum(newPos);
            for (int newPos : pos[clamp])
                update(newPos, 1);
            ans[clamp] = totalPair - pairRemoved;
        }
        for (int i = 0; i < ans.length; i++) {
            out.println(ans[i]);
        }
        out.close();
    }
}
