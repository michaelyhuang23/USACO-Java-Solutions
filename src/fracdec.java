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
TASK: fracdec
*/
public class fracdec {

	public static int getPlace (String num,int place) {
		if(place>num.length())
			return 0;
		return Character.getNumericValue(num.charAt(place-1));
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fracdec.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter2Section2.4/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
		String[] inputs = f.readLine().split(" ",2);
		int numerator = Integer.parseInt(inputs[0]);
		int denominator = Integer.parseInt(inputs[1]);
		String numeratorStr = inputs[0];
		int numeratorLength = inputs[0].length(), denominatorLength=inputs[1].length();
		int currentNumer = 0;

		StringBuffer resultingDigits=new StringBuffer();
		int correctingPower = 0;
		HashMap<Integer,Integer> traversedMod = new HashMap<Integer,Integer>();
		int currentPlace;
		for(currentPlace = 1;true;currentPlace++) {
			currentNumer = currentNumer*10+getPlace(numeratorStr,currentPlace);
			//System.out.println(currentNumer+" "+denominator);
			if(currentNumer < denominator) {
				resultingDigits.append(0);
				continue;
			}
			
			resultingDigits.append((int)(currentNumer/denominator));
			//System.out.println("end: "+currentNumer+" "+(int)(currentNumer/denominator)+" "+currentNumer%denominator);
			currentNumer%=denominator;
			if(currentPlace>=numeratorLength) {
				if(currentNumer==0 || traversedMod.keySet().contains(currentNumer))
					break;
				else
					traversedMod.put(currentNumer, currentPlace);
			}
			
		}
		if(currentNumer!=0) {

			int otherEnd =(int)(traversedMod.get(currentNumer));
			int i;
			for(i=1;true;i++) {
				if(resultingDigits.charAt(otherEnd-i)!=resultingDigits.charAt(currentPlace-i) || otherEnd-i<numeratorLength)
					break;
			}
			
			resultingDigits.insert(currentPlace-(i-1),')');
			resultingDigits.delete(currentPlace-i+2, resultingDigits.length());
			resultingDigits.insert(otherEnd-(i-1), '(');
		}
		resultingDigits.insert(numeratorLength, '.');
		while(true) {
			if(resultingDigits.charAt(0)!='0')
				break;
			resultingDigits.deleteCharAt(0);
		}
		if(resultingDigits.charAt(0)=='.')
			resultingDigits.insert(0, 0);
		if(resultingDigits.charAt(resultingDigits.length()-1)=='.')
			resultingDigits.append(0);

		for(int i=76;i<resultingDigits.length();i+=76) {
			resultingDigits.insert(i++, "\n");
		}
		out.println(resultingDigits);
		
		f.close();
		out.close();
		
	}
}
