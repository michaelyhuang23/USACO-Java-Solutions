import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
/*
ID: yhuang22
LANG: JAVA
TASK: milk
*/
class Farmer implements Comparable<Farmer>{
	public int price;
	public int units;
	public int ID;
	public Farmer(int ID,int price, int units) {
		this.ID=ID;
		this.price=price;
		this.units=units;
	}
	public int compareTo(Farmer other) {
		if(this.price==other.price)
			return this.ID-other.ID;
		return this.price-other.price;
	}
	
	public String toString() {
		return this.ID+" "+this.price +" "+ this.units;
	}
	public boolean equals(Object other) {
		return this.ID==((Farmer)other).ID;
	}
	
}
class milk {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("/Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.4/src/milk.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.4/src/
		File file=new File("/Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.4/src/milk.out");
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.4/src/milk.out")));
		String[] inputs=f.readLine().split(" ",2);
		int milkRequired=Integer.parseInt(inputs[0]);
		int numFarmers=Integer.parseInt(inputs[1]);
		Set<Farmer> farmers=new TreeSet<Farmer>();
		for(int i=0;i<numFarmers;i++) {
			String[] farmerIn=f.readLine().split(" ",2);
			Farmer farm=new Farmer(i,Integer.parseInt(farmerIn[0]),Integer.parseInt(farmerIn[1]));
			if(!farmers.add(farm))
				System.out.println(farm);
		}
		int price=0;
		for(Farmer farm:farmers) {
			
			milkRequired-=farm.units;
			price+=farm.units*farm.price;
			if(milkRequired<=0) {
				price-=-milkRequired*farm.price;
				break;
			}
		}
		file.delete();
		out.println(price);
		
		f.close();
		out.close();
	}
}
