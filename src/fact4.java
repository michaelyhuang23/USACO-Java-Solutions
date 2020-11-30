import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: yhuang22
LANG: JAVA
TASK: fact4
*/
public class fact4 {
	public static int getLast6Digit(int n) {
		for(int i=0;true;i++) {
			int temp=(int)(n/Math.pow(10, i));
			if(temp%10!=0)
				return temp%1000000;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fact4.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter3Section3.2/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		
		int maxInt = Integer.parseInt(f.readLine());
		int product = 1;
		for(int i=1; i<=maxInt; i++) {
			product = getLast6Digit(product*i);
		}
		out.println(product%10);
		f.close();
		out.close();
	}
}
