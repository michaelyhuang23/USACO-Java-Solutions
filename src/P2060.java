import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2060 {
	static class Pos{
		int x,y;
		int[] moveCount = new int[4];
		public Pos(int i, int j) {
			x=i;
			y=j;
		}
		public boolean equals(Object other) {
			Pos otherP = (Pos) other;
			return x==otherP.x && y==otherP.y;
		}
		public int hashCode() {
			return Integer.hashCode(13*x*y+7*x+11*y);
		}
		public String toString() {
			return x+" "+y;
		}
	}
	static final int[][] DIR = {{-1, 2}, {1, 2}, {2, 1}, {-2, 1}, {1, -2}, {-1, -2}, {-2, -1}, {2, -1}};
	static int[] moveCount = new int[4];
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		Pos start = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		Pos target = new Pos(Integer.parseInt(st.nextToken())-start.x,Integer.parseInt(st.nextToken())-start.y);
		Pos currentPos = new Pos(0,0);
		while(Math.abs(target.x-currentPos.x)>4 || Math.abs(target.y-currentPos.y)>4) {
			int maxDir=0;
			int maxDotProduct=0;
			for(int i=0;i<8;i++) {
				int newDot = DIR[i][0]*(target.x-currentPos.x)+DIR[i][1]*(target.y-currentPos.y);
				if(newDot>maxDotProduct) {
					maxDir = i;
					maxDotProduct=newDot;
				}
			}
			Pos newPos = new Pos(currentPos.x+DIR[maxDir][0],currentPos.y+DIR[maxDir][1]);
			newPos.moveCount = currentPos.moveCount.clone();
			if(maxDir<4)
				newPos.moveCount[maxDir]++;
			else
				newPos.moveCount[maxDir-4]--;
			currentPos = newPos;
		}
		Queue<Pos> frontier = new ArrayDeque<Pos>();
		frontier.offer(currentPos);
		HashSet<Pos> visited = new HashSet<Pos>();
		visited.add(currentPos);
//		System.out.println(currentPos);
//		for(int i=0;i<4;i++)
//			System.out.print(currentPos.moveCount[i]+" ");
//		System.out.println();
		while(!frontier.isEmpty()) {
			Pos currPos = frontier.poll();
			//System.out.println(currPos);
			if(currPos.equals(target)) {
				int totalMoves=0;
				//System.out.println(currPos);
				for(int i=0;i<4;i++) {
					//System.out.print(currPos.moveCount[i]+" ");
					totalMoves += Math.abs(currPos.moveCount[i]);
				}
				//System.out.println();
				System.out.println(totalMoves);
				break;
			}
			for(int i=0;i<8;i++) {
//				if(DIR[i][0]*(target.x-currPos.x)+DIR[i][1]*(target.y-currPos.y)<0)
//					continue;
				Pos newPos = new Pos(currPos.x+DIR[i][0],currPos.y+DIR[i][1]);
				if(visited.contains(newPos))
					continue;

				newPos.moveCount=currPos.moveCount.clone();
				if(i<4)
					newPos.moveCount[i]++;
				else
					newPos.moveCount[i-4]--;
				visited.add(newPos);
				frontier.offer(newPos);
			}
			
		}
		
	}
	
}
