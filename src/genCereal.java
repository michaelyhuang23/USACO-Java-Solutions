import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class genCereal {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/michaelhyh/Project Data/Eclipse Java/USACO3/src/cereal.in")));
		Random rand=new Random();
		out.println(100+" "+5);
		for(int i=0;i<100;i++) {
			out.println((rand.nextInt(5)+1)+" "+(rand.nextInt(5)+1));
		}
		out.close();
	}
}
