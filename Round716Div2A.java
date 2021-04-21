import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Round716Div2A {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			int[] arr = new int[n];
			StringTokenizer st = new StringTokenizer(f.readLine());
			for (int j = 0; j < n; j++) {
				arr[j]=Integer.parseInt(st.nextToken());
			}
			boolean hasNonSquare = false;
			for (int j = 0; j < n; j++) {
				int s = (int) Math.round(Math.sqrt(arr[j]));
				if(s*s!=arr[j])
					hasNonSquare=true;
			}
			if(hasNonSquare)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}
