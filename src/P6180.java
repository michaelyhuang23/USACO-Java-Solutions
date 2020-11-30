import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P6180 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		int numCow = Integer.parseInt(tk.nextToken());
		int querieNum = Integer.parseInt(tk.nextToken());
		int[][] prefixCowNum = new int[3][numCow+1];
        for(int i=0;i<numCow;i++) {
        	int cow = Integer.parseInt(in.readLine())-1;
        	for(int j=0;j<3;j++) 
        		prefixCowNum[j][i+1] = prefixCowNum[j][i];
        	prefixCowNum[cow][i+1]++;
        	
        }
        for(int i=0;i<querieNum; i++) {
        	tk = new StringTokenizer(in.readLine());
        	int start = Integer.parseInt(tk.nextToken());
        	int end = Integer.parseInt(tk.nextToken());
        	System.out.print(prefixCowNum[0][end]-prefixCowNum[0][start-1]+" ");
        	System.out.print(prefixCowNum[1][end]-prefixCowNum[1][start-1]+" ");
        	System.out.print(prefixCowNum[2][end]-prefixCowNum[2][start-1]+"\n");
        }
        in.close();
	}
}
