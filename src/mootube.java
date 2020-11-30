import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class mootube {
	static int[] finder;
	static int[] counter;
	static class Connection implements Comparable<Connection>{
		int first, second, relevance;
		public Connection(int f, int s, int re) {
			first= f;
			second=s;
			relevance=re;
		}
		public int compareTo(Connection other) {
			return other.relevance-relevance;
		}
	}
	static class Query implements Comparable<Query>{
		int maxRelevance, starter, id, answer;
		public Query(int maxR, int st, int id) {
			maxRelevance=maxR;
			starter=st;
			this.id=id;
		}
		public int compareTo(Query other) {
			return other.maxRelevance-maxRelevance;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("mootube.in")); //new FileReader("mootube.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numVid = Integer.parseInt(st.nextToken());
		int numQuery = Integer.parseInt(st.nextToken());
		Connection[] allConnectors = new Connection[numVid-1];
		for(int i=0;i<numVid-1;i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken())-1;
			int second = Integer.parseInt(st.nextToken())-1;
			int relevance = Integer.parseInt(st.nextToken());
			allConnectors[i]=new Connection(first, second, relevance);
		}
		Arrays.sort(allConnectors);
		Query[] allQueries = new Query[numQuery];
		for(int i=0;i<numQuery;i++) {
			st = new StringTokenizer(f.readLine());
			int maxR = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken())-1;
			allQueries[i] = new Query(maxR, start, i);
		}
		Arrays.sort(allQueries);
		finder = new int[numVid];
		counter = new int[numVid];
		for(int i=0;i<numVid;i++) {
			finder[i]=i;
			counter[i]=0;
		}
		int curConnect = 0;
		for(int i=0;i<numQuery;i++) {

			while(curConnect<numVid-1 && allConnectors[curConnect].relevance>=allQueries[i].maxRelevance) {
				merge(allConnectors[curConnect].first,allConnectors[curConnect].second);
				curConnect++;
			}
			allQueries[i].answer=counter[find(allQueries[i].starter)];
			
		}
		
		Arrays.sort(allQueries, new Comparator<Query>() {
			public int compare(Query o1, Query o2) {
				return o1.id-o2.id;
			}
		});
		for(int i=0;i<numQuery;i++) {
			out.println(allQueries[i].answer);
		}
		
		
		f.close();
		out.close();
	}
	
	static void merge(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		if(fx != fy) {
			finder[fy] = fx;
			counter[fx]+=counter[fy]+1;
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
