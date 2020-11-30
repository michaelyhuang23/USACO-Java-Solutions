import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P4816 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numCard = Integer.parseInt(f.readLine());
		int[] opponentFirst = new int[numCard/2];
		int[] opponentSecond = new int[numCard/2];
		boolean[] cardUsed = new boolean[numCard*2];
		for(int i=0;i<numCard/2;i++) {
			opponentFirst[i] = Integer.parseInt(f.readLine())-1;
			cardUsed[opponentFirst[i]]=true;
		}
		for(int i=0;i<numCard/2;i++) {
			opponentSecond[i] = Integer.parseInt(f.readLine())-1;
			cardUsed[opponentSecond[i]]=true;
		}
		int[] myCards = new int[numCard];
		int counter=0;
		for(int i=0;i<numCard*2;i++)
			if(!cardUsed[i]) {
				myCards[counter]=i;
				counter++;
			}
		Arrays.sort(myCards);
		Arrays.sort(opponentFirst);
		Arrays.sort(opponentSecond);
		int point = 0;
		for(int i=numCard/2-1,j=numCard-1;i>=0 && j>=numCard/2;) {
			if(myCards[j]>opponentFirst[i]) {
				point++;
				i--;
				j--;
			}else {
				i--;
			}
		}
		
		for(int i=0,j=0;i<numCard/2 && j<numCard/2;) {
			if(myCards[j]<opponentSecond[i]) {
				point++;
				i++;
				j++;
			}else {
				i++;
			}
		}
		
		System.out.println(point);
		
	}
}
