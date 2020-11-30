import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class P4085 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        int N;
        long M;
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        long[] F = new long[N];
        long[] S = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(f.readLine());
            F[i] = Long.parseLong(st.nextToken());
            S[i] = Long.parseLong(st.nextToken());
        }
        long sumF = 0;
        long ans = Integer.MAX_VALUE;
        TreeMap<Long, Integer> maxS = new TreeMap<>();
        int left = 0;
        for (int right = 0; right < N; right++) {
            sumF += F[right];
            update(maxS, S[right], 1);
            if (sumF < M) {
                continue;
            } else {
                while (sumF - F[left] >= M) {
                    sumF -= F[left];
                    update(maxS, S[left], -1);
                    left++;
                }
                ans = Math.min(ans, maxS.lastKey());
            }
        }
        System.out.println(ans);
    }

    private static void update(TreeMap<Long, Integer> map, long key, int change) {
        if (map.containsKey(key)) {
            int newVal = map.get(key) + change;
            if (newVal != 0)
                map.put(key, newVal);
            else
                map.remove(key);
        } else
            map.put(key, change);
    }
}
