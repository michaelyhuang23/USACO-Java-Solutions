import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1996 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int number = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Queue<Integer> circle = new ArrayDeque<>();
		for(int i=1;i<=number;i++)
			circle.offer(i);
		int count=0;
		StringBuffer str = new StringBuffer();
		while(!circle.isEmpty()) {
			int num =circle.poll();
			count++;
			if(count%m==0) 
				str.append(num+" ");
			else
				circle.offer(num);
		}
		str.deleteCharAt(str.length()-1);
		System.out.println(str);
	}
}
