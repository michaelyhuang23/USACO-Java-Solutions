import java.io.*;
import java.util.*;

public class P5018_TODO {
	
	static Node[] nodes;
	
	static class Node {
		int w, lno, rno;
	}
	
	static int dfs(int root) {
		if(root==-1)
			return 0;
		if(check(nodes[root].lno,nodes[root].rno))
			return cnt(root);
		
		return Math.max(dfs(nodes[root].lno), dfs(nodes[root].rno));
	}
	
	static boolean check(int lroot, int rroot) {
		if(lroot==-1 && rroot==-1)
			return true;
		if(lroot==-1 || rroot==-1 || nodes[lroot].w!=nodes[rroot].w)
			return false;
		return check(nodes[lroot].lno,nodes[rroot].rno) && check(nodes[lroot].rno,nodes[rroot].lno);
	}
	
	static int cnt(int root) {
		if(root==-1)
			return 0;
		return 1+cnt(nodes[root].lno)+cnt(nodes[root].rno);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		nodes = new Node[n+1];
		
		StringTokenizer tk = new StringTokenizer(in.readLine());
		for(int i=1; i<=n; i++) {
			nodes[i] = new Node();
			nodes[i].w = Integer.parseInt(tk.nextToken());
		}
		for(int i=1; i<=n; i++) {
			tk = new StringTokenizer(in.readLine());
			nodes[i].lno = Integer.parseInt(tk.nextToken());
			nodes[i].rno = Integer.parseInt(tk.nextToken());
		}
		
		System.out.println(dfs(1));
	}
}