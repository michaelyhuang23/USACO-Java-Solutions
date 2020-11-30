import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class milkvisits {
	static int[] finderH;
	static int[] finderG;
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("milkvisits.in")); //new FileReader("mooyomooyo.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numFarm = Integer.parseInt(st.nextToken());
		int numFriend = Integer.parseInt(st.nextToken());
		finderH = new int[numFarm];
		finderG = new int[numFarm];
		for(int i=0;i<numFarm;i++) {
			finderH[i]=i;
			finderG[i]=i;
		}
		
		String farmStr = f.readLine();
		
		for(int i=0;i<numFarm-1;i++) {
			st = new StringTokenizer(f.readLine());
			int first= Integer.parseInt(st.nextToken())-1;
			int second = Integer.parseInt(st.nextToken())-1;
			if(farmStr.charAt(first)=='H' && farmStr.charAt(second)=='H')
				mergeH(first,second);
			if(farmStr.charAt(first)=='G' && farmStr.charAt(second)=='G')
				mergeG(first,second);
		}
		
		for(int i=0;i<numFriend;i++) {
			st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			char breed = st.nextToken().charAt(0);
			if(start==end) {
				if(farmStr.charAt(start)==breed)
					out.print(1);
				else
					out.print(0);
				continue;
			}
			if(breed=='H') {
				if(findG(start)==findG(end))
					out.print(0);
				else
					out.print(1);
			}else {
				if(findH(start)==findH(end))
					out.print(0);
				else
					out.print(1);
			}
		}
		out.println();
		out.close();
		f.close();
	}
	static boolean mergeH(int x, int y) {
		int fx = findH(x);
		int fy = findH(y);
		if(fx != fy) {
			finderH[fy] = fx;
			return true;
		}
		return false;
	}
	
	static int findH(int u) {
		if (finderH[u] == u) {
			return u;
		}
		
		finderH[u] = findH(finderH[u]); // 路径压缩
		
		return finderH[u];
	}
	
	static boolean mergeG(int x, int y) {
		int fx = findG(x);
		int fy = findG(y);
		if(fx != fy) {
			finderG[fy] = fx;
			return true;
		}
		return false;
	}
	
	static int findG(int u) {
		if (finderG[u] == u) {
			return u;
		}
		
		finderG[u] = findG(finderG[u]); // 路径压缩
		
		return finderG[u];
	}
}
