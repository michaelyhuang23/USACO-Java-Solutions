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
TASK: ariprog
*/
public class ariprog{
	class sequence implements Comparable<sequence>{
		public int startNum,interval,length;
		public sequence(int starter, int interv, int len) {
			startNum=starter;
			interval=interv;
			length=len;
		}
		@Override
		public int compareTo(sequence o) {
			if(interval-o.interval==0)
				return startNum-o.startNum;
			return interval-o.interval;
		}
		public boolean equals(Object other) {
			sequence newSeq=(sequence)other;
			return compareTo(newSeq)==0;
		}
		
		public String toString() {
			return startNum+" "+interval;
		}
		
	}
	public static void generateSequence(int upperBound, HashSet<Integer> allNums, TreeSet<Integer> allNumsSequenced) {
		for(int big=0;big<=upperBound;big++) 
			for(int small=0;small<=big;small++) {
				allNums.add(big*big+small*small);
				allNumsSequenced.add(big*big+small*small);
			}
	}
	public static boolean isPossible(int[] passedCases, int startNum, int interval, int length) {
		int num=startNum+interval;
		if(num>=passedCases.length || passedCases[num]==0) {
			passedCases[startNum]=1;
			return false;
		}else {
			int newNum=passedCases[num]+1;
			passedCases[startNum]=newNum;
			return newNum>=length;
		}
	}
	public static void main(String[] args) throws IOException {
		ariprog thisAri=new ariprog();
		BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.4/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		int length=Integer.parseInt(f.readLine());
		int upperBound=Integer.parseInt(f.readLine());
		HashSet<Integer> allNums=new HashSet<Integer>();
		TreeSet<Integer> allNumsList=new TreeSet<Integer>();
		generateSequence(upperBound, allNums,allNumsList);
		//System.out.println(allNumsList.size());
		TreeSet<sequence> allSeqs=new TreeSet<sequence>();
		
		LinkedList<Integer> newList=new LinkedList<Integer>();
		Iterator<Integer> iter=allNumsList.descendingIterator();
		while(iter.hasNext()) 
			newList.add(iter.next());
		int thres1=(newList.getFirst()-newList.getLast())/(length-1);
		//int count=0;
		//System.out.println(thres1);
		int[] passedCases;
		for(int interv=1;interv<=thres1;interv++) {
			//int thres2=newList.getFirst()-interv*(length-1);
			passedCases=new int[newList.getFirst()+1];
			for(int startNum : newList) {
				//System.out.println(startNum+" "+interv);
				
				if(isPossible(passedCases,startNum,interv,length)) {
					sequence seq=thisAri.new sequence(startNum,interv,length);
					allSeqs.add(seq);
				}
				//count++;
					
			}
		}
		//System.out.println(count);
//		count=0;
//		for(int startNum:allNumsList) {
//			if(startNum+length-1>allNumsList.last())
//				break;
//			int threshold=(allNumsList.last()-startNum)/(length-1);
//			for(int interv=1;interv<=threshold;interv++) {
//				sequence seq=thisAri.new sequence(startNum,interv,length);
//				//int temp=seq.isPossible(allNums);
//				int temp=0;
//				if(temp==-1)
//					allSeqs.add(seq);
//				count++;
//				//System.out.println(startNum+" "+interv);
//			}
//		}
//		System.out.println(count);
		if(allSeqs.isEmpty())
			out.println("NONE");
		for(sequence seq:allSeqs) {
			out.println(seq);
		}
		out.close();
		f.close();
	}
}
