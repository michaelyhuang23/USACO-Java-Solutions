import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class dishes {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dishes.out")));
        int numDish = Integer.parseInt(f.readLine());
        int[] dishes = new int[numDish];
        int prevMax = 0;
        ArrayList<Integer> stacks = new ArrayList<>();
        for (int i = 0; i < numDish; i++) {
            int order = Integer.parseInt(f.readLine());
            dishes[i] = order;
            if (order > prevMax) {
                prevMax = order;
                stacks.add(order);
            }
        }

        ArrayList<ArrayList<Integer>> arrByStack = new ArrayList<>();
        for (int i = 0; i < stacks.size(); i++) {
            arrByStack.add(new ArrayList<>());
        }
        for (int i = 0; i < numDish; i++) {
            int index = Collections.binarySearch(stacks, dishes[i]);
            if (index >= 0)
                continue;
            index = -index - 1;
            arrByStack.get(index).add(dishes[i]);
        }
        int sum = 0;
        for (int i = 0; i < stacks.size(); i++) {
            ArrayList<Integer> stack = arrByStack.get(i);
            sum += increSubseq(stack) + 1;
        }
        System.out.println(sum);
        out.close();
    }

    private static int increSubseq(ArrayList<Integer> stack) {
        for (int i = 0; i < stack.size() / 2; i++) {
            int first = stack.get(i);
            int second = stack.get(stack.size() - i - 1);
            stack.set(i, second);
            stack.set(stack.size() - i - 1, first);
        }
        ArrayList<ArrayList<Integer>> stacks = new ArrayList<>();
        for (int item : stack) {
            boolean added = false;
            for (int i = 0; i < stacks.size(); i++) {
                ArrayList<Integer> sta = stacks.get(i);
                if (sta.get(sta.size() - 1) > item) {
                    sta.add(item);
                    added = true;
                    break;
                }
            }
            if (!added) {
                ArrayList<Integer> newSta = new ArrayList<>();
                newSta.add(item);
                stacks.add(newSta);
            }
        }
        return stacks.size();

    }
}
