import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;


public class whereami {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("whereami.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("whereami.out")));

		int totalLength = Integer.parseInt(f.readLine());
		String allColors = f.readLine();

		for (int currentLength = 1; currentLength <= totalLength; currentLength++) {
			HashSet<String> patterns = new HashSet<String>();
			boolean success = true;
			for (int startIndex = 0; startIndex + currentLength <= totalLength; startIndex++) {
				String patt = allColors.substring(startIndex, startIndex + currentLength);
				if (patterns.contains(patt)) {
					success = false;
					break;
				}
				patterns.add(patt);
			}
			if (success) {
				out.println(currentLength);
				break;
			}
		}

		f.close();
		out.close();
	}
}
