import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: yhuang22
LANG: JAVA
TASK: palsquare
*/

class palsquare {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		int base=Integer.parseInt(f.readLine());
		for(int i=1;i<=300;i++) 
			if(isParl(convertFromBaseToBase(Integer.toString(i*i),10,base)))
				out.println(convertFromBaseToBase(Integer.toString(i),10,base)+" "+convertFromBaseToBase(Integer.toString(i*i),10,base));	
		f.close();
		out.close();	
	}
	public static boolean isParl(String num) {
		for(int i=0;i<num.length()/2;i++) 
			if(num.charAt(i)!=num.charAt(num.length()-i-1))
				return false;
		return true;
	}
	public static String convertFromBaseToBase(String str, int fromBase, int toBase) {
	    return Integer.toString(Integer.parseInt(str, fromBase), toBase).toUpperCase();
	}

}
