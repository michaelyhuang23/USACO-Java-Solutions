import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Round715Div2A {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			int[] arr = new int[n];
			StringTokenizer st = new StringTokenizer(f.readLine());
			int evenCount = 0;
			int oddPos = n-1;
			int[] result = new int[n];
			for (int j = 0; j < n; j++) {
				arr[j]=Integer.parseInt(st.nextToken());
				if(arr[j]%2==0) {
					result[evenCount]=arr[j];
					evenCount++;
				}else {
					result[oddPos]=arr[j];
					oddPos--;
				}
			}
			for (int j = 0; j < n-1; j++) {
				System.out.print(result[j]+" ");
			}
			System.out.println(result[n-1]);
			
		}
	}
}
