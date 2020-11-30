import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1281 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numBook = Integer.parseInt(st.nextToken());
		int numPeople = Integer.parseInt(st.nextToken());
		int[] bookPages = new int[numBook+1];
		int[] prefix = new int[numBook+1];
		st = new StringTokenizer(f.readLine());
		for(int i=1;i<=numBook;i++) {
			bookPages[i] = Integer.parseInt(st.nextToken());
			prefix[i] = bookPages[i]+prefix[i-1];
		}
		
		int[][] dp = new int[numPeople+1][numBook+1];
		for(int i=0;i<=numPeople;i++)
			Arrays.fill(dp[i], Integer.MAX_VALUE/3);
		dp[0][0]=0;
		for(int person = 1; person<=numPeople; person++) 
			for(int book = 1; book<=numBook; book++) 
				for(int thisPersonBook = 0; thisPersonBook<=book; thisPersonBook++) 
					dp[person][book] = Math.min(dp[person][book], Math.max(dp[person-1][book-thisPersonBook],prefix[book]-prefix[book-thisPersonBook]));

		int minTime = dp[numPeople][numBook];
		int book = numBook;
		int[] starts = new int[numPeople+1];
		int[] ends = new int[numPeople+1];
		for(int person = numPeople; person>0; person--) {
			int counter=0;
			int time = 0;
			do {
				if(book<=0) {
					starts[person] = (book+1);
					ends[person] = (book+counter);
					break;
				}
				time+=bookPages[book];
				book--;
				counter++;
			}while(time<=minTime);
			book++;
			counter--;
			time-=bookPages[book];
			if(starts[person]!=0)
				continue;
			starts[person] = (book+1);
			ends[person] = (book+counter);
		}
		for(int person = 1; person<=numPeople; person++) {
			System.out.println(starts[person]+" "+ends[person]);
		}
	}
}
