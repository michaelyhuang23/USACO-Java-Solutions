import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class cbarn {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cbarn.in")); //new FileReader("cbarn.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
		int numBarn = Integer.parseInt(f.readLine());
		PriorityQueue<Integer>[] barns = new PriorityQueue[numBarn];
		for(int i=0;i<numBarn;i++) {
			int numCow = Integer.parseInt(f.readLine());
			barns[i] = new PriorityQueue<Integer>();
			for(int h=0;h<numCow;h++)
				barns[i].offer(0);
		}
		for(int i=0;i<numBarn*2;i++) {
			int index = i%numBarn;
			while(barns[index].size()>1) {
				int cow = barns[index].poll();
				cow++;
				barns[(index+1)%numBarn].offer(cow);
			}
		}
		int energy = 0;
		for(int i=0;i<numBarn;i++) {
			int d = barns[i].poll();
			energy+=d*d;
		}
		out.println(energy);
		out.close();
		f.close();
	}
}
