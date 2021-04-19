import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class pairup {
    static class Type implements Comparable<Type> {
        int count, time;

        public Type(int c, int t) {
            count = c;
            time = t;
        }

        @Override
        public int compareTo(pairup.Type o) {
            return time - o.time;
        }
    }

    static Type[] allTypes;
    static int numType;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("pairup.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
        numType = Integer.parseInt(f.readLine());
        allTypes = new Type[numType];
        for (int i = 0; i < numType; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int count = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            allTypes[i] = new Type(count, time);
        }
        Arrays.sort(allTypes);
        long leftBT = 0, rightBT = 2000000001L;
        long ansT = 0;
        while (leftBT <= rightBT) {
            long midT = (leftBT + rightBT) / 2;
            if (check(midT)) {
                ansT = midT;
                rightBT = midT - 1;
            } else
                leftBT = midT + 1;
        }

        out.println(ansT);
        out.close();
    }

    private static boolean check(long midT) {
        Type[] copyTypes = new Type[numType];
        for (int i = 0; i < numType; i++)
            copyTypes[i] = new Type(allTypes[i].count, allTypes[i].time);
        int first = 0, second = numType - 1;
        while (first <= second) {
            if (copyTypes[first].time + copyTypes[second].time > midT)
                return false;
            if (first == second) {
                break;
            }
            if (copyTypes[first].count < copyTypes[second].count) {
                copyTypes[second].count -= copyTypes[first].count;
                copyTypes[first].count = 0;
                first++;
            } else if (copyTypes[second].count < copyTypes[first].count) {
                copyTypes[first].count -= copyTypes[second].count;
                copyTypes[second].count = 0;
                second--;
            } else {
                copyTypes[first].count = 0;
                copyTypes[second].count = 0;
                first++;
                second--;
            }
        }
        return true;
    }
}
