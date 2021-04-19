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
TASK: barn1
*/

class reversedComparator implements Comparator<Integer>{
	@Override
	public int compare(Integer o1, Integer o2) {
		return o2-o1;
	}
}
class barn1 {
	public static void main(String[] args) throws IOException {
		PriorityQueue<Integer> tops = new PriorityQueue<Integer>(new reversedComparator());
		boolean[] stalls;
		BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.4/src/
		//File file=new File("/Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.4/src/barn1.out");
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
		String[] inputs=f.readLine().split(" ",3);
		int maxBoards, numStalls, numCows;
		maxBoards=Integer.parseInt(inputs[0]);
		numStalls=Integer.parseInt(inputs[1]);
		numCows=Integer.parseInt(inputs[2]);
		stalls=new boolean[numStalls];
		for(int i=0;i<numStalls;i++)
			stalls[i]=false;
		for(int i=0;i<numCows;i++)
			stalls[Integer.parseInt(f.readLine())-1]=true;
		int min;
		for(min=0;!stalls[min];min++);
		int max;
		for(max=numStalls-1;!stalls[max];max--);
		for(int i=min;i<=max;i++) {
			int num=0;
			//System.out.print(i+" ");
			while(i+num<=max && !stalls[i+num]) 
				num++;
			i=i+num;
			//System.out.println(i);
			tops.add(num);
			//System.out.println(num);
		}
		int result=0;
		
		for(int i=0;i<maxBoards-1;i++) {
			//System.out.println(tops.peek());
			if(tops.peek()==0)
				break;
			result+=tops.remove();
		}
	
		out.println(max-min+1-result);
		f.close();
		out.close();
	}
}
