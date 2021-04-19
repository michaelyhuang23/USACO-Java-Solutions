import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;
import java.util.*;
/*
ID: yhuang22
LANG: JAVA
TASK: subset
*/
public class subset {
	public static int required;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("subset.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section2.2/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		int setSize=Integer.parseInt(f.readLine());
		int total=0;
		for(int i=1;i<=setSize;i++) 
			total+=i;
		if(total%2==0) {
		required=total/2;
		out.println(permute(setSize,0,new HashMap<Integer,Long>())/2);
		}else {
			out.println(0);
		}
		out.close();
		f.close();
	}
	
	public static long permute(int number, int oldSum, HashMap<Integer, Long> ways) {
		if(number==0)
			return 0;
		int temp=required*2*number+oldSum;
		if(ways.containsKey(temp)) {
			return ways.get(temp);
		}
		long sum=permute(number-1,oldSum, ways);
		if(number+oldSum==required) {
			sum++;
		}
		if(number+oldSum<required) {
			sum+= permute(number-1,oldSum+number, ways);
		}
		ways.put(temp, sum);
		return sum;
	}
}
