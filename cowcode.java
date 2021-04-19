import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowcode {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowcode.in")); //new FileReader("cowcode.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		String baseText = st.nextToken();
		long baseLength = baseText.length();
		long numPlace = Long.parseLong(st.nextToken());
		long currLen = baseLength;
		while(currLen<numPlace)
			currLen = currLen<<1;
		while(currLen>baseLength) {
			//System.out.println(numPlace+" "+currLen);
			currLen=currLen>>1;
			if(numPlace>currLen)
				numPlace = (numPlace-currLen-1)%currLen;
			if(numPlace<1)
				numPlace+=currLen;
		}
		//System.out.println(numPlace+" "+currLen);
		out.println(baseText.charAt((int) (numPlace-1)));
		out.close();
		f.close();
	}
}
