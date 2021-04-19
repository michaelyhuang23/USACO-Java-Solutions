import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: yhuang22
LANG: JAVA
TASK: skidesign
*/
public class skidesign {
	class Range {
		int low, high;

		public Range(int low) {
			this.low = low;
			this.high = low+17;
		}
		
		public boolean inRange(int pos) {
			return pos>=low && pos<=high;
		}
		
		public int distanceToRange(int pos) {
			if(inRange(pos))
				return 0;
			return Math.min(Math.abs(pos-low), Math.abs(pos-high));
		}
	}

	public static void main(String[] args) throws IOException {
		skidesign thisDesign=new skidesign();
		BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.4/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
		int numHills=Integer.parseInt(f.readLine());
		int[] hills=new int[numHills];
		for(int i=0;i<numHills;i++)
			hills[i]=Integer.parseInt(f.readLine());
		
		int min=100;
		int max=0;
		
		for(int height:hills) {
			min=Math.min(min, height);
			max=Math.max(max, height);
		}
		int minPrice=-1;
		for(int h=min; h<=max-17;h++) {
			Range newRange = thisDesign.new Range(h);
			
			int price=calculatePrice(newRange, hills);
			if(minPrice==-1 || price<minPrice) {
				minPrice=price;
			}
		}
		out.println(minPrice);
		out.close();
		f.close();
	}
	
	public static int calculatePrice(Range range, int[] hills) {
		int price=0;
		for(int height:hills) {
			price+=range.distanceToRange(height)*range.distanceToRange(height);
		}
		return price;
	}
}
