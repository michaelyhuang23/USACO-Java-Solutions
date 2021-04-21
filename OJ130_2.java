import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class OJ130_2 {
    static class SegmentTree {
        long[] tree;
        int[] segmentLeft;
        int[] segmentRight; // not inclusive
        long[] latent;

        public SegmentTree(int[] arr) {
            tree = new long[arr.length * 4];
            segmentLeft = new int[arr.length * 4];
            segmentRight = new int[arr.length * 4];
            latent = new long[arr.length * 4];
            createTree(arr, 0, arr.length, 1);
        }

        public long createTree(int[] arr, int left, int right, int index) {
            segmentLeft[index] = left;
            segmentRight[index] = right;
            if (left + 1 == right) {
                tree[index] = arr[left];
                return arr[left];
            }
            long sum = 0; // modify this if you want to store something else.
            int mid = (left + right) >> 1;
            sum += createTree(arr, left, mid, index << 1);
            sum += createTree(arr, mid, right, (index << 1) + 1);
            tree[index] = sum;
            return sum;
        }

        public long getRange(int index, int left, int right) {
            if (segmentLeft[index] >= left && segmentRight[index] <= right)
                return tree[index];
            int mid = (segmentLeft[index] + segmentRight[index]) >> 1;
            long sum = 0;
            if (left < mid)
                sum += getRange(index * 2, left, right);
            if (right > mid)
                sum += getRange(index * 2 + 1, left, right);
            return sum;
        }

        public long update(int index, int left, int right, int change) {
            if (segmentLeft[index] >= left && segmentRight[index] <= right) {
                long realChange = (latent[index] + change) * (segmentRight[index] - segmentLeft[index]);
                tree[index] += realChange;
                if (index * 2 + 1 < tree.length) {
                    latent[index * 2] += change;
                    latent[index * 2 + 1] += change;
                }
                return realChange;
            }
            int mid = (segmentLeft[index] + segmentRight[index]) >> 1;
            latent[index * 2] += latent[index];
            latent[index * 2 + 1] += latent[index];
            latent[index] = 0;
            long sum = 0;
            if (left < mid)
                sum += update(index * 2, left, right, change);
            if (right > mid)
                sum += update(index * 2 + 1, left, right, change);
            tree[index] += sum;
            return sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("/Users/michaelhyh/Downloads/130_1.in"));
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter("/Users/michaelhyh/Downloads/OJ130_2.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int length = Integer.parseInt(st.nextToken());
        int numQuery = Integer.parseInt(st.nextToken());
        int[] arr = new int[length];
        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < length; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        SegmentTree tree = new SegmentTree(arr);
        for (int i = 0; i < numQuery; i++) {
            st = new StringTokenizer(f.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int index = Integer.parseInt(st.nextToken()) - 1;
                int value = Integer.parseInt(st.nextToken());
                tree.update(1, index, index + 1, value);
            } else {
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;
                out.println(tree.getRange(1, left, right + 1));
            }
        }
        out.close();
    }
}
