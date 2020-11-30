import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P3608 {
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
	static class Cow implements Comparable<Cow>{
		int origPos,sortedPos, height;
		public Cow(int o, int h) {
			origPos = o;
			height = h;
		}
		@Override
		public int compareTo(Cow o) {
			return o.height - height;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		numCow = Integer.parseInt(f.readLine());
		Cow[] cowHeight = new Cow[numCow+1];
		Cow[] cowLine = new Cow[numCow+1];
		for(int i=1;i<=numCow;i++)
			cowHeight[i]=new Cow(i,Integer.parseInt(f.readLine()));
		cowLine = cowHeight.clone();
		Arrays.sort(cowHeight,1,numCow+1);
		bit = new int[numCow+1];
		for(int i=1;i<=numCow;i++)
			cowHeight[i].sortedPos=i;
		int cowCount = 0;
		for(int i=1;i<=numCow;i++) {
			int totalHigher = cowLine[i].sortedPos-1;
			int leftHigher = getSum(cowLine[i].sortedPos-1);
			update(cowLine[i].sortedPos,1);
			if(leftHigher*3<totalHigher || leftHigher*3>totalHigher*2)
				cowCount++;
		}
		System.out.println(cowCount);
		
	}
}
