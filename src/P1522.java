import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1522 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numPast = Integer.parseInt(f.readLine());
		StringTokenizer st;
		double[] Xs = new double[numPast];
		double[] Ys = new double[numPast];
		double[][] connection = new double[numPast][numPast];
		for(int i=0;i<numPast;i++) {
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Xs[i]=x;
			Ys[i]=y;
			connection[i][i]=0;
			
		}
		for(int i=0;i<numPast;i++) {
			String input = f.readLine();
			for(int j=0;j<numPast;j++) {
				if(input.charAt(j)=='1') {
					connection[i][j] = Math.sqrt((Xs[i]-Xs[j])*(Xs[i]-Xs[j])+(Ys[i]-Ys[j])*(Ys[i]-Ys[j]));
					//connection[j][i] = connection[i][j];
				}else {
					if(i!=j)
						connection[i][j]=Integer.MAX_VALUE/2;
					//connection[j][i]=Integer.MAX_VALUE/2;
				}
			}
		}
		double[][] dist = new double[numPast][numPast];
		for(int i=0;i<numPast;i++)
			Arrays.fill(dist[i], Integer.MAX_VALUE/2);
		for(int i=0;i<numPast;i++)
			for(int j=0;j<numPast;j++)
				dist[i][j]=connection[i][j];
		
		for(int inter=0;inter<numPast;inter++)
			for(int first=0;first<numPast;first++)
				for(int second=0;second<numPast;second++) {
					dist[first][second] = Math.min(dist[first][second], dist[first][inter]+dist[inter][second]);
					dist[second][first] = dist[first][second];
				}
		double[] maxDist = new double[numPast];
		double currentMaxDia = 0;
		for(int i=0;i<numPast;i++) {
			for(int j=0;j<numPast;j++) {
				if(dist[i][j]>=Integer.MAX_VALUE/2-100)
					continue;
				//System.out.println(dist[i][j]);
				maxDist[i] = Math.max(maxDist[i], dist[i][j]);
			}
		}
		
		for(int i=0;i<numPast;i++)
			currentMaxDia = Math.max(currentMaxDia, maxDist[i]);
		double minCrossDia = Integer.MAX_VALUE;
		for(int first=0;first<numPast;first++) {
			for(int second=0;second<numPast;second++) {
				if(dist[first][second]<Integer.MAX_VALUE/2-100)
					continue;
				minCrossDia=Math.min(minCrossDia, maxDist[first]+maxDist[second]+Math.sqrt((Xs[first]-Xs[second])*(Xs[first]-Xs[second])+(Ys[first]-Ys[second])*(Ys[first]-Ys[second])));
			}
		}
		//System.out.println(minCrossDia+" "+currentMaxDia);
		System.out.printf("%.6f\n",Math.max(minCrossDia, currentMaxDia));
		//out.close()
		
		
	}
}
