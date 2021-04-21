import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Round710Div3E {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			StringTokenizer st = new StringTokenizer(f.readLine());
			int[] arr = new int[n];
			int[] minResult = new int[n];
			int[] maxResult = new int[n];
			int[] divs = new int[n+1];
			int count=0;
			PriorityQueue<Integer> mins = new PriorityQueue<>();
			PriorityQueue<Integer> maxs = new PriorityQueue<>(Collections.reverseOrder());
			boolean[] mark = new boolean[n+1];
			for (int j = 0; j < n; j++) {
				arr[j]=Integer.parseInt(st.nextToken());
				if(j==0 || arr[j]!=arr[j-1]) {
					minResult[j]=arr[j];
					maxResult[j]=arr[j];
					divs[count]=j;
					mark[arr[j]]=true;
					count++;
				}
			}
			divs[count]=n;
			for (int j = 1; j <= n; j++) {
				if(!mark[j])
					mins.offer(j);
			}
			
			for (int j = 0; j < n; j++) {
				if(j==0 || arr[j]!=arr[j-1]) 
					continue;
				minResult[j]=mins.poll();
			}
			
			for (int j = 0; j < count; j++) {
				int p = 1;
				if(j-1>=0)
					p=arr[divs[j-1]]+1;
				for (; p < arr[divs[j]]; p++) {
					maxs.offer(p);
				}
				for (int k = divs[j]+1; k < divs[j+1]; k++) {
					maxResult[k]=maxs.poll();
				}
			}
			
			
			
			for (int j = 0; j < n - 1; j++) {
				System.out.print(minResult[j] + " ");
			}
			System.out.println(minResult[n - 1]);
			
			for (int j = 0; j < n - 1; j++) {
				System.out.print(maxResult[j] + " ");
			}
			System.out.println(maxResult[n - 1]);
		}
	}
}
