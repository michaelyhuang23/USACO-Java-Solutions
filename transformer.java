/*
ID: yhuang22
LANG: JAVA
TASK: transform
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class transform{
	public static int size;
	public static Set<Integer> comboSolutions;
	public static int[] trans(int type, int c, int r) {
		switch(type) {
		case 1:
			int oldRow=r;
			r=c;
			c=size-1-oldRow;
			break;
		case 2:
			int[] newPos=trans(1,c,r);
			newPos=trans(1,newPos[0],newPos[1]);
			c=newPos[0];
			r=newPos[1];
			break;
		case 3:
			int oldCol=c;
			c=r;
			r=size-1-oldCol;
			break;
		case 4:
			c=size-1-c;
			break;
		default:
			break;
		}
		return new int[]{c,r};	
	}
	public static boolean isTrue(char[][] board, char[][] boardAft,int type, int c, int r) {
		if(type!=5) {
			int[] newPos=trans(type,c,r);
			return board[r][c]==boardAft[newPos[1]][newPos[0]];
		}else {
			int[] newPos=trans(4,c,r);
			if(comboSolutions.isEmpty())
				return false;
			Iterator<Integer> iter=comboSolutions.iterator();
			while(iter.hasNext()) { 
				int p=iter.next();
				int[] newerPos=trans(p,newPos[0],newPos[1]);
				if(board[r][c]!=boardAft[newerPos[1]][newerPos[0]])
					iter.remove();
			}
			return !comboSolutions.isEmpty();
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("transform.in"));
        // input file name goes above /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.3/src
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		size=Integer.parseInt(f.readLine());
		char[][] board = new char[size][size];
		char[][] boardAft = new char[size][size];
		for(int row=0;row<size;row++) 			
			board[row]=f.readLine().toCharArray();
		for(int row=0;row<size;row++) 			
			boardAft[row]=f.readLine().toCharArray();
				
		comboSolutions=new TreeSet<Integer>();
		comboSolutions.add(1);
		comboSolutions.add(2);
		comboSolutions.add(3);
		boolean[] statements= {true, true, true, true, true, true, true};
		for(int col=0;col<size;col++) 
			for(int row=0;row<size;row++) {
				//System.out.println(board[row][col]+" and "+boardAft[row][col]);
				for(int i=1;i<7;i++) 
					statements[i-1]=statements[i-1] && isTrue(board,boardAft,i,col,row);
			}

		for(int i=0;i<7;i++) {
			//System.out.println(statements[i]);
			if(statements[i]) {
				out.println(i+1);
				break;
			}
		}

		out.close();
		f.close();
	}
}
