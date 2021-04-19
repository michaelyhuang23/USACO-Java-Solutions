import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class tracing {
	public static void sort(int[] arr, int[] incumb1, int[] incumb2) {
		for(int pos=0; pos<arr.length;pos++) {
			int min=arr[pos];
			int minIndex=pos;
			for(int survey=pos+1;survey<arr.length;survey++)
				if(arr[survey]<min) {
					min=arr[survey];
					minIndex=survey;
				}
			int temp = arr[pos], inc1 = incumb1[pos], inc2 = incumb2[pos];
			arr[pos]=min;
			incumb1[pos]=incumb1[minIndex];
			incumb2[pos]=incumb2[minIndex];
			incumb1[minIndex] = inc1;
			incumb2[minIndex] = inc2;
			arr[minIndex]=temp;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("tracing.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACO4/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tracing.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numCows = Integer.parseInt(st.nextToken()), numInter = Integer.parseInt(st.nextToken());
		boolean[] infectionResults = new boolean[numCows];
		char[] inputs = f.readLine().toCharArray();
		for(int i=0;i<numCows;i++)
			infectionResults[i] = inputs[i] == '1' ? true : false;
		
		int[] interactionT = new int[numInter];
		int[] interactor1 = new int[numInter];
		int[] interactor2 = new int[numInter];
		int[] numInteractions = new int[numCows];
		for(int i=0;i<numInter;i++) {
			st = new StringTokenizer(f.readLine());
			interactionT[i] = Integer.parseInt(st.nextToken());
			interactor1[i] = Integer.parseInt(st.nextToken())-1;
			interactor2[i] = Integer.parseInt(st.nextToken())-1;
			numInteractions[interactor1[i]]++;
			numInteractions[interactor2[i]]++;
		}
		int maxInteraction = 0;
		for(int i=0;i<numCows;i++)
			maxInteraction = Math.max(maxInteraction, numInteractions[i]);
		
		sort(interactionT, interactor1, interactor2);
		int[] possiblePatient = new int[numCows];
		int minK=Integer.MAX_VALUE, maxK=0;
		boolean started=false;
		int infectionLimit;
		for(infectionLimit=0; infectionLimit<=maxInteraction; infectionLimit++) {
			boolean bigSuccess=false;
			for(int zeroPatient=0; zeroPatient<numCows; zeroPatient++) {
				if(!infectionResults[zeroPatient])
					continue;
				boolean[] infected = new boolean[numCows];
				infected[zeroPatient]=true;
				int[] numCurrentInter = new int[numCows];
				for(int currentInter=0;currentInter<numInter; currentInter++) {
					int inter1=interactor1[currentInter],inter2=interactor2[currentInter];
					boolean temp=infected[inter2];
					if(infected[inter1] && numCurrentInter[inter1]<infectionLimit) { 
						infected[inter2]=true;
						numCurrentInter[inter1]++;
					}
					if(temp && numCurrentInter[inter2]<infectionLimit) { 
						infected[inter1]=true;
						numCurrentInter[inter2]++;
					}

					
				}
				boolean secondCheck=true;
				for(int i=0;i<numCows;i++) {
					if(infectionResults[i]!=infected[i]) {
						secondCheck=false;
						break;
					}
				}
				if(secondCheck) {
					possiblePatient[zeroPatient]++;
					bigSuccess=true;
				}
				
			}

			if(bigSuccess) {
				minK=Math.min(minK, infectionLimit);
				maxK=Math.max(maxK, infectionLimit);
			}

				
			
		}
			
		int patientCount=0;
		for(int numOK : possiblePatient)
			if(numOK>0)
				patientCount++;
		if(maxK==maxInteraction)
			out.println(patientCount+" "+minK+" Infinity");
		else
			out.println(patientCount+" "+minK+" "+maxK);
		f.close();
		out.close();
	}
}
