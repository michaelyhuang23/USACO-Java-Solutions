import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mountains {
	static class Mount implements Comparable<Mount>{
		int leftIndicator, rightIndicator, x, y;
		public Mount(int x, int y) {
			this.x=x;
			this.y=y;
			leftIndicator=y-x;
			rightIndicator=y+x;
		}
		public int compareTo(Mount other) {
			if(other.leftIndicator-leftIndicator==0)
				return other.rightIndicator-rightIndicator;
			return other.leftIndicator-leftIndicator;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("mountains.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
		int numMount = Integer.parseInt(f.readLine());
		Mount[] allMounts = new Mount[numMount];
		StringTokenizer st;
		for(int i=0;i<numMount;i++) {
			st = new StringTokenizer(f.readLine());
			allMounts[i] = new Mount(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(allMounts);
		
		int currentLargestRightIndicator =0;
		int count=numMount;
		for(int i=0;i<numMount;i++) {
			if(allMounts[i].rightIndicator<=currentLargestRightIndicator)
				count--;
			currentLargestRightIndicator = Math.max(currentLargestRightIndicator, allMounts[i].rightIndicator);
		}
		out.println(count);
		f.close();
		out.close();
	}
}
