import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class fenceplan {
    static int numNode, numEdge;
    static int[] posX, posY;
    static ArrayList<Integer>[] connector;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("fenceplan.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        numNode = Integer.parseInt(st.nextToken());
        numEdge = Integer.parseInt(st.nextToken());
        posX = new int[numNode];
        posY = new int[numNode];
        connector = new ArrayList[numNode];
        for (int i = 0; i < numNode; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            posX[i] = x;
            posY[i] = y;
            connector[i] = new ArrayList<>();
        }
        for (int i = 0; i < numEdge; i++) {
            st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            connector[first].add(second);
            connector[second].add(first);
        }
        visited = new boolean[numNode];
        long lowPeri = Long.MAX_VALUE / 2;
        for (int i = 0; i < numNode; i++) {
            if (visited[i])
                continue;
            lowX = Integer.MAX_VALUE / 2;
            lowY = Integer.MAX_VALUE / 2;
            highX = 0;
            highY = 0;
            dfs(i);
            long peri = ((highX - lowX) + (long) (highY - lowY)) * 2;
            lowPeri = Math.min(lowPeri, peri);
        }

        out.println(lowPeri);
        out.close();
    }

    static int lowX, highX, lowY, highY;

    private static void dfs(int index) {
        lowX = Math.min(lowX, posX[index]);
        highX = Math.max(highX, posX[index]);
        lowY = Math.min(lowY, posY[index]);
        highY = Math.max(highY, posY[index]);
        for (int next : connector[index]) {
            if (visited[next])
                continue;
            visited[next] = true;
            dfs(next);
        }
    }
}
