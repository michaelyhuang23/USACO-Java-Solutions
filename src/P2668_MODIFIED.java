import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2668_MODIFIED {
	static int numGroup, numCard;
	private static int toNumber(int[] arr) {
		int counter=0;
		int number=0;
		for(int i=1; i<arr.length; i++) {
			for(int a=0;a<arr[i];a++) {
				number+=1<<(a+counter);
			}
			counter+=standardCardForNum[i];
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
		for(int groupI=0;groupI<numGroup;groupI++) {
			cardForNum = new int[15];
			for(int i=0;i<numCard;i++) {
				st = new StringTokenizer(f.readLine());
				String firstToken = st.nextToken();
				if(firstToken.charAt(0)=='0') {
					cardForNum[14]++;
				}else {
					int number = Integer.parseInt(firstToken);
					cardForNum[number]++;
				}
			}
			standardCardForNum=cardForNum.clone();
			minSolution = new int[1<<numCard];
			Arrays.fill(minSolution,numCard);
			playGame(numCard, 0);
			System.out.println(minSolution[0]);
		}
	}

	private static void playGame(int cardLeft, int stepTaken) {
		int number = toNumber(cardForNum);
		if(minSolution[number]<=stepTaken)
			return;
		minSolution[number]=stepTaken;
		if(cardLeft<=0) {
			return;
		}
		for(int num=3;num<=13;num++) {
			if(cardForNum[num]==0)
				continue;
			
			boolean triStop = false, douStop = false, sinStop = false;
			int triCounter=0,douCounter=0,sinCounter=0;
			for(int j=num;j<=13;j++) {
				if(cardForNum[j]>=3 && !triStop)
					triCounter++;
				else
					triStop=true;
				if(cardForNum[j]>=2 && !douStop)
					douCounter++;
				else
					douStop=true;
				if(cardForNum[j]>=1 && !sinStop)
					sinCounter++;
				else
					sinStop=true;
			}
			if(triCounter>=2) {
				int[] backup1 = cardForNum.clone();
				for(int j=num;j<num+triCounter;j++) {
					cardForNum[j]-=3;
					if(j-num+1>=2) {
						int[] backup = cardForNum.clone();
						playGame(cardLeft-(j-num+1)*3, stepTaken+1);
						cardForNum = backup;
					}
				}
				cardForNum = backup1;
			}
			if(douCounter>=3) {
				int[] backup1 = cardForNum.clone();
				for(int j=num;j<num+douCounter;j++) {
					cardForNum[j]-=2;
					if(j-num+1>=3) {
						int[] backup = cardForNum.clone();
						playGame(cardLeft-(j-num+1)*2, stepTaken+1);
						cardForNum = backup;
					}
				}
				cardForNum = backup1;
			}
			if(sinCounter>=5) {
				int[] backup1 = cardForNum.clone();
				for(int j=num;j<num+sinCounter;j++) {
					cardForNum[j]-=1;
					if(j-num+1>=5) {
						int[] backup = cardForNum.clone();
						playGame(cardLeft-(j-num+1), stepTaken+1);
						cardForNum = backup;
					}
				}
				cardForNum = backup1;
			}
		}
		
		for(int num=1;num<=14;num++) {
			if(cardForNum[num]==0)
				continue;
			if(cardForNum[num]==4) 
				for(int num2=1;num2<=14;num2++) 
					if(cardForNum[num2]>=1 && num2!=num)
						for(int num3=num2+1;num3<=14;num3++)
							if(cardForNum[num3]>=1 && num3!=num) {
								if(cardForNum[num3]>=2 && cardForNum[num2]>=2) {
									int[] backup = cardForNum.clone();
									cardForNum[num]=0;
									cardForNum[num3]-=2;
									cardForNum[num2]-=2;
									playGame(cardLeft-8, stepTaken+1);
									cardForNum=backup;
								}
								int[] backup = cardForNum.clone();
								cardForNum[num]=0;
								cardForNum[num3]-=1;
								cardForNum[num2]-=1;
								playGame(cardLeft-6, stepTaken+1);
								cardForNum=backup;
							}
		}
		for(int num=1;num<=14;num++) {
			if(cardForNum[num]==0)
				continue;
			if(cardForNum[num]>=3)
				for(int num2=1;num2<=14;num2++) {
					if(cardForNum[num2]>=2 && num2!=num) {
						int[] backup = cardForNum.clone();
						cardForNum[num]-=3;
						cardForNum[num2]-=2;
						playGame(cardLeft-5, stepTaken+1);
						cardForNum=backup;
					}
					if(cardForNum[num2]>=1 && num2!=num) {
						int[] backup = cardForNum.clone();
						cardForNum[num]-=3;
						cardForNum[num2]-=1;
						playGame(cardLeft-4, stepTaken+1);
						cardForNum=backup;
					}
				}
		}
		for(int many=4; many>=1; many--)
			for(int num=1;num<=14;num++) 
				if(cardForNum[num]>=many) {
					int[] backup = cardForNum.clone();
					cardForNum[num]-=many;
					playGame(cardLeft-many, stepTaken+1);
					cardForNum=backup;
				}			
			
		
		
	}
}
