import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5200 {
	static int[] bit;
	static int numCow;
	private static int lowbit(int x) {
		return x&(-x);
	}
	private static int getSum(int index) {
		int sum=0;
		for(int i=index;i>0;i-=lowbit(i))
			sum+=bit[i];
		return sum;
	}
	private static void update(int index, int value) {
		for(int i=index; i<=numCow; i+=lowbit(i))
			bit[i]+=value;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//we are using prefix to do suffix so reverse the indices
		numCow = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] allCows = new int[numCow+1];
		for(int i=1;i<=numCow;i++)
			allCows[i] = Integer.parseInt(st.nextToken());
		bit = new int[numCow+1];
		int prev = Integer.MAX_VALUE;
		int maxMove = numCow-1;
		int index;
		for(index=numCow;index>=1;index--) {
			if(allCows[index]>prev)
				break;
			prev=allCows[index];
			update(allCows[index],1);
		}
		StringBuilder result = new StringBuilder();
		for(int i=1;i<=index;i++) {
			int numBigger = getSum(numCow)-getSum(allCows[i]);
			result.append(maxMove-numBigger);
			result.append(' ');
			update(allCows[i],1);
		}
		result.deleteCharAt(result.length()-1);
		result.append('\n');
		System.out.println(index);
		System.out.print(result);
		
	}
}
