import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P1981 {
	static int startPos=0, index=0;
	static final int MOD =10000;
	public static int readNum(String str) {
		for(;index<str.length();index++) {
			if(!Character.isDigit(str.charAt(index))) {
				int result = Integer.parseInt(str.substring(startPos,index));
				startPos=index+1;
				return result;
			}
		}
		return 0;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String express = br.readLine()+"#";
		Stack<Integer> operations = new Stack<Integer>();
		operations.push(readNum(express)%MOD);
		while(express.charAt(index)!='#') {
			if(express.charAt(index)=='+') {
				index++;
				operations.push(readNum(express)%MOD);
			}else {
				index++;
				operations.push((readNum(express)*operations.pop())%MOD);
			}
		}
		int sum=0;
		while(!operations.isEmpty()) {
			sum+=operations.pop();
			sum%=MOD;
		}
		System.out.println(sum%MOD);
	}
}
