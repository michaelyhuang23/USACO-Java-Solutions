import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class circlecross {
    static class Cow implements Comparable<Cow> {
        int start, end;

        public Cow(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public int compareTo(circlecross.Cow o) {
            return start - o.start;
        }
    }

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
        for (int i = index; i <= numCow * 2; i += lowbit(i))
            bit[i] += value;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("circlecross.in")); // new InputStreamReader(System.in)
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
        numCow = Integer.parseInt(f.readLine());
        bit = new int[2 * numCow + 1];
        Cow[] allCows = new Cow[numCow + 1];
        for (int i = 1; i <= numCow * 2; i++) {
            int cowNO = Integer.parseInt(f.readLine());
            if (allCows[cowNO] == null) {
                allCows[cowNO] = new Cow(i, 0);
            } else
                allCows[cowNO].end = i;
        }

        Arrays.sort(allCows, 1, numCow + 1);
        int total = 0;
        for (int i = 1; i <= numCow; i++) {
            total += getSum(allCows[i].end) - getSum(allCows[i].start);
            update(allCows[i].end, 1);
        }
        out.println(total);
        out.close();
    }
}
