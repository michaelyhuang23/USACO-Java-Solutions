import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class balancing {
	static class Point{
		int x, y;
		public Point(int inX, int inY) {
			x=inX;
			y=inY;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("balancing.in")); //new FileReader("balancing.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
		int numCow = Integer.parseInt(f.readLine());
		Point[] allPoints = new Point[numCow];
		for(int i=0;i<numCow;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			allPoints[i] = new Point(x,y);
		}
	
		Arrays.sort(allPoints,new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				if(o1.x-o2.x==0)
					return o1.y-o2.y;
				return o1.x-o2.x;
			}
		});
		int counter=0;
		for(int i=0;i<numCow;i++)
			if(i==0 || allPoints[i].x!=allPoints[i-1].x)
				counter++;
		ArrayList<Integer>[] pointByColumn = new ArrayList[counter];
		int[] xOfColumn = new int[counter];
		for(int i=0;i<counter;i++)
			pointByColumn[i]=new ArrayList<Integer>();
		int index=-1;
		for(int i=0;i<numCow;i++) {
			if(i==0 || allPoints[i].x!=allPoints[i-1].x)
				index++;
			pointByColumn[index].add(allPoints[i].y);
			xOfColumn[index]=allPoints[i].x;
		}
		
		
		
		Arrays.sort(allPoints,new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				if(o1.y-o2.y==0)
					return o1.x-o2.x;
				return o1.y-o2.y;
			}
		});
		counter=0;
		for(int i=0;i<numCow;i++)
			if(i==0 || allPoints[i].y!=allPoints[i-1].y)
				counter++;
		ArrayList<Integer>[] pointByRow = new ArrayList[counter];
		int[] yOfRow = new int[counter];
		for(int i=0;i<counter;i++)
			pointByRow[i]=new ArrayList<Integer>();
		index=-1;
		for(int i=0;i<numCow;i++) {
			if(i==0 || allPoints[i].y!=allPoints[i-1].y)
				index++;
			pointByRow[index].add(allPoints[i].x);
			yOfRow[index] = allPoints[i].y;
		}
		
		int leftTop = 0;
		int leftBottom = 0;
		int rightTop = numCow;
		int rightBottom = 0;
		int minOfMax = Integer.MAX_VALUE;
		for(int rowCut = 0; rowCut<pointByRow.length; rowCut++) {
			for(int colCut = 0; colCut<pointByColumn.length; colCut++) {
				minOfMax = Math.min(minOfMax, Math.max(Math.max(leftTop, leftBottom),Math.max(rightTop, rightBottom)));
				int resultI = Collections.binarySearch(pointByColumn[colCut], yOfRow[rowCut]);
				if(resultI<0)
					resultI = -resultI-1;
				rightBottom-=resultI;
				leftBottom+=resultI;
				rightTop-=pointByColumn[colCut].size()-resultI;
				leftTop+=pointByColumn[colCut].size()-resultI;
			}
			rightBottom=leftBottom;
			rightTop=leftTop;
			leftBottom=0;
			leftTop=0;
			rightTop-=pointByRow[rowCut].size();
			rightBottom+=pointByRow[rowCut].size();
		}
		
		out.println(minOfMax);
		out.close();
		f.close();
		
		
	}

}
