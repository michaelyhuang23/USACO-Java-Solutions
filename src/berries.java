import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class berries {
	static class Tree implements Comparable<Tree>{
		int treeSize, divide, id;
		public Tree(int tS, int d, int index) {
			treeSize=tS;
			divide = d;
			id = index;
		}
		@Override
		public int compareTo(Tree o) {
			if(o.treeSize*(divide) - treeSize*(o.divide)==0)
				return id-o.id;
			return o.treeSize*(divide) - treeSize*(o.divide);
		}
		public boolean equals(Object o) {
			return compareTo((Tree)o)==0;
		}
		
	}
	static int[] allTrees;
	static int numTree, numBasket;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in)); //new FileReader("berries.in")  //new InputStreamReader(System.in)
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numTree = Integer.parseInt(st.nextToken());
		numBasket = Integer.parseInt(st.nextToken());
		allTrees = new int[numTree];
		st = new StringTokenizer(f.readLine());
		for(int i=0;i<numTree;i++)
			allTrees[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(allTrees);
		TreeSet<Tree> treeInBucket = new TreeSet<>();
		for(int i=0;i<Math.min(numTree, numBasket);i++)
			treeInBucket.add(new Tree(allTrees[numTree-i-1],1,i));
		if(numBasket>Math.min(numTree, numBasket))
			treeInBucket.add(new Tree(0,numBasket-Math.min(numTree, numBasket),Math.min(numTree, numBasket)));

		int maxBerryCount = 0;
		while(true) {
			Tree topTree = new Tree(0,1,-1);
			topTree.divide+=1;
			for(Tree t : treeInBucket) {
				t.divide+=1;
				if(t.compareTo(topTree)<0) {
					topTree.divide-=1;
					topTree=t;
				}
				else {
					t.divide-=1;
					if(topTree.compareTo(t)<0) 
						break;
				}
			}
			topTree.divide-=1;	
			if(!topTree.equals(treeInBucket.first()))
				System.out.println(topTree.treeSize+" "+topTree.divide+"   "+treeInBucket.first().treeSize+" "+treeInBucket.first().divide);
			Tree bottomTree = treeInBucket.last();
			treeInBucket.remove(topTree);
			treeInBucket.remove(bottomTree);
			topTree.divide+=1;
			if(topTree.compareTo(bottomTree)>=0) {
				topTree.divide-=1;
				treeInBucket.add(topTree);
				treeInBucket.add(bottomTree);
				break;
			}
			bottomTree.divide-=1;
			treeInBucket.add(topTree);
			if(bottomTree.divide>0)
				treeInBucket.add(bottomTree);
			System.out.println();
			int newBerryCount = countBerry(treeInBucket);
			System.out.println();
			if(newBerryCount>=maxBerryCount)
				maxBerryCount = newBerryCount;
			else
				break;
		}
		
		System.out.println(maxBerryCount);
		f.close();
		//out.close();
	}
	private static int countBerry(TreeSet<Tree> treeInBucket) {
		int bucketCount = 0;
		boolean started = false;
		int berryCount = 0;
		for(Tree t : treeInBucket) {
			System.out.println(t.treeSize+" "+t.divide);
			bucketCount+=t.divide;
			if(started) {
				berryCount+=t.treeSize;
				continue;
			}
			if(bucketCount>numBasket/2) {
				bucketCount-=t.divide;
				started = true;
				berryCount+=(t.treeSize/t.divide)*(bucketCount+t.divide-numBasket/2);
				if(bucketCount+t.treeSize%t.divide>numBasket/2) 
					berryCount+=bucketCount+t.treeSize%t.divide-numBasket/2;
			}
		}
		return berryCount;
	}
}
