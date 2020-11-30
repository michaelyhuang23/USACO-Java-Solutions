import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1417 {
	static class Food implements Comparable<Food>{
		long taste, degrade, time;
		public Food(int a, int b, int c) {
			taste = a;
			degrade = b;
			time = c;
		}
		@Override
		public int compareTo(Food o) {
			// TODO Auto-generated method stub
			return Long.compare(o.degrade*time,degrade*o.time);
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int totalTime = Integer.parseInt(st.nextToken());
		int numFood = Integer.parseInt(st.nextToken());
		int[] aVals = new int[numFood+1];
		st = new StringTokenizer(f.readLine());
		for(int i=1;i<=numFood;i++)
			aVals[i] = Integer.parseInt(st.nextToken());
		int[] bVals = new int[numFood+1];
		st = new StringTokenizer(f.readLine());
		for(int i=1;i<=numFood;i++)
			bVals[i] = Integer.parseInt(st.nextToken());
		int[] cVals = new int[numFood+1];
		st = new StringTokenizer(f.readLine());
		for(int i=1;i<=numFood;i++)
			cVals[i] = Integer.parseInt(st.nextToken());
		Food[] allFoods = new Food[numFood+1];
		for(int i=1;i<=numFood;i++) 
			allFoods[i] = new Food(aVals[i], bVals[i], cVals[i]);
		allFoods[0] = new Food(0, 1000000, 0);
		Arrays.sort(allFoods);
		long[] dp = new long[totalTime+1];
		for(int doneFood = 1; doneFood<=numFood; doneFood++) {
			for(int time = totalTime; time>=allFoods[doneFood].time; time--) {
				dp[time] = Math.max(dp[time], dp[(int) (time-allFoods[doneFood].time)]+allFoods[doneFood].taste-allFoods[doneFood].degrade*time);
			}
		}
		long max=0;
		for(int time = 1; time<=totalTime; time++)
			max = Math.max(max, dp[time]);
		System.out.println(max);
	}
}
