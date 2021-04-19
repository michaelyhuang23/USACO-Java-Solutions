import java.io.BufferedReader;
import java.math.*;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
/*
ID: yhuang22
LANG: JAVA
TASK: nocows
*/
public class nocows {
	static int nodeNum,height;
	public static HashMap<Integer,Integer> solutions=new HashMap<Integer,Integer>();


	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section2.2/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		String[] inputs=f.readLine().split(" ",2);
		nodeNum=Integer.parseInt(inputs[0]);
		height=Integer.parseInt(inputs[1]);
		out.println(countPermute(height-1,nodeNum-1,1)%9901);
		f.close();
		out.close();
		
		
	}
	
	public static long choose(int chos, int from) {
		double preResult=1;
		for(int i=from; i>chos;i--) {
			preResult*=i;
			preResult/=i-chos;
		}
		return ((long)Math.round(preResult))%9901;
	}
	public static int hash(int h, int node, int leaves) {
		return h*height*nodeNum*13+nodeNum*node*11+leaves*7;
	}
	public static int countPermute(int heightLeft, int nodeLeft, int lastLeavesNum) {
		if(heightLeft<=0 || nodeLeft-heightLeft*2<0) {
			if(heightLeft==0 && nodeLeft==0) {
				return 1;
			}
			return 0;
		}
		int hashed=hash(heightLeft,nodeLeft,lastLeavesNum);
		if(solutions.containsKey(hashed))
			return solutions.get(hashed);
		int result=0;
		for(int i=1;i<=lastLeavesNum;i++) {
			int temp=countPermute(heightLeft-1,nodeLeft-i*2,i*2);
			result+=(choose(i,lastLeavesNum)*temp)%9901;
			//System.out.println(lastLeavesNum);
		}
		result%=9901;
		solutions.put(hashed, result);
		//System.out.println(result);
		return result;
	}
	
}
