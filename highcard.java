import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class highcard {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("highcard.in")); //new FileReader("highcard.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
		int numCard = Integer.parseInt(f.readLine());
		int[] opponent = new int[numCard];
		boolean[] cardAvail = new boolean[2*numCard];
		int[] mine = new int[numCard];
		Arrays.fill(cardAvail, true);
		for(int i=0;i<numCard;i++) {
			int card = Integer.parseInt(f.readLine())-1;
			opponent[i]=card;
			cardAvail[card]=false;
		}
		int j=0;
		for(int i=0;i<numCard*2;i++) {
			if(cardAvail[i]) {
				mine[j]=i;
				j++;
			}
		}
		int pointCounter=0;
		for(int i=0;i<numCard;i++) {
			int card = opponent[i];
			int index=-Arrays.binarySearch(mine, card)-1;
			while(index<numCard && !cardAvail[mine[index]])
				index++;
			if(index>=numCard)
				continue;
			cardAvail[mine[index]]=false;
			pointCounter++;
		}
		out.println(pointCounter);
		out.close();
		f.close();
	}
}
