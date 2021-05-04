import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Round717Div2B {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			int[] arr = new int[n];
			StringTokenizer st = new StringTokenizer(f.readLine());
			for (int j = 0; j < arr.length; j++) {
				arr[j]=Integer.parseInt(st.nextToken());
			}
			//permute 1 joint
			int front = 0;
			int end = 0;
			for (int j = 0; j < arr.length; j++) {
				end^=arr[j];
			}
			boolean success=false;
			for (int j = 0; j < n-1; j++) {
				front^=arr[j];
				end^=arr[j];
				if(front==end)
					success=true;
			}
			
			front = 0;
			int endf = 0;
			for (int j = 0; j < arr.length; j++) {
				endf^=arr[j];
			}
			int mid = 0;
			for (int j1 = 0; j1 < n-1; j1++) {
				front^=arr[j1];
				end = endf^front;
				mid = 0;
				for (int j2 = j1+1; j2 < n-1; j2++) {
					end^=arr[j2];
					mid^=arr[j2];
					if(front==mid && mid==end)
						success=true;
				}
			}
			if(success)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}
