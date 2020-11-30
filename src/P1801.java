import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1801 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int addNum = Integer.parseInt(st.nextToken());
		int getNum = Integer.parseInt(st.nextToken());
		int[] addArray = new int[addNum];
		int[] getArray = new int[getNum];
		st = new StringTokenizer(f.readLine());
		for(int i=0;i<addNum;i++) {
			addArray[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(f.readLine());
		for(int i=0;i<getNum;i++) {
			getArray[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		PriorityQueue<Integer> firstQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return  o2-o1;
			}	
		});
		PriorityQueue<Integer> secondQueue = new PriorityQueue<Integer>();
		int getCount=0;
		for(int i=0;i<addNum;i++) {
			firstQueue.offer(addArray[i]);
			secondQueue.offer(firstQueue.poll());
			while(getArray[getCount]==i) {
				int num = secondQueue.poll();
				System.out.println(num);
				firstQueue.offer(num);
				getCount++;
				if(getCount>=getNum)
					return;
			}
		}
		
	}
}
