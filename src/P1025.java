import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1025 {
	static int counter=0;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int totalNum = Integer.parseInt(st.nextToken());
		int numParts = Integer.parseInt(st.nextToken());
		partition(totalNum,numParts,1);
		System.out.println(counter);
		
	}
	public static void partition(int leftNum, int leftParts, int lastPart) {
		if(leftParts==1) {
			counter++;
			return;
		}
			
		for(int thisPart=lastPart;thisPart<=(int)(leftNum/leftParts);thisPart++) {
			partition(leftNum-thisPart,leftParts-1,thisPart);
		}
	}
}
