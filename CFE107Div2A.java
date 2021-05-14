import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CFE107Div2A {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			StringTokenizer st = new StringTokenizer(f.readLine());
			int count = 0;
			for (int j = 0; j < n; j++) {
				int type = Integer.parseInt(st.nextToken());
				if(type==1 || type==3)
					count++;
			}
			System.out.println(count);
		}
	}
}
