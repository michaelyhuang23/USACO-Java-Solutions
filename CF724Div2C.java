import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CF724Div2C {
	private static int gcd(int a, int b) {
		if(b==0)
			return a;
		return gcd(b,a%b);
	}
	static class Pair{
		int a,b;
		public Pair(int a, int b) {
			this.a=a;
			this.b=b;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + a;
			result = prime * result + b;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (a != other.a)
				return false;
			if (b != other.b)
				return false;
			return true;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			String str = f.readLine();
			HashMap<Pair,Integer> ratioCount = new HashMap<>();
			int d = 0, k = 0;
			int[] cuts = new int[n];
			for (int j = 0; j < n; j++) {
				if(str.charAt(j)=='D')
					d++;
				else
					k++;
				int g = gcd(d,k);
				int dg = d/g;
				int kg = k/g;
				Pair newP = new Pair(dg,kg);
				if(ratioCount.containsKey(newP)) {
					cuts[j] = ratioCount.get(newP)+1;
				}else {
					cuts[j] = 1;
				}
				ratioCount.put(newP, cuts[j]);
			}
			for (int j = 0; j < n-1; j++) {
				System.out.print(cuts[j]+" ");
			}
			System.out.println(cuts[n-1]);
		}
	}
}
