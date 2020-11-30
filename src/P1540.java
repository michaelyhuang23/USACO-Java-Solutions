import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1540 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numMemo = Integer.parseInt(st.nextToken());
		int numWord = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		Queue<Integer> currentDict = new ArrayDeque<>();
		int count=0;
		for(int i=0;i<numWord;i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(!currentDict.contains(num)) {
				if(currentDict.size()==numMemo)
					currentDict.poll();
				currentDict.offer(num);
				count++;
			}
		}
		System.out.println(count);
	}
}
