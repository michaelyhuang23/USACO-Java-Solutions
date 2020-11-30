import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1080 {
	static class Man implements Comparable<Man>{
		long left, right, product;
		public Man(long l, long r) {
			left=l;
			right=r;
			product=l*r;
		}
		@Override
		public int compareTo(Man o) {
			return Long.compare(product, o.product);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numMan = Integer.parseInt(f.readLine().trim());
		StringTokenizer st =new StringTokenizer(f.readLine().trim());
		
		BigInteger leftProduct = new BigInteger(st.nextToken());
		Man[] allMan = new Man[numMan];
		for(int i=0;i<numMan;i++) {
			st =new StringTokenizer(f.readLine().trim());
			long left = Long.parseLong(st.nextToken());
			long right = Long.parseLong(st.nextToken());
			allMan[i] = new Man(left, right);
		}
		Arrays.sort(allMan);
		BigInteger maxVal = new BigInteger("0");
		for(int i=0;i<numMan;i++) {
			BigInteger result = leftProduct.divide(BigInteger.valueOf(allMan[i].right));
			if(result.compareTo(maxVal)>0) {
				maxVal = result;
			}
			leftProduct=leftProduct.multiply(BigInteger.valueOf(allMan[i].left));
		}
		System.out.println(maxVal);
	}
}
