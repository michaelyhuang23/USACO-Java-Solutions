import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Round717Div2D {
	static int[] maxDiv;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		maxDiv = new int[100001];
		Arrays.fill(maxDiv, 1);
		for (int i = 2; i < maxDiv.length; i++) {
			if(maxDiv[i]==1) {
				for (int j = 1; j*i < maxDiv.length; j++) {
					maxDiv[j*i]=i;
				}
			}
		}
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] next = new int[n];
		HashSet<Integer> tracker = new HashSet<>();
		int j;
		for (j = 0; j < n; j++) {
			if (containPrime(tracker,arr[j])) {
				break;
			}
			addPrime(tracker, arr[j]);
		}
		next[0]=j;
		rmPrime(tracker, arr[0]);
		for (int i = 1; i < n; i++) {
			for (j = next[i - 1]; j < n; j++) {
				if (containPrime(tracker,arr[j])) {
					break;
				}
				addPrime(tracker,arr[j]);
			}
			next[i] = j;
			rmPrime(tracker, arr[i]);
		}
		
		jumps = new int[n+1][20];
		for (int i = 0; i < n; i++) {
			jumps[i][0]=next[i];
		}
		jumps[n][0]=n;
		for (int i = 1; i < 20; i++) {
			for (int j1 = 0; j1 < n; j1++) {
				jumps[j1][i]=jumps[jumps[j1][i-1]][i-1];
			}
		}
		
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(f.readLine());
			int l = Integer.parseInt(st.nextToken())-1;
			int r = Integer.parseInt(st.nextToken())-1;
			int lbound = 1, rbound = r-l+1;
			int ans=lbound;
			while(lbound<=rbound) {
				int mid = (lbound+rbound)/2;
				if(check(mid,l,r)) {ans=mid; rbound=mid-1;}
				else lbound=mid+1;
			}
			System.out.println(ans);
		}
		
	}
	private static boolean containPrime(HashSet<Integer> tracker, int num) {
		while(num>1) {
			int div = maxDiv[num];
			num/=div;
			if(tracker.contains(div))
				return true;
		}
		return false;
	}
	
	private static void addPrime(HashSet<Integer> tracker, int num) {
		while(num>1) {
			int div = maxDiv[num];
			num/=div;
			tracker.add(div);
		}
	}
	
	private static void rmPrime(HashSet<Integer> tracker, int num) {
		while(num>1) {
			int div = maxDiv[num];
			num/=div;
			tracker.remove(div);
		}
	}
	static int[][] jumps;
	private static boolean check(int intervNum, int l, int r) {
		int cur = l;
		for (int i = 0; i < 20; i++) {
			if(((1<<i) & intervNum)>0) {
				cur = jumps[cur][i];
			}
		}
		return cur>r;
	}
}
