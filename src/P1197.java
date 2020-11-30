import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class P1197 {

	static int[] finder;
	static int numConnectedBlocks;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numPlanet = Integer.parseInt(st.nextToken());
		int numConnect = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] allConnects = new ArrayList[numPlanet];
		for(int i=0;i<numPlanet;i++) {
			allConnects[i] = new ArrayList<Integer>();
		}
		for(int i=0;i<numConnect;i++) {
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			allConnects[x].add(y);
			allConnects[y].add(x);
		}
		int numAttack = Integer.parseInt(f.readLine());
		int[] attacks = new int[numAttack];
		HashSet<Integer> attacked = new HashSet<Integer>();
		for(int i=0;i<numAttack;i++) {
			int pla =Integer.parseInt(f.readLine());
			attacks[i] = pla;
			attacked.add(pla);
		}
		finder = new int[numPlanet];
		for(int i=0;i<numPlanet;i++)
			finder[i]=i;
		numConnectedBlocks = numPlanet-numAttack;
		for(int i=0;i<numPlanet;i++) {
			if(attacked.contains(i))
				continue;
			for(int otherP : allConnects[i]) {
				if(attacked.contains(otherP))
					continue;
				merge(otherP,i);
			}
		}
		int[] answers = new int[numAttack+1];
		for(int i=numAttack-1;i>=0;i--) {
			answers[i+1]=numConnectedBlocks++;
			attacked.remove(attacks[i]);
			for(int otherP : allConnects[attacks[i]]) {
				if(attacked.contains(otherP))
					continue;
				merge(otherP,attacks[i]);
			}
		}
		answers[0]=numConnectedBlocks;
		for(int i=0;i<numAttack+1;i++) {
			System.out.println(answers[i]);
		}
	}
	static void merge(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		if(fx != fy) {
			finder[fy] = fx;
			numConnectedBlocks--;
		}
	}
	
	static int find(int u) {
		if (finder[u] == u) {
			return u;
		}
		
		finder[u] = find(finder[u]); // 路径压缩
		
		return finder[u];
	}
}
