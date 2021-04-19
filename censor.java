import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class censor {
	static StringBuilder content;
	static String censor;
	static final int maxHash = 7141417;
	static int[] expoOf7=new int[1000001];
	private static int mod(int input) {
		int output = input%maxHash;
		if(output<0)
			output+=maxHash;
		return output;
	}
	private static int rollingHashVal(char addLetter, int currentHashVal) {
		return mod(mod(currentHashVal*7)+((int)addLetter)*23);
	}
	private static int deleteHash(char deleteLetter, int currentHashVal) {
		return mod(currentHashVal-mod((mod(expoOf7[censor.length()-1]*23))*((int)deleteLetter)));
	}
	
	public static void main(String[] args) throws IOException {
		expoOf7[0]=1;
		for(int i=1;i<1000001;i++) {
			expoOf7[i]=(expoOf7[i-1]*7)%maxHash;			
		}
		BufferedReader f = new BufferedReader(new FileReader("censor.in")); //new FileReader("censor.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("censor.out")));
		content = new StringBuilder(f.readLine());
		censor = f.readLine();
		int hashCensor = 0;
		for(int i=0;i<censor.length();i++) {
			hashCensor = rollingHashVal(censor.charAt(i), hashCensor);
		}
		int hashValue = 0;
		StringBuilder finalContent = new StringBuilder();
		Queue<Character> window = new ArrayDeque<Character>();
		for(int i=0;i<censor.length();i++) {
			hashValue = rollingHashVal(content.charAt(i), hashValue);
			window.offer(content.charAt(i));
			finalContent.append(content.charAt(i));
			//System.out.println(finalContent);
		}
		for(int i=censor.length();i<=content.length();i++) {
			//System.out.println(finalContent);
			//System.out.println(window.size()+" "+hashValue+" "+hashCensor);
			//hashvalue represent hashvalue of the previous censor-length characters
			if(hashValue == hashCensor && censorString(window)) {
				hashValue = 0;
				//System.out.println(i);
				finalContent.delete(finalContent.length()-censor.length(), finalContent.length());
				window.clear();
				if(finalContent.length()-censor.length()<0) {
					
					for(int j=0;j<finalContent.length();j++) {
						hashValue = rollingHashVal(finalContent.charAt(j), hashValue);
						window.offer(finalContent.charAt(j));
					}
					int limit = Math.min(i+censor.length()-finalContent.length(),content.length());
					for(;i<limit;i++) {
						hashValue = rollingHashVal(content.charAt(i), hashValue);
						window.offer(content.charAt(i));
						finalContent.append(content.charAt(i));
					}
					i--;
					continue;
				}else {
					for(int j=0;j<censor.length();j++) {
						hashValue = rollingHashVal(finalContent.charAt(finalContent.length()-censor.length()+j), hashValue);
						window.offer(finalContent.charAt(finalContent.length()-censor.length()+j));
					}
				}

				
			}
			if(i>=content.length())
				break;
			//System.out.println("pre: "+finalContent);
			hashValue = deleteHash(window.poll(), hashValue);
			hashValue = rollingHashVal(content.charAt(i), hashValue);
			window.add(content.charAt(i));
			finalContent.append(content.charAt(i));
			//System.out.println(finalContent);
//			if(finalContent.substring(0, censor.length()).equals(censor)) {
//				break;
//			}
		}

		out.println(finalContent);
		out.close();
	}
	private static boolean censorString(Queue<Character> window) {
		int i=0;
		for(char letter:window) {
			if(letter!=censor.charAt(i))
				return false;
			i++;
		}
		return true;
	}
}
