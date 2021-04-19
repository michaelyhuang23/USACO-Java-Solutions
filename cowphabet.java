import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class cowphabet {
    static class Sequence implements Comparable<Sequence> {
        int head, tail;
        int count;

        public Sequence(int h, int t, int c) {
            head = h;
            tail = t;
            count = c;
        }

        @Override
        public int compareTo(Sequence o) {
            return o.count - count; // descending
        }
    }

    static int find(int element) {
        if (root[element] == element) {
            return element;
        }

        root[element] = find(root[element]);

        return root[element];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        String str = f.readLine();
        int length = str.length();
        int[][] seq = new int[26][26];
        for (int i = 0; i < length - 1; i++) {
            int cur = Character.getNumericValue(str.charAt(i)) - Character.getNumericValue('a');
            int next = Character.getNumericValue(str.charAt(i + 1)) - Character.getNumericValue('a');
            if (cur != next)
                seq[cur][next]++;
        }
        ArrayList<Sequence> allSeqs = new ArrayList<>();
        for (int h = 0; h < 26; h++) {
            for (int t = 0; t < 26; t++) {
                if (seq[h][t] > 0)
                    allSeqs.add(new Sequence(h, t, seq[h][t]));
            }
        }

        allSeqs.sort(null);

        root = new int[26];
        for (int i = 0; i < 26; i++) {
            root[i] = i;
        }

        boolean[] fronts = new boolean[26];
        boolean[] ends = new boolean[26];
        int costCut = 0;
        for (Sequence curSeq : allSeqs) {
            int h = curSeq.head;
            int t = curSeq.tail;
            if (!ends[h] && !fronts[t] && find(h) != find(t)) {
                ends[h] = true;
                fronts[t] = true;
                merge(h, t);
                costCut += curSeq.count;
            }
        }
        System.out.println(length - costCut);
    }

    static int[] root;

    static void merge(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            root[rootB] = rootA;
        }
    }
}
