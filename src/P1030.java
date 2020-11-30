import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1030 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inorder = br.readLine();
		String postorder = br.readLine();
		makeTree(inorder,postorder);
		System.out.println();
		
	}
	public static void makeTree (String inorder, String postorder) {
		if(inorder.length()==0)
			return;
		char root = postorder.charAt(postorder.length()-1);
		int rootIndex = inorder.indexOf(root);
		System.out.print(root);
		makeTree(inorder.substring(0,rootIndex),postorder.substring(0,rootIndex));
		makeTree(inorder.substring(rootIndex+1,inorder.length()),postorder.substring(rootIndex,postorder.length()-1));
	}
}
