import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CF724Div2A {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			int[] arr = new int[300];
			Arrays.fill(arr, Integer.MAX_VALUE);
			StringTokenizer st = new StringTokenizer(f.readLine());
			HashSet<Integer> container = new HashSet<>();
			for (int j = 0; j < n; j++) {
				arr[j]=Integer.parseInt(st.nextToken());
				container.add(arr[j]);
			}
			boolean success=true;
			for (int j = 1; j < 300; j++) {
				if(!success || j==n)
					break;
				for (int j2 = 0; j2 < j; j2++) {
					int diff = Math.abs(arr[j2]-arr[j]);
					if(container.contains(diff))
						continue;
					if(n>=300) {
						success=false;
						break;
					}
					container.add(diff);
					arr[n]=diff;
					n++;
				}
			}
			if(success) {
				System.out.println("YES");
				System.out.println(n);
				for (int j = 0; j < n-1; j++) {
					System.out.print(arr[j]+" ");
				}
				System.out.println(arr[n-1]);
			}else {
				System.out.println("NO");
			}
			
		}
	}
}
