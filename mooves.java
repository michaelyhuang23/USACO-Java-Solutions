import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class mooves {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numCow = Integer.parseInt(st.nextToken());
        int numMove = Integer.parseInt(st.nextToken());
        long totalMove = Integer.parseInt(st.nextToken());
        long numRounds = totalMove / numMove;
        int leftMove = (int) totalMove % numMove;
        int[] arr = new int[numCow];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        HashSet<Integer>[] tracker = new HashSet[numCow];
        HashSet<Integer>[] leftTracker = new HashSet[numCow];
        for (int i = 0; i < tracker.length; i++) {
            tracker[i] = new HashSet<>();
            tracker[i].add(i);
        }
        for (int i = 0; i < numMove; i++) {
            st = new StringTokenizer(f.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            int temp = arr[first];
            arr[first] = arr[second];
            arr[second] = temp;
            tracker[arr[second]].add(second);
            tracker[arr[first]].add(first);
            if (i + 1 == leftMove) {
                for (int j = 0; j < numCow; j++) {
                    leftTracker[j] = (HashSet<Integer>) tracker[j].clone();
                }
            }
        }
        int[] operation = new int[numCow];
        for (int i = 0; i < numCow; i++) {
            operation[arr[i]] = i;
        }

        // visited array to track
        boolean[] visited = new boolean[numCow];
        int[] ans = new int[numCow];
        for (int startPos = 0; startPos < numCow; startPos++) {
            if (visited[startPos])
                continue;
            int step = 0;
            int next = startPos;
            HashMap<Integer, Integer> bigTracker = new HashMap<>();
            for (next = startPos; step < numRounds && (step == 0 || next != startPos); next = operation[next]) {
                for (int trav : tracker[next]) {
                    if (!bigTracker.containsKey(trav))
                        bigTracker.put(trav, 1);
                    else
                        bigTracker.put(trav, bigTracker.get(trav) + 1);
                }
                step++;
            }
            while (!visited[startPos]) {
                visited[startPos] = true;

                for (int trav : leftTracker[next]) {
                    if (!bigTracker.containsKey(trav))
                        bigTracker.put(trav, 1);
                    else
                        bigTracker.put(trav, bigTracker.get(trav) + 1);
                }
                ans[startPos] = bigTracker.size();
                for (int trav : leftTracker[next]) {
                    bigTracker.put(trav, bigTracker.get(trav) - 1);// it should be contained
                    if (bigTracker.get(trav) == 0)
                        bigTracker.remove(trav);
                }

                for (int trav : tracker[startPos]) {
                    bigTracker.put(trav, bigTracker.get(trav) - 1);// it should be contained
                    if (bigTracker.get(trav) == 0)
                        bigTracker.remove(trav);
                }
                startPos = operation[startPos];
                for (int trav : tracker[next]) {
                    if (!bigTracker.containsKey(trav))
                        bigTracker.put(trav, 1);
                    else
                        bigTracker.put(trav, bigTracker.get(trav) + 1);
                }
                next = operation[next];
            }

        }

        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }

    }
}
