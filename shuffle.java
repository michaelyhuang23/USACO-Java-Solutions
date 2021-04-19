import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class shuffle {
    static class Node {
        int child, parentCount;

        public Node(int ch) {
            child = ch;
            parentCount = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
        int numNode = Integer.parseInt(f.readLine());
        Node[] allNodes = new Node[numNode];
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i = 0; i < numNode; i++) {
            int child = Integer.parseInt(st.nextToken()) - 1;
            Node thisNode = new Node(child);
            allNodes[i] = thisNode;
        }
        for (int i = 0; i < numNode; i++)
            allNodes[allNodes[i].child].parentCount++;
        boolean[] visited = new boolean[numNode];
        Queue<Integer> tails = new ArrayDeque<>();
        for (int i = 0; i < numNode; i++)
            if (allNodes[i].parentCount == 0)
                tails.offer(i);
        while (!tails.isEmpty()) {
            int id = tails.poll();
            visited[id] = true;
            allNodes[allNodes[id].child].parentCount--;
            if (allNodes[allNodes[id].child].parentCount == 0)
                tails.offer(allNodes[id].child);
        }
        int counter = 0;
        for (int i = 0; i < numNode; i++)
            if (!visited[i])
                counter++;
        out.println(counter);
        out.close();
    }
}
