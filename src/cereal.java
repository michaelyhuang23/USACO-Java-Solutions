import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class cereal {
	static class Breakfast{
		int firstChoice, secondChoice, curChoice, firstChoiceIndex, secondChoiceIndex;
		public Breakfast(int f, int s) {
			firstChoice = f;
			secondChoice = s;
		}		
	}
	static ArrayList<Breakfast>[] cerealWant;
	static int satisfiedCow;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cereal.in")); //new FileReader("cereal.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
		int numCow, numCereal;
		StringTokenizer st = new StringTokenizer(f.readLine());
		numCow = Integer.parseInt(st.nextToken());
		numCereal = Integer.parseInt(st.nextToken());
		Breakfast[] allCows = new Breakfast[numCow];
		boolean[] cerealUsed = new boolean[numCereal];
		cerealWant = new ArrayList[numCereal];
		for(int i=0;i<numCereal;i++)
			cerealWant[i] = new ArrayList<Breakfast>();
		for(int i=0;i<numCow;i++) {
			st = new StringTokenizer(f.readLine());
			Breakfast newB = new Breakfast(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1);
			if(!cerealUsed[newB.firstChoice]) {
				cerealUsed[newB.firstChoice]=true;
				newB.curChoice=1;
			}else if(!cerealUsed[newB.secondChoice]) {
				cerealUsed[newB.secondChoice]=true;
				newB.curChoice=2;
			}else
				newB.curChoice=3;
			newB.firstChoiceIndex=cerealWant[newB.firstChoice].size();
			newB.secondChoiceIndex=cerealWant[newB.secondChoice].size();
			cerealWant[newB.firstChoice].add(newB);
			cerealWant[newB.secondChoice].add(newB);
			allCows[i]=newB;
		}
		satisfiedCow = 0;
		for(int i=0;i<numCow;i++)
			if(allCows[i].curChoice<3)
				satisfiedCow++;
		
		for(int i=0;i<numCow;i++) {
			out.println(satisfiedCow);
			if(allCows[i].curChoice==1) 
				allowCereal(allCows[i].firstChoice, allCows[i].firstChoiceIndex+1);
			else
				allowCereal(allCows[i].secondChoice, allCows[i].secondChoiceIndex+1);
		}
		out.close();
		f.close();
		
	}
	private static void allowCereal(int cereal, int index) {
		if(index>=cerealWant[cereal].size()) {
			satisfiedCow--;
			return;
		}
		Breakfast thisBreakfast = cerealWant[cereal].get(index);
		int cerealPos=1;
		if(thisBreakfast.firstChoice==cereal) 
			cerealPos=1;
		else 
			cerealPos=2;
		
		if(thisBreakfast.curChoice<cerealPos) {
			allowCereal(cereal, index+1);
			return;
		}
		if(thisBreakfast.curChoice!=2) {
			thisBreakfast.curChoice=cerealPos;
			return;
		}
		thisBreakfast.curChoice=cerealPos;
		allowCereal(thisBreakfast.secondChoice,thisBreakfast.secondChoiceIndex+1);
	}
}
