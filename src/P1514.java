import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1514 {
	final static int[][] DIR = {{1,0},{0,1},{-1,0},{0,-1}};
	static class Interval implements Comparable<Interval>{
		int start, end;
		public Interval(int s, int e) {
			start=s;
			end=e;
		}
		@Override
		public int compareTo(Interval o) {
			if(start==o.start)
				return end-o.end;
			return start-o.start;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int height,width;
		StringTokenizer st = new StringTokenizer(f.readLine());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		int[][] altitudes = new int[height][width];
		for(int i=0;i<height;i++) {
			st = new StringTokenizer(f.readLine());
			for(int j=0;j<width;j++) 
				altitudes[i][j]=Integer.parseInt(st.nextToken());
		}
		
		boolean[][] visitability = new boolean[width][width];
		for(int index=0;index<width;index++) {
			if(index>0 && altitudes[0][index-1]>altitudes[0][index] || index<width-1 && altitudes[0][index+1]>altitudes[0][index])
				continue;
			Queue<Integer> frontier = new ArrayDeque<>();
			boolean[][] visited = new boolean[height][width];
			frontier.offer(index);
			visited[0][index]=true;
			while(!frontier.isEmpty()) {
				int thisInt = frontier.poll();
				int row = thisInt/width;
				int col = thisInt%width;
				for(int i=0;i<4;i++) {
					int newR = row+DIR[i][0];
					int newC = col+DIR[i][1];
					if(newR<0 || newR>=height || newC<0 || newC>=width)
						continue;
					if(altitudes[newR][newC]>=altitudes[row][col] || visited[newR][newC])
						continue;
					visited[newR][newC]=true;
					frontier.offer(newR*width+newC);
				}
			}
			for(int i=0;i<width;i++) 
				visitability[index][i]=visited[height-1][i];

		}
		boolean[] failureCheck = new boolean[width];
		for(int i=0;i<width;i++) {
			for(int j=0;j<width;j++)
				if(visitability[i][j])
					failureCheck[j]=true;
		}
		int counter=0;
		for(int i=0;i<width;i++)
			if(failureCheck[i])
				counter++;
		if(counter<width) {
			System.out.println(0);
			System.out.println(width-counter);
			System.exit(0);
		}
		ArrayList<Interval> intervs = new ArrayList<>();
		for(int index=0;index<width;index++) {
			int start=-1, end=-1;
			for(int i=0;i<width;i++) {
				if(visitability[index][i]) {
					if(start==-1)
						start = i;
				}else {
					if(end==-1 && start>-1) {
						end = i-1;
						break;
					}
				}
			}
			if(start>-1) {
				if(end==-1)
					end += width;
				intervs.add(new Interval(start, end));
			}
		}
		intervs.sort(null);
		Interval prevInterv = new Interval(-1, -1);
		Interval furthestInterv = prevInterv;
		int intervCount=0;
		for(int i=0;i<width;i++) {
			if(intervs.get(i).start<=prevInterv.end+1) {
				if(intervs.get(i).end>furthestInterv.end) {
					furthestInterv = intervs.get(i);
					if(furthestInterv.end>=width-1) {
						intervCount++;
						break;
					}
				}
			}else {
				//System.out.println(prevInterv.start+" "+prevInterv.end+"  "+furthestInterv.start+" "+furthestInterv.end);
				prevInterv = furthestInterv;
				intervCount++;
				i--;
			}
		}
		System.out.println(1);
		System.out.println(intervCount);
	}
}
