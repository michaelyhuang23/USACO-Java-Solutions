import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1478 {
	public static class Apple implements Comparable<Apple>{
		public int height,energy;
		public int compareTo(Apple other) {
			return energy - other.energy;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numApple = Integer.parseInt(st.nextToken());
		int energy = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		int totalLength = Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken());
		Apple[] apples = new Apple[numApple];
		for(int i=0;i<numApple;i++) {
			st = new StringTokenizer(f.readLine());
			apples[i]=new Apple();
			apples[i].height=Integer.parseInt(st.nextToken());
			apples[i].energy=Integer.parseInt(st.nextToken());
		}
		Arrays.parallelSort(apples);
		int counter=0;
		for(int i=0;i<numApple;i++) {
			if(totalLength<apples[i].height)
				continue;
			energy-= apples[i].energy;
			if(energy<0)
				break;
			counter++;
			
		}
		System.out.println(counter);
	}
}
