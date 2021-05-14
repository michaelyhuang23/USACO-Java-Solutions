import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CFE107Div2D {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] pairs = new int[k][k];
		int c = 0;
		ArrayList<Integer> ans = new ArrayList<>();
		ans.add(k-1);
		c++;
		while(c<n) {
			for (int level = 0; level < k; level++) {
				if(c>=n)
					break;
				for (int step = 0; step < (k-level)*2-1; step++) {
					if(c>=n)
						break;
					if(step==0) {
						pairs[k-1][level]++;
						ans.add(level);
					}else if(step==1) {
						pairs[level][level]++;
						ans.add(level);
					}else {
						if(step%2==0) {
							pairs[level][level+step/2]++;
							ans.add(level+step/2);
						}else {
							pairs[level+step/2][level]++;
							ans.add(level);
						}
					}
					c++;
				}
			}
		}
		assert (ans.size()==n);
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		for (int i : ans) {
			System.out.print(alphabet.charAt(i));
		}
		System.out.println();
	}
}
