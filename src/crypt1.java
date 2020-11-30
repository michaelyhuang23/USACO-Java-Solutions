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
TASK: crypt1
*/
public class crypt1 {
	public static boolean isGood(StringBuffer str, Set<Integer> availNum) {
		for(int i=0;i<str.length();i++) 
			if(!availNum.contains(Character.getNumericValue(str.charAt(i))))
				return false;
		return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.4/src/
		//File file=new File("/Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.4/src/barn1.out");
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
		int nums=Integer.parseInt(f.readLine());
		Set<Integer> availNum=new TreeSet<Integer>();
		String[] inputs=f.readLine().split(" ",nums);
		for(int i=0; i<nums; i++)
			availNum.add(Integer.parseInt(inputs[i]));
		int[] firstNum=new int[3];
		//int[] secondNum=new int[2];
		int successCases=0;
		for(int digit0:availNum) {
			firstNum[2]=digit0;
			for(int digit1:availNum) {
				firstNum[1]=digit1;
				for(int digit2:availNum) {
					firstNum[0]=digit2;
					if(digit2==0)
						continue;
					for(int digit20:availNum) {
						StringBuffer firstPart=new StringBuffer(Integer.toString(digit20*(firstNum[0]*100+firstNum[1]*10+firstNum[2])));
						if(firstPart.length()!=3 || !isGood(firstPart, availNum))
							continue;
						for(int digit21:availNum) {
							if(digit21==0)
								continue;
							StringBuffer secondPart=new StringBuffer(Integer.toString(digit21*(firstNum[0]*100+firstNum[1]*10+firstNum[2])));
							if(secondPart.length()!=3 || !isGood(secondPart, availNum))
								continue;
							StringBuffer finalResult=new StringBuffer(Integer.toString(Integer.parseInt(firstPart.toString())+Integer.parseInt(secondPart.toString())*10));
							if(isGood(finalResult,availNum)) {
								successCases++;
								//System.out.println(digit2+""+digit1+""+digit0+" * "+digit21+""+digit20+" = "+finalResult.toString());
							}
						}
					}
				}
			}
		}
		out.println(successCases);
		f.close();
		out.close();
	}
}
