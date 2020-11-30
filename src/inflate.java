import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: yhuang22
LANG: JAVA
TASK: inflate
*/

public class inflate {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter3Section3.1/src/
		String[] inputs = f.readLine().replaceAll(" +", " ").split(" ", 2);
		int timeAllowed = Integer.parseInt(inputs[0]);
		int totalCategories= Integer.parseInt(inputs[1]);
		int[] categoryTime = new int[totalCategories];
		int[] categoryPoint = new int[totalCategories];
		for(int i=0;i<totalCategories;i++) {
			String[] cateIn = f.readLine().split(" ",2);
			categoryPoint[i] = Integer.parseInt(cateIn[0]);
			categoryTime[i] = Integer.parseInt(cateIn[1]);
		}
		f.close();
		int[] MaxPointNewLevel = new int[timeAllowed+1];
		int[] MaxPointPrevLevel = new int[timeAllowed+1];
		for(int categoriesAllowed=1;categoriesAllowed<=totalCategories;categoriesAllowed++) {
			for(int maxTime=1;maxTime<=timeAllowed;maxTime++) {
				if(maxTime<categoryTime[categoriesAllowed-1]) {
					int maxPoint=0;
					for(int numNewCate = 0; numNewCate*categoryTime[categoriesAllowed-1] <= maxTime; numNewCate++) {
						maxPoint = Math.max(MaxPointPrevLevel[maxTime-numNewCate*categoryTime[categoriesAllowed-1]]+numNewCate*categoryPoint[categoriesAllowed-1], maxPoint);
					}
					MaxPointNewLevel[maxTime] = maxPoint;
				}else {
					MaxPointNewLevel[maxTime] = Math.max(MaxPointNewLevel[maxTime-categoryTime[categoriesAllowed-1]]+categoryPoint[categoriesAllowed-1],MaxPointPrevLevel[maxTime]);
				}
				//System.out.print(maxPoint+" ");
			}
			for(int i=1;i<=timeAllowed;i++)
				MaxPointPrevLevel[i] = MaxPointNewLevel[i];
			//System.out.println();
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		out.println(MaxPointNewLevel[timeAllowed]);
		out.close();
	}
}
