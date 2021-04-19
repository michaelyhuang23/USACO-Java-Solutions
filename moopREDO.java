import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class moopREDO {
    static class Segment implements Comparable<Segment> {
        long left, right;

        public Segment(long lf, long rg) {
            left = lf;
            right = rg;
        }

        @Override
        public int compareTo(Segment o) {
            return right == o.right ? Long.compare(left, o.left) : Long.compare(right, o.right);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("moop.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));
        int numParticle = Integer.parseInt(f.readLine());
        Segment[] allSegs = new Segment[numParticle];
        for (int i = 0; i < numParticle; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            allSegs[i] = new Segment(left, right);
        }

        Arrays.sort(allSegs, new Comparator<Segment>() {
            public int compare(Segment s1, Segment s2) {
                return s1.left == s2.left ? Long.compare(s1.right, s2.right) : Long.compare(s1.left, s2.left);
            }
        });
        TreeSet<Segment> tailSort = new TreeSet<>();

        for (int i = 0; i < numParticle; i++) { // what if two heads are together?
            Segment thisSeg = allSegs[i];
            NavigableSet<Segment> header = tailSort.headSet(thisSeg, true);
            Segment minSeg = thisSeg;
            for (Segment seg : header) {
                if (seg.compareTo(minSeg) < 0) // what comparator u using!!!
                    minSeg = seg;
            }
            header.clear();
            tailSort.add(minSeg);
        }
        out.println(tailSort.size());
        out.close();

    }
}
