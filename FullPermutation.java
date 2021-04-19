import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FullPermutation {
	static int[] arr;
	static int N;
	static boolean[] usedNumber;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		usedNumber = new boolean[N+1];
		permute(0);
	}
	
	public static void permute(int n) {
		if(n==N) {
			for(int i=0;i<N;i++)
				System.out.print(arr[i]+" ");
			System.out.println();
			return;
		}
		
		for(int number = 1; number<=N;number++) {
			if(usedNumber[number])
				continue;
			arr[n]=number;
			usedNumber[number]=true;
			permute(n+1);
			usedNumber[number]=false;
		}
		
	}
}
