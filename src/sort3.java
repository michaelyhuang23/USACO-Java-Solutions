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
TASK: sort3
*/
public class sort3 {
	class Pair implements Comparable<Pair>{
		public int lower,higher,priority;
		public Pair(int lo, int hi, int prio) {
			lower=lo;
			higher=hi;
			priority=prio;
		}
		public int compareTo(Pair other) {
			return other.priority-priority;
		}
		public void swap(ArrayList<Integer> unsorted) {
			int temp=unsorted.get(lower);
			unsorted.set(lower, unsorted.get(higher));
			unsorted.set(higher, temp);
		}
		public String toString() {
			return lower+" "+higher+" "+priority;
		}
	}
	public static void main(String[] args) throws IOException {
		sort3 thisSorter=new sort3();
		BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section2.1/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		int length=Integer.parseInt(f.readLine());
		ArrayList<Integer> unsorted=new ArrayList<Integer>();
		ArrayList<Integer> sorted;
		for(int i=0;i<length;i++)
			unsorted.add(Integer.parseInt(f.readLine()));
		sorted=(ArrayList<Integer>) unsorted.clone();
		sorted.sort(null);
		int count=0;//!sorted.equals(unsorted)
		while(!sorted.equals(unsorted)) {
			ArrayList<Pair> allSwaps=new ArrayList<Pair>();
			for(int higher=1;higher<length;higher++) {
				for(int lower=0;lower<higher;lower++) {
					allSwaps.add(thisSorter.new Pair(lower,higher,getPriority(lower,higher,unsorted,sorted)));
											
				}
			}
			allSwaps.sort(null);
			HashSet<Integer> allIndices=new HashSet<Integer>();
			for(Pair swap:allSwaps) {
				if(allIndices.contains(swap.higher) || allIndices.contains(swap.lower))
					continue;
				allIndices.add(swap.higher);
				allIndices.add(swap.lower);
				if(swap.priority<=0) {
					break;
				}
				swap.swap(unsorted);
				count++;
			}
			//System.out.println();
			
		}
		out.println(count);
		f.close();
		out.close();
	}
	public static int getPriority(int lower, int higher, ArrayList<Integer> unsorted, ArrayList<Integer> sorted) {
		return getState(sorted.get(lower),sorted.get(higher),unsorted.get(lower),unsorted.get(higher))-getState(sorted.get(lower),sorted.get(higher),unsorted.get(higher),unsorted.get(lower));
	}
	public static int getState(int a1,int a2,int b1,int b2) {
		int first,second;
		if(a1==b2)
			first=1;
		else
			first=0;
		if(a2==b1)
			second=1;
		else
			second=0;
		return first + second;
	}
}
