import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Round710Div3C {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			String a = f.readLine();
			String b = f.readLine();
			int maxLen = 0;
			for (int j = 0; j < a.length(); j++) {
				for (int k = 0; k < b.length(); k++) {
					int p = 0;
					for (p = 0; p+j < a.length() && p+k<b.length() ; p++) {
						if(a.charAt(j+p)!=b.charAt(k+p))
							break;
					}
					maxLen = Math.max(maxLen, p);
				}
			}
			System.out.println(a.length()-maxLen+b.length()-maxLen);
		}
	}
}
