import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: yhuang22
LANG: JAVA
TASK: ttwo
*/

public class ttwo {
	static class Pair{
		public int x,y;
		public Pair(int x, int y) {
			this.x=x;
			this.y=y;
		}
		public void addTo(Pair otherPair) {
			this.x+=otherPair.x;
			this.y+=otherPair.y;
		}
		public Pair add(Pair otherPair) {
			return new Pair(x+otherPair.x,y+otherPair.y);
		}
		public boolean isOutBound() {
			if(x>=0 && x<10 && y>=0 && y<10)
				return false;
			return true;
		}
		public void turn() {
			int temp=this.x;
			this.x=-this.y;
			this.y=temp;
		}
		public boolean equals(Object other) {
			Pair otherP = (Pair)other;
			return this.x==otherP.x && this.y==otherP.y;
		}
		public int getDirectionID() {
			if(x==1 && y==0)
				return 0;
			if(x==0 && y==1)
				return 1;
			if(x==-1 && y==0)
				return 2;
			if(x==0 && y==-1)
				return 3;
			return -1;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter2Section2.4/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
		boolean[][] landMap=new boolean[10][10];
		boolean[][][][][][] traversedPos = new boolean[10][10][4][10][10][4];
		Pair farmerPos = new Pair(0,0), cowPos = new Pair(0,0), farmerDir=new Pair(0,-1), cowDir=new Pair(0,-1);
		for(int i=0;i<10;i++) {
			char[] inputs=f.readLine().toCharArray();
			for(int c=0;c<10;c++) {
				switch(inputs[c]) {
				case '*':
					landMap[i][c]=true;
					break;
				case 'F':
					farmerPos=new Pair(c,i);
					break;
				case 'C':
					cowPos=new Pair(c,i);
					break;
				}
			}
		}
		int steps=0;
		while(true) {
			Pair newPos = farmerPos.add(farmerDir);
			if(newPos.isOutBound() || landMap[newPos.y][newPos.x])
				farmerDir.turn();
			else
				farmerPos.addTo(farmerDir);
			
			newPos = cowPos.add(cowDir);
			if(newPos.isOutBound() || landMap[newPos.y][newPos.x])
				cowDir.turn();
			else
				cowPos.addTo(cowDir);
			if(traversedPos[farmerPos.y][farmerPos.x][farmerDir.getDirectionID()][cowPos.y][cowPos.x][cowDir.getDirectionID()]) {
				steps=0;
				break;
			}
				
			traversedPos[farmerPos.y][farmerPos.x][farmerDir.getDirectionID()][cowPos.y][cowPos.x][cowDir.getDirectionID()]=true;
			steps++;
			if(farmerPos.equals(cowPos))
				break;
			
		}
		out.println(steps);
		out.close();
		f.close();
		
	}
}
