import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1312 {
	static int[][] map;
	static int[][] moves;
	static int stepLimit;
	static int[] colorCounter=new int[11];
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		stepLimit = Integer.parseInt(f.readLine());
		map = new int[7][5];
		moves = new int[stepLimit][3];
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int numBlock = st.countTokens() - 1;
			for (int j = 0; j < numBlock; j++) {
				map[j][i] = Integer.parseInt(st.nextToken());
				colorCounter[map[j][i]]++;
			}
		}
		dfs(0);
		System.out.println(-1);

	}

	private static void applyGravity(int[][] arr) {
		for (int col = 0; col < 5; col++) {
			int down = 0;
			for (int r = 0; r < 7; r++) {
				while (r < 7 && arr[r][col] == 0) {
					r++;
					down++;
				}
				if (r >= 7)
					break;
				int temp = arr[r][col];
				arr[r][col] = 0;
				arr[r - down][col] = temp;
			}
		}
	}

	private static boolean clear(int[][] arr) {
		boolean[][] toEmpty = new boolean[7][5];
		boolean cleared = false;
		for (int r = 0; r < 7; r++) {
			for (int c = 0; c < 3; c++) {
				if (arr[r][c] == 0)
					continue;
				if (arr[r][c] == arr[r][c + 1] && arr[r][c] == arr[r][c + 2]) {
					toEmpty[r][c] = true;
					toEmpty[r][c + 1] = true;
					toEmpty[r][c + 2] = true;
				}
			}
		}
		for (int c = 0; c < 5; c++) {
			for (int r = 0; r < 5; r++) {
				if (arr[r][c] == 0)
					continue;
				if (arr[r][c] == arr[r + 1][c] && arr[r][c] == arr[r + 2][c]) {
					toEmpty[r][c] = true;
					toEmpty[r + 1][c] = true;
					toEmpty[r + 2][c] = true;
				}
			}
		}
		for (int r = 0; r < 7; r++) {
			for (int c = 0; c < 5; c++) {
				if (toEmpty[r][c]) {
					cleared = true;
					colorCounter[arr[r][c]]--;
					arr[r][c] = 0;
				}
			}
		}
		return cleared;

	}

	private static boolean isEmpty(int[][] arr) {
		for(int i=0;i<11;i++)
			if(colorCounter[i]>0)
				return false;
		return true;
	}

	private static void dfs(int numMoved) {
		if (isEmpty(map)) {
			for (int i = 0; i < numMoved; i++)
				System.out.println(moves[i][0] + " " + moves[i][1] + " " + moves[i][2]);
			System.exit(0);
		}
		if (numMoved >= stepLimit)
			return;
		for(int i=0;i<11;i++) {
			if(colorCounter[i]>0 && colorCounter[i]<3)
				return;
		}
		for (int c = 0; c < 5; c++) {
			for (int r = 0; r < 7; r++) {
				if (map[r][c] == 0)
					continue;
				if (c < 4 && map[r][c] != map[r][c + 1]) {
					int[][] backUp = new int[7][5]; // move right
					int[] colorBackup = colorCounter.clone();
					for (int h = 0; h < 7; h++)
						backUp[h] = map[h].clone();

					int temp = map[r][c];
					map[r][c] = map[r][c + 1];
					map[r][c + 1] = temp;
					applyGravity(map);
					while (clear(map))
						applyGravity(map);

					moves[numMoved][0] = c;
					moves[numMoved][1] = r;
					moves[numMoved][2] = 1;
					dfs(numMoved + 1);
					colorCounter=colorBackup;
					Arrays.fill(moves[numMoved], 0);
					map = backUp;
				}
				if (c > 0 && map[r][c - 1] == 0) {
					int[][] backUp = new int[7][5]; // move left
					int[] colorBackup = colorCounter.clone();
					for (int h = 0; h < 7; h++)
						backUp[h] = map[h].clone();
					int temp = map[r][c];
					map[r][c] = map[r][c - 1];
					map[r][c - 1] = temp;
					applyGravity(map);
					while (clear(map))
						applyGravity(map);

					moves[numMoved][0] = c;
					moves[numMoved][1] = r;
					moves[numMoved][2] = -1;
					dfs(numMoved + 1);
					colorCounter=colorBackup;
					Arrays.fill(moves[numMoved], 0);
					map = backUp;
				}

			}
		}
	}
}
