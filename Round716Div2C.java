import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Round716Div2C {
	private static int gcd(int a, int b) {// a is larger
		if(b==0)
			return a;
		return gcd(b,a%b);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(f.readLine());
		long prod = 1;
		int len = 0;
		for (int i = 1; i < n; i++) {
			if(gcd(n,i)!=1)
				continue;
			prod*=i;
			prod%=n;
			len++;
		}
		int inverse=0;
		for (int i = 1; i < n; i++) {
			if((prod*i)%n==1) {
				inverse=i;
				break;
			}
		}
		assert (inverse!=0);
		if(inverse!=1)
			len--;
		boolean started = false;
		System.out.println(len);
		if(prod==1) {
			for (int i = 1; i < n; i++) {
				if(gcd(n,i)!=1)
					continue;
				if(started)
					System.out.print(" ");
				started=true;
				System.out.print(i);
			}
			System.out.println();
			return;
		}
		for (int i = 1; i < n; i++) {
			if(gcd(n,i)!=1)
				continue;
			if(i==inverse)
				continue;
			if(started)
				System.out.print(" ");
			started=true;
			System.out.print(i);
		}
		System.out.println();
	}
}
