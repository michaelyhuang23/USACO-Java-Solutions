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
TASK: hamming
*/
public class hamming {
	static int max=0;
	static int Distance;
	static int length;
	static TreeSet<Integer> solution=new TreeSet<Integer>();
	public static String getBinary(int n) {
		String initStr=Integer.toBinaryString(n);
		String zeros="";
		for(int i=0;i<(length-initStr.length());i++)
			zeros+="0";
		return zeros+initStr;
	}
	public static int distance(int num1, int num2) {
		String str1=getBinary(num1);
		String str2=getBinary(num2);
		int count=0;
		for(int i =0;i<str1.length();i++) 
			if(str1.charAt(i)!=str2.charAt(i))
				count++;
		return count;
	}
	public static TreeSet<Integer> getNexts(int min, TreeSet<Integer> prevNum) {
		TreeSet<Integer> choices=new TreeSet<Integer>();
		for(int i=min+1;i<max;i++) {
			boolean success=true;
			for(int num : prevNum) {
				if(distance(i,num)<Distance) {
					success=false;
					break;
				}
			}
			if(success)
				choices.add(i);
		}
		return choices;
	}
	
	public static boolean permute(int min, TreeSet<Integer> currentSet, int sizeLeft) {

		if(sizeLeft==0) {
			solution=currentSet;
			return true;
		}
		TreeSet<Integer> choices=getNexts(min,currentSet);
		for(int choice:choices) {
			currentSet.add(choice);
			if(permute(choice,currentSet,sizeLeft-1))
				return true;
			currentSet.remove(choice);
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section2.1/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		String[] inputs=f.readLine().split(" ",3);
		int setSize=Integer.parseInt(inputs[0]);
		length=Integer.parseInt(inputs[1]);
		Distance=Integer.parseInt(inputs[2]);
		max=(int)Math.round(Math.pow(2, length));
		if(permute(-1, new TreeSet<Integer>(), setSize)) {
			int counter=0;
			for(int num:solution) {
				counter++;
				if(counter==solution.size()) {
					out.print(num+"\n");
					break;
				}
				if(counter%10==0)
					out.print(num+"\n");
				else
					out.print(num+" ");
			}
		}
		else
			System.out.println("NONE");
		out.close();
		f.close();
		
	}
}
