import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class stampede {
	static class Cow implements Comparable<Cow>{
		int start, end, y;
		public Cow(int s, int e, int h) {
			start = s;
			end = e;
			y = h;
		}
		@Override
		public int compareTo(Cow o) {
			return y - o.y;
		}
	}
	static class Separator implements Comparable<Separator>{
		int position;
		boolean isStart;
		Separator other;
		public Separator(int pos, boolean fo) {
			position = pos;
			isStart = fo;
		}
		@Override
		public int compareTo(Separator o) {
			return position-o.position;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("stampede.in")); //new FileReader("stampede.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stampede.out")));
		int numCow = Integer.parseInt(f.readLine());
		Cow[] allCows = new Cow[numCow];
		for(int i=0;i<numCow;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int timeTraverse = Integer.parseInt(st.nextToken());
			allCows[i] = new Cow(-x*timeTraverse-timeTraverse,-x*timeTraverse,y);
		}
		Arrays.sort(allCows);
		int count=0;
		TreeSet<Separator> separators = new TreeSet<Separator>();
		for(int i=0;i<numCow;i++) {
			Separator end = new Separator(allCows[i].end, false);
			Separator start = new Separator(allCows[i].start, true);
			start.other=end;
			end.other=start;
			Separator endPost = separators.ceiling(end);
			Separator startPre = separators.floor(start);
			if(startPre!=null && endPost!=null && startPre.isStart && !endPost.isStart && startPre.other==endPost) //this one is covered already
				continue;
			count++;
			startPre = separators.lower(start);
			endPost = separators.higher(end);
			if(startPre!=null && startPre.isStart)
				start.position = startPre.position;
			if(endPost!=null && !endPost.isStart)
				end.position = endPost.position;
			Separator endPre = separators.floor(end);
			Separator startPost = separators.ceiling(start);
			if(startPost!=null && endPre!=null && startPost.compareTo(endPre)<=0) {
				TreeSet<Separator> subset = (TreeSet<Separator>) separators.subSet(startPost,true,endPre,true);
				subset = (TreeSet<Separator>) subset.clone();
				for(Separator sep : subset)
					separators.remove(sep);
			}
			separators.add(start);
			separators.add(end);
		}
		out.println(count);
		out.close();
		f.close();
	}
}
