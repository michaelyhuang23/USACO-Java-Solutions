import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1028 {
	static int[] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int starter = Integer.parseInt(br.readLine());
		memo = new int[starter+1];
		System.out.println(process(starter));
	}
	public static int process(int previousNum) {
		if(memo[previousNum]!=0)
			return memo[previousNum];
		int count=1;
		for(int i=1;i*2<=previousNum;i++) {
			count+=process(i);
		}
		memo[previousNum]=count;
		return count;
	}
}
