import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CFE107Div2C {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		int[] pos = new int[50];
		Arrays.fill(pos, -1);
		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < n; i++) {
			int c = Integer.parseInt(st.nextToken()) - 1;
			if (pos[c] == -1)
				pos[c] = i + 1;
		}
		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < q-1; i++) {
			int c = Integer.parseInt(st.nextToken()) - 1;
			System.out.print(pos[c]+" ");
			for (int j = 0; j < 50; j++) {
				if(pos[j]<pos[c])
					pos[j]++;
			}
			pos[c]=1;
		}
		int c = Integer.parseInt(st.nextToken()) - 1;
		System.out.println(pos[c]);
	}
}
