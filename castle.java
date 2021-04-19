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
TASK: castle
*/
public class castle {
	
	public static int groupNum=1;
	public static TreeSet<Bridge> allBridge=new TreeSet<Bridge>();
	class Bridge implements Comparable<Bridge>{
		public Group g1,g2;
		public int r,c,size;
		public char direction;
		public Bridge(Group g1,Group g2,int r,int c,char direction) {
			this.g1=g1;
			this.g2=g2;
			this.r=r;
			this.c=c;
			this.direction=direction;
			size=g1.groupSize+g2.groupSize;
		}
		
		public int compareTo(Bridge other) { //in priority
			if(size==other.size) {
				if(c==other.c) {
					if(r==other.r)
						return (int)other.direction-(int)direction;
					return other.r-r;
				}
				return c-other.c;
			}
			return other.size-size;
		}
		
	}
	
	class Group implements Comparable<Group>{
		public int groupN;
		public int groupSize;
		public Group(int size) {
			groupN=groupNum;
			groupSize=size;
		}
		public int compareTo(Group other) {
			return groupSize-other.groupSize;
		}
	}
	public static void main(String[] args) throws IOException {
		castle thisCastle=new castle();
		BufferedReader f = new BufferedReader(new FileReader("castle.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section2.1/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		String[] inputs = f.readLine().split(" ",2);
		int colNum=Integer.parseInt(inputs[0]), rowNum=Integer.parseInt(inputs[1]);
		String[][] matrix=new String[rowNum][colNum];
		int[][] groupedMatrix=new int[rowNum][colNum];
		for(int r=0;r<rowNum;r++) {
			String[] cols=f.readLine().split(" ",colNum);
			for(int c=0;c<colNum;c++) 
				matrix[r][c]=getBinaryString(Integer.parseInt(cols[c]));
		}
		f.close();
		ArrayList<Group> groupSize=new ArrayList<Group>();
		Map<Integer, Group> groupMap=new HashMap<Integer,Group>();
		for(int r=0;r<rowNum;r++) {
			for(int c=0;c<colNum;c++) {
				if(groupedMatrix[r][c]<=0) {
					Group newG=thisCastle.new Group(floodFill(r,c,matrix,groupedMatrix));
					groupSize.add(newG);
					groupMap.put(groupNum, newG);
					groupNum++;
				}
				//System.out.print(groupedMatrix[r][c]+" ");
			}
			//System.out.println();
		}
		
		groupSize.sort(null);
		out.println(groupSize.size());
		out.println(groupSize.get(groupSize.size()-1).groupSize);
		
		for(int r=0;r<rowNum;r++) 
			for(int c=0;c<colNum;c++) 
				if(matrix[r][c]!="") 
					reFlood(groupMap,r,c,matrix,groupedMatrix,groupMap.get(groupedMatrix[r][c]));
		out.println(allBridge.first().size);
		out.println((allBridge.first().r+1)+" "+(allBridge.first().c+1)+" "+allBridge.first().direction);
		out.close();
		
	}
	public static int floodFill(int r,int c,String[][] matrix, int[][] groupedMatrix) {
		if(r<0 || r>=matrix.length || c<0 || c>=matrix[r].length || groupedMatrix[r][c]>0)
			return 0;
		groupedMatrix[r][c]=groupNum;
		int result=1;
		if(matrix[r][c].charAt(0)=='0') 
			result+=floodFill(r+1,c,matrix,groupedMatrix);
		if(matrix[r][c].charAt(1)=='0') 
			result+=floodFill(r,c+1,matrix,groupedMatrix);
		if(matrix[r][c].charAt(2)=='0') 
			result+=floodFill(r-1,c,matrix,groupedMatrix);
		if(matrix[r][c].charAt(3)=='0') 
			result+=floodFill(r,c-1,matrix,groupedMatrix);
		return result;
	}
	
	public static void reFlood(Map<Integer, Group> groupMap,int r,int c,String[][] matrix, int[][] groupedMatrix, Group startGroup) {
		if(matrix[r][c]=="")
			return;
		castle thisCastle=new castle();
		if(matrix[r][c].charAt(1)=='1') 
			if(c<groupedMatrix[r].length-1 && groupedMatrix[r][c+1]!=startGroup.groupN) {
				allBridge.add(thisCastle.new Bridge(startGroup, groupMap.get(groupedMatrix[r][c+1]),r,c,'E'));
			}
		if(matrix[r][c].charAt(2)=='1') 
			if(r>0 && groupedMatrix[r-1][c]!=startGroup.groupN) {
				allBridge.add(thisCastle.new Bridge(startGroup, groupMap.get(groupedMatrix[r-1][c]),r,c,'N'));
			}
		
		matrix[r][c]="";
		if(r<groupedMatrix.length-1 && groupedMatrix[r+1][c]==startGroup.groupN) 
			reFlood(groupMap,r+1,c,matrix,groupedMatrix,startGroup);
		if(c<groupedMatrix[r].length-1 && groupedMatrix[r][c+1]==startGroup.groupN) 
			reFlood(groupMap,r,c+1,matrix,groupedMatrix,startGroup);
		if(r>0 && groupedMatrix[r-1][c]==startGroup.groupN) 
			reFlood(groupMap,r-1,c,matrix,groupedMatrix,startGroup);
		if(c>0 && groupedMatrix[r][c-1]==startGroup.groupN) 
			reFlood(groupMap,r,c-1,matrix,groupedMatrix,startGroup);
	}
	
	public static String getBinaryString(int num) {
		String binary=Integer.toBinaryString(num);
		while(binary.length()<4) 
			binary="0"+binary;
		return binary;
	}
}
