import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CFRound108Div2C {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
//		int[] maxPrimeDiv = new int[2*100000+1];
//		Arrays.fill(maxPrimeDiv, 1);
//		for (int i = 2; i < maxPrimeDiv.length; i++) {
//			if(maxPrimeDiv[i]==1) {
//				for (int j = 1; j*i < maxPrimeDiv.length; j++) {
//					maxPrimeDiv[j*i]=i;
//				}
//			}
//		}
		
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			ArrayList<Integer>[] unis = new ArrayList[n];
			StringTokenizer st = new StringTokenizer(f.readLine());
			for (int j = 0; j < unis.length; j++) {
				unis[j]=new ArrayList<>();
			}
			int[] stu2uni = new int[n];
			for (int j = 0; j < n; j++) {
				int stu = Integer.parseInt(st.nextToken())-1;
				stu2uni[j]=stu;
			}
			
			long total = 0;
			st = new StringTokenizer(f.readLine());
			for (int j = 0; j < n; j++) {
				int score = Integer.parseInt(st.nextToken());
				unis[stu2uni[j]].add(score);
				total+=score;
			}
			
			stu2uni=null;
			
			for (int j = 0; j < n; j++) {
				unis[j].sort(null);
			}
			
			long[] kSub = new long[n+1];
			long[] kPref = new long[n+1];
			
			for (int uni = 0; uni < n; uni++) {
				long preSum = 0;
				for (int stuI=0; stuI<unis[uni].size(); stuI++) {
					preSum+=unis[uni].get(stuI);
					int chosen = unis[uni].size()-(stuI+1);
					for (int j = 1; j*j <= chosen; j++) {
						if(chosen%j==0) {
							if(j>=stuI+1+1)
								kSub[j]+=preSum;
							if(j!=chosen/j && chosen/j>=stuI+1+1)
								kSub[chosen/j]+=preSum;
						}
					}
				}
				if(unis[uni].size()<n)
					kPref[unis[uni].size()+1]+=preSum;
			}
			
			for (int j = 1; j < kPref.length; j++) {
				kPref[j]+=kPref[j-1];
			}
			
			for (int j = 1; j <= n-1; j++) {
				System.out.print(total-kSub[j]-kPref[j]+" ");
			}
			System.out.println(total-kSub[n]-kPref[n]);
			
		}
	}
}
