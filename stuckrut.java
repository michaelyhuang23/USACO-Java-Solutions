import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class stuckrut {
    static class Cow implements Comparable<Cow> {
        boolean isEast;
        long x, y;
        int id;

        public Cow(int x, int y, boolean isE, int id) {
            isEast = isE;
            this.x = x;
            this.y = y;
            this.id = id;
        }

        @Override
        public int compareTo(stuckrut.Cow o) {
            return Long.compare(y, o.y);
        }

        public boolean stopCow(Cow o) { // should ensure one north one east
            if (isEast) {
                if (o.x - x < y - o.y)
                    return true;
                return false;
            } else {
                if (o.y - y < x - o.x)
                    return true;
                return false;
            }
        }

    }

    static int[] stoppedBy, stopCount;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        int numCow = Integer.parseInt(f.readLine());
        Cow[] allCows = new Cow[numCow];
        for (int i = 0; i < numCow; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            boolean isEast = st.nextToken().charAt(0) == 'E' ? true : false;
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            allCows[i] = new Cow(x, y, isEast, i);
        }
        Arrays.sort(allCows, new Comparator<Cow>() {
            @Override
            public int compare(stuckrut.Cow o1, stuckrut.Cow o2) {
                return Long.compare(o1.x, o2.x);
            }
        });
        stoppedBy = new int[numCow]; // who stopped it
        stopCount = new int[numCow]; // how many does it stop
        Arrays.fill(stoppedBy, -1);
        TreeSet<Cow> activeEast = new TreeSet<>();
        for (int i = 0; i < numCow; i++) {
            Cow thisCow = allCows[i];
            if (thisCow.isEast) {
                activeEast.add(thisCow);
                continue;
            }
            Queue<Cow> toRemove = new ArrayDeque<>();
            SortedSet<Cow> tail = activeEast.tailSet(thisCow);
            for (Cow eastCow : tail) {
                if (eastCow.stopCow(thisCow)) {
                    stoppedBy[thisCow.id] = eastCow.id;
                    stopCount[eastCow.id]++;
                    break;
                } else if (thisCow.stopCow(eastCow)) {
                    stoppedBy[eastCow.id] = thisCow.id;
                    stopCount[thisCow.id]++;
                    toRemove.offer(eastCow);
                }
            }
            activeEast.removeAll(toRemove); // not sure if this works
        }
        visited = new boolean[numCow];
        Queue<Integer> tails = new ArrayDeque<>();
        for (int i = 0; i < numCow; i++) {
            int id = allCows[i].id;
            if (stopCount[id] == 0) { // it could have no parent
                tails.offer(id);
            }
        }
        int[] totalStopCount = new int[numCow];

        while (!tails.isEmpty()) {
            int thisID = tails.poll();
            if (stoppedBy[thisID] == -1)
                continue;
            int next = stoppedBy[thisID];
            totalStopCount[next] += totalStopCount[thisID] + 1;
            stopCount[next]--; // this is direct stoppage count
            if (stopCount[next] == 0)
                tails.offer(next);
        }
        for (int i = 0; i < totalStopCount.length; i++) {
            System.out.println(totalStopCount[i]);
        }
    }

    static boolean[] visited;

}
