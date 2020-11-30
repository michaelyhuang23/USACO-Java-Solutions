import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeMap;


public class lineup {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("lineup.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lineup.out")));

		String[] allCows = { "Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue" };
		TreeMap<String, Integer> cowNo = new TreeMap<String, Integer>();
		cowNo.put("Beatrice", 0);
		cowNo.put("Belinda", 1);
		cowNo.put("Bella", 2);
		cowNo.put("Bessie", 3);
		cowNo.put("Betsy", 4);
		cowNo.put("Blue", 5);
		cowNo.put("Buttercup", 6);
		cowNo.put("Sue", 7);

		int numCondition = Integer.parseInt(f.readLine());
		
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] connections = new ArrayList[8];
		for (int i = 0; i < 8; i++)
			connections[i] = new ArrayList<Integer>();

		for (int i = 0; i < numCondition; i++) {
			String[] inputs = f.readLine().split(" ", 6);
			connections[cowNo.get(inputs[0])].add(cowNo.get(inputs[5]));
			connections[cowNo.get(inputs[5])].add(cowNo.get(inputs[0]));
		}

		
		ArrayList<Integer> orderedCow = new ArrayList<Integer>();
		for (int cowNum = 0; cowNum < 8; cowNum++) {
			if (orderedCow.contains(cowNum))
				continue;

			if (connections[cowNum].size() < 2) {
				addConnectedCows(orderedCow, cowNum, cowNum, connections);
			}
		}
		
		
		for (int cow : orderedCow) {
			out.println(allCows[cow]);
		}

		f.close();
		out.close();
	}

	public static void addConnectedCows(ArrayList<Integer> orderedCow, int currentCow, int previousCow,
			ArrayList<Integer>[] connections) {
		orderedCow.add(currentCow);
		for (int otherCow : connections[currentCow]) {
			if (otherCow == previousCow)
				continue;
			addConnectedCows(orderedCow, otherCow, currentCow, connections);
		}
	}
}
