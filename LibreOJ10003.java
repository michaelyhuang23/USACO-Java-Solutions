import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;


public class LibreOJ10003 {
	static class Pair implements Comparable<Pair>{
		int a, b, id;
		public Pair(int st, int e, int i) {
			a=st;
			b=e;
			id=i;
		}
		@Override
		public int compareTo(Pair o) {
			return a-o.a;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Pair> set1 = new ArrayList<Pair>();
		ArrayList<Pair> set2 = new ArrayList<Pair>();
		int numObj = Integer.parseInt(f.readLine());
		int[] timeA = new int[numObj];
		int[] timeB = new int[numObj];
		StringTokenizer st =new StringTokenizer(f.readLine());
		for(int i=0;i<numObj;i++)
			timeA[i] = Integer.parseInt(st.nextToken());
		st =new StringTokenizer(f.readLine());
		for(int i=0;i<numObj;i++)
			timeB[i] = Integer.parseInt(st.nextToken());
		for(int i=0;i<numObj;i++) {
			if(timeA[i]<timeB[i]) 
				set1.add(new Pair(timeA[i],timeB[i],i+1));
			else
				set2.add(new Pair(timeA[i],timeB[i],i+1));
		}
		set1.sort(null);
		set2.sort(new Comparator<Pair>() {
			public int compare(Pair first, Pair second) {
				return second.b-first.b;
			}
		});
		set1.addAll(set2);
		int thisTimeB=0;
		int thisTimeA=0;
		for(Pair job : set1) {
			thisTimeA += job.a;
			thisTimeB = Math.max(thisTimeB+job.b,thisTimeA+job.b);
		}
		System.out.println(thisTimeB);
		StringBuffer str = new StringBuffer();
		for(Pair job : set1) 
			str.append(job.id+" ");
		str.deleteCharAt(str.length()-1);
		System.out.println(str);
	}
}
