import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class tilechng {
	
	static int numSquare;
	static int target;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in)); //new FileReader("tilechng.in")  //new InputStreamReader(System.in)
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tilechng.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numSquare = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		int[] allSquares = new int[numSquare]; //unsquare values
		int currentSum = 0;
		for(int i=0;i<numSquare;i++) {
			allSquares[i] = Integer.parseInt(f.readLine());
			currentSum += allSquares[i]*allSquares[i];
		}
		target -= currentSum;
		int distance = Math.abs(target);
		Arrays.sort(allSquares);
		int[][] minCost = new int[numSquare+1][2*distance+1];
		for(int i=0;i<=numSquare; i++)
			Arrays.fill(minCost[i], Integer.MAX_VALUE/2);
		minCost[0][distance]=0;
		for(int allowedS = 1; allowedS<=numSquare; allowedS++) {
			for(int curI = 0; curI<=2*distance; curI++) {
				if(currentSum-distance+curI<=0 || minCost[allowedS-1][curI]>=Integer.MAX_VALUE/2)
					continue;
				int startS;
				if(curI>=allSquares[allowedS-1]*allSquares[allowedS-1])
					startS = 1;
				double root =  Math.sqrt(allSquares[allowedS-1]*allSquares[allowedS-1]-curI);
				if(Math.round(root)*Math.round(root)==allSquares[allowedS-1]*allSquares[allowedS-1]-curI)
					startS = (int)Math.round(root);
				else
					startS = (int)Math.ceil(Math.sqrt(allSquares[allowedS-1]*allSquares[allowedS-1]-curI));
				for(int square=Math.max(startS, 1); curI+square*square<=2*distance; square++) {
					int newMin=minCost[allowedS-1][curI]+(square-allSquares[allowedS-1])*(square-allSquares[allowedS-1]);
					int newPos = curI+square*square-allSquares[allowedS-1]*allSquares[allowedS-1];
					minCost[allowedS][newPos] = Math.min(newMin, minCost[allowedS][newPos]);
				}
			}
		}
		if(minCost[numSquare][target+distance]>=Integer.MAX_VALUE/2)
			System.out.println(-1);
		else
			System.out.println(minCost[numSquare][target+distance]);
	}
}
