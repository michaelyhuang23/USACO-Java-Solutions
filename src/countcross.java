import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class countcross {
	static boolean[][] hasRoad;
	static int mapLength, numConnect, numCow;
	static int[][] component;
	static int count=0;
	final static int[][] DIR = {{1,0},{0,1},{-1,0},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("countcross.in")); //new FileReader("countcross.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		mapLength = Integer.parseInt(st.nextToken());
		numCow = Integer.parseInt(st.nextToken());
		numConnect = Integer.parseInt(st.nextToken());
		hasRoad = new boolean[mapLength*2][mapLength*2];
		component = new int[mapLength*2][mapLength*2];
		for(int i=0;i<numConnect;i++) {
			st = new StringTokenizer(f.readLine());
			int firstR = Integer.parseInt(st.nextToken())*2-2;
			int firstC = Integer.parseInt(st.nextToken())*2-2;
			int secondR = Integer.parseInt(st.nextToken())*2-2;
			int secondC = Integer.parseInt(st.nextToken())*2-2;
			int midC = (firstC+secondC)/2;
			int midR = (firstR+secondR)/2;
			hasRoad[midR][midC]=true;
		}
		for(int r=0;r<mapLength*2;r+=2) {
			for(int c=0;c<mapLength*2;c+=2) {
				if(component[r][c]>0)
					continue;
				count++;
				floodFill(r, c);
			}
		}
		int[] componentSize = new int[count];
		for(int i=0;i<numCow;i++) {
			st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken())*2-2;
			int c = Integer.parseInt(st.nextToken())*2-2;
			componentSize[component[r][c]-1]++;
		}
		for(int i=0;i<count;i++)
			System.out.print(componentSize[i]+" ");
		System.out.println();
		int totalPair=0;
		for(int i=0;i<count;i++)
			for(int j=0;j<i;j++)
				totalPair+=componentSize[i]*componentSize[j];
		out.println(totalPair);
		out.close();
		f.close();
	}
	private static void floodFill(int r, int c) {
		component[r][c]=count;
		for(int i=0;i<4;i++) {
			int newR = r + 2*DIR[i][0];
			int newC = c + 2*DIR[i][1];
			if(newR<0 || newR>=mapLength*2 || newC<0 || newC>=mapLength*2)
				continue;
			if(component[newR][newC]==count)
				continue;
			if(hasRoad[r+DIR[i][0]][c+DIR[i][1]])
				continue;
			floodFill(newR, newC);
		}
	}
}
