import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1119 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numNode, numConnect;
		StringTokenizer st = new StringTokenizer(f.readLine());
		numNode = Integer.parseInt(st.nextToken());
		numConnect = Integer.parseInt(st.nextToken());
		int[] nodeTime = new int[numNode];
		st = new StringTokenizer(f.readLine());
		for(int i=0;i<numNode;i++)
			nodeTime[i] = Integer.parseInt(st.nextToken());
		int[][] connection = new int[numNode][numNode];
		int[][] dist = new int[numNode][numNode];
		for(int i=0;i<numNode;i++)
			for(int j=0;j<numNode;j++) {
				connection[i][j]=Integer.MAX_VALUE/2;
				connection[j][i]=Integer.MAX_VALUE/2;
			}
		for(int i=0;i<numConnect;i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			connection[first][second]=weight;
			connection[second][first]=weight;
		}
		dist = connection.clone();
		int numQuery = Integer.parseInt(f.readLine());
		int index=0;
		for(int i=0;i<numQuery;i++) {
			st = new StringTokenizer(f.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			for(;index<numNode && nodeTime[index]<=time; index++) {
				for(int f1 = 0; f1<numNode; f1++)
					for(int f2=0; f2<numNode; f2++) {
						dist[f1][f2] = Math.min(dist[f1][f2], dist[f1][index]+dist[index][f2]);
						dist[f2][f1]=dist[f1][f2];
					}
			}
			if(nodeTime[first]>time || nodeTime[second]>time || dist[first][second]>=Integer.MAX_VALUE/2-1)
				System.out.println(-1);
			else
				System.out.println(dist[first][second]);
		}
	}
}
