import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class gymnastics {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("gymnastics.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gymnastics.out")));
		String[] inputs = f.readLine().split(" ", 2);
		int numSessions = Integer.parseInt(inputs[0]), numCows = Integer.parseInt(inputs[1]);
		int[][] cowPlacement = new int[numSessions][numCows];

		for (int i = 0; i < numSessions; i++) {
			inputs = f.readLine().split(" ", numCows);
			for (int j = 0; j < numCows; j++)
				cowPlacement[i][Integer.parseInt(inputs[j]) - 1] = j;
		}

		int count = 0;
		for (int cow1 = 1; cow1 <= numCows; cow1++) {
			for (int cow2 = cow1 + 1; cow2 <= numCows; cow2++) {
				boolean ascending = cowPlacement[0][cow2 - 1] > cowPlacement[0][cow1 - 1] ? true : false;
				boolean success = true;
				for (int session = 1; session < numSessions; session++)
					if ((cowPlacement[session][cow2 - 1] > cowPlacement[session][cow1 - 1]) != ascending)
						success = false;

				if (success)
					count++;
			}
		}

		out.println(count);

		f.close();
		out.close();
	}
}
