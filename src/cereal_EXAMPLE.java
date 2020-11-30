
import java.io.*;
import java.util.*;

public class cereal_EXAMPLE {
	
	// n cows, m cereal types
	static int n, m;
	static int[][] cow; //奶牛喜欢的麦片
	static int[] cereal; //每种麦片的领取情况
	static int happyCows = 0;

	static void unhappyCow(int cowId) {
		int food1 = cow[cowId][0];  // 第一喜欢的麦片的领取情况
		int contestedCowId = cereal[food1];
		if(contestedCowId == -1) {  //无人领取
			cereal[food1] = cowId;
			return;
		}
		if(contestedCowId < cowId) { // //被前面的奶牛领走了
			int food2 = cow[cowId][1];  //第二喜欢的麦片的领取情况，同样方法处理
			contestedCowId = cereal[food2];
			if(contestedCowId == -1) {
				cereal[food2] = cowId;
				return;
			}
			if(contestedCowId < cowId) {
				happyCows--;
				return;
			}
			else {
				cereal[food2] = cowId;
				unhappyCow(contestedCowId);
			}
		}
		else {
			//抢走后面奶牛的麦片，后面奶牛需要重新判断
			cereal[food1] = cowId;
			unhappyCow(contestedCowId);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cereal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		cow = new int[n][2];
		cereal = new int[m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			cow[i][0] = Integer.parseInt(st.nextToken()) - 1;
			cow[i][1] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		Arrays.fill(cereal, -1);
		
		int[] answers = new int[n];
		for(int i = n - 1; i >= 0; i--) {
			// work backwards
			happyCows++;
			unhappyCow(i);
			answers[i] = happyCows;
		}
		
		for(int i = 0; i < n; i++) {
			out.println(answers[i]);
		}
		
		in.close();
		out.close();
	}
		
}
