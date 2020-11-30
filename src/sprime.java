import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;



/*
ID: yhuang22
LANG: JAVA
TASK: sprime
*/
public class sprime {
	public static boolean isPrime(int num) {
		for(int i=3;i<=Math.sqrt(num)+1;i+=2) 
			if(num%i==0)
				return false;
		if((num!=2 && num%2==0) || num==1)
			return false;
		return true;

	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.6/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		int digits=Integer.parseInt(f.readLine());
		f.close();
		permute(0,digits,0);
		for(int i:allSprimes) {
			out.println(i);
		}
		out.close();
	}
	public static TreeSet<Integer> allSprimes=new TreeSet<Integer>();
	public static void permute(int number,int length, int index) {
		if(index==length) {
			allSprimes.add(number);
			return;
		}
		for(int i=1;i<=9;i++) {
			if(isPrime(number*10+i))
				permute(number*10+i,length,index+1);
		}
	}
}
