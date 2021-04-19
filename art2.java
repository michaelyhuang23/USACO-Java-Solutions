import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;

public class art2 {
    public static void main(String[] args) throws IOException {
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader f = new BufferedReader(new FileReader("art2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("art2.out")));
        int length = Integer.parseInt(f.readLine()) + 2;
        int[] seq = new int[length];
        for (int i = 1; i < length - 1; i++) {
            int color = Integer.parseInt(f.readLine());
            seq[i] = color;
        }
        int[] towerHeightOn = new int[length - 1];
        boolean[] inStack = new boolean[length - 1];
        boolean[] used = new boolean[length - 1];
        Stack<Integer> tracker = new Stack<>();
        int prevColor = 0;
        boolean success = true;
        for (int i = 0; i < length; i++) {
            if (used[seq[i]])
                success = false;
            if (seq[i] != prevColor) {
                tracker.push(prevColor);
                inStack[prevColor] = true;
                if (inStack[seq[i]]) {
                    int top = 0;
                    int maxHeight = 0;
                    do {
                        top = tracker.pop();
                        inStack[top] = false;
                        if (top != seq[i]) {
                            used[top] = true;
                            maxHeight = Math.max(maxHeight, towerHeightOn[top]);
                        }
                    } while (top != seq[i]);
                    towerHeightOn[seq[i]] = Math.max(towerHeightOn[seq[i]], maxHeight + 1);
                }
                prevColor = seq[i];
            }
        }
        if (success)
            out.println(towerHeightOn[0]);
        else
            out.println(-1);
        out.close();
    }
}
