import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class P3605 {
	static int numCow;
	static Cow[] allCows;
	static int[] bit;
	private static int lowbit(int x) {
		return x&(-x);
	}
	private static int getSum(int index) {
		int sum=0;
		for(int i=index;i>0;i-=lowbit(i))
			sum+=bit[i];
		return sum;
	}
	private static void update(int index, int value) {
		for(int i=index; i<=numCow; i+=lowbit(i))
			bit[i]+=value;
	}
	static class Cow implements Comparable<Cow>{
		int profic, parent, subordinateCount, answer, treeRank, nextSiblingTreeRank;
		ArrayList<Integer> children;
		public Cow(int profic, int parent, int id) {
			this.profic=profic;
			this.parent=parent;
			children = new ArrayList<Integer>();
		}
		@Override
		public int compareTo(Cow o) {
			return o.profic-profic;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		numCow = Integer.parseInt(f.readLine());
		allCows = new Cow[numCow+1];
		allCows[0]=new Cow(0,0,0);
		for(int i=1;i<=numCow;i++)
			allCows[i]=new Cow(Integer.parseInt(f.readLine()),0,i);
		for(int i=2;i<=numCow;i++) {
			int father = Integer.parseInt(f.readLine());
			allCows[i].parent=father;
			allCows[father].children.add(i);
		}
		treeIndex=1;
		dfs(allCows[1]);
		Cow[] treeRankedCows = allCows.clone();
		Arrays.sort(treeRankedCows,1,numCow+1,new Comparator<Cow>() {
			public int compare(Cow o1, Cow o2) {
				return o1.treeRank-o2.treeRank;
			}
		});
//		for(int i=1;i<=numCow;i++)
//			System.out.println(allCows[i].treeRank);
//		System.out.println();
		treeRankedCows[1].nextSiblingTreeRank=numCow+1;
		for(int i=1;i<=numCow;i++) {
			for(int childI=0; childI<treeRankedCows[i].children.size()-1; childI++) {
				int thisChild = treeRankedCows[i].children.get(childI);
				int nextChild = treeRankedCows[i].children.get(childI+1);
				allCows[thisChild].nextSiblingTreeRank=allCows[nextChild].treeRank;
			}
			if(treeRankedCows[i].children.size()>0)
				allCows[treeRankedCows[i].children.get(treeRankedCows[i].children.size()-1)].nextSiblingTreeRank = treeRankedCows[i].nextSiblingTreeRank;
		}
//		for(int i=1;i<=numCow;i++)
//			System.out.println(allCows[i].treeRank+" "+allCows[i].nextSiblingTreeRank);
		//System.out.println("finish 1");
		Cow[] sortedCows = allCows.clone();
		Arrays.sort(sortedCows, 1, numCow+1);
		bit = new int[numCow+1];
		//System.out.println("finish 2");
		for(int i=1;i<=numCow;i++) {
			int numSubord = getSum(sortedCows[i].nextSiblingTreeRank-1)-getSum(sortedCows[i].treeRank);
			//System.out.println(numSubord);
			sortedCows[i].answer = numSubord;
			update(sortedCows[i].treeRank,1);
		}
		StringBuilder result = new StringBuilder();
		for(int i=1;i<=numCow;i++) {
			result.append(allCows[i].answer);
			result.append('\n');
		}
		System.out.print(result);
		
		
	}
	static int treeIndex = 1;
	private static void dfs(Cow start) {
		Stack<Cow> stacker = new Stack<>();
		stacker.push(start);
		while(!stacker.isEmpty()) {
			Cow s = stacker.pop();
			s.treeRank=treeIndex;
			treeIndex++;
			for(int childI=s.children.size()-1; childI>=0; childI--) {
				stacker.push(allCows[s.children.get(childI)]);
			}
		}
	}


}
