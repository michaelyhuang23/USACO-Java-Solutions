import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class moobuzz {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("moobuzz.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));
		
		int NthNum = Integer.parseInt(f.readLine());
		int initialNum = (int)(NthNum/8.0)*15-1;
		int furtherCount = NthNum%8;
		int num;
		for(num = Math.max(initialNum+1,1); furtherCount>0; num++) {
			if(num%5==0 || num%3==0)
				continue;
			furtherCount--;
		}
		out.println(num-1);
		f.close();
		out.close();
	}
}
