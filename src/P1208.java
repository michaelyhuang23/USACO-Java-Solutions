import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1208 {
	public static class Farm implements Comparable<Farm>{
		int price,yield;
		public int compareTo(Farm other) {
			return price-other.price;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int totalNeed = Integer.parseInt(st.nextToken());
		int numFarm = Integer.parseInt(st.nextToken());
		Farm[] allFarms = new Farm[numFarm];
		for(int i=0;i<numFarm;i++) {
			st = new StringTokenizer(f.readLine());
			allFarms[i]=new Farm();
			allFarms[i].price=Integer.parseInt(st.nextToken());
			allFarms[i].yield=Integer.parseInt(st.nextToken());
		}
		Arrays.parallelSort(allFarms);
		int money=0;
		for(int farmN=0;farmN<numFarm;farmN++) {
			if(totalNeed-allFarms[farmN].yield<=0) {
				money+=totalNeed*allFarms[farmN].price;
				break;
			}
			totalNeed-=allFarms[farmN].yield;
			money+=allFarms[farmN].yield*allFarms[farmN].price;
		}
		System.out.println(money);
	}
}
