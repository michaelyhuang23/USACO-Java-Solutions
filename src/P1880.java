import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1880 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numGroup = Integer.parseInt(f.readLine());
		int[] groupStone = new int[numGroup*2+1];
		int[] prefix = new int[numGroup*2+1];
		StringTokenizer st = new StringTokenizer(f.readLine());
		for(int i=1;i<=numGroup;i++) {
			groupStone[i] = Integer.parseInt(st.nextToken());
			prefix[i] = prefix[i-1]+groupStone[i];
		}
		for(int j=1;j<=numGroup;j++) {
			groupStone[j+numGroup] = groupStone[j];
			prefix[j+numGroup] = prefix[numGroup+j-1]+groupStone[j];
		}
		
		int[][] dpMin = new int[numGroup*2+1][numGroup*2+1];
		long[][] dpMax = new long[numGroup*2+1][numGroup*2+1];
		for(int i=0;i<numGroup*2+1;i++)
			Arrays.fill(dpMin[i], Integer.MAX_VALUE/3);
		for(int i=0;i<numGroup*2+1;i++) 
			dpMin[i][i]=0;
		
		for(int start = numGroup*2-1; start > 0; start--) {
			for(int end = start+1; end <= Math.min(numGroup*2,start+numGroup-1); end++) {
				int sum = prefix[end]-prefix[start-1];
				for(int middle = start; middle<end; middle++) {
					dpMin[start][end] = Math.min(dpMin[start][end], dpMin[start][middle]+dpMin[middle+1][end]+sum);
					dpMax[start][end] = Math.max(dpMax[start][end], dpMax[start][middle]+dpMax[middle+1][end]+sum);
				}
			}
		}
		int min = Integer.MAX_VALUE;
		long max = 0;
		for(int i=1;i<=numGroup;i++)
			min = Math.min(min, dpMin[i][numGroup+i-1]);
		for(int i=1;i<=numGroup;i++)
			max = Math.max(max, dpMax[i][numGroup+i-1]);
		System.out.println(min);
		System.out.println(max);
	}
}
