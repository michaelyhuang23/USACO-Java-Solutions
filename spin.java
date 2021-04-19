import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/*
ID: yhuang22
LANG: JAVA
TASK: spin
*/


class TwoPointInterval{
	int start, extent;

	public TwoPointInterval(int a, int b) {
		start=a;
		extent=b;
	}
	
	public String toString() {
		return start+" "+extent;
	}
}
class intervals{
	LinkedList<TwoPointInterval> allIntervals;
	public intervals() {
		allIntervals= new LinkedList<TwoPointInterval>();
	}
	
	public void addInterval(TwoPointInterval interv) {
		allIntervals.add(interv);
	}
	
	public void intersection(intervals others) {
		LinkedList<TwoPointInterval> newIntervals = new LinkedList<TwoPointInterval>();
		for(TwoPointInterval interv1:allIntervals) {
			for(TwoPointInterval interv2:others.allIntervals) {
				if(interv1.start>interv2.start) {
					if(interv2.start+interv2.extent>=interv1.start) {
						newIntervals.add(new TwoPointInterval(interv1.start,Math.min(interv2.start+interv2.extent,interv1.start+interv1.extent)-interv1.start));
					}
					if(interv1.start+interv1.extent>=interv2.start+360) {
						newIntervals.add(new TwoPointInterval(interv2.start,Math.min(interv1.start+interv1.extent,interv2.start+360+interv2.extent)-interv2.start-360));
					}	
				}else {
					if(interv1.start+interv1.extent>=interv2.start) {
						newIntervals.add(new TwoPointInterval(interv2.start,Math.min(interv1.start+interv1.extent,interv2.start+interv2.extent)-interv2.start));
					}
					if(interv2.start+interv2.extent>=interv1.start+360) {
						newIntervals.add(new TwoPointInterval(interv1.start,Math.min(interv2.start+interv2.extent,interv1.start+360+interv1.extent)-interv1.start-360));
					}
				}
			}
		}
		allIntervals = newIntervals;
	}
	public String toString() {
		String result="";
		for(TwoPointInterval interv : allIntervals) {
			result+=interv.toString()+", ";
		}
		return result;
	}
	
}
public class spin {
	public static int getPos(int n) {
		while(n>=360)
			n-=360;
		return n;
	}
	public static double getTruncatedLength(double n) {
		if(n>180)
			n=360-n;
		return n;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("spin.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter3Section3.2/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
		int[][] wedgeStartPos = new int[5][5];
		int[][] wedgeExtent = new int[5][5];
		int[] wheelSpeed = new int[5];
		for(int i=0;i<5;i++) {
			String[] inputs = f.readLine().split(" ");
			wheelSpeed[i] = Integer.parseInt(inputs[0]);
			int wedgeNum = Integer.parseInt(inputs[1]);
			for(int j=0; j<wedgeNum; j++) {
				wedgeStartPos[i][j] = Integer.parseInt(inputs[2*j+2]);
				wedgeExtent[i][j] = Integer.parseInt(inputs[2*j+3]);
			}
		}
//		for(int i=0;i<5;i++) {
//			for(int j=0;j<5;j++)
//				System.out.print(wedgeStartPos[i][j]+" ");
//			System.out.println();
//		}
//		System.out.println();
//		for(int i=0;i<5;i++) {
//			for(int j=0;j<5;j++)
//				System.out.print(wedgeExtent[i][j]+" ");
//			System.out.println();
//		}
		
		boolean bigSuccess=true;
		for(int t=0; t<360; t++) {
			bigSuccess=false;
			intervals[] wheelIntervals = new intervals[5];
			for(int wheel=0;wheel<5;wheel++) {
				wheelIntervals[wheel] = new intervals();
				for(int edge=0;edge<5;edge++) {
					if(wedgeExtent[wheel][edge]==0)
						break;
					wheelIntervals[wheel].addInterval(new TwoPointInterval(getPos(wedgeStartPos[wheel][edge]+wheelSpeed[wheel]*t),wedgeExtent[wheel][edge]));
				}
				//System.out.println(t+" "+wheel+" pre: "+wheelIntervals[wheel]);
				if(wheel>0) {
					wheelIntervals[0].intersection(wheelIntervals[wheel]);
				}
				//System.out.println(t+" "+wheel+" post: "+wheelIntervals[0]);
			}
			if(wheelIntervals[0].allIntervals.size()>0) {
				bigSuccess=true;
				out.println(t);
				break;
			}

		}
		if(!bigSuccess)
			out.println("none");
		
		f.close();
		out.close();
		
	}
}
