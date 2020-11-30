import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class P1886 {
	static Deque<Integer> ascender;
	static Deque<Integer> descender;
	static int[] arr;
	static int slideSize,length;
	private static int buildAscender(int inputI) {
		while(!ascender.isEmpty() && ascender.peekFirst()<=inputI-slideSize)
			ascender.pollFirst();
		while(!ascender.isEmpty() && arr[ascender.peekLast()]>=arr[inputI])
			ascender.pollLast();
		ascender.offerLast(inputI);
		
		return arr[ascender.peekFirst()];
	}
	private static int buildDescender(int inputI) {
		while(!descender.isEmpty() && descender.peekFirst()<=inputI-slideSize)
			descender.pollFirst();
		while(!descender.isEmpty() && arr[descender.peekLast()]<=arr[inputI])
			descender.pollLast();
		descender.offerLast(inputI);
		
		return arr[descender.peekFirst()];
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		length = Integer.parseInt(st.nextToken());
		slideSize = Integer.parseInt(st.nextToken());
		arr = new int[length];
		st = new StringTokenizer(f.readLine());
		for(int i=0;i<length;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		ascender = new ArrayDeque<>();
		descender = new ArrayDeque<>();
		int[] min = new int[length-slideSize+1];
		int[] max = new int[length-slideSize+1];
		for(int endI=0;endI<length;endI++) {
			int thisMax = buildDescender(endI);
			int thisMin = buildAscender(endI);
			if(endI>=slideSize-1) {
				max[endI-slideSize+1] = thisMax;
				min[endI-slideSize+1] = thisMin;
			}
		}
		
		for(int i=0;i<length-slideSize;i++)
			System.out.print(min[i]+" ");
		System.out.println(min[min.length-1]);
		
		for(int i=0;i<length-slideSize;i++)
			System.out.print(max[i]+" ");
		System.out.println(max[max.length-1]);
	}
}
