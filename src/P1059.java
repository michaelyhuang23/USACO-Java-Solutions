import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class P1059 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numInt = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		TreeSet<Integer> allNums = new TreeSet<Integer>();
		for(int i=0;i<numInt;i++) 
			allNums.add(Integer.parseInt(st.nextToken()));
		System.out.println(allNums.size());
		StringBuffer str = new StringBuffer();
		for(int num : allNums)
			str.append(num+" ");
		str.deleteCharAt(str.length()-1);
		System.out.println(str);
	}
}
