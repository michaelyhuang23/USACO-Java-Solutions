import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;
/*
ID: yhuang22
LANG: JAVA
TASK: cowtour
*/
class Past{
	public double x,y;
	public Past(double x, double y) {
		this.x=x;
		this.y=y;
	}
	public boolean equals(Object other) {
		Past otherP = (Past)other;
		return Math.abs(this.x-otherP.x)<0.001 && Math.abs(this.y-otherP.y)<0.001;
	}
	public String toString() {
		return x+" "+y;
	}
	public double distance(Past otherP) {
		return Math.sqrt((otherP.x-x)*(otherP.x-x)+(otherP.y-y)*(otherP.y-y));
	}
}
public class cowtour {
	public static void floodFill(int pastureI, boolean[][] pastConnectivity, int pastNum, int[] pastField, int fieldIndex) {
		pastField[pastureI]=fieldIndex;
		for(int otherPast=0;otherPast<pastNum;otherPast++) {
			if(!pastConnectivity[pastureI][otherPast] || pastField[otherPast]!=0)
				continue;
			floodFill(otherPast, pastConnectivity, pastNum, pastField, fieldIndex);
		}
	}
	public static void main(String[] args) throws IOException {
		DecimalFormat df = new DecimalFormat("#.000000");
		
		BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter2Section2.4/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
		int numPast = Integer.parseInt(f.readLine());
		Past[] allPasts=new Past[numPast];
		for(int i=0;i<numPast;i++) {
			String[] inputs=f.readLine().split(" ",2);
			allPasts[i]=new Past(Integer.parseInt(inputs[0]),Integer.parseInt(inputs[1]));
		}
		
		boolean[][] pastConnectivity = new boolean[numPast][numPast];
		for(int r=0;r<numPast;r++) {
			char[] inputs=f.readLine().toCharArray();
			for(int c=0;c<numPast;c++) {
				pastConnectivity[r][c] = inputs[c]=='1' ? true : false;
				pastConnectivity[c][r] = pastConnectivity[r][c];
			}
		}
		
		int[] pastField = new int[numPast];
		int fieldCount=0;
		for(int pastI=0;pastI<numPast;pastI++) {
			if(pastField[pastI]==0) {
				fieldCount++;
				floodFill(pastI, pastConnectivity, numPast, pastField, fieldCount);
			}
		}
		

		
		double[][] shortestDist = new double[numPast][numPast];
		
		for(int i=0;i<numPast;i++)
			for(int j=0;j<numPast;j++)
				shortestDist[i][j]=Double.MAX_VALUE;
		for(int r=0;r<numPast;r++)
			shortestDist[r][r]=0;
		for(int r=0;r<numPast;r++)
			for(int c=r+1;c<numPast;c++)
				if(pastConnectivity[r][c]) {
					shortestDist[r][c]=allPasts[r].distance(allPasts[c]);
					shortestDist[c][r]=shortestDist[r][c];
				}
		
		for(int interPast=0;interPast<numPast;interPast++) 
			for(int past1=0;past1<numPast;past1++)
				for(int past2=past1+1;past2<numPast;past2++)
					if(shortestDist[past1][interPast]+shortestDist[interPast][past2] < shortestDist[past1][past2]) {
						shortestDist[past1][past2]=shortestDist[past1][interPast]+shortestDist[interPast][past2];
						shortestDist[past2][past1]=shortestDist[past1][past2];
					}
		
//		for(int i=0;i<numPast;i++) {
//			for(int j=0;j<numPast;j++)
//				System.out.print(shortestDist[i][j]+" ");
//			System.out.println();
//		}
		
		double[] diameters = new double[fieldCount];
		for(int fieldI=1;fieldI<=fieldCount;fieldI++) {
			for(int past1=0;past1<numPast;past1++) {
				if(pastField[past1]!=fieldI)
					continue;
				for(int past2=past1+1;past2<numPast;past2++) {
					if(pastField[past2]!=fieldI)
						continue;
					diameters[fieldI-1]=Math.max(diameters[fieldI-1], shortestDist[past1][past2]);
				}
			}
					
		}
		double minDia=Double.MAX_VALUE;
		for(int firstPast=0;firstPast<numPast;firstPast++) {
			double maxDist1=0;
			for(int otherPast=0;otherPast<numPast;otherPast++) {
				if(pastField[firstPast]!=pastField[otherPast])
					continue;
				maxDist1= Math.max(shortestDist[firstPast][otherPast],maxDist1);
			}
			
			for(int secondPast=firstPast+1; secondPast<numPast;secondPast++) {
				if(pastField[firstPast]==pastField[secondPast])
					continue;
				
				double maxDist2=0;
				for(int otherPast=0;otherPast<numPast;otherPast++) {
					if(pastField[secondPast]!=pastField[otherPast])
						continue;
					maxDist2= Math.max(shortestDist[secondPast][otherPast],maxDist2);
				}
				double newDia=maxDist1+maxDist2+allPasts[firstPast].distance(allPasts[secondPast]);
				double maxDia=newDia;
				for(int i=0;i<fieldCount;i++)
					maxDia=Math.max(diameters[i],maxDia);
				minDia= Math.min(minDia, maxDia);
			}
		}
		
		out.println(df.format(minDia));
		out.close();
		f.close();
	}
}
