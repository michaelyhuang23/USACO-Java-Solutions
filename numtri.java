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
TASK: numtri
*/
public class numtri {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.6/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		int numRows=Integer.parseInt(f.readLine());
		ArrayList<ArrayList<Integer>> triangle=new ArrayList<ArrayList<Integer>>();
		for(int i=1;i<=numRows;i++) {
			triangle.add(new ArrayList<Integer>());
			String[] inputs=f.readLine().split(" ", i);
			for(String input:inputs) {
				triangle.get(i-1).add(Integer.parseInt(input));
			}
		}
		
		for(int i=numRows-2;i>=0;i--) {
			for(int c=0;c<=i;c++) {
				triangle.get(i).set(c, calculate(triangle,i,c));
			}
		}
		//System.out.println(triangle);
		out.println(triangle.get(0).get(0));
		out.close();
		f.close();
	}
	public static int calculate(ArrayList<ArrayList<Integer>> triangle, int level, int c) {
		return triangle.get(level).get(c)+Math.max(triangle.get(level+1).get(c),triangle.get(level+1).get(c+1));
	}
}
