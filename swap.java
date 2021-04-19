import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.io.BufferedReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: yhuang23
LANG: JAVA
PROB: swap
*/
class Pair{
	public int left;
	public int right;
	public Pair(int left, int right) {
		this.left=left;
		this.right=right;
	}
}
class swap {
	//static ArrayList<ArrayList<Integer>> repetition=new ArrayList<ArrayList<Integer>>();
	static int[] memorizedSwaps;
	static int[] memorizedSingleSwap;
	static int steps;
	static File file;
	public static void swaps(ArrayList<Integer> cows, int startIndex, int endIndex) {
		for(int i=startIndex;i<=(int)((startIndex+endIndex)/2);i++) {
			int temp=cows.get(endIndex-i+startIndex);
			cows.set(endIndex-i+startIndex, cows.get(i));
			cows.set(i, temp);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static int allSwap(ArrayList<Integer> cows, ArrayList<Pair> pairs,int end) {
		//repetition.add((ArrayList<Integer>) cows.clone());
		//ArrayList<Integer> initial=(ArrayList<Integer>) cows.clone();
		int rept=0;
		do {
			swapFromMemory(cows);
			//repetition.add(clone);
			//System.out.println(rept);
			rept++;
		}
		while(rept<end);
		return rept;
	}
	public static void swapFromMemory(ArrayList<Integer> cows) {
		@SuppressWarnings("unchecked")
		ArrayList<Integer> clone=(ArrayList<Integer>) cows.clone();
		for(int i=0;i<cows.size();i++) {
			cows.set(memorizedSwaps[i],clone.get(i));
		}
		clone=null;
	}
	public static void singleSwapFromMemory(ArrayList<Integer> cows) {
		@SuppressWarnings("unchecked")
		ArrayList<Integer> clone=(ArrayList<Integer>) cows.clone();
		for(int i=0;i<cows.size();i++) {
			cows.set(memorizedSingleSwap[i],clone.get(i));
		}
		clone=null;
	}
	public static void memorizeSingleSwap(ArrayList<Integer> cows, ArrayList<Pair> pairs){
		for(Pair swapper:pairs) {
			swaps(cows, swapper.left, swapper.right);
		}
		for(int i=0;i<cows.size();i++) {
			memorizedSingleSwap[cows.get(i)-1]=i;
		}
	}
	public static void memorizeSwaps(ArrayList<Integer> cows, ArrayList<Pair> pairs,int numSwaps) {
		for(int i=0;i<numSwaps;i++) {
			singleSwapFromMemory(cows);
		}
		for(int i=0;i<cows.size();i++) {
			memorizedSwaps[cows.get(i)-1]=i;
		}
	}
	public static void main(String[] args) throws IOException { //there must be repetition because there are finite number of cows
		BufferedReader f = new BufferedReader(new FileReader("swap.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACO2/src/
		file=new File("swap.out");
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
		String[] inputs=f.readLine().split(" ",3);
		int numCows=Integer.parseInt(inputs[0]);
		int numPairs=Integer.parseInt(inputs[1]);
		steps=Integer.parseInt(inputs[2]);
		ArrayList<Integer> cows=new ArrayList<Integer>(numCows);
		for(int i=1;i<=numCows;i++) {
			cows.add(i);
		}
		//System.out.println(cows);
		memorizedSwaps=new int[numCows];
		memorizedSingleSwap=new int[numCows];
		ArrayList<Pair> pairs=new ArrayList<Pair>();
		for(int i=0;i<numPairs;i++) {
			String[] swapsPos=f.readLine().split(" ",2);
			pairs.add(new Pair(Integer.parseInt(swapsPos[0])-1,Integer.parseInt(swapsPos[1])-1));
		}
		int blockSize=(int)Math.sqrt(steps);
		int numBlock=(int)(steps/blockSize);
		int leftOverSwaps=steps%blockSize;
		memorizeSingleSwap(cows,pairs);
		for(int i=1;i<=numCows;i++) {
			cows.set(i-1,i);
		}
		memorizeSwaps(cows,pairs,blockSize);
		for(int i=1;i<=numCows;i++) {
			cows.set(i-1,i);
		}
		int rept=allSwap(cows,pairs,numBlock);
		for(int i=0;i<leftOverSwaps;i++)
			singleSwapFromMemory(cows);
			//cows=repetition.get(steps%rept);
		
		//System.out.println(steps%rept);
		//cows=repetition.get(steps%rept);
		//System.out.println(repetition);
		for(int i:cows) {
			out.println(i);
		}
		f.close();
		out.close();
	}
}
