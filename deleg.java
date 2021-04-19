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

public class deleg {
    static int numNode;
    static ArrayList<Integer>[] connector;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("deleg.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("deleg.out")));
        numNode = Integer.parseInt(f.readLine());
        connector = new ArrayList[numNode];
        for (int i = 0; i < connector.length; i++) {
            connector[i] = new ArrayList<>();
        }
        for (int i = 0; i < numNode - 1; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            connector[first].add(second);
            connector[second].add(first);
        }
        StringBuilder str = new StringBuilder();
        str.append(1);
        for (int segLength = 2; segLength <= (numNode - 1); segLength++) {
            if ((numNode - 1) % segLength != 0) {
                str.append(0);
                continue;
            }
            int ret = dfs(0, 0, segLength);
            if (ret == -1) {
                str.append(0);
                continue;
            }
            if (ret == 1)
                str.append(1);
            else
                str.append(0);
        }
        out.println(str);
        out.close();
    }

    private static int dfs(int son, int father, int segLength) {
        int[] lengthCounter = new int[segLength];
        HashSet<Integer> lengths = new HashSet<>();
        int size = 0;

        for (int next : connector[son]) {
            if (father == next)
                continue;
            int ret = dfs(next, son, segLength);
            if (ret == -1)
                return -1;
            if (ret == 0)
                continue;
            lengthCounter[ret]++;
            size++;
            lengths.add(ret);
        }
        int survivor = 0;
        for (int len : lengths) {
            if (len * 2 == segLength) {
                int newCount = lengthCounter[len] % 2;
                size -= (lengthCounter[len] - newCount);
                lengthCounter[len] = newCount;
            } else {
                while (lengthCounter[len] > 0 && lengthCounter[segLength - len] > 0) {
                    lengthCounter[segLength - len]--; // consider special cases, what if the two are equal?
                    lengthCounter[len]--;
                    size -= 2;
                }
            }
            if (lengthCounter[len] > 0)
                survivor = len;
        }
        if (size > 1)
            return -1;
        if (size == 1)
            return (survivor + 1) % segLength;
        return 1 % segLength;
    }
}
