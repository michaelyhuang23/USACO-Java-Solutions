import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Round714Div2A {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			if(n-k<k+1) {
				System.out.println(-1);
				continue;
			}
			int s = 0;
			int l = 0;
			int[] results = new int[n];
			for (int j = 0; j < n; j++) {
				if(l<k) {
					if(j%2==1) {
						results[j]=n-l;
						l++;
					}else {
						results[j]=s+1;
						s++;
					}
				}else {
					results[j]=s+1;
					s++;
				}
			}
			for (int j = 0; j < results.length-1; j++) {
				System.out.print(results[j]+" ");
			}
			System.out.println(results[n-1]);
		}
	}
}
