import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

/*
ID: yhuang22
LANG: JAVA
TASK: contact
*/
class PatternFreq implements Comparable<PatternFreq>{
	public String pattern;
	public int frequency;
	public PatternFreq(String pattern, int freq) {
		this.pattern=pattern;
		this.frequency=freq;
	}
	public int compareTo(PatternFreq otherP) {
		if(this.frequency == otherP.frequency) {
			if(this.pattern.length() == otherP.pattern.length())
				return Integer.parseUnsignedInt(otherP.pattern, 2) - Integer.parseUnsignedInt(pattern, 2);
			else
				return otherP.pattern.length()-this.pattern.length();
		}
		return this.frequency - otherP.frequency;
	}
	public boolean equals(Object o) {
		return compareTo((PatternFreq) o)==0;
	}
}
public class contact {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("contact.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter3Section3.1/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
		String[] inputs = f.readLine().split(" ",3);
		int minLength = Integer.parseInt(inputs[0]), maxLength = Integer.parseInt(inputs[1]), numFreq = Integer.parseInt(inputs[2]);
		StringBuffer inputString = new StringBuffer();
		String input="";
		do {
			inputString.append(input);
			input = f.readLine();
		}while(input!=null);
		
		String allBits = inputString.toString();
		HashMap<String, Integer> allPatterns = new HashMap<String, Integer>();
		for(int length = minLength; length <= maxLength; length++) {
			for(int startingIndex = 0; startingIndex + length <= allBits.length(); startingIndex++) {
				String currentPattern = allBits.substring(startingIndex, startingIndex+length);
				try {
					allPatterns.put(currentPattern,allPatterns.get(currentPattern)+1);
				}catch(Exception e) {
					allPatterns.put(currentPattern, 1);
				}
			}
		}
		PatternFreq[] patterns = new PatternFreq[allPatterns.size()];

		int index=0;
		for(String bit:allPatterns.keySet()) {
			patterns[index] = new PatternFreq(bit, allPatterns.get(bit));
			index++;
		}
		Arrays.parallelSort(patterns);
//		for(int i=0;i<patterns.length;i++)
//			System.out.println(patterns[i].pattern+" "+patterns[i].frequency);
		int count=0;
		int prevFreq=0;
		int countPattern=0;
		for(int i = patterns.length-1; i>=0; i--) {
			if(patterns[i].frequency!=prevFreq) {
				if(count==numFreq) {
					break;
				}
					
				count++;
				prevFreq=patterns[i].frequency;
				if(count!=1)
					out.println("\n"+prevFreq);
				else
					out.println(prevFreq);
				out.print(patterns[i].pattern);
				countPattern=1;
				continue;
			}
			if(countPattern%6 == 0)
				out.print("\n"+patterns[i].pattern);
			else
				out.print(" "+patterns[i].pattern);
			countPattern++;
		}
		out.println();
		f.close();
		out.close();
	}
}
