import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class milkorder {
    static class Edge implements Comparable<Edge> {
        int to;
        long id;

        public Edge(int t, long i) {
            to = t;
            id = i;
        }

        @Override
        public int compareTo(milkorder.Edge o) {
            return to == o.to ? Long.compare(id, o.id) : to - o.to;
        }
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader f = new BufferedReader(new
        // FileReader("/Users/michaelhyh/Downloads/milkorder_gold_open18/4.in"));
        BufferedReader f = new BufferedReader(new FileReader("milkorder.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numNode = Integer.parseInt(st.nextToken());
        int numInfo = Integer.parseInt(st.nextToken());
        TreeSet<Edge>[] connector = new TreeSet[numNode]; // mind repetition of edge
        for (int i = 0; i < connector.length; i++) {
            connector[i] = new TreeSet<>();
        }
        ArrayList<Integer>[] info = new ArrayList[numInfo];
        int[] inDeg = new int[numNode];
        for (int i = 0; i < numInfo; i++) {
            st = new StringTokenizer(f.readLine());
            int length = Integer.parseInt(st.nextToken());
            info[i] = new ArrayList<>();
            for (int j = 0; j < length; j++) {
                int curr = Integer.parseInt(st.nextToken()) - 1;
                info[i].add(curr);
                if (j > 0) {
                    connector[info[i].get(j - 1)].add(new Edge(curr, i * 1000000L + j));
                    inDeg[curr]++;
                }
            }
        }
        int traversedCount = 0;
        Queue<Integer> tails = new ArrayDeque<>();
        for (int i = 0; i < numNode; i++) {
            if (inDeg[i] == 0)
                tails.offer(i);
        }
        boolean[] visited = new boolean[numNode]; // technically a visited array is not needed
        int infoIndex = numInfo - 1;
        while (traversedCount < numNode) {
            // if (traversedCount == 99997)
            // System.out.println(traversedCount);
            while (!tails.isEmpty()) {
                int curr = tails.poll(); // We need two topological sort, so we'd better not modify anything here.
                if (visited[curr])
                    continue;
                visited[curr] = true;
                traversedCount++;
                for (Edge next : connector[curr]) {
                    inDeg[next.to]--;
                    if (inDeg[next.to] == 0)
                        tails.offer(next.to);
                }
            }
            if (traversedCount == numNode) // it shouldn't be larger so mind that
                break;
            // if (infoIndex == 0)
            // System.out.println("hllo");
            ArrayList<Integer> thisInfo = info[infoIndex];
            for (int j = 1; j < thisInfo.size(); j++) {
                int prev = thisInfo.get(j - 1);
                int curr = thisInfo.get(j);
                connector[prev].remove(new Edge(curr, infoIndex * 1000000L + j));
                if (!visited[prev]) {
                    inDeg[curr]--;
                    if (inDeg[curr] == 0)
                        tails.offer(curr);
                }
            }
            infoIndex--;
        }
        // System.out.println(infoIndex);
        inDeg = new int[numNode];
        for (int i = 0; i <= infoIndex; i++) {// what if infoIndex<0?
            ArrayList<Integer> thisInfo = info[i];
            for (int j = 1; j < thisInfo.size(); j++) {
                inDeg[thisInfo.get(j)]++;
            }
        } // recreating indeg info

        PriorityQueue<Integer> sortedTails = new PriorityQueue<>();
        for (int i = 0; i < numNode; i++) {
            if (inDeg[i] == 0)
                sortedTails.offer(i);
        }
        int index = 0;
        int[] sortedArr = new int[numNode];
        while (!sortedTails.isEmpty()) {
            int curr = sortedTails.poll();
            sortedArr[index] = curr;
            index++;
            for (Edge next : connector[curr]) {
                inDeg[next.to]--;
                if (inDeg[next.to] == 0)
                    sortedTails.offer(next.to);
            }
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < sortedArr.length; i++) {
            str.append(sortedArr[i] + 1);
            str.append(" ");
        }
        str.deleteCharAt(str.length() - 1);
        out.println(str);
        out.close();
    }
}
