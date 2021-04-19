
/*
ID: yhuang23
LANG: JAVA
PROB: word
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class word {	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("word.in"));
        // input file name goes above /Users/michaelhyh/Project Data/Eclipse Java/word/src/word/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("word.out")));
		String[] nums=f.readLine().split(" ", 2);
		int N=Integer.parseInt(nums[0]);
		int K=Integer.parseInt(nums[1]);
		String[] words=f.readLine().split(" ", N);
		ArrayList<StringBuffer> results=new ArrayList<StringBuffer>();
		int lastLineNum=0;
		for(String word : words) {
			if(results.size()==0 || results.get(results.size()-1).length()-lastLineNum+1+word.length()>K) {
				results.add(new StringBuffer(word));
				lastLineNum=1;
			}else {
				results.get(results.size()-1).append(" "+word);
				lastLineNum++;
			}
		}
		for(StringBuffer line : results)
			out.println(line);
		f.close();
		out.close();
	}
}
