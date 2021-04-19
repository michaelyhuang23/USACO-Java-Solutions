import java.io.IOException;

public class Recursion {
	public static void main(String[] args) throws IOException {
		
	}
	public static int factorial(int n) {
		if(n==0)
			return 1;
		return factorial(n-1)*n;
	}
	public static int fib(int n) {
		if(n==1 || n==2)
			return 1;
		return fib(n-1)+fib(n-2);
	}
	public static int euclid(int a, int b) {
		if(a%b==0)
			return b;
		return euclid(b,a%b);
	}
}
