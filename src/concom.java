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
TASK: concom
*/
class Company{
	public int index;
	public LinkedList<Integer> directControl;
	public HashMap<Integer,Integer> indirectConnect;
	public Company(int index) {
		this.index=index;
		directControl = new LinkedList<Integer>();
		indirectConnect = new HashMap<Integer,Integer>();
	}
	public String toString() {
		return Integer.toString(index);
	}
	
}
public class concom {
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("concom.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter1Section2.2/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		int numConnection = Integer.parseInt(f.readLine());
		Company[] comps = new Company[101];
		boolean[][] compsControl = new boolean[101][101]; //let the row be the controller, and the column be the controlled
		for(int i=0;i<numConnection;i++) {
			String[] inputStr=f.readLine().split(" ",3);
			int[] inputs= {Integer.parseInt(inputStr[0]),Integer.parseInt(inputStr[1]),Integer.parseInt(inputStr[2])};
			if(inputs[0]==inputs[1])
				continue;
			if(comps[inputs[1]]==null)
				comps[inputs[1]]=new Company(inputs[1]);
			if(comps[inputs[0]]==null)
				comps[inputs[0]]=new Company(inputs[0]);
			if(inputs[2]>50)
				comps[inputs[0]].directControl.add(inputs[1]);
			else
				comps[inputs[0]].indirectConnect.put(inputs[1], inputs[2]);
		}
		//System.out.println(comps[34].directControl);
		for(int index=1;index<=100;index++) {
			if(comps[index]==null)
				continue;
			compsControl[index][index]=true;
			int[] controlledCompPercent =new int[101];
			for(int indexOther : comps[index].indirectConnect.keySet())
				controlledCompPercent[indexOther]+=comps[index].indirectConnect.get(indexOther);
			LinkedList<Integer> copiedControl=(LinkedList<Integer>) comps[index].directControl.clone();
			HashSet<Integer> traversedComp = new HashSet<Integer>();
			while(!copiedControl.isEmpty()) {
				Company controComp=comps[copiedControl.remove()];
				traversedComp.add(controComp.index);
				compsControl[index][controComp.index]=true;
				//System.out.println(index+" "+controComp.index);
				for(int connect2 : controComp.directControl) {
					//compsControl[index][connect2.index]=true;
					if(!traversedComp.contains(connect2))
						copiedControl.add(connect2);
					
				}
				for(int comp : controComp.indirectConnect.keySet()) {
					controlledCompPercent[comp]+=controComp.indirectConnect.get(comp);
					if(controlledCompPercent[comp]>50) {
						//compsControl[index][comp]=true;
						if(!traversedComp.contains(comp))
							copiedControl.add(comp);
					}
						
				}
			}
			
			
		}
		for(int r=1;r<=100;r++) {
			for(int c=1;c<=100;c++) {
				if(c==r || !compsControl[r][c])
					continue;
				out.println(r+" "+c);
			}
		}
		f.close();
		out.close();
	}
}
