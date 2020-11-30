import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: yhuang22
LANG: JAVA
TASK: dualpal
*/
public class dualpal {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
		String[] inputs=f.readLine().split(" ",2);
		//String[] inputs= {"3","25"};
		int numberNeeded=Integer.parseInt(inputs[0]);
		int startNum=Integer.parseInt(inputs[1]);
		for(int i=startNum+1;numberNeeded>0;i++) {
			int numBase=0;
			for(int base=2;base<=10;base++) {
				if(isParl(convertFromBaseToBase(Integer.toString(i),10,base)))
					numBase++;
				if(numBase==2) {
					out.println(i);
					numberNeeded--;
					break;
				}
					
			}
			
		}
					
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
