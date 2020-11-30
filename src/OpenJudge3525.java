import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OpenJudge3525 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int readIn;
		long[] waysToDo = new long[100];
		waysToDo[1]=1;
		waysToDo[2]=2;
		waysToDo[3]=4;
		int length=3;
		while(true) {
			readIn = Integer.parseInt(f.readLine());
			if(readIn==0)
				break;
			for(int i=length+1;i<=readIn;i++) {
				waysToDo[i]=waysToDo[i-1]+waysToDo[i-2]+waysToDo[i-3];
			}
			if(readIn>length)
				length=readIn;
			System.out.println(waysToDo[readIn]);
		}
	}
}
