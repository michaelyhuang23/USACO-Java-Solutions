import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1010 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.parseInt(br.readLine());
		System.out.println(changeFormat(number));
	}
	static String changeFormat(int number) {
		if(number==0)
			return Integer.toString(0);
		String result="";
		while(number>0) {
			int i=0;
			while(1<<i<=number) i++;
			i--;
			//System.out.println("here it is:"+result+" "+number+" "+i);
			number-=1<<i;
			if(i==1)
				result=result+"2+";
			else
				result=result+"2("+changeFormat(i)+")+";
		}
		return result.substring(0, result.length()-1);
	}
}
