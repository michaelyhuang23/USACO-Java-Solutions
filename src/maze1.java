import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/*
ID: yhuang22
LANG: JAVA
TASK: maze1
*/
class Pos{
	public int x,y;
	public Pos(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public boolean equals(Object other) {
		Pos otherP = (Pos)other;
		return this.x==otherP.x && this.y==otherP.y;
	}
	public String toString() {
		return x+" "+y;
	}
}
public class maze1 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("maze1.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter2Section2.4/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		String[] inputs=f.readLine().split(" ",2);
		int width = Integer.parseInt(inputs[0]), height = Integer.parseInt(inputs[1]);
		boolean[][] connectivityMapVerti = new boolean[height-1][width];
		boolean[][] connectivityMapHori = new boolean[height][width-1];
		Pos firstExit=null, secondExit=null;
		for(int h=0; h<height*2+1;h++) {
			char[] row = f.readLine().toCharArray();
			if(h%2==0) {
				for(int w=1;w<width*2+1;w+=2) {
					if(row[w]==' ') {
						if(h==0) {
							if(firstExit==null)
								firstExit=new Pos((w-1)/2,0);
							else
								secondExit=new Pos((w-1)/2,0);
						}else if(h==height*2) {
							if(firstExit==null)
								firstExit=new Pos((w-1)/2,height-1);
							else
								secondExit=new Pos((w-1)/2,height-1);
						}else {
							connectivityMapVerti[h/2-1][(w-1)/2]=true;
						}

					}
				}
			}else {
				for(int w=0;w<width*2+1;w+=2) {
					if(row[w]==' ') {
						if(w==0) {
							if(firstExit==null)
								firstExit=new Pos(0,(h-1)/2);
							else
								secondExit=new Pos(0,(h-1)/2);
						}else if(w==width*2) {
							if(firstExit==null)
								firstExit=new Pos(width-1,(h-1)/2);
							else
								secondExit=new Pos(width-1,(h-1)/2);
						}else {
							connectivityMapHori[(h-1)/2][w/2-1]=true;
						}

					}
				}
			}
				
		}
		
		Queue<Pos> connectedPoints=new LinkedList<Pos>();
		connectedPoints.add(firstExit);
		int[][] mapDistance1=new int[height][width];
		for(int r=0;r<height;r++)
			for(int c=0;c<width;c++)
				mapDistance1[r][c]=-1;
		mapDistance1[firstExit.y][firstExit.x]=1;
		while(!connectedPoints.isEmpty()) {
			Pos nextPoint = connectedPoints.remove();
			try {
				if(connectivityMapVerti[nextPoint.y][nextPoint.x] && mapDistance1[nextPoint.y+1][nextPoint.x]==-1) {
					mapDistance1[nextPoint.y+1][nextPoint.x]=mapDistance1[nextPoint.y][nextPoint.x]+1;
					connectedPoints.add(new Pos(nextPoint.x,nextPoint.y+1));
				}
			} catch(Exception e) {}
			try {
				if(connectivityMapVerti[nextPoint.y-1][nextPoint.x] && mapDistance1[nextPoint.y-1][nextPoint.x]==-1) {
					mapDistance1[nextPoint.y-1][nextPoint.x]=mapDistance1[nextPoint.y][nextPoint.x]+1;
					connectedPoints.add(new Pos(nextPoint.x,nextPoint.y-1));
				}
			} catch(Exception e) {}
			try {
				if(connectivityMapHori[nextPoint.y][nextPoint.x] && mapDistance1[nextPoint.y][nextPoint.x+1]==-1) {
					mapDistance1[nextPoint.y][nextPoint.x+1]=mapDistance1[nextPoint.y][nextPoint.x]+1;
					connectedPoints.add(new Pos(nextPoint.x+1,nextPoint.y));
				}
			} catch(Exception e) {}
			try {
				if(connectivityMapHori[nextPoint.y][nextPoint.x-1] && mapDistance1[nextPoint.y][nextPoint.x-1]==-1) {
					mapDistance1[nextPoint.y][nextPoint.x-1]=mapDistance1[nextPoint.y][nextPoint.x]+1;
					connectedPoints.add(new Pos(nextPoint.x-1,nextPoint.y));
				}
			} catch(Exception e) {}
		}
		
		
		connectedPoints=new LinkedList<Pos>();
		connectedPoints.add(secondExit);
		int[][] mapDistance2=new int[height][width];
		for(int r=0;r<height;r++)
			for(int c=0;c<width;c++)
				mapDistance2[r][c]=-1;
		mapDistance2[secondExit.y][secondExit.x]=1;
		while(!connectedPoints.isEmpty()) {
			Pos nextPoint = connectedPoints.remove();
			try {
				if(connectivityMapVerti[nextPoint.y][nextPoint.x] && mapDistance2[nextPoint.y+1][nextPoint.x]==-1) {
					mapDistance2[nextPoint.y+1][nextPoint.x]=mapDistance2[nextPoint.y][nextPoint.x]+1;
					connectedPoints.add(new Pos(nextPoint.x,nextPoint.y+1));
				}
			} catch(Exception e) {}
			try {
				if(connectivityMapVerti[nextPoint.y-1][nextPoint.x] && mapDistance2[nextPoint.y-1][nextPoint.x]==-1) {
					mapDistance2[nextPoint.y-1][nextPoint.x]=mapDistance2[nextPoint.y][nextPoint.x]+1;
					connectedPoints.add(new Pos(nextPoint.x,nextPoint.y-1));
				}
			} catch(Exception e) {}
			try {
				if(connectivityMapHori[nextPoint.y][nextPoint.x] && mapDistance2[nextPoint.y][nextPoint.x+1]==-1) {
					mapDistance2[nextPoint.y][nextPoint.x+1]=mapDistance2[nextPoint.y][nextPoint.x]+1;
					connectedPoints.add(new Pos(nextPoint.x+1,nextPoint.y));
				}
			} catch(Exception e) {}
			try {
				if(connectivityMapHori[nextPoint.y][nextPoint.x-1] && mapDistance2[nextPoint.y][nextPoint.x-1]==-1) {
					mapDistance2[nextPoint.y][nextPoint.x-1]=mapDistance2[nextPoint.y][nextPoint.x]+1;
					connectedPoints.add(new Pos(nextPoint.x-1,nextPoint.y));
				}
			} catch(Exception e) {}
		}

		int maxDistance=1;
		for(int r=0;r<height;r++) 
			for(int c=0;c<width;c++)
				maxDistance=Math.max(Math.min(mapDistance2[r][c],mapDistance1[r][c]),maxDistance);
		out.println(maxDistance);
		out.close();
		f.close();
	}
}
