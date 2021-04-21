import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OJ10115 {
	static int[] bitL;
	static int[] bitR;
	static int length, numQuery;
	private static int lowbit(int x) {
		return x&(-x);
	}
	private static int getSum(int index, int[] bit) {
		int sum=0;
		for(int i=index;i>0;i-=lowbit(i))
			sum+=bit[i];
		return sum;
	}
	private static void update(int index, int value, int[] bit) {
		for(int i=index; i<=length; i+=lowbit(i))
			bit[i]+=value;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		length = Integer.parseInt(st.nextToken());
		numQuery = Integer.parseInt(st.nextToken());
		bitL = new int[length+1];
		bitR = new int[length+1];
		
		for(int i=0;i<numQuery;i++) {
			st = new StringTokenizer(f.readLine());
			if(st.nextToken().charAt(0)=='1') {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				update(left, 1, bitL);
				update(right, 1, bitR);
			}else {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				System.out.println(getSum(right, bitL)-getSum(left-1, bitR));
			}
		}
	}
}
