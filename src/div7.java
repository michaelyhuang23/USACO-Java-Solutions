import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class div7 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("div7.in")); //new FileReader("div7.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
		int numNum = Integer.parseInt(f.readLine());
		int[] prefixMod = new int[numNum];
		ArrayList<Integer>[] modCounter = new ArrayList[7];
		for(int i=0;i<7;i++)
			modCounter[i]=new ArrayList<Integer>();
		modCounter[0].add(-1);
		for(int i=0;i<numNum;i++) {
			if(i==0)
				prefixMod[i]=Integer.parseInt(f.readLine())%7;
			else
				prefixMod[i]=(prefixMod[i-1]+Integer.parseInt(f.readLine()))%7;
		}
		for(int i=0;i<numNum;i++) {
			modCounter[prefixMod[i]].add(i);
		}
		int maxLen = 0;
		for(int i=0;i<7;i++) {
			if(modCounter[i].size()==0)
				continue;
			maxLen = Math.max(maxLen, modCounter[i].get(modCounter[i].size()-1)-modCounter[i].get(0));
		}
		out.println(maxLen);
		out.close();
		f.close();
	}
}
