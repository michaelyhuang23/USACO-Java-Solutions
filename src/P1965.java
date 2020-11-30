import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1965 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		long n = Long.parseLong(st.nextToken());
		long move = Long.parseLong(st.nextToken());
		long expo = Long.parseLong(st.nextToken());
		long pos = Long.parseLong(st.nextToken());
		System.out.println((pos+(move*fastPowerModRecurse(10, expo, n))%n)%n);
	}
	static long fastPowerModRecurse(long base, long exponent, long mod) {
		//(base^exponent)%mod = (base^(exponent/2)%mod)^2*(base^(exponent%2))%mod
		if(exponent==0)
			return 1;
		long prevMod = fastPowerModRecurse(base,exponent/2,mod);
		return ((prevMod*prevMod)%mod*((exponent%2==1 ? base : 1)%mod))%mod;
		
	}
}
