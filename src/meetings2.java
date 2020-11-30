import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class meetings2 {
	static class Cow implements Comparable<Cow>{
		int weight, pos, dir;
		public Cow(int w, int p, int d) {
			weight =w;
			pos =p;
			dir =d;
		}
		@Override
		public int compareTo(Cow o) {
			return pos-o.pos;
		}
	}
	static int numCow, length,totalWeight;
	static Cow[] allCows;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("meetings.in")); //new FileReader("meetings.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numCow = Integer.parseInt(st.nextToken());
		length = Integer.parseInt(st.nextToken());
		allCows = new Cow[numCow];
		totalWeight=0;
		for(int i=0;i<numCow;i++) {
			st = new StringTokenizer(f.readLine());
			int w = Integer.parseInt(st.nextToken());
			allCows[i] = new Cow(w,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			totalWeight+=w;
		}
		Arrays.sort(allCows);
		int leftTB=0, rightTB=length;
		int curMinT=Integer.MAX_VALUE;
		while(leftTB<=rightTB) {
			int midT = (leftTB+rightTB)/2;
			if(check(midT)) {curMinT = midT; rightTB=midT-1;}
			else leftTB=midT+1;
		}
		int[] cowPos = new int[numCow];
		for(int i=0;i<numCow;i++) {
			cowPos[i] = allCows[i].pos+allCows[i].dir*curMinT;
		}
		mergeSort(0, numCow-1, cowPos);
		out.println(pairCounter);
		out.close();
		f.close();
	}
	static long pairCounter=0;
	public static void mergeSort(int start, int end, int[] arr) {
		if(end<=start)
			return;
		
		int mid = (start+end)/2;
		mergeSort(start,mid,arr);
		mergeSort(mid+1,end,arr);
		int[] sorted = new int[end-start+1];
		int i=start,j=mid+1,count=0;
		while(i<=mid && j<=end) {
			if(arr[i]<arr[j]) {
				sorted[count]=arr[i];
				i++;
			}else {
				sorted[count]=arr[j];
				pairCounter+=mid-i+1;
				j++;
			}
			count++;	
		}
		while(i<=mid) {
			sorted[count]=arr[i];
			count++;
			i++;
		}
		while(j<=end) {
			sorted[count]=arr[j];
			count++;
			j++;
		}
		
		for(int k=0;k<sorted.length;k++) {
			arr[start+k]=sorted[k];
		}

		return;
		
	}
	private static boolean check(int midT) {
		int leftCount=0,rightCount=0;
		for(int i=0;i<numCow;i++) {
			long newPos = allCows[i].pos+allCows[i].dir*midT;
			if(newPos<=0)
				leftCount++;
			if(newPos>=length)
				rightCount++;
		}
		long curWeight=0;
		for(int i=0;i<leftCount;i++) {
			curWeight+=allCows[i].weight;
		}
		for(int i=numCow-1; i>=numCow-rightCount; i--) {
			curWeight+=allCows[i].weight;
		}
		return curWeight*2>=totalWeight;
	}
	
}
