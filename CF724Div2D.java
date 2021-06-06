import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class CF724Div2D {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			int[] barr = new int[n];
			StringTokenizer st = new StringTokenizer(f.readLine());
			for (int j = 0; j < barr.length; j++) {
				barr[j] = Integer.parseInt(st.nextToken());
			}
			ArrayDeque<Integer> left = new ArrayDeque<>(), right = new ArrayDeque<>();
			int mid = barr[0];
			boolean success = true;
			for (int j = 1; j < n; j++) {
				if(barr[j]==mid) {
					left.addLast(Integer.MIN_VALUE);
					right.addLast(Integer.MAX_VALUE);
				}else if(barr[j]<mid) {
					left.addLast(Integer.MIN_VALUE);
					left.addLast(Integer.MIN_VALUE);
					right.addFirst(mid);
					if(left.peekFirst()>barr[j]) {
						success=false;
						break;
					}else if(left.peekFirst()==barr[j]) {
						mid = left.pollFirst();
					}else if(left.peekFirst()<barr[j]) {
						mid = barr[j];
						left.pollLast();
					}
				}else {
					right.addLast(Integer.MAX_VALUE);
					right.addLast(Integer.MAX_VALUE);
					left.addFirst(mid);
					if(right.peekFirst()<barr[j]) {
						success=false;
						break;
					}else if(right.peekFirst()==barr[j]) {
						mid = right.pollFirst();
					}else if(right.peekFirst()>barr[j]) {
						mid = barr[j];
						right.pollLast();
					}
				}
			}
			if(success) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
	}
}
