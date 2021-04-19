import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class sleepy {
    static int[] bit;
    static int numCow;

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
        for (int i = index; i <= numCow; i += lowbit(i))
            bit[i] += value;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("sleepy.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
        numCow = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        int[] original = new int[numCow + 1];
        for (int i = 1; i <= numCow; i++)
            original[i] = Integer.parseInt(st.nextToken());
        int last = 1;
        bit = new int[numCow + 1];
        for (int i = numCow; i > 0; i--) {
            if (i < numCow && original[i] > original[i + 1]) {
                last = i;
                break;
            } else
                update(original[i], 1);
        }
        out.println(last);
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= last; i++) {
            str.append(getSum(original[i]) + last - i);
            update(original[i], 1);
            str.append(" ");
        }
        str.deleteCharAt(str.length() - 1);
        out.println(str);
        out.close();
    }
}
