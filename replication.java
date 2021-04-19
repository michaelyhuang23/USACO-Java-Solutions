import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class replication {
    static class State {
        int r, c;
        int time; // should be %D
        int size;
        int startID;

        public State(int r, int c, int time, int size, int startID) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.size = size;
            this.startID = startID;
        }
    }

    static final int[][] DIR = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int length = Integer.parseInt(st.nextToken());
        int growthTime = Integer.parseInt(st.nextToken());
        // char[][] map = new char[size][size];
        boolean[][] blocked = new boolean[length][length];
        Queue<State> frontier = new ArrayDeque<>();
        HashSet<Integer>[][] stamped = new HashSet[length][length];
        boolean[][] visited = new boolean[length][length];
        int startCount = 0;
        for (int r = 0; r < length; r++) {
            String input = f.readLine();
            for (int c = 0; c < length; c++) {
                stamped[r][c] = new HashSet<>();
                // map[r][c] = input.charAt(c);
                blocked[r][c] = input.charAt(c) == '#' ? true : false;
                if (input.charAt(c) == 'S') {
                    frontier.offer(new State(r, c, 0, 0, startCount));
                    visited[r][c] = true;
                    stamped[r][c].add(startCount);
                    startCount++;
                }
            }
        }
        while (!frontier.isEmpty()) {
            State curr = frontier.poll();
            if (curr.time == growthTime) {
                curr.time = 0;
                curr.size++;
                boolean block = false;
                for (int r = curr.r - curr.size; r <= curr.r + curr.size; r++) {
                    int deltaC = curr.size - Math.abs(r - curr.r);
                    int c1 = curr.c + deltaC;
                    int c2 = curr.c - deltaC;
                    if (blocked[r][c1] || blocked[r][c2]) {
                        block = true;
                        break;
                    }
                }
                if (block)
                    continue;
                else {
                    for (int r = curr.r - curr.size; r <= curr.r + curr.size; r++) {
                        int deltaC = curr.size - Math.abs(r - curr.r);
                        int c1 = curr.c + deltaC;
                        int c2 = curr.c - deltaC;
                        stamped[r][c1].add(curr.startID);
                        stamped[r][c2].add(curr.startID);
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                int newR = curr.r + DIR[i][0];
                int newC = curr.c + DIR[i][1];
                int newTime = curr.time + 1;

                boolean block = false;
                for (int r = newR - curr.size; r <= newR + curr.size; r++) {
                    int deltaC = curr.size - Math.abs(r - newR);
                    int c1 = newC + deltaC;
                    int c2 = newC - deltaC;
                    if (blocked[r][c1] || blocked[r][c2]) {
                        block = true;
                        break;
                    }
                }
                if (block)
                    continue;
                else {
                    for (int r = newR - curr.size; r <= newR + curr.size; r++) {
                        int deltaC = curr.size - Math.abs(r - newR);
                        int c1 = newC + deltaC;
                        int c2 = newC - deltaC;
                        stamped[r][c1].add(curr.startID);
                        stamped[r][c2].add(curr.startID);
                    }
                }

                if (visited[newR][newC] || stamped[newR][newC].size() > 1)
                    continue;
                visited[newR][newC] = true;
                frontier.offer(new State(newR, newC, newTime, curr.size, curr.startID));
            }
        }
        int count = 0;
        for (int r = 0; r < length; r++) {
            for (int c = 0; c < length; c++) {
                if (stamped[r][c].size() > 0)
                    count++;
            }
        }
        System.out.println(count);
    }
}
