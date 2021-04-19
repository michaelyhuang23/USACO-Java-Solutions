import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class rental {
	static class PurchaseRequest implements Comparable<PurchaseRequest> {
		int amount, price;

		public PurchaseRequest(int a, int p) {
			amount = a;
			price = p;
		}

		public int compareTo(PurchaseRequest other) {
			return other.price - price;
		}
	}


	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("rental.in")); //new FileReader("rental.in")
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
		int numCow, numStore, numRenter;
		StringTokenizer st = new StringTokenizer(f.readLine());
		numCow = Integer.parseInt(st.nextToken());
		numStore = Integer.parseInt(st.nextToken());
		numRenter = Integer.parseInt(st.nextToken());
		int[] cows = new int[numCow];
		PurchaseRequest[] allStores = new PurchaseRequest[numStore];
		int[] renters = new int[numRenter];
		for (int i = 0; i < numCow; i++)
			cows[i] = Integer.parseInt(f.readLine());
		for (int i = 0; i < numStore; i++) {
			st = new StringTokenizer(f.readLine());
			allStores[i] = new PurchaseRequest(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for (int i = 0; i < numRenter; i++)
			renters[i] = Integer.parseInt(f.readLine());

		Arrays.sort(cows);
		Arrays.sort(renters);
		Arrays.sort(allStores);
		int currentStore=0;
		long cumulativeProfit=0;
		long[] sellProfitDown = new long[numCow+1];
		sellProfitDown[numCow]=0;
		for (int currentCow = numCow-1; currentCow >= 0; currentCow--) {
			int leftAmount = cows[currentCow];
			int sellProfit = 0;
			int j;
			for(j=currentStore; j<numStore; j++) {
				if(leftAmount<allStores[j].amount) {
					sellProfit+=leftAmount*allStores[j].price;
					allStores[j].amount-=leftAmount;
					leftAmount=0;
					break;
				}
				sellProfit+=allStores[j].amount*allStores[j].price;
				leftAmount-=allStores[j].amount;
				allStores[j].amount=0;
			}
			currentStore=j;
			cumulativeProfit+=sellProfit;
			sellProfitDown[currentCow]=cumulativeProfit;
		}
		long maxProfit = sellProfitDown[0];
		long totalRenterProfit=0;
		for (int i = 0; i<Math.min(numCow,numRenter); i++) {
			totalRenterProfit+=renters[numRenter-i-1];
			maxProfit = Math.max(totalRenterProfit+sellProfitDown[i+1],maxProfit);
		}

		out.println(maxProfit);
		f.close();
		out.close();
	}
}
