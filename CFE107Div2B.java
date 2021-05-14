import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CFE107Div2B {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] pow10 = new int[10];
		pow10[0]=1;
		for (int i = 1; i < pow10.length; i++) 
			pow10[i]=pow10[i-1]*10;

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int z = pow10[c-1];
			int swap = 0;
			if(a>b) {
				swap = b;
				b = a;
				a = swap;
			}
			a -= c;
			b -= c;
			int x = pow10[a];
			int y = pow10[b]+1;
			x*=z;
			y*=z;
			if(swap==0) {
				System.out.println(x+" "+y);
			}else {
				System.out.println(y+" "+x);
			}
		}
	}
}
