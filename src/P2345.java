import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class P2345 {
	static long[] cowCount;
	static long[] cowSum;
	static int numCow;
	private static int lowbit(int x) {
		return x&(-x);
	}
	private static long getSum(int index, long[] bit) {
		long sum=0;
		for(int i=index;i>0;i-=lowbit(i))
			sum+=bit[i];
		return sum;
	}
	private static void update(int index, int value, long[] bit) {
		for(int i=index; i<=numCow; i+=lowbit(i))
			bit[i]+=value;
	}
	static class Cow implements Comparable<Cow>{
		int volume, pos, rank;
		public Cow(int v, int p) {
			volume = v;
			pos = p;
		}
		@Override
		public int compareTo(Cow o) {
			return volume - o.volume;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		numCow = Integer.parseInt(f.readLine());
		Cow[] allCows = new Cow[numCow+1];
		for(int i=1;i<=numCow;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int volume = Integer.parseInt(st.nextToken());
			int pos = Integer.parseInt(st.nextToken());
			allCows[i] = new Cow(volume, pos);
		}
		Arrays.sort(allCows, 1, numCow+1, new Comparator<Cow>() {
			public int compare(Cow o1, Cow o2) {
				return o1.pos-o2.pos;
			}
		});
		for(int i=1;i<=numCow;i++)
			allCows[i].rank=i;
		Arrays.sort(allCows,1,numCow+1);
		cowCount = new long[numCow+1];
		cowSum = new long[numCow+1];
		long totalVolume = 0;
		for(int i=1;i<=numCow;i++) {
			long rightCount = getSum(numCow,cowCount)-getSum(allCows[i].rank,cowCount);
			long leftCount = getSum(allCows[i].rank-1,cowCount);
			long rightTotal = getSum(numCow, cowSum)-getSum(allCows[i].rank, cowSum);
			long leftTotal = getSum(allCows[i].rank-1, cowSum);
			totalVolume+=(leftCount*allCows[i].pos-leftTotal)*allCows[i].volume;
			totalVolume+=(rightTotal-rightCount*allCows[i].pos)*allCows[i].volume;
			update(allCows[i].rank,1,cowCount);
			update(allCows[i].rank,allCows[i].pos,cowSum);
		}
		System.out.println(totalVolume);
		
	}
}
