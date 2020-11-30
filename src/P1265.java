import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class P1265 {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numCity = Integer.parseInt(f.readLine());
		double[] posX = new double[numCity];
		double[] posY = new double[numCity];
		for(int i=0;i<numCity;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			posX[i]=x;
			posY[i]=y;
		}
		int count=0;
		double[] dist = new double[numCity];
		Arrays.fill(dist, Double.MAX_VALUE);
		dist[0]=0;
		boolean[] visited = new boolean[numCity];
		double totalDist = 0;
		while(count<numCity) {
			double minDist=Double.MAX_VALUE;
			int minIndex=0;
			for(int i=0;i<numCity;i++) {
				if(!visited[i] && dist[i]<minDist) {
					minDist = dist[i];
					minIndex = i;
				}
			}
			//System.out.println(posX[minIndex]+" "+posY[minIndex]+" "+dist[minIndex]);
			visited[minIndex]=true;
			totalDist+=minDist;
			dist[minIndex]=0;
			count++;
			for(int i=0;i<numCity;i++) {
				if(visited[i])
					continue;
				double dX = posX[i]-posX[minIndex];
				double dY = posY[i]-posY[minIndex];
				dist[i]=Math.min(dist[i], Math.sqrt(dX*dX+dY*dY));
			}
		}
		System.out.printf("%.2f\n",totalDist);
		
	}
}
