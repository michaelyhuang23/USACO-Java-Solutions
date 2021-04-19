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
TASK: holstein
*/
public class holstein {
	static int[] requirements;
	static int[][]  allFeeds;
	static ArrayList<HashSet<Integer>> allSolutions=new ArrayList<HashSet<Integer>>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section2.1/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		int numTypes=Integer.parseInt(f.readLine());
		requirements=new int[numTypes];
		String[] inputs=f.readLine().split(" ",numTypes);
		for(int i=0;i<numTypes;i++) 
			requirements[i]=Integer.parseInt(inputs[i]);
		int numFeeds=Integer.parseInt(f.readLine());
		allFeeds=new int[numFeeds][numTypes];
		for(int r=0;r<numFeeds;r++) {
			String[] ins=f.readLine().split(" ",numTypes);
			for(int c=0;c<numTypes;c++)
				allFeeds[r][c]=Integer.parseInt(ins[c]);
		}
		for(int i=1;i<=numFeeds;i++) {
			permute(i,new HashSet<Integer>(),numFeeds,new int[numTypes],-1);
			if(!allSolutions.isEmpty()) {
				out.print(i);
				break;
			}
		}
		int min=-1;
		TreeSet<Integer> minSolu=new TreeSet<Integer>();
		for(HashSet<Integer> solu:allSolutions) {
			int size=0;
			for(int i:solu)
				size+=i;
			if(min==-1 || size<min) {
				min=size;
				minSolu=new TreeSet<Integer>(solu);
			}
		}
		for(int type:minSolu) {
			out.print(" "+(type+1));
		}
		out.println();
		f.close();
		out.close();
		
	}
	public static void addFeeds(int index,int[] result) {
		for(int i=0;i<result.length;i++) {
			result[i]+=allFeeds[index][i];
		}
	}
	public static void subFeeds(int index,int[] result) {
		for(int i=0;i<result.length;i++) {
			result[i]-=allFeeds[index][i];
		}
	}
	
	public static void permute(int scoops, HashSet<Integer> chosenFeeds, int numFeeds, int[] result, int lastFeed) {
		if(scoops==0) {
			boolean suc=true;
			for(int i=0;i<requirements.length;i++) 
				if(result[i]<requirements[i])
					suc=false;
			if(suc)
				allSolutions.add((HashSet<Integer>)chosenFeeds.clone());
		}
		for(int i=lastFeed+1;i<numFeeds;i++) {
			chosenFeeds.add(i);
			addFeeds(i,result);
			permute(scoops-1,chosenFeeds,numFeeds,result,i);
			subFeeds(i,result);
			chosenFeeds.remove(i);
		}
	}
}
