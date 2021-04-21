import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class OJ132 {
    static class SegmentTree {
        long[] tree;
        int[] segmentLeft;
        int[] segmentRight; // not inclusive
        long[] clatent;

        public SegmentTree(int[] arr) {
            tree = new long[arr.length * 4];
            segmentLeft = new int[arr.length * 4];
            segmentRight = new int[arr.length * 4];
            clatent = new long[arr.length * 4];
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
            sum += createTree(arr, left, mid, index * 2);
            sum += createTree(arr, mid, right, index * 2 + 1);
            tree[index] = sum;
            return sum;
        }

        public long getRange(int index, int left, int right) {

            if (segmentLeft[index] >= left && segmentRight[index] <= right)
                return tree[index];
            pushDown(index);
            int mid = (segmentLeft[index] + segmentRight[index]) >> 1;
            long sum = 0;
            if (left < mid)
                sum += getRange(index * 2, left, right);
            if (right > mid)
                sum += getRange(index * 2 + 1, left, right);
            return sum;
        }

        private void pushDown(int index) {
            clatent[index * 2] += clatent[index];
            clatent[index * 2 + 1] += clatent[index];
            tree[index * 2] += clatent[index] * (segmentRight[index * 2] - segmentLeft[index * 2]);
            tree[index * 2 + 1] += clatent[index] * (segmentRight[index * 2 + 1] - segmentLeft[index * 2 + 1]);
            clatent[index] = 0;
        }

        public void update(int index, int left, int right, long change) {
            if (segmentLeft[index] >= left && segmentRight[index] <= right) {
                clatent[index] += change;
                tree[index] += change * (segmentRight[index] - segmentLeft[index]);
                return;
            }
            pushDown(index);
            int mid = (segmentLeft[index] + segmentRight[index]) >> 1;
            if (left < mid)
                update(index * 2, left, right, change);
            if (right > mid)
                update(index * 2 + 1, left, right, change);
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("/Users/michaelhyh/Downloads/2.in"));
        // new FileReader("/Users/michaelhyh/Downloads/maxnumber2.in")
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/michaelhyh/Downloads/2.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int length = Integer.parseInt(st.nextToken());
        int numQuery = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        SegmentTree tree = new SegmentTree(arr);
        for (int i = 0; i < numQuery; i++) {
            st = new StringTokenizer(f.readLine());
            if (Integer.parseInt(st.nextToken()) == 1) {
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;
                int change = Integer.parseInt(st.nextToken());
                tree.update(1, left, right + 1, change);
            } else {
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;
                out.println(tree.getRange(1, left, right + 1));
            }
        }
        out.close();
    }
}
