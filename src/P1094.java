import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1094 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int upperBound = Integer.parseInt(f.readLine());
		int numObj = Integer.parseInt(f.readLine());
		int[] objPrice = new int[numObj];
		for(int i=0;i<numObj;i++) {
			int price = Integer.parseInt(f.readLine());
			objPrice[i]=price;
		}
		Arrays.parallelSort(objPrice);
		int start=0,end=numObj-1;
		int group=0;
		while(start<end) {
			group++;
			if(objPrice[start]+objPrice[end]<=upperBound) {
				start++;
				end--;
			}else
				end--;
		}
		if(start==end)
			group++;
		System.out.println(group);
	}
}
