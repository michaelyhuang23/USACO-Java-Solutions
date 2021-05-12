import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Round720Div2A {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			long a = Integer.parseInt(st.nextToken());
			long b = Integer.parseInt(st.nextToken());
			if(b==1) {
				System.out.println("NO");
				continue;
			}else {
				System.out.println("YES");
				System.out.println(a+" "+a*b+" "+a*(b+1));
			}
		}
	}
}
