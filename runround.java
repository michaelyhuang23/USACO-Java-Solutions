import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: yhuang22
LANG: JAVA
TASK: runround
*/
public class runround {
	public static boolean isRunaround(String number) {
		boolean[] digitUsed=new boolean[10];
		boolean[] posUsed=new boolean[number.length()];
		int index=0;
		do {
			posUsed[index]=true;
			int num=Character.getNumericValue(number.charAt(index));
			index=(index+num)%number.length();
			if((digitUsed[num]) || num%number.length()==0)
				return false;
			digitUsed[num]=true;
		} while(index!=0);
		for(int i=0;i<number.length();i++)
			if(!posUsed[i])
				return false;
		return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("runround.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section2.2/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		int startNum=Integer.parseInt(f.readLine());
		int i;
		for(i=startNum+1;true;i++) {
			if(isRunaround(Integer.toString(i)))
				break;
		}
		out.println(i);
		f.close();
		out.close();
	}
}
