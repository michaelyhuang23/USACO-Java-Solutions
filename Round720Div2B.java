import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Round720Div2B {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			StringTokenizer st = new StringTokenizer(f.readLine());
			int[] arr = new int[n];
			for (int j = 0; j < n; j++) {
				arr[j]=Integer.parseInt(st.nextToken());
			}
			int min = Integer.MAX_VALUE;
			int minI = 0;
			for (int j = 0; j < arr.length; j++) {
				if(arr[j]<min) {
					min = arr[j];
					minI = j;
				}
			}
			System.out.println(n-1);
			for (int j = 0; j < arr.length; j++) {
				if(j==minI)
					continue;
				int diff = Math.abs(j-minI);
				System.out.println((minI+1)+" "+(j+1)+" "+min+" "+(min+diff));
			}
		}
	}
}
