import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P1739 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		String express = f.readLine();
		Stack<Integer> counterStack = new Stack<Integer>();
		for(char letter : express.toCharArray()) {
			if(letter=='(')
				counterStack.push(1);
			if(letter==')')
				if(counterStack.isEmpty()) {
					System.out.println("NO");
					return;
				}else
					counterStack.pop();
					
		}
		if(counterStack.isEmpty())
			System.out.println("YES");
		else
			System.out.println("NO");
	}
}
