import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OpenJudge666 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numTest = Integer.parseInt(f.readLine());
		int[][] solutions = new int[11][11];
		solutions[1][1]=1;
		for(int box=1;box<=10;box++) {
			for(int apple=1;apple<=10;apple++) {
				if(box>apple)
					solutions[box][apple] = solutions[apple][apple];
				else
					solutions[box][apple] = ((box<=1) ? 0 : solutions[box-1][apple]) + ((apple==box) ? 1 : solutions[box][apple-box]);
			}
		}
		for(int i=0;i<numTest;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int numApple = Integer.parseInt(st.nextToken());
			int numBox = Integer.parseInt(st.nextToken());
			System.out.println(solutions[numBox][numApple]);
		}
	}
}
