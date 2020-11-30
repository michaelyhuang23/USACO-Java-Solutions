import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class OJ10127 {
    static class SegmentTree {
        int[] tree;
        int[] segmentLeft;
        int[] segmentRight; // not inclusive
        int[] latent;

        public SegmentTree(int[] arr) {
            tree = new int[arr.length * 4];
            segmentLeft = new int[arr.length * 4];
            segmentRight = new int[arr.length * 4];
            latent = new int[arr.length * 4];
            createTree(arr, 0, arr.length, 1);
        }

        public int createTree(int[] arr, int left, int right, int index) {
            segmentLeft[index] = left;
            segmentRight[index] = right;
            if (left + 1 == right) {
                tree[index] = arr[left];
                return arr[left];
            }
            int max = 0; // modify this if you want to store something else.
            int mid = (left + right) >> 1;
            max = Math.max(max, createTree(arr, left, mid, index << 1));
            max = Math.max(max, createTree(arr, mid, right, (index << 1) + 1));
            tree[index] = max;
            return max;
        }

        public int getRange(int index, int left, int right) {
            if (segmentLeft[index] >= left && segmentRight[index] <= right)
                return tree[index];
            int mid = (segmentLeft[index] + segmentRight[index]) >> 1;
            int max = 0;
            if (left < mid)
                max = Math.max(max, getRange(index * 2, left, right));
            if (right > mid)
                max = Math.max(max, getRange(index * 2 + 1, left, right));
            return max;
        }

        public int update(int index, int left, int right, int change) {
            if (segmentLeft[index] >= left && segmentRight[index] <= right) {
                int realChange = (latent[index] + change) * (segmentRight[index] - segmentLeft[index]);
                tree[index] += realChange;
                if (index * 2 + 1 < tree.length) {
                    latent[index * 2] += change;
                    latent[index * 2 + 1] += change;
                }
                return tree[index];
            }
            int mid = (segmentLeft[index] + segmentRight[index]) >> 1;
            latent[index * 2] += latent[index];
            latent[index * 2 + 1] += latent[index];
            latent[index] = 0;
            int max = tree[index];
            if (left < mid)
                max = Math.max(max, update(index * 2, left, right, change));
            if (right > mid)
                max = Math.max(max, update(index * 2 + 1, left, right, change));
            tree[index] = max;
            return max;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("/Users/michaelhyh/Downloads/maxnumber2.in"));
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter("/Users/michaelhyh/Downloads/OJ10127.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numQuery = Integer.parseInt(st.nextToken());
        int mod = Integer.parseInt(st.nextToken());
        int lastQuery = 0;
        SegmentTree tree = new SegmentTree(new int[numQuery]);
        int index = 0;
        for (int i = 0; i < numQuery; i++) {
            st = new StringTokenizer(f.readLine());
            if (st.nextToken().charAt(0) == 'A') {
                int t = Integer.parseInt(st.nextToken());
                t = (t + lastQuery) % mod;
                tree.update(1, index, index + 1, t);
                index++;
            } else {
                int L = Integer.parseInt(st.nextToken());
                lastQuery = tree.getRange(1, index - L, index);
                out.println(lastQuery);
            }
        }
        out.close();
    }
}
