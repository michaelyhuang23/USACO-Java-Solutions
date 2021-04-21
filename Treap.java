import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class Treap<T extends Comparable<? super T>> {

    Random rand;

    class Node {
        Node leftChild, rightChild;
        int priority, nodeCount;
        T val;

        public Node(Node lf, Node rg, T qVal, int count) {
            leftChild = lf;
            rightChild = rg;
            val = qVal;
            nodeCount = count;
            priority = rand.nextInt();
        }
    }

    Node root = null;

    public Treap() {
        rand = new Random();
    }

    public boolean contains(T qVal) {
        return contains(qVal, root);
    }

    public int getIndex(T qVal) {
        return getIndex(qVal, root, 0);
    }

    public void add(T qVal) {
        root = add(qVal, root);
        return;
    }

    public void remove(T qVal) {
        root = remove(qVal, root);
        return;
    }

    public T searchIndex(int index) {
        return searchIndex(index, root);
    }

    public T getPrevious(T qVal) {
        return getPrevious(qVal, root);
    }

    public T getNext(T qVal) {
        return getNext(qVal, root);
    }

    private boolean contains(T qVal, Node son) {
        if (son == null)
            return false;
        int compare = qVal.compareTo(son.val);
        if (compare == 0)
            return true;
        else if (compare < 0)
            return contains(qVal, son.leftChild);
        else
            return contains(qVal, son.rightChild);
    }

    private T getPrevious(T qVal, Node son) {
        if (son == null)
            return null;
        int compare = qVal.compareTo(son.val);
        if (compare == 0)
            return max(son.leftChild);
        else if (compare < 0)
            return getPrevious(qVal, son.leftChild);
        else {
            T rightResult = getPrevious(qVal, son.rightChild);
            if (rightResult == null)
                return son.val;
            return rightResult;
        }
    }

    private T getNext(T qVal, Node son) {
        if (son == null)
            return null;
        int compare = qVal.compareTo(son.val);
        if (compare == 0)
            return min(son.rightChild);
        else if (compare < 0) {
            T leftResult = getNext(qVal, son.leftChild);
            if (leftResult == null)
                return son.val;
            return leftResult;
        } else
            return getNext(qVal, son.rightChild);

    }

    private T max(Node son) { // son shouldn't be null
        if (son == null)
            return null;
        if (son.rightChild == null)
            return son.val;
        return max(son.rightChild);
    }

    private T min(Node son) { // son shouldn't be null
        if (son == null)
            return null;
        if (son.leftChild == null)
            return son.val;
        return min(son.leftChild);
    }

    private int getIndex(T qVal, Node son, int leftCount) {
        if (son == null)
            return leftCount + 1;
        int compare = qVal.compareTo(son.val);
        if (compare == 0)
            return leftCount + getNodeCount(son.leftChild) + 1;
        else if (compare < 0)
            return getIndex(qVal, son.leftChild, leftCount);
        else
            return getIndex(qVal, son.rightChild, leftCount + getNodeCount(son.leftChild) + 1);
    }

    private int getNodeCount(Node son) {
        if (son == null)
            return 0;
        return son.nodeCount;
    }

    private Node turnRight(Node son) {
        Node newSon = son.leftChild;
        Node oldLeftRight = newSon.rightChild;
        son.nodeCount = getNodeCount(oldLeftRight) + getNodeCount(son.rightChild) + 1;
        newSon.nodeCount = getNodeCount(newSon.leftChild) + getNodeCount(son) + 1;
        newSon.rightChild = son;
        son.leftChild = oldLeftRight;
        return newSon;
    }

    private Node turnLeft(Node son) {
        Node newSon = son.rightChild;
        Node oldRightLeft = newSon.leftChild;
        son.nodeCount = getNodeCount(oldRightLeft) + getNodeCount(son.leftChild) + 1;
        newSon.nodeCount = getNodeCount(newSon.rightChild) + getNodeCount(son) + 1;
        newSon.leftChild = son;
        son.rightChild = oldRightLeft;
        return newSon;
    }

    private Node add(T qVal, Node son) {
        if (son == null) {
            return new Node(null, null, qVal, 1);
        }
        int compare = qVal.compareTo(son.val);
        if (compare == 0)
            return son;
        else if (compare < 0) {
            son.leftChild = add(qVal, son.leftChild);
            if (son.leftChild.priority < son.priority)
                son = turnRight(son);
        } else {
            son.rightChild = add(qVal, son.rightChild);
            if (son.rightChild.priority < son.priority)
                son = turnLeft(son);
        }
        son.nodeCount = getNodeCount(son.leftChild) + getNodeCount(son.rightChild) + 1;
        return son;
    }

    private Node remove(T qVal, Node son) {
        if (son == null)
            return null;
        int compare = qVal.compareTo(son.val);
        if (compare == 0) {
            return fixAndRemove(son);
        } else if (compare < 0)
            son.leftChild = remove(qVal, son.leftChild);
        else
            son.rightChild = remove(qVal, son.rightChild);
        son.nodeCount = getNodeCount(son.leftChild) + getNodeCount(son.rightChild) + 1;
        return son;
    }

    private Node fixAndRemove(Node son) {
        if (son.leftChild == null) {
            return son.rightChild;
        }
        if (son.rightChild == null) {
            return son.leftChild;
        }
        if (son.leftChild.priority < son.rightChild.priority) {
            son = turnRight(son);
            son.rightChild = fixAndRemove(son.rightChild);
        } else {
            son = turnLeft(son);
            son.leftChild = fixAndRemove(son.leftChild);
        }
        son.nodeCount = getNodeCount(son.leftChild) + getNodeCount(son.rightChild) + 1;
        return son;
    }

    private T searchIndex(int index, Node son) { // index should start at 1;
        if (son == null)
            return null;
        if (index <= getNodeCount(son.leftChild))
            return searchIndex(index, son.leftChild);
        else if (index == getNodeCount(son.leftChild) + 1)
            return son.val;
        else
            return searchIndex(index - (getNodeCount(son.leftChild) + 1), son.rightChild);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in)); // new FileReader("triangles.in") //new
                                                                                 // InputStreamReader(System.in)
        Treap<Integer> testTreap = new Treap<>();
        testTreap.add(55);
        testTreap.add(89);
        testTreap.add(98);
        testTreap.add(1);
        testTreap.add(3);
        testTreap.add(89);
        testTreap.add(89);
        System.out.println(testTreap.contains(2));
        System.out.println(testTreap.contains(89));
        testTreap.remove(89);
        System.out.println(testTreap.contains(89));
        System.out.println(testTreap.getIndex(98));
        System.out.println(testTreap.getIndex(99));
        System.out.println(testTreap.searchIndex(2));
        System.out.println(testTreap.getPrevious(3));
        System.out.println(testTreap.getPrevious(99));
        System.out.println(testTreap.getPrevious(98));
        System.out.println(testTreap.getNext(98));
        System.out.println(testTreap.getNext(3));
        // int numOpt = Integer.parseInt(f.readLine());
        // for (int i = 0; i < numOpt; i++) {
        // StringTokenizer st = new StringTokenizer(f.readLine());
        // int opt = Integer.parseInt(st.nextToken());
        // int arg = Integer.parseInt(st.nextToken());
        // switch (opt) {
        // case 1:
        // testTreap.add(arg);
        // break;
        // case 2:
        // testTreap.remove(arg);
        // break;
        // case 3:
        // System.out.println(testTreap.getIndex(arg));
        // break;
        // case 4:
        // System.out.println(testTreap.searchIndex(arg));
        // break;
        // case 5:
        // System.out.println(testTreap.getPrevious(arg));
        // break;
        // case 6:
        // System.out.println(testTreap.getNext(arg));
        // break;
        // }
        // }
    }

}
