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
TASK: combo
*/
public class combo {
	class Combination implements Comparable<Combination>{
		public int a,b,c;
		public int limit;
		public Combination(int a,int b,int c,int limit) {
			this.a=a;
			this.b=b;
			this.c=c;
			this.limit=limit;
		}
		public boolean equals(Object other) {
			Combination comb=(Combination)other;
			return a==comb.a && b==comb.b && c==comb.c;
		}
		public int compareTo(Combination o) {
			return a*(limit+1)*(limit+1)+b*(limit+1)+c-o.a*(limit+1)*(limit+1)-o.b*(limit+1)-o.c;
		}
		public String toString() {
			return "("+a+", "+b+", "+c+")";
		}
	}
	public static void main(String[] args) throws IOException {
		Set<Combination> allCombs=new TreeSet<Combination>();
		BufferedReader f = new BufferedReader(new FileReader("combo.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.4/src/
		//File file=new File("/Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.4/src/combo.out");
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
		combo thisCombo=new combo();
		int limitingSize=Integer.parseInt(f.readLine());
		for(int i=0;i<2;i++) {
			String[] inputs=f.readLine().split(" ",3);
			int a=Integer.parseInt(inputs[0]),b=Integer.parseInt(inputs[1]),c=Integer.parseInt(inputs[2]);
			for(int shift1=-2;shift1<=2;shift1++)
				for(int shift2=-2;shift2<=2;shift2++)
					for(int shift3=-2;shift3<=2;shift3++)
						allCombs.add(thisCombo.new Combination(changeIndex(shift1,limitingSize,a),changeIndex(shift2,limitingSize,b),changeIndex(shift3,limitingSize,c),limitingSize));
		}
		//System.out.println(allCombs);
		out.println(allCombs.size());
		out.close();
		f.close();
	}
	public static int changeIndex(int change, int limit, int original) {
		original+=change;
		if(original>limit)
			original%=limit;
		while(original<1)
			original+=limit;
		return original;
	}
}
