import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class lemonade {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("lemonade.in")); // new FileReader("lemonade.in") //new
                                                                              // InputStreamReader(System.in)
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
        int N = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        int front = 0;
        int end = N - 1;
        int ahead = 0;
        while (front <= end) {
            if (arr[front] < ahead)
                front++;
            else {
                end--;
                ahead++;
            }

        }
        out.println(ahead);
        out.close();
    }
}
