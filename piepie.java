import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class piepie {
    static class Pie implements Comparable<Pie> {
        long thisVal, otherVal;
        int owner, index, id;
        Pie reverse;

        public Pie(long tV, int oV, int own, int id) {
            thisVal = tV;
            otherVal = oV;
            owner = own;
            this.id = id;
        }

        @Override
        public int compareTo(piepie.Pie o) {
            return thisVal == o.thisVal ? id - o.id : Long.compare(thisVal, o.thisVal);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("piepie.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("piepie.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numNode = Integer.parseInt(st.nextToken());
        long maxDiff = Integer.parseInt(st.nextToken());
        Pie[][] pies = new Pie[2][numNode];
        Pie[][] otherPies = new Pie[2][numNode];
        for (int i = 0; i < numNode; i++) {
            st = new StringTokenizer(f.readLine());
            int bessie = Integer.parseInt(st.nextToken());
            int elsie = Integer.parseInt(st.nextToken());
            pies[0][i] = new Pie(bessie, elsie, 0, i);
            otherPies[1][i] = new Pie(elsie, bessie, 0, i);
            otherPies[1][i].reverse = pies[0][i];
            pies[0][i].reverse = otherPies[1][i];
        }

        for (int i = 0; i < numNode; i++) {
            st = new StringTokenizer(f.readLine());
            int bessie = Integer.parseInt(st.nextToken());
            int elsie = Integer.parseInt(st.nextToken());
            pies[1][i] = new Pie(elsie, bessie, 1, i);
            otherPies[0][i] = new Pie(bessie, elsie, 1, i);
            otherPies[0][i].reverse = pies[1][i];
            pies[1][i].reverse = otherPies[0][i];
        }

        Arrays.sort(pies[0]);
        for (int i = 0; i < numNode; i++)
            pies[0][i].index = i;
        Arrays.sort(pies[1]);
        for (int i = 0; i < numNode; i++)
            pies[1][i].index = i;
        Arrays.sort(otherPies[0]);
        Arrays.sort(otherPies[1]);
        int[][] dist2Zero = new int[2][numNode];
        Arrays.fill(dist2Zero[0], -1);
        Arrays.fill(dist2Zero[1], -1);
        for (int cow = 0; cow < 2; cow++)
            for (int pie = 0; pie < numNode; pie++) {
                if (pies[cow][pie].otherVal != 0)
                    continue;
                Queue<Pie> frontier = new ArrayDeque<>();
                frontier.offer(pies[cow][pie]);
                dist2Zero[cow][pie] = 1;
                while (!frontier.isEmpty()) {
                    Pie thisP = frontier.poll();
                    int newCow = thisP.owner;
                    int index = Arrays.binarySearch(otherPies[newCow], new Pie(thisP.thisVal, 0, 0, numNode + 1));
                    if (index < 0) {
                        index = -index - 1;
                        index--;
                    }
                    for (int i = index; i >= 0; i--) {
                        Pie newPie = otherPies[newCow][i];
                        if (newPie.thisVal + maxDiff < thisP.thisVal)
                            break;
                        int newI = newPie.reverse.index;
                        if (dist2Zero[1 - newCow][newI] == -1
                                || dist2Zero[1 - newCow][newI] > 1 + dist2Zero[newCow][thisP.index]) {
                            dist2Zero[1 - newCow][newI] = 1 + dist2Zero[newCow][thisP.index];
                            frontier.offer(newPie.reverse);
                        }
                    }
                }
            }
        int[] ans = new int[numNode];
        for (int i = 0; i < numNode; i++)
            ans[pies[0][i].id] = dist2Zero[0][i];

        for (int i = 0; i < numNode; i++)
            out.println(ans[i]);
        out.close();
    }
}
