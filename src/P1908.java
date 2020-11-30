import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1908 {
	static long pairCounter=0;
	public static void mergeSort(int start, int end, int[] arr) {
		if(end<=start)
			return;
		
		int mid = (start+end)/2;
		mergeSort(start,mid,arr);
		mergeSort(mid+1,end,arr);
		int[] sorted = new int[end-start+1];
		int i=start,j=mid+1,count=0;
		while(i<=mid && j<=end) {
			if(arr[i]<=arr[j]) {
				sorted[count]=arr[i];
				i++;
			}else {
				sorted[count]=arr[j];
				pairCounter+=mid-i+1;
				j++;
			}
			count++;	
		}
		while(i<=mid) {
			sorted[count]=arr[i];
			count++;
			i++;
		}
		while(j<=end) {
			sorted[count]=arr[j];
			count++;
			j++;
		}
		
		for(int k=0;k<sorted.length;k++) {
			arr[start+k]=sorted[k];
		}

		return;
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int arrSize = Integer.parseInt(f.readLine());
		int[] arr = new int[arrSize];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for(int i=0;i<arrSize;i++)
			arr[i]=Integer.parseInt(st.nextToken());
		mergeSort(0,arr.length-1,arr);
		System.out.println(pairCounter);


	}
}
