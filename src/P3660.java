import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P3660 {
	static int[] bit;
	static int numPair;
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
		for(int i=index; i<=numPair*2; i+=lowbit(i))
			bit[i]+=value;
	}
	static class Pair implements Comparable<Pair>{
		int first, second;
		public Pair(int f, int s) {
			first = f;
			second = s;
		}
		@Override
		public int compareTo(Pair o) {
			return first-o.first;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		numPair = Integer.parseInt(f.readLine());
		Pair[] allPairs = new Pair[numPair+1];
		for(int i=1;i<=2*numPair;i++) {
			int index = Integer.parseInt(f.readLine());
			if(allPairs[index]==null) 
				allPairs[index]=new Pair(i,i);
			else
				allPairs[index].second=i;
		}
		Arrays.sort(allPairs,1,numPair+1);
		bit = new int[2*numPair+1];
		long totalPair = 0;
		for(int i=1;i<=numPair;i++) {
			totalPair+=getSum(allPairs[i].second)-getSum(allPairs[i].first);
			//System.out.println(allPairs[i].second+" "+allPairs[i].first);
			update(allPairs[i].second,1);
		}
		System.out.println(totalPair);
	}
}
