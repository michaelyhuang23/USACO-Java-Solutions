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
TASK: frac1
*/
public class frac1 {
	class Fraction implements Comparable<Fraction>{
		public int numerator,denominator;
		public Fraction(int nume,int deno) {
			numerator=nume;
			denominator=deno;
		}
		public double getDoubleValue() {
			return (double)numerator/(double)denominator;
		}
		public int compareTo(Fraction other) {
			double val=getDoubleValue()-other.getDoubleValue();
			if(val<0)
				return -1;
			if(val>0)
				return 1;
			return 0;
		}
		public String toString() {
			return numerator+"/"+denominator;
		}
	}
	public static void main(String[] args) throws IOException {
		frac1 thisFrac=new frac1();
		BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section2.1/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		int maxDeno=Integer.parseInt(f.readLine());
		TreeSet<Fraction> allFracs=new TreeSet<Fraction>();
		for(int deno=1;deno<=maxDeno;deno++) {
			for(int nume=0;nume<=deno;nume++) {
				if(relativelyPrime(nume,deno))
					allFracs.add(thisFrac.new Fraction(nume,deno));
			}
		}
		for(Fraction frac:allFracs) {
			out.println(frac);
		}
		out.close();
		f.close();
	}
	public static boolean relativelyPrime(int nume, int deno) {
		int min=Math.min(nume, deno);
		for(int i=2;i<=min/2;i++)
			if(nume%i==0 && deno%i==0)
				return false;
		return true;
	}
}
