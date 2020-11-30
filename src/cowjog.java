import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class cowjog {
	static class Cow{
		long pos, speed;
		public Cow(long p, long s) {
			pos = p;
			speed = s;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowjog.in")); //new FileReader("cowjog.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));
		int numCow, time;
		StringTokenizer st = new StringTokenizer(f.readLine());
		numCow = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		Cow[] allCows = new Cow[numCow];
		for(int i=0;i<numCow;i++) {
			st = new StringTokenizer(f.readLine());
			int pos = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			allCows[i] = new Cow(pos, speed);
		}
		Stack<Cow> cowStack = new Stack<>();
		cowStack.push(allCows[numCow-1]);
		for(int i=numCow-2; i>=0; i--) {
			Cow nextCow = cowStack.peek();
			if((allCows[i].speed-nextCow.speed)*time<nextCow.pos-allCows[i].pos)
				cowStack.push(allCows[i]);	
		}
		out.println(cowStack.size());
		out.close();
		f.close();
	}
}
