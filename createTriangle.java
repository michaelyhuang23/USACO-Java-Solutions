import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
/*class position{
	public int x;
	public int y;
	
	public position(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public int hashCode() {
		return Double.hashCode(x*x/(y*y+1));
	}
	public boolean equals(Object o) {
		return x==((position)o).x && y==((position)o).y;
	}
}*/
public class createTriangle {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/michaelhyh/Project Data/Eclipse Java/USACO2/src/triangles.in")));
		int N=100000;
		out.println(N);
		Random rand=new Random();
		//Set<position> pos=new HashSet<position>();
		
		for(int i=0;i<N;i++) {
			out.println(i%10000+" "+(int)(i/10000));
			
		}
	}
}
