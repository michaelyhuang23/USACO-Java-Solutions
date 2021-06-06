import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class AndoverCP_gunga_password {
	static boolean[] isPrime;
	static int n;
	static long counter;
	
	private static long solve(String path) throws IOException {
		//BufferedReader f = new BufferedReader(new FileReader(path));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(f.readLine());
		isPrime = new boolean[41];
		for (int i = 2; i <= 40; i++) {
			isPrime[i] = true;
			for (int j = 2; j * j <= i; j++) {
				if (i % j == 0) {
					isPrime[i] = false;
					break;
				}
			}
		}
		counter = 0;
		long start = System.nanoTime();
		recurse(0, new int[n]);
		System.out.println(System.nanoTime()-start);
		System.out.println(counter);
		return counter;
	}
	
	private static void genTests() throws IOException {
		Random rand = new Random();
		int[] ns = new int[20];
		int[] ms = new int[20];
		ns[0]=4;
		ms[0]=6;
		ns[1]=4;
		ms[1]=0;
		ns[2]=6;
		ms[2]=24;
		ns[3]=5;
		ms[3]=1000;
		ns[4]=10;
		ms[4]=0;
		for (int i = 5; i < 20; i++) {
			int digit = rand.nextInt(7)+4;
			ns[i]=digit;
			int prod = rand.nextInt(10000);
			ms[i]=prod;
		}
		int zeroCount = 0;
		for (int i = 0; i < 20; i++) {
			String s = String.format("%02d", i);
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/michaelhyh/Downloads/password_testcases/input/input"+s+".txt")));
			out.println(ns[i]+" "+ms[i]);
			System.out.println(i);
			out.close();
			out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/michaelhyh/Downloads/password_testcases/output/output"+s+".txt")));
			long solution = solve("/Users/michaelhyh/Downloads/password_testcases/input/input"+s+".txt");
			if(solution==0)
				zeroCount++;
			out.println(solution);
			out.close();
			if(solution==0 && zeroCount>2) {
				int digit = rand.nextInt(7)+4;
				ns[i]=digit;
				int prod = rand.nextInt(10000);
				ms[i]=prod;
				i--;
			}
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		solve("");
	}

	private static void recurse(int i, int[] arr) {
		if (i == n) {
			counter++;
			return;
		}
		if (i <= 2) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 && j == 0)
					continue;
				arr[i] = j;
				recurse(i + 1, arr);
				arr[i] = 0;
			}
			return;
		}
		for (int j = 0; j < 10; j++) {
			if (isPrime[j + arr[i - 1] + arr[i - 2] + arr[i - 3]]) {
				arr[i] = j;
				recurse(i + 1, arr);
				arr[i] = 0;
			}
		}
	}
}
