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
TASK: prefix
*/
public class prefix {
	public static HashMap<Integer, Integer> prevResults=new HashMap<Integer,Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("prefix.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section2.2/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		ArrayList<String> primitives=new ArrayList<String>();
		while(true) {
			String input=f.readLine();
			if(input.equals("."))
				break;
			String inputs[]=input.split(" ");
			for(String i : inputs)
				primitives.add(i);
		}
		StringBuffer sequence=new StringBuffer();
		while(true) {
			String input=f.readLine();
			if(input==null)
				break;
			sequence.append(input);
		}
		
		//System.out.println(sequence);
		f.close();
		int[] maxStep=new int[sequence.length()];
		for(int i=sequence.length()-1;i>=0;i--) {
			int max=0;
			for(String part:primitives) {
				if(part.length()+i<=sequence.length() && part.equals(sequence.substring(i, i+part.length()))) {
					if(i+part.length()==sequence.length())
						max=Math.max(max, part.length());
					else
						max=Math.max(max, maxStep[i+part.length()]+part.length());
				}
			}
			maxStep[i]=max;
		}
		
		out.println(maxStep[0]);
		out.close();
		
		
	}

}
