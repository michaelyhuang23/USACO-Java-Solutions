import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
/*
ID: yhuang22
LANG: JAVA
TASK: lamps
*/
public class lamps {
	public static String getBinary(int[] binary) {
		StringBuffer result=new StringBuffer();
		for(int i=0;i<binary.length;i++)
			result.append(binary[i]);
		return result.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lamps.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section2.2/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		int numLamps=Integer.parseInt(f.readLine());
		int numSwitch=Integer.parseInt(f.readLine());
		int[] lamps=new int[numLamps];
		int[] finalLamps=new int[numLamps];
		for(int i=0;i<numLamps;i++) {
			lamps[i]=1;
			finalLamps[i]=-1;
		}
		TreeSet<String> solutions=new TreeSet<String>();
		String[] onLamps=f.readLine().split(" ");
		String[] offLamps=f.readLine().split(" ");
		for(int i=0;i<onLamps.length-1;i++)
			finalLamps[Integer.parseInt(onLamps[i])-1]=1;
		for(int i=0;i<offLamps.length-1;i++)
			finalLamps[Integer.parseInt(offLamps[i])-1]=0;
		
		for(int first=0;first<2;first++) {
			if(first==1)
				for(int i=0;i<numLamps;i++)
					lamps[i]=1-lamps[i];
			for(int second=0;second<2;second++) { 
				if(second==1)
					for(int i=0;i<numLamps;i+=2)
						lamps[i]=1-lamps[i];
				for(int third=0;third<2;third++) {
					if(third==1)
						for(int i=1;i<numLamps;i+=2)
							lamps[i]=1-lamps[i];
					for(int forth=0;forth<2;forth++) {
						if((first+second+third+forth)>numSwitch || (first+second+third+forth)%2!=numSwitch%2)
							continue;
						if(forth==1)
							for(int i=0;i<numLamps;i+=3)
								lamps[i]=1-lamps[i];
						boolean success=true;
						for(int i=0;i<numLamps;i++) {
							if(finalLamps[i]==-1)
								continue;
							if(finalLamps[i]!=lamps[i]) {
								success=false;
								break;
							}
						}
						if(success) {
							//System.out.println(getBinaryLength(getBinary(lamps),numLamps)+"  "+first+" "+second+" "+third+" "+forth);
							solutions.add(getBinary(lamps));
						}
						if(forth==1)
							for(int i=0;i<numLamps;i+=3)
								lamps[i]=1-lamps[i];
					}
					if(third==1)
						for(int i=1;i<numLamps;i+=2)
							lamps[i]=1-lamps[i];
				}
				if(second==1)
					for(int i=0;i<numLamps;i+=2)
						lamps[i]=1-lamps[i];
			}
			if(first==1)
				for(int i=0;i<numLamps;i++)
					lamps[i]=1-lamps[i];
		}
		if(solutions.isEmpty())
			out.println("IMPOSSIBLE");
		for(String i : solutions) {
			out.println(i);
		}
		out.close();
		f.close();
		
	}
}
