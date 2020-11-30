import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class triangles {
	static class Point implements Comparable<Point>{
		int x, y;
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
		@Override
		public int compareTo(Point o) {
			if(x-o.x==0)
				return y-o.y;
			return x-o.x;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("triangles.in")); //new FileReader("triangles.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
		int numPoint = Integer.parseInt(f.readLine());
		Point[] allPoints = new Point[numPoint];
		int minX=Integer.MAX_VALUE, minY=Integer.MAX_VALUE, maxX=0, maxY=0;
		for(int i=0;i<numPoint;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			allPoints[i] = new Point(x,y);
			minX = Math.min(minX, x);
			minY = Math.min(minY, y);
			maxX = Math.max(maxX, x);
			maxY = Math.max(maxY, y);
		}
		Arrays.sort(allPoints);
		for(int i=0;i<numPoint;i++) {
			allPoints[i].x-=minX;
			allPoints[i].y-=minY;
			//System.out.println(allPoints[i].x+" "+allPoints[i].y);
		}
		maxX-=minX;
		maxY-=minY;
		long[] columnSum = new long[maxX+1];
		int[] columnCount = new int[maxX+1];
		int[] prevInCol = new int[maxX+1];
		Arrays.fill(prevInCol, -1);
		int[] prevInRow = new int[maxY+1];
		Arrays.fill(prevInRow, -1);
		int[] rowCount = new int[maxY+1];
		long[] rowSum = new long[maxY+1];
		for(int i=0;i<numPoint;i++) {
			columnCount[allPoints[i].x]++;
			rowCount[allPoints[i].y]++;
			columnSum[allPoints[i].x]+=allPoints[i].y;
			rowSum[allPoints[i].y]+=allPoints[i].x;

		}
		int[] columnTotal = columnCount.clone();
		int[] rowTotal = rowCount.clone();
		long MOD = 1000000007;
		long totalSum = 0;
		for(int i=0;i<numPoint;i++) {
			if(prevInCol[allPoints[i].x]>-1)
				columnSum[allPoints[i].x]+=(allPoints[i].y-prevInCol[allPoints[i].x])*(columnTotal[allPoints[i].x]-columnCount[allPoints[i].x]);
			prevInCol[allPoints[i].x] = allPoints[i].y;
			long thisColSum = columnSum[allPoints[i].x]-allPoints[i].y*(columnCount[allPoints[i].x]);
			columnCount[allPoints[i].x]--;
			columnSum[allPoints[i].x]-=allPoints[i].y;
			if(prevInRow[allPoints[i].y]>-1)
				rowSum[allPoints[i].y]+=(allPoints[i].x-prevInRow[allPoints[i].y])*(rowTotal[allPoints[i].y]-rowCount[allPoints[i].y]);
			prevInRow[allPoints[i].y] = allPoints[i].x;
			long thisRowSum = rowSum[allPoints[i].y]-allPoints[i].x*(rowCount[allPoints[i].y]);
			//System.out.println(rowSum[allPoints[i].y]);
			rowCount[allPoints[i].y]--;
			rowSum[allPoints[i].y]-=allPoints[i].x;
		//	System.out.println(totalSum+thisRowSum*thisColSum);
			totalSum=(totalSum+thisRowSum*thisColSum)%MOD;
		}
		out.println(totalSum%MOD);
		out.close();
		f.close();
		
	}
}
