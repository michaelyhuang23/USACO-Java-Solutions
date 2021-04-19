import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/*
ID: yhuang22
LANG: JAVA
TASK: wormhole
*/
public class wormhole {
	public static int count=0;
	class hole implements Comparable<hole>{
		public int x,y;
		public hole linkHole,nextHole,previousHole;
		public hole(int x,int y) {
			this.x=x;
			this.y=y;
		}
		public boolean equals(Object other) {
			if(other==null)
				return false;
			hole otherHole=(hole)other;
			return x==otherHole.x && y==otherHole.y;
		}
		public int compareTo(hole otherHole) {
			if(y!=otherHole.y)
				return y-otherHole.y;
			return x-otherHole.x;
		}
		public void setPrevious(hole previousHole) {
			this.previousHole=previousHole;
		}
		public void setNext(hole nextHole) {
			this.nextHole=nextHole;
		}
		public void setLinkHole(hole linkHole) {
			this.linkHole=linkHole;
		}
		public String toString() {
			return "("+x+", "+y+")";
		}
	}
	
	class passerby implements Comparable<passerby>{
		public hole holePassed;
		public boolean isEnter;
		public passerby(hole h, boolean isEnter) {
			this.holePassed=h;
			this.isEnter=isEnter;
		}
		public boolean equals(Object other) {
			if(other==null)
				return false;
			passerby otherPasser=(passerby)other;
			return this.holePassed.equals(otherPasser.holePassed) && this.isEnter==otherPasser.isEnter;
		}
		public int compareTo(passerby otherPasser) {
			if(holePassed.compareTo(otherPasser.holePassed)==0) {
				int thisInt = isEnter ? 1 : 0;
				int otherInt = otherPasser.isEnter ? 1:0;
				return thisInt-otherInt;
			}
			return holePassed.compareTo(otherPasser.holePassed);
		}
		public String toString() {
			return holePassed+" by "+isEnter;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
		// /Users/michaelhyh/Project Data/Eclipse Java/USACOChapter1Section1.4/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
		int numHoles=Integer.parseInt(f.readLine());
		wormhole thisWorm=new wormhole();
		Map<Integer, ArrayList<hole>> yMap=new TreeMap<Integer, ArrayList<hole>>();
		Set<hole> allHoles=new TreeSet<hole>();
		for(int i=0;i<numHoles;i++) {
			String[] inputs=f.readLine().split(" ",2);
			hole newHole=thisWorm.new hole(Integer.parseInt(inputs[0]),Integer.parseInt(inputs[1]));
			//allHoles.add(newHole);
			if(!yMap.containsKey(newHole.y))
				yMap.put(newHole.y,new ArrayList<hole>());
			yMap.get(newHole.y).add(newHole);
			allHoles.add(newHole);
		}
		for(int y:yMap.keySet()) {
			yMap.get(y).sort(null);
		}
		for(ArrayList<hole> levelHoles:yMap.values()) {
			for(int i=0;i<levelHoles.size()-1;i++) {
				levelHoles.get(i).setNext(levelHoles.get(i+1));
				levelHoles.get(i+1).setPrevious(levelHoles.get(i));
			}
		}
		
		//System.out.println(allHoles);
		TreeSet<hole> newAllHoles=new TreeSet<hole>();
		for(hole h:allHoles)
			newAllHoles.add(h);
		permutation(newAllHoles,allHoles);
		out.println(count);
		out.close();
		f.close();
	}
	public static boolean DFS(hole startHole,Set<passerby> passedHoles,boolean isEnter) {
		wormhole thisWorm=new wormhole();
		//System.out.println(startHole+"  "+isEnter+"   "+passedHoles);
		if(startHole==null) 
			return false;
		if(passedHoles.contains(thisWorm.new passerby(startHole,isEnter))) {
			
			return true;
		}
		passedHoles.add(thisWorm.new passerby(startHole,isEnter));
		if(isEnter) {
			return DFS(startHole.linkHole, passedHoles, false);
		}else {
			return DFS(startHole.nextHole, passedHoles, true);
		}
	}
	public static boolean isCyclic(Set<hole> allHoles) {
		//System.out.println("\n\n");
		for(hole eachHole:allHoles) {
			boolean isGood=DFS(eachHole, new TreeSet<passerby>(),true);
			//System.out.println();
			if(isGood) {
				return true;
			}
		}
		return false;
	}
	public static void permutation(TreeSet<hole> allHoles, Set<hole> allHolesPreserved) {
		if(allHoles.isEmpty()) {
			//System.out.println("haha");
			if(isCyclic(allHolesPreserved))
				count++;
			return;
			//check cyclic
		}
		Queue<hole> newAllHoles=new LinkedList<hole>();
		for(hole h:allHoles)
			newAllHoles.add(h);
		
		hole firstHole=newAllHoles.remove();
		allHoles.remove(firstHole);
		while(!newAllHoles.isEmpty()) {
			hole nextHole=newAllHoles.remove();
			nextHole.setLinkHole(firstHole);
			firstHole.setLinkHole(nextHole);
			allHoles.remove(nextHole);
			//System.out.println(firstHole+" - "+nextHole+"   "+allHoles);
			permutation(allHoles, allHolesPreserved);
			allHoles.add(nextHole);
			//System.out.println(firstHole+" - "+nextHole+" end   "+allHoles);
			nextHole.setLinkHole(null);
			firstHole.setLinkHole(null);
		}
		allHoles.add(firstHole);

	}
	

}
