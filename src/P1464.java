import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1464 {
	static long[][][] memo;
	static boolean[][][] memoEnabled;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long a,b,c;
		memo = new long[21][21][21];
		memoEnabled=new boolean[21][21][21];
		ArrayList<String> results = new ArrayList<String>();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a=Long.parseLong(st.nextToken());
			b=Long.parseLong(st.nextToken());
			c=Long.parseLong(st.nextToken());
			if(a==-1 && b==-1 && c==-1)
				break;
			long result = compute((int)Math.min(21, Math.max(0, a)),(int)Math.min(21, Math.max(0, b)),(int)Math.min(21, Math.max(0, c)));
			results.add("w("+a+", "+b+", "+c+") = "+result);
		}
		for(String result : results)
			System.out.println(result);
	}
	public static long compute(int a, int b, int c) {
		if(a<=0 || b<=0 || c<=0)
			return 1;
		if(a>20 || b>20 || c>20)
			return compute(20,20,20);
		if(memoEnabled[a][b][c])
			return memo[a][b][c];
		long result;
		if(a<b && b<c) {
			result=compute(a,b,c-1)+compute(a,b-1,c-1)-compute(a,b-1,c);
			memo[a][b][c]=result;
			memoEnabled[a][b][c]=true;
			return result;
		}
		result= compute(a-1,b,c)+compute(a-1,b-1,c)+compute(a-1,b,c-1)-compute(a-1,b-1,c-1);
		memo[a][b][c]=result;
		memoEnabled[a][b][c]=true;
		return result;
	}
}
