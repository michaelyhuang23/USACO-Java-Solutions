
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2668 {
	static int numGroup, numCard;

	private static int toNumber(int[] arr) {
		int counter = 0;
		int number = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int a = 0; a < arr[i]; a++) {
				number += 1 << (a + counter);
			}
			counter += standardCardForNum[i];
		}
		return number;
	}

	static int[] standardCardForNum;
	static int[] cardForNum;
	static int[] minSolution;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		numGroup = Integer.parseInt(st.nextToken());
		numCard = Integer.parseInt(st.nextToken());
		int[] solutions = new int[numGroup];
		for (int groupI = 0; groupI < numGroup; groupI++) {
			cardForNum = new int[15];
			for (int i = 0; i < numCard; i++) {
				st = new StringTokenizer(f.readLine());
				String firstToken = st.nextToken();
				if (firstToken.charAt(0) == '0') {
					cardForNum[14]++;
				} else {
					int number = Integer.parseInt(firstToken)-2;
					if(number<=0)
						number+=13;
					cardForNum[number]++;
				}
			}
			standardCardForNum = cardForNum.clone();
			minSolution = new int[1 << numCard];
			Arrays.fill(minSolution, numCard);
			playCard(1, 0);
			// playCard(1, 0);
			solutions[groupI]=minSolution[0];
			
		}
		for(int i=0;i<numGroup;i++)
			System.out.println(solutions[i]);
	}

	private static void playCard(int cardNum, int stepTaken) {
		int number = toNumber(cardForNum);

//		if (minSolution[number] < stepTaken) {
//			System.out.println("hello2");
//			return;
//		}
		if (minSolution[number] > stepTaken)
			minSolution[number] = stepTaken;
		if (cardNum >= 15) {
			int[] backup = cardForNum.clone();
			int newStep = stepTaken;
			for (int i = 1; i <= 14; i++) {
				if (cardForNum[i] == 0)
					continue;
				cardForNum[i] = 0;
				int newNumber = toNumber(cardForNum);
				newStep++;

				minSolution[newNumber]=Math.min(minSolution[newNumber], newStep);
			}
			cardForNum = backup;
			return;
		}
		if (cardForNum[cardNum] == 0) {
			playCard(cardNum + 1, stepTaken);
			return;
		}

		if (cardNum <= 12) {
			boolean triStop = false, douStop = false, sinStop = false;
			int triCounter = 0, douCounter = 0, sinCounter = 0;
			for (int j = cardNum; j <= 12; j++) {
				if (cardForNum[j] >= 3 && !triStop)
					triCounter++;
				else
					triStop = true;
				if (cardForNum[j] >= 2 && !douStop)
					douCounter++;
				else
					douStop = true;
				if (cardForNum[j] >= 1 && !sinStop)
					sinCounter++;
				else
					sinStop = true;
			}
			if (triCounter >= 2) {
				int[] backup1 = cardForNum.clone();
				for (int j = cardNum; j < cardNum + triCounter; j++) {
					cardForNum[j] -= 3;
					if (j - cardNum + 1 >= 2) {
						int[] backup = cardForNum.clone();
						playCard(cardNum, stepTaken + 1);
						cardForNum = backup;
					}
				}
				cardForNum = backup1;
			}
			if (douCounter >= 3) {
				int[] backup1 = cardForNum.clone();
				for (int j = cardNum; j < cardNum + douCounter; j++) {
					cardForNum[j] -= 2;
					if (j - cardNum + 1 >= 3) {
						int[] backup = cardForNum.clone();
						playCard(cardNum, stepTaken + 1);
						cardForNum = backup;
					}
				}
				cardForNum = backup1;
			}
			if (sinCounter >= 5) {
				int[] backup1 = cardForNum.clone();
				for (int j = cardNum; j < cardNum + sinCounter; j++) {
					cardForNum[j] -= 1;
					if (j - cardNum + 1 >= 5) {
						int[] backup = cardForNum.clone();
						playCard(cardNum, stepTaken + 1);
						cardForNum = backup;
					}
				}
				cardForNum = backup1;
			}
		}

		if (cardForNum[cardNum] == 4)
			for (int num2 = 1; num2 <= 14; num2++)
				if (cardForNum[num2] >= 1 && num2 != cardNum)
					for (int num3 = num2 + 1; num3 <= 14; num3++)
						if (cardForNum[num3] >= 1 && num3 != cardNum) {
							if (cardForNum[num3] >= 2 && cardForNum[num2] >= 2) {
								int[] backup = cardForNum.clone();
								cardForNum[cardNum] = 0;
								cardForNum[num3] -= 2;
								cardForNum[num2] -= 2;
								playCard(cardNum, stepTaken + 1);
								cardForNum = backup;
							}
							int[] backup = cardForNum.clone();
							cardForNum[cardNum] = 0;
							cardForNum[num3] -= 1;
							cardForNum[num2] -= 1;
							playCard(cardNum, stepTaken + 1);
							cardForNum = backup;
						}

		if (cardForNum[cardNum] >= 3)
			for (int num2 = 1; num2 <= 14; num2++) {
				if (cardForNum[num2] >= 1 && num2 != cardNum) {
					if (cardForNum[num2] >= 2) {
						int[] backup = cardForNum.clone();
						cardForNum[cardNum] -= 3;
						cardForNum[num2] -= 2;
						playCard(cardNum, stepTaken + 1);
						cardForNum = backup;
					}
					int[] backup = cardForNum.clone();
					cardForNum[cardNum] -= 3;
					cardForNum[num2] -= 1;
					playCard(cardNum, stepTaken + 1);
					cardForNum = backup;
				}

			}

		playCard(cardNum + 1, stepTaken);

	}

}
