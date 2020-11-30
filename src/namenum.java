import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
/*
ID: yhuang22
LANG: JAVA
TASK: namenum
*/

class namenum {
	public static int wordSize;
	public static Set<String> dict;
	public static PrintWriter out;
	public static int wordCount=0;
	public static int[] process(long seri) {
		String[] temp = Long.toString(seri).split("");
		int[] result = new int[temp.length];
		for (int i = 0; i < temp.length; i++)
			result[i] = Integer.parseInt(temp[i]);
		return result;
	}

	public static void init(Map<Integer, Integer> converter) {
		for (int i = 2; i <= 7; i++)
			converter.put(i, 59 + i * 3);
		for (int i = 8; i <= 9; i++)
			converter.put(i, 60 + i * 3);

	}

	public static void permuteString(StringBuffer name, int[] num, Map<Integer, Integer> converter) {
		int k = name.length();
		if (k == wordSize) {
			if(dict.contains(name.toString())) {
				out.println(name);
				wordCount++;
			}
			name.setLength(Math.max(0, k-1));
			return;
				
		}
		int numb1=(int) converter.get(num[k]);
		int numb2=numb1+1;
		int numb3=numb2+1;
		if(num[k]==7) {
			numb2++;
			numb3++;
		}
		//if((char) (numb1)=='Y' || (char) (numb2)=='Y' || (char) (numb3)=='Y')
		//System.out.println((char) (numb1)+" "+(char) (numb2)+" "+(char) (numb3));
		name.append(Character.toString((char) (numb1)));
		permuteString(name, num, converter);
		name.append(Character.toString((char) (numb2)));
		permuteString(name, num, converter);
		name.append(Character.toString((char) (numb3)));
		permuteString(name, num, converter);
		name.setLength(Math.max(0, k-1));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
		BufferedReader dictReader = new BufferedReader(new FileReader("dict.txt"));
		dict = new HashSet<String>();
		Map<Integer, Integer> converter = new HashMap<Integer, Integer>();
		init(converter);
		String reading = "";
		while (reading != null) {
			reading = dictReader.readLine();
			dict.add(reading);
		}
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.3/src/
		out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		long serialNum = Long.parseLong(f.readLine());
		int[] digits = process(serialNum);
		StringBuffer name = new StringBuffer();
		wordSize = digits.length;
		//System.out.println(converter);
		permuteString(name,digits,converter);
		if(wordCount==0)
			out.println("NONE");
		f.close();
		out.close();
		dictReader.close();

	}
}
