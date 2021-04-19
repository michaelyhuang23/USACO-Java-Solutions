import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class teleport {
	static class Point implements Comparable<Point>{
		int pos, id;
		public Point(int p, int i) {
			pos = p;
			id = i;
		}
		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return pos-o.pos;
		}
	}
	static class Shit extends Point{
		int startPos, endPos;
		boolean usingTele=false;
		public Shit(int s, int e, int i) {
			super(e, i);
			startPos = s;
			endPos = e;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("teleport.in")); //new FileReader("teleport.in")  //new InputStreamReader(System.in)
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
		int numShit = Integer.parseInt(f.readLine());
		Shit[] allShits = new Shit[numShit];
		ArrayList<Point> allPoints = new ArrayList<>();
		for(int i=0;i<numShit;i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int startPos = Integer.parseInt(st.nextToken());
			int endPos = Integer.parseInt(st.nextToken());
			allShits[i] = new Shit(startPos, endPos, i);
			allPoints.add(allShits[i]);
			if(startPos<=0 && endPos>=0 || startPos>=0 && endPos<=0)
				allPoints.add(new Point(2*endPos,i));
			else {
				allPoints.add(new Point(2*startPos,i));
				allPoints.add(new Point(2*(endPos-startPos),i));
			}
		}
		allPoints.sort(null);
		boolean[] shitUsingTele = new boolean[numShit];
		int leftTeleShit = 0;
		int rightTeleShit = 0;
		long totalDist = 0;
		int prevPos = allPoints.get(0).pos;
		for(int i=0;i<allPoints.size();i++) {
			if(allPoints.get(i).getClass().getSimpleName().equals("Shit")) {
				int shitIndex = allPoints.get(i).id;
				int normalDist = Math.abs(allShits[shitIndex].endPos-allShits[shitIndex].startPos);
				int teleDist = Math.abs(allShits[shitIndex].endPos-prevPos)+Math.abs(allShits[shitIndex].startPos);
				if(teleDist<normalDist) {
					shitUsingTele[shitIndex]=true;
					rightTeleShit++;
					totalDist += teleDist;
				}else
					totalDist += normalDist;
			}
		}
		//System.out.println(totalDist);
		long minDist = totalDist;
		int pointIndex;
		for(pointIndex=0;pointIndex<allPoints.size();pointIndex++) {	//pre-0 phase
			if(allPoints.get(pointIndex).pos>0)
				break;
			if(allPoints.get(pointIndex).getClass().getSimpleName().equals("Shit")) {
				int shitIndex = allPoints.get(pointIndex).id;
				totalDist+=leftTeleShit*(allShits[shitIndex].pos-prevPos);
				totalDist-=rightTeleShit*(allShits[shitIndex].pos-prevPos);
				prevPos = allShits[shitIndex].pos;
				if(shitUsingTele[shitIndex]) {
					leftTeleShit++;
					rightTeleShit--;
				}
				minDist = Math.min(minDist, totalDist);
			}else {
				int shitIndex = allPoints.get(pointIndex).id;
				int pos = allPoints.get(pointIndex).pos+1;
				int newDist = Math.abs(allShits[shitIndex].endPos-pos)+Math.abs(allShits[shitIndex].startPos);
				int displace = pos-1-prevPos;
				totalDist+=displace*leftTeleShit;
				totalDist-=displace*rightTeleShit;
				prevPos = pos-1;
				if(newDist<Math.abs(allShits[shitIndex].endPos-allShits[shitIndex].startPos)) {
					if(shitUsingTele[shitIndex]==false) {
						if(allShits[shitIndex].endPos < pos-1)
							leftTeleShit++;
						else
							rightTeleShit++;
					}
					shitUsingTele[shitIndex]=true;
				}else {
					if(shitUsingTele[shitIndex]==true) {
						if(allShits[shitIndex].endPos < pos-1)
							leftTeleShit--;
						else
							rightTeleShit--;
					}
					shitUsingTele[shitIndex]=false;
				}
				minDist = Math.min(minDist, totalDist);
			}
			//System.out.println(totalDist);
		}
		
		shitUsingTele = new boolean[numShit];
		leftTeleShit = 0;
		rightTeleShit = 0;
		totalDist = 0;
		prevPos = 0;
		for(int i=0;i<allPoints.size();i++) {
			if(allPoints.get(i).getClass().getSimpleName().equals("Shit")) {
				int shitIndex = allPoints.get(i).id;
				int normalDist = Math.abs(allShits[shitIndex].endPos-allShits[shitIndex].startPos);
				int teleDist = Math.abs(allShits[shitIndex].endPos-prevPos-1)+Math.abs(allShits[shitIndex].startPos);
				if(teleDist<normalDist) {
					shitUsingTele[shitIndex]=true;
					if(allShits[shitIndex].endPos<=0)
						leftTeleShit++;
					else
						rightTeleShit++;
					totalDist += Math.abs(allShits[shitIndex].endPos-prevPos)+Math.abs(allShits[shitIndex].startPos);;
				}else
					totalDist += normalDist;
			}
		}
		minDist = Math.min(minDist, totalDist);
		//System.out.println(totalDist);
		for(;pointIndex<allPoints.size();pointIndex++) {	//post-0 phase

			if(allPoints.get(pointIndex).getClass().getSimpleName().equals("Shit")) {
				int shitIndex = allPoints.get(pointIndex).id;
				totalDist+=leftTeleShit*(allShits[shitIndex].pos-prevPos);
				totalDist-=rightTeleShit*(allShits[shitIndex].pos-prevPos);
				prevPos = allShits[shitIndex].pos;
				if(shitUsingTele[shitIndex]) {
					leftTeleShit++;
					rightTeleShit--;
				}
				minDist = Math.min(minDist, totalDist);
				
			}else {
				int shitIndex = allPoints.get(pointIndex).id;
				int pos = allPoints.get(pointIndex).pos+1;
				int newDist = Math.abs(allShits[shitIndex].endPos-pos)+Math.abs(allShits[shitIndex].startPos);
				int displace = pos-1-prevPos;
				totalDist+=displace*leftTeleShit;
				totalDist-=displace*rightTeleShit;
				prevPos = pos-1;
				if(newDist<Math.abs(allShits[shitIndex].endPos-allShits[shitIndex].startPos)) {
					if(shitUsingTele[shitIndex]==false) {
						if(allShits[shitIndex].endPos < pos-1)
							leftTeleShit++;
						else
							rightTeleShit++;
					}
					shitUsingTele[shitIndex]=true;
				}else {
					if(shitUsingTele[shitIndex]==true) {
						if(allShits[shitIndex].endPos < pos-1)
							leftTeleShit--;
						else
							rightTeleShit--;
					}
					shitUsingTele[shitIndex]=false;
				}
				minDist = Math.min(minDist, totalDist);
			}
			//System.out.println(totalDist);
		}
		out.println(minDist);
		out.close();
		f.close();
		
	}
}
