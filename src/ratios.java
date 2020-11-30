import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
/*
ID: yhuang22
LANG: JAVA
TASK: ratios
*/
public class ratios {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ratios.in"));
		// /Users/michaelhyh/ProjectData/Eclipse Java/USACOChapter3Section3.2/src/
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int[] goal = new int[3];
		int[] first = new int[3];
		int[] second = new int[3];
		int[] third = new int[3];
		for(int i=0;i<3;i++) 
			goal[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		for(int i=0;i<3;i++)
			first[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		for(int i=0;i<3;i++)
			second[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		for(int i=0;i<3;i++)
			third[i] = Integer.parseInt(st.nextToken());
		
		int minTotal=Integer.MAX_VALUE;
		int[] minRatio = new int[4];
		for(int a=0;a<100;a++) {
			int[] const1 = new int[3];
			for(int i=0;i<3;i++) 
				const1[i]=first[i]*a;	
			for(int b=0;b<100;b++) {
				int[] const2 = new int[3];
				for(int i=0;i<3;i++) 
					const2[i]=second[i]*b;
				for(int c=0;c<100;c++) {
					int sum0 = const1[0]+const2[0]+third[0]*c, sum1 = const1[1]+const2[1]+third[1]*c, sum2 = const1[2]+const2[2]+third[2]*c;
					if(sum0==0 || sum0%goal[0]!=0)
						continue;
					int ratio = sum0/goal[0];
					if(ratio*goal[1]!=sum1 || ratio*goal[2]!=sum2)
						continue;
					if(a+b+c<minTotal) {
						minTotal=a+b+c;
						minRatio[0]=a;
						minRatio[1]=b;
						minRatio[2]=c;
						minRatio[3]=ratio;
					}
				}
				
			}
		}
		if(minRatio[3]==0)
			out.println("NONE");
		else
			out.println(minRatio[0]+" "+minRatio[1]+" "+minRatio[2]+" "+minRatio[3]);
		out.close();
		f.close();
	}
}
