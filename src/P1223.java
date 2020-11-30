import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1223 {
	public static class Person implements Comparable<Person>{
		int time,id;
		public int compareTo(Person other) {
			return time-other.time;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numPeople = Integer.parseInt(f.readLine());
		StringTokenizer st = new StringTokenizer(f.readLine());
		Person[] people = new Person[numPeople];
		for(int i=0;i<numPeople;i++) {
			people[i]=new Person();
			people[i].time=Integer.parseInt(st.nextToken());
			people[i].id=i+1;
		}
		
		Arrays.parallelSort(people);
		long sum=0;
		for(int i=0;i<numPeople-1;i++) {
			System.out.print(people[i].id+" ");
			sum+=people[i].time*(numPeople-i-1);
		}
		System.out.println(people[numPeople-1].id);
		DecimalFormat fmt = new DecimalFormat("0.00");
		System.out.println(fmt.format((double)sum/(double)numPeople));
	}
}
