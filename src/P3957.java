import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class P3957 {
	static int[]  boxPoint,boxPos;
	static int leastPoint, numBox, baseStep;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numBox = Integer.parseInt(st.nextToken());
		baseStep = Integer.parseInt(st.nextToken());
		leastPoint = Integer.parseInt(st.nextToken());
		boxPos = new int[numBox+1];
		boxPoint = new int[numBox+1];
		boxPos[0]=0;
		boxPoint[0]=0;
		for(int i=1;i<=numBox;i++) {
			st = new StringTokenizer(f.readLine());
			boxPos[i] = Integer.parseInt(st.nextToken());
			boxPoint[i] = Integer.parseInt(st.nextToken());
		}
		
		int leftBound = 0, rightBound = boxPos[numBox];
		int currentGoldAns = rightBound;
		while(leftBound<=rightBound) {
			int midGold = (leftBound+rightBound)/2;
			if(check(midGold)) {currentGoldAns = midGold; rightBound = midGold-1;}
			else leftBound = midGold+1;
		}
		if(currentGoldAns==rightBound)
			System.out.println(-1);
		else
			System.out.println(currentGoldAns);
			
	}
	static long[] maxPointTo;
	static Deque<Integer> slider;
	private static boolean check(int gold) {
		maxPointTo = new long[numBox+1];
		slider = new ArrayDeque<>();
		int minStep = Math.max(1, baseStep-gold);
		int maxStep = baseStep+gold;
		Arrays.fill(maxPointTo, Long.MIN_VALUE/2);
		maxPointTo[0]=0;
		for(int i=1;i<=numBox;i++) {
			long prevMax = getMax(boxPos[i]-maxStep,boxPos[i]-minStep,i);
			//System.out.println(prevMax+" "+i+" "+(boxPos[i]-maxStep)+" "+(boxPos[i]-minStep));
			maxPointTo[i] = prevMax+boxPoint[i];
			if(maxPointTo[i]>=leastPoint)
				return true;
		}
		return false;
	}
	private static long getMax(int left, int right, int i) {
		if(i==0) {
			if(left<=0 && right>=0) {
				return 0;
			}
			else {
				return Long.MIN_VALUE/2;
			}
		}
		while(!slider.isEmpty() && boxPos[slider.peekFirst()]<left)
			slider.pollFirst();
		
		int j=0;
		if(slider.isEmpty()) {
			j=Arrays.binarySearch(boxPos, left);
			if(j<0)
				j=-j-1;
		}
		else
			j=slider.peekFirst()+1;
		while(boxPos[j]<=right) {
			if(slider.isEmpty() || maxPointTo[slider.peekLast()]<maxPointTo[j])
				slider.offerLast(j);
			j++;
		}
		if(slider.isEmpty())
			return Long.MIN_VALUE/2;
		return maxPointTo[slider.peekLast()];
	}
}
