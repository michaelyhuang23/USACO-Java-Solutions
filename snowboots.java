import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class snowboots {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("snowboots.in")); // new FileReader("snowboots.in") //new
																				// InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int length = Integer.parseInt(st.nextToken());
		int numBoot = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		int[] snowDepth = new int[length];
		for (int i = 0; i < length; i++)
			snowDepth[i] = Integer.parseInt(st.nextToken());
		int[] bootDepth = new int[numBoot];
		int[] bootMaxStep = new int[numBoot];
		for (int i = 0; i < numBoot; i++) {
			st = new StringTokenizer(f.readLine());
			int depth = Integer.parseInt(st.nextToken());
			int step = Integer.parseInt(st.nextToken());
			bootDepth[i] = depth;
			bootMaxStep[i] = step;
		}
		int[] bootOnTo = new int[length];
		Arrays.fill(bootOnTo, Integer.MAX_VALUE / 2);
		bootOnTo[0] = 0;
		for (int i = 0; i < length; i++) {
			int boot;
			for (boot = bootOnTo[i]; boot < numBoot; boot++) {
				if (bootDepth[boot] < snowDepth[i])
					continue;
				int j;
				for (j = Math.min(length - 1, i + bootMaxStep[boot]); j > i; j--)
					if (snowDepth[j] <= bootDepth[boot])
						break;
				bootOnTo[j] = Math.min(boot, bootOnTo[j]);
			}

		}
		out.println(bootOnTo[length - 1]);
		out.close();
		f.close();
	}
}
