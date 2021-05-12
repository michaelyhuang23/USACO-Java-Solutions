import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Round720Div2DREDO {
	static class Pair{
		int a, b;
		
		public Pair(int i, int j) {
			a=i;
			b=j;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + a;
			result = prime * result + b;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (a != other.a)
				return false;
			if (b != other.b)
				return false;
			return true;
		}
		
	}
	static ArrayList<Integer>[] connector;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			connector = new ArrayList[n];
			for (int j = 0; j < n; j++) {
				connector[j] = new ArrayList<>();
			}
			HashSet<Pair> deletes = new HashSet<>();
			int[] degs = new int[n];
			int[] first = new int[n-1];
			int[] second = new int[n-1];
			for (int j = 0; j < n - 1; j++) {
				StringTokenizer st = new StringTokenizer(f.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				first[j]=a;
				second[j]=b;
				degs[a]++;
				degs[b]++;
			}
			for (int j = 0; j < n-1; j++) {
				int a=first[j],b=second[j];
				if(degs[a]>2 && degs[b]>2) {
					degs[a]--;
					degs[b]--;
					deletes.add(new Pair(a,b));
				}
			}
			for (int j = 0; j < n-1; j++) {
				int a=first[j],b=second[j];
				if(deletes.contains(new Pair(a,b)))
					continue;
				if(degs[a]>2 || degs[b]>2) {
					degs[a]--;
					degs[b]--;
					deletes.add(new Pair(a,b));
				}
			}
			
			for (int j = 0; j < n-1; j++) {
				int a=first[j],b=second[j];
				if(deletes.contains(new Pair(a,b)))
					continue;
				connector[a].add(b);
				connector[b].add(a);
			}
			int color = 1;
			colors = new int[n];
			for (int j = 0; j < n; j++) {
				if(colors[j]>0)
					continue;
				floodfill(j,color);
				color++;
			}
			boolean[] colorUsed = new boolean[color];
			int[] head = new int[color];
			int[] tail = new int[color];
			for (int j = 0; j < n; j++) {
				if(degs[j]<2 && !colorUsed[colors[j]]) {
					colorUsed[colors[j]]=true;
					head[colors[j]]=j;
				}else if(degs[j]<2 && colorUsed[colors[j]]){
					tail[colors[j]]=j;
				}
				if(degs[j]==0) {
					head[colors[j]]=j;
					tail[colors[j]]=j;
				}
			}
			ArrayList<Pair> adds = new ArrayList<>();
			for (int j = 2; j < color; j++) {
				adds.add(new Pair(tail[j-1],head[j]));
			}
			assert (adds.size()==deletes.size());
			System.out.println(adds.size());
			int k = 0;
			for(Pair del : deletes) {
				Pair ad = adds.get(k);
				k++;
				System.out.println((del.a+1)+" "+(del.b+1)+" "+(ad.a+1)+" "+(ad.b+1));
			}
		}
	}
	private static void floodfill(int j, int color) {
		// TODO Auto-generated method stub
		if(colors[j]>0)
			return;
		colors[j]=color;
		for (int i : connector[j]) {
			floodfill(i,color);
		}
		
	}
	static int[] colors;
}
