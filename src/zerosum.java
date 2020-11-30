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
TASK: zerosum
*/
public class zerosum {
	static TreeSet<String> solutions=new TreeSet<String>();
	
	public static int evaluate(char[] signs) {
		int result=0;
		for(int i=0;i<signs.length;i++) {
			char sign;
			if(i-1<0)
				sign='+';
			else
				sign=signs[i-1];
			int number=i+1;
			while(i<signs.length && signs[i]==' ') {
				number*=10;
				number+=i+2;
				i++;
			}
			if(sign=='+')
				result+=number;
			else if(sign=='-')
				result-=number;
		}
		if(signs[signs.length-1]=='+')
			result+=signs.length+1;
		if(signs[signs.length-1]=='-')
			result-=signs.length+1;
		return result;
	}
	public static String getString(char[] signs) {
		StringBuffer buf=new StringBuffer();
		buf.append(1);
		for(int i=0;i<signs.length;i++) {
			buf.append(signs[i]);
			buf.append(i+2);
		}
		return buf.toString();
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("zerosum.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section2.2/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		int N=Integer.parseInt(f.readLine());
		char[] signs=new char[N-1];
		permuteSigns(0,signs);
		for(String solu:solutions) {
			out.println(solu);
		}
		f.close();
		out.close();
	}
	public static void permuteSigns(int index,char[] signs) {
		if(index==signs.length) {
			if(evaluate(signs)==0) {
				solutions.add(getString(signs));
				//System.out.println(signs);
			}
			return;
		}
		signs[index]='+';
		permuteSigns(index+1,signs);
		signs[index]='-';
		permuteSigns(index+1,signs);
		signs[index]=' ';
		permuteSigns(index+1,signs);
		
	}
}
