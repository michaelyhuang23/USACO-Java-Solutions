import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class meetings {
	static int numCow, length;
	static class Cow implements Comparable<Cow>{
		int weight, pos, index, dir;
		public Cow(int w, int p, int d) {
			weight = w;
			pos = p;
			dir=d;
		}
		@Override
		public int compareTo(Cow o) {
			return pos - o.pos;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("meetings.in")); //new FileReader("meetings.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numCow = Integer.parseInt(st.nextToken());
		length = Integer.parseInt(st.nextToken());
		ArrayList<Cow> rightCow = new ArrayList<Cow>();
		ArrayList<Cow> leftCow = new ArrayList<Cow>();
		Cow[] allCows = new Cow[numCow];
		int totalWeight = 0;
		for(int i=0;i<numCow;i++) {
			st = new StringTokenizer(f.readLine());
			int w = Integer.parseInt(st.nextToken());
			int pos = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			Cow c = new Cow(w,pos,dir);
			totalWeight += w;
			if(dir==1) 
				rightCow.add(c);
			else
				leftCow.add(c);
			allCows[i] = c;
		}
		Arrays.sort(allCows);
		rightCow.add(new Cow(0,0,0));
		rightCow.sort(null);
		leftCow.sort(null);
		for(int i=0;i<numCow;i++)
			allCows[i].index=i;
		leftCow.add(new Cow(0,length,0));
		int leftI=0, rightI=0;
		int currentWeight = 0;
		int lastT=0;
		int collisionCount = 0;
		int[] rightToTheRight = new int[numCow];
		for(int i=numCow-1;i>=0;i--) {
			if(i!=numCow-1)
				rightToTheRight[i] = rightToTheRight[i+1];
			if(allCows[i].dir==1)
				rightToTheRight[i]++;
		}
		while(currentWeight*2<totalWeight) {
			if(leftCow.get(leftI).pos<length-rightCow.get(rightCow.size()-1-rightI).pos) {
				currentWeight+=allCows[leftI].weight;
				lastT = leftCow.get(leftI).pos;
				if(leftI<leftCow.size()-1)
					leftI++;
			}else {
				currentWeight+=allCows[numCow-1-rightI].weight;
				lastT = length-rightCow.get(rightCow.size()-1-rightI).pos;
				if(rightI<rightCow.size()-1)
					rightI++;
			}
		}
		
		for(int i=0;i<leftCow.size()-1;i++) {
			int result = Arrays.binarySearch(allCows, new Cow(0,leftCow.get(i).pos-2*lastT,0));
			if(result>=0) {
				collisionCount+=rightToTheRight[result]-rightToTheRight[leftCow.get(i).index];
			}else {
				collisionCount+=rightToTheRight[-result-1]-rightToTheRight[leftCow.get(i).index];
			}
		}
		
		out.println(collisionCount);
		f.close();
		out.close();
		
	}
}
