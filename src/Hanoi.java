
public class Hanoi {
	void hanoi(int n, char a, char b, char c) {
		if(n==1) {
			System.out.println(a+" to "+c);
			return;
		}
		hanoi(n-1,a,c,b);
		hanoi(1,a,b,c);
		hanoi(n-1,b,a,c);
	}
}
