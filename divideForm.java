import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class divideForm {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(f.readLine());
		int[][] form = new int[1<<m][1<<m];
		form[0][0]=1;
		for(int i=0;i<m;i++) {
			int len = 1<<i;
			
			for(int r=0;r<len;r++) {
				for(int c=0;c<len;c++) {
					form[r][c+len] = form[r][c]+len;
				}
			}
			
			for(int r=0;r<len;r++) {
				for(int c=0;c<len;c++) {
					form[r+len][c] = form[r][c+len];
					form[r+len][c+len] = form[r][c];
				}
			}
		}
		
		for(int i=0;i<1<<m;i++) {
			for(int j=0;j<1<<m;j++)
				System.out.print(form[i][j]);
			System.out.println();
		}
		System.out.println(fastPowerModLoop(2,10,9));
	}
	static long fastPowerModLoop(long base, long exponent, long mod) {
		long currentMod = base;
		long currentExpo = exponent;
		long ans = 1;
		while(currentExpo>0) {
			if(currentExpo%2==1)
				ans = (ans*currentMod)%mod;
			currentMod = (currentMod*currentMod)%mod;
			currentExpo/=2;
		}
		return ans;
	}
	static long fastPowerModRecurse(long base, long exponent, long mod) {
		//(base^exponent)%mod = (base^(exponent/2)%mod)^2*(base^(exponent%2))%mod
		if(exponent==0)
			return 1;
		long prevMod = fastPowerModRecurse(base,exponent/2,mod);
		return ((prevMod*prevMod)%mod*((exponent%2==1 ? base : 1)%mod))%mod;
		
	}
}
