import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class bphoto {
    static int numCow;

    static class Cow implements Comparable<Cow> {
        int height;
        int origPos;

        public Cow(int h, int pos) {
            height = h;
            origPos = pos;
        }

        @Override
        public int compareTo(bphoto.Cow o) {
            return height - o.height;
        }
    }

    static int[] bit;

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
        BufferedReader f = new BufferedReader(new FileReader("bphoto.in")); // new FileReader("berries.in") //new
                                                                            // InputStreamReader(System.in)
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
        numCow = Integer.parseInt(f.readLine());
        Cow[] original = new Cow[numCow + 1];
        for (int i = 1; i <= numCow; i++)
            original[i] = new Cow(Integer.parseInt(f.readLine()), i);
        Cow[] sorted = original.clone();
        bit = new int[numCow + 1];
        Arrays.sort(sorted, 1, numCow + 1);
        int numTaller = 0;
        int counter = 0;
        for (int i = numCow; i > 0; i--) {
            int left = getSum(sorted[i].origPos);
            int right = numTaller - left;
            if (left * 2 < right || left > 2 * right)
                counter++;
            update(sorted[i].origPos, 1);
            numTaller++;
        }
        out.println(counter);
        out.close();
    }
}
