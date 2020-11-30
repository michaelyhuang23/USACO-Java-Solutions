import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class sort {
    static class number implements Comparable<number> {
        int value, position;

        public number(int val, int pos) {
            value = val;
            position = pos;
        }

        @Override
        public int compareTo(sort.number o) {
            if (value == o.value)
                return o.position - position;
            return o.value - value;
        }
    }

    static int length;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("sort.in")); // new FileReader("socdist.in") //new
        // InputStreamReader(System.in)
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
        length = Integer.parseInt(f.readLine());
        number[] arr = new number[length + 1];
        bit = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            arr[i] = new number(Integer.parseInt(f.readLine()), i);
        }
        number[] arrSorted = arr.clone();
        Arrays.sort(arrSorted, 1, length + 1);
        for (int i = 1; i <= length; i++)
            arrSorted[i].position = i;
        int counter = 0;
        update(arr[1].position, 1);
        // System.out.println(arr[1].position + " " + getSum(arr[1].position));
        for (int i = 2; i <= length; i++) {
            // System.out.println(arr[i].position + " " + getSum(arr[i].position));
            if (arr[i].position > 1)
                counter = Math.max(getSum(arr[i].position - 1), counter);
            update(arr[i].position, 1);
        }
        out.println(counter + 1);
        out.close();
        f.close();
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
        for (int i = index; i <= length; i += lowbit(i))
            bit[i] += value;
    }
}
