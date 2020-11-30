import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class P1177 {

	public static void quickSorter(int start, int end, int[] arr) {
		if(start>=end)
			return;
		int midValue = arr[(start+end)/2];

		int i=start,j=end;
		while(j>=i) {
			while(arr[i]<midValue) i++;
			while(arr[j]>midValue) j--;

			if(j<i) {
				break;
			}
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;

		}

		quickSorter(start,j,arr);
		quickSorter(i,end,arr);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] arr = new int[num];
		for(int i=0;i<num;i++)
			arr[i]=Integer.parseInt(st.nextToken());
		quickSorter(0,arr.length-1,arr);

		for(int i=0;i<arr.length;i++)
			if(i==arr.length-1)
				System.out.println(arr[i]);
			else
				System.out.print(arr[i]+" ");
	}
}
