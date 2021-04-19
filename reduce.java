import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class reduce {
    static class Cow {
        int x, y, id;

        public Cow(int ix, int iy, int id) {
            x = ix;
            y = iy;
            this.id = id;
        }
    }

    static TreeSet<Cow> horizontal, vertical;
    static long minArea = Long.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("reduce.in")); // new FileReader("reststops.in") //new
                                                                            // InputStreamReader(System.in)
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
        int numCow = Integer.parseInt(f.readLine());
        horizontal = new TreeSet<Cow>(new Comparator<Cow>() {
            public int compare(Cow c1, Cow c2) {
                if (c1.x == c2.x)
                    return c1.id - c2.id;
                return c1.x - c2.x;
            }
        });
        vertical = new TreeSet<Cow>(new Comparator<Cow>() {
            public int compare(Cow c1, Cow c2) {
                if (c1.y == c2.y)
                    return c1.id - c2.id;
                return c1.y - c2.y;
            }
        });
        for (int i = 0; i < numCow; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Cow thisC = new Cow(x, y, i);
            horizontal.add(thisC);
            vertical.add(thisC);
        }
        search(3);
        out.println(minArea);
        out.close();
    }

    private static void search(int numLeft) {
        if (numLeft == 0) {
            long hori = horizontal.last().x - horizontal.first().x;
            long verti = vertical.last().y - vertical.first().y;
            minArea = Math.min(hori * verti, minArea);
            return;
        }
        Cow leftC = horizontal.first();
        horizontal.remove(leftC);
        vertical.remove(leftC);
        search(numLeft - 1);
        horizontal.add(leftC);
        vertical.add(leftC);

        Cow rightC = horizontal.last();
        horizontal.remove(rightC);
        vertical.remove(rightC);
        search(numLeft - 1);
        horizontal.add(rightC);
        vertical.add(rightC);

        Cow topC = vertical.last();
        horizontal.remove(topC);
        vertical.remove(topC);
        search(numLeft - 1);
        horizontal.add(topC);
        vertical.add(topC);

        Cow bottomC = vertical.first();
        horizontal.remove(bottomC);
        vertical.remove(bottomC);
        search(numLeft - 1);
        horizontal.add(bottomC);
        vertical.add(bottomC);
    }
}
