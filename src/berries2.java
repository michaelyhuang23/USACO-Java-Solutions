import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class berries2 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("berries.in")); //new FileReader("berries.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numTree = Integer.parseInt(st.nextToken());
		int numBasket = Integer.parseInt(st.nextToken());
		int[] allTrees = new int[numTree];
		st = new StringTokenizer(f.readLine());
		int maxTree = 0;
		for(int i=0;i<numTree;i++) {
			allTrees[i] = Integer.parseInt(st.nextToken());
			maxTree = Math.max(maxTree, allTrees[i]);
		}
		long maxBerry=0;
		for(int bucketCutoff=1; bucketCutoff<=maxTree; bucketCutoff++) {
			int[] remnantTree = new int[numTree];
			int fullBucketCount=0;
			for(int i=0;i<numTree;i++) {
				fullBucketCount+=allTrees[i]/bucketCutoff;
				remnantTree[i] = allTrees[i]%bucketCutoff;
			}
			Arrays.sort(remnantTree);
			if(fullBucketCount<numBasket/2)
				continue;
			if(fullBucketCount>=numBasket)
				maxBerry = Math.max(maxBerry, numBasket/2*bucketCutoff);
			if(fullBucketCount<numBasket) {
				long numBerry = (fullBucketCount-numBasket/2)*bucketCutoff;
				for(int i=numTree-1;i>=Math.max(0, numTree-(numBasket-fullBucketCount));i--)
					numBerry+=remnantTree[i];
				maxBerry = Math.max(maxBerry, numBerry);
			}
		}
		out.println(maxBerry);
		f.close();
		out.close();
	}
	
}
