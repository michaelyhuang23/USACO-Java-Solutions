import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OpenJudge1944 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int readIn;
		long[] waysToDo = new long[20];
		waysToDo[1]=1;
		waysToDo[2]=2;
		readIn = Integer.parseInt(f.readLine());
		for(int i=3;i<=readIn;i++) {
			waysToDo[i]=waysToDo[i-1]+waysToDo[i-2];
		}

		System.out.println(waysToDo[readIn]);
		
	}
}
