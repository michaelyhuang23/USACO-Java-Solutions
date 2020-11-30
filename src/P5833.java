import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5833 {
	static boolean[][] links;
	static String[] allCows = { "Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue" };
	static int[] currentCows = new int[8];
	static boolean[] cowUsed = new boolean[8];
	public static int findCowIndex(String cow) {
		for(int i=0;i<8;i++)
			if(cow.equals(allCows[i]))
				return i;
		return -1;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numConstraints = Integer.parseInt(f.readLine());
		links=new boolean[8][8];
		for(int i=0;i<numConstraints;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int cow1Index = findCowIndex(st.nextToken());
			st.nextToken();
			st.nextToken();
			st.nextToken();
			st.nextToken();
			int cow2Index = findCowIndex(st.nextToken());
			links[cow1Index][cow2Index]=true;
			links[cow2Index][cow1Index]=true;
		}
		permute(0,numConstraints);
	}
	public static void permute(int index,int constraintLeft) {
		if(index>=8) {
			if(constraintLeft==0) {
				for(int i=0;i<8;i++)
					System.out.println(allCows[currentCows[i]]);
				System.exit(0);
			}
			return;
		}
		for(int cow=0;cow<8;cow++) {
			if(cowUsed[cow])
				continue;
			currentCows[index]=cow;
			cowUsed[cow]=true;
			if(index>0 && links[currentCows[index-1]][cow]) {
				links[currentCows[index-1]][cow]=false;
				links[cow][currentCows[index-1]]=false;
				permute(index+1,constraintLeft-1);
				links[currentCows[index-1]][cow]=true;
				links[cow][currentCows[index-1]]=true;
			}else {
				permute(index+1,constraintLeft);
			}			
			cowUsed[cow]=false;
		}
	}
}
