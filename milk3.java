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
TASK: milk3
*/
public class milk3 {
	public static int aMax, bMax, cMax;
	class State implements Comparable<State>{
		public int a, b, c;
		public State(int a, int b, int c) {
			this.a=a;
			this.b=b;
			this.c=c;
		}
		public boolean equals(Object other) {
			State o=(State)other;
			return a==o.a && b==o.b && c==o.c;
		}
		public int hashCode() {
			return a*aMax*bMax*cMax*13+b*bMax*cMax*11+c*cMax*7;
		}
		public int compareTo(State st) {
			return c-st.c;
		}
		public State pourAtoB() {
			int exch=Math.min(a, bMax-b);
			return new State(a-exch,b+exch,c);
		}
		public State pourBtoA() {
			int exch=Math.min(aMax-a, b);
			return new State(a+exch,b-exch,c);
		}
		public State pourAtoC() {
			int exch=Math.min(a, cMax-c);
			return new State(a-exch,b,c+exch);
		}
		public State pourCtoA() {
			int exch=Math.min(aMax-a, c);
			return new State(a+exch,b,c-exch);
		}
		public State pourBtoC() {
			int exch=Math.min(b, cMax-c);
			return new State(a,b-exch,c+exch);
		}
		public State pourCtoB() {
			int exch=Math.min(bMax-b, c);
			return new State(a,b+exch,c-exch);
		}
	}
	
	static HashSet<State> triedStates = new HashSet<State>();
	static TreeSet<State> goodStates = new TreeSet<State>();
	public static void main(String[] args) throws IOException {
		milk3 milker=new milk3();
		BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.4/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		String[] inputs=f.readLine().split(" ",3);
		aMax = Integer.parseInt(inputs[0]);
		bMax = Integer.parseInt(inputs[1]); 
		cMax = Integer.parseInt(inputs[2]);
		permute(milker.new State(0,0,cMax));
		StringBuffer result=new StringBuffer();
		for(State eachState:goodStates) {
			result.append(eachState.c);
			result.append(" ");
		}
		result.deleteCharAt(result.length()-1);
		out.println(result);
		f.close();
		out.close();
	}
	public static void permute(State st) {
		if(triedStates.contains(st))
			return;
		else {
			triedStates.add(st);
			if(st.a==0)
				goodStates.add(st);
		}
		if(Math.min(st.a, bMax-st.b)>0)
			permute(st.pourAtoB());
		if(Math.min(st.b, aMax-st.a)>0)
			permute(st.pourBtoA());
		if(Math.min(st.a, cMax-st.c)>0)
			permute(st.pourAtoC());
		if(Math.min(st.c, aMax-st.a)>0)
			permute(st.pourCtoA());
		if(Math.min(st.b, cMax-st.c)>0)
			permute(st.pourBtoC());
		if(Math.min(st.c, bMax-st.b)>0)
			permute(st.pourCtoB());
	}
}
