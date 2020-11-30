import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class P4087 {
	public static class Measurement implements Comparable<Measurement>{
		public int date, cowNum, change;
		public int compareTo(Measurement other) {
			return date-other.date;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numMeasure = Integer.parseInt(st.nextToken());
		int origMilk = Integer.parseInt(st.nextToken());
		TreeMap<Integer,Integer> milkToCow = new TreeMap<Integer, Integer>();
		milkToCow.put(0, numMeasure+1);
		HashMap<Integer,Integer> cowToMilk = new HashMap<Integer, Integer>();
		Measurement[] allMeasurements = new Measurement[numMeasure];
		for(int i=0;i<numMeasure;i++) {
			st = new StringTokenizer(f.readLine());
			allMeasurements[i]=new Measurement();
			allMeasurements[i].date=Integer.parseInt(st.nextToken());
			allMeasurements[i].cowNum=Integer.parseInt(st.nextToken());
			allMeasurements[i].change=Integer.parseInt(st.nextToken());
		}
		
		Arrays.parallelSort(allMeasurements);
		for(int i=0;i<numMeasure;i++)
			cowToMilk.put(allMeasurements[i].cowNum, 0);
		int counter=0;
		for(int i=0;i<numMeasure;i++) {
			int oldHighest=milkToCow.lastKey();
			int oldVal = milkToCow.get(oldHighest);
			
			int oldMilk=cowToMilk.get(allMeasurements[i].cowNum);
			int newMilk=oldMilk+allMeasurements[i].change;
			if(milkToCow.containsKey(newMilk))
				milkToCow.put(newMilk, milkToCow.get(newMilk)+1);
			else
				milkToCow.put(newMilk, 1);
			int oldMilkCow=milkToCow.get(oldMilk);
			if(oldMilkCow<=1)
				milkToCow.remove(oldMilk);
			else
				milkToCow.put(oldMilk, oldMilkCow-1);
			cowToMilk.put(allMeasurements[i].cowNum,newMilk);
			int newHighest=milkToCow.lastKey();
			if(oldHighest!=oldMilk && newHighest==newMilk || oldHighest==oldMilk && newHighest!=newMilk || oldVal!=milkToCow.get(milkToCow.lastKey()))	//|| oldVal!=milkToCow.get(milkToCow.lastKey())
				counter++;
			//System.out.println(milkToCow.keySet());
		}
		System.out.println(counter);
	}
}
