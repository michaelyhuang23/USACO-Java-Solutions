import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

public class photo {
	static class Cow implements Comparable<Cow>{
		int[] positions;
		int id;
		public Cow(int[] pos, int i) {
			positions = pos;
			id=i;
		}
		@Override
		public int compareTo(Cow o) {
			int comparer=0;
			for(int i=0;i<positions.length;i++) {
				comparer+=Integer.compare(positions[i], o.positions[i]);
			}
			return comparer;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in)); //new FileReader("pails.in")  //new InputStreamReader(System.in)
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
		int numCow = Integer.parseInt(f.readLine());
		int[][] cowLines = new int[5][numCow];
		for(int i=0;i<5;i++) {
			for(int cowI=0;cowI<numCow;cowI++) {
				cowLines[i][cowI]=Integer.parseInt(f.readLine().trim());
			}
		}
		int[] referenceLine = cowLines[0].clone();
		Arrays.sort(referenceLine);
		HashMap<Integer,Integer> mapper = new HashMap<Integer, Integer>();
		for(int i=0;i<numCow;i++)
			mapper.put(referenceLine[i], i);
		for(int i=0;i<5;i++)
			for(int cowI=0;cowI<numCow;cowI++)
				cowLines[i][cowI]=mapper.get(cowLines[i][cowI]);
		int[][] cowPos = new int[numCow][5];
		for(int i=0;i<5;i++)
			for(int posI=0;posI<numCow;posI++)
				cowPos[cowLines[i][posI]][i]=posI;
		Cow[] allCows = new Cow[numCow];
		for(int i=0;i<numCow;i++)
			allCows[i]=new Cow(cowPos[i],i);
		
		Arrays.sort(allCows);
		int[] answers=new int[numCow];
		for(int i=0;i<numCow;i++) {
			System.out.println(referenceLine[allCows[i].id]);
			answers[i]=referenceLine[allCows[i].id];
		}
		boolean success=true;
		for(int i=0;i<numCow;i++) {
			success &= Integer.parseInt(f.readLine().trim())==answers[i];
		}
		System.out.println(success);
		f.close();
	}

	
}
