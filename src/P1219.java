import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class P1219 {
	static int N;
	static boolean[] column;
	static boolean[] leftDia;
	static boolean[] rightDia;
	static int[] rowNum;
	static int[][] solutions;
	static int counter=0;
	static int soluCount=0;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(f.readLine());
		column = new boolean[N];
		leftDia = new boolean[2*N];
		rightDia = new boolean[3*N];
		rowNum = new int[N];
		solutions = new int[3][N];
		permute(0);
		for(int soluC=0;soluC<3;soluC++) {
			for(int i=0;i<N-1;i++) {
				System.out.print(solutions[soluC][i]+1+" ");
			}
			System.out.println(solutions[soluC][N-1]+1);
		}
		System.out.println(counter);
	}
	public static void permute(int row) {
		if(row==N) {
			if(soluCount<3) {
				for(int i=0;i<N;i++)
					solutions[soluCount][i]=rowNum[i];
				soluCount++;
			}
			counter++;
			return;
		}
		for(int col=0;col<N;col++) {
			if(column[col])
				continue;
			if(leftDia[row-col+N] || rightDia[row+col])
				continue;
			rowNum[row]=col;
			column[col]=true;
			leftDia[row-col+N]=true;
			rightDia[row+col]=true;
			permute(row+1);
			column[col]=false;
			leftDia[row-col+N]=false;
			rightDia[row+col]=false;
		}
	}
}
