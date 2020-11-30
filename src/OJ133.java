import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class OJ133 {
	static long[][] bit;
	static int height, width;
	private static int lowbit(int x) {
		return x&(-x);
	}
	private static long getSum(int row, int col) {
		long sum=0;
		for(int r=row;r>0;r-=lowbit(r))
			for(int c=col;c>0;c-=lowbit(c))
				sum+=bit[r][c];
		return sum;
	}
	private static void update(int row, int col, int value) {
		for(int r=row; r<=height; r+=lowbit(r))
			for(int c=col; c<=width; c+=lowbit(c)) {
				bit[r][c]+=value;
			}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		bit = new long[height+1][width+1];
		StringBuilder output = new StringBuilder(300001);
		while(f.ready()) {
			st = new StringTokenizer(f.readLine());
			if(st.nextToken().charAt(0)=='1') {
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				update(row,col,val);				
			}else {
				int row1 = Integer.parseInt(st.nextToken())-1;
				int col1 = Integer.parseInt(st.nextToken())-1;
				int row2 = Integer.parseInt(st.nextToken());
				int col2 = Integer.parseInt(st.nextToken());
				output.append(getSum(row2, col2)-getSum(row2, col1)-getSum(row1, col2)+getSum(row1, col1));
				output.append('\n');
			}
		}
		System.out.print(output);
		
	}
}
