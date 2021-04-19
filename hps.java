import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class hps {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hps.in")); //new FileReader("hps.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		int numGame = Integer.parseInt(f.readLine());
		int[] farmerGests = new int[numGame];
		HashMap<Character,Integer> gestToNum = new HashMap<Character,Integer>();
		gestToNum.put('H', 0);
		gestToNum.put('P', 1);
		gestToNum.put('S', 2);
		int[][] gestureCounter = new int[3][numGame];
		int[] gestCounterTemp = new int[3];
		for(int i=0;i<numGame;i++) {
			farmerGests[i] = gestToNum.get(f.readLine().charAt(0));
			gestCounterTemp[farmerGests[i]]++;
			for(int j=0;j<3;j++)
				gestureCounter[j][i]=gestCounterTemp[j];
		}
		int maxWin=0;
		for(int switchAfter = 0; switchAfter<numGame; switchAfter++) {
			int frontMax=0;
			for(int i=0;i<3;i++)
				frontMax = Math.max(frontMax, gestureCounter[i][switchAfter]);
			int backMax = 0;
			for(int i=0;i<3;i++)
				backMax = Math.max(backMax, gestureCounter[i][numGame-1]-gestureCounter[i][switchAfter]);
			//System.out.println(switchAfter+" "+frontMax+" "+backMax);
			maxWin = Math.max(maxWin, frontMax+backMax);
		}
		//System.out.println(maxWin);
		out.println(maxWin);
		out.close();
		f.close();
		
	}
}
