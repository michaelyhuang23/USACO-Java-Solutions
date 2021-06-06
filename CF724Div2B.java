import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CF724Div2B {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		int numT = Integer.parseInt(f.readLine());
		for (int i = 0; i < numT; i++) {
			int n = Integer.parseInt(f.readLine());
			boolean success =false;
			String str = f.readLine();
			for (int j = 'a'; j <= 'z'; j++) {
				if(str.contains(Character.toString((char)j)))
					continue;
				System.out.println((char)j);
				success=true;
				break;
			}
			if(success) {
				continue;
			}
			for (int j = 'a'; j <= 'z'; j++) {
				for (int j2 = 'a'; j2 <= 'z'; j2++) {
					String tmp = Character.toString((char)j)+Character.toString((char)j2);
					if(str.contains(tmp))
						continue;
					System.out.println(tmp);
					success=true;
					break;
				}
				if(success)
					break;
			}
			if(success) {
				continue;
			}
			for (int j = 'a'; j <= 'z'; j++) {
				for (int j2 = 'a'; j2 <= 'z'; j2++) {
					for (int j3 = 'a'; j3 <= 'z'; j3++) {
						String tmp = Character.toString((char)j)+Character.toString((char)j2)+Character.toString((char)j3);
						if(str.contains(tmp))
							continue;
						System.out.println(tmp);
						success=true;
						break;
					}
					if(success)
						break;
				}
				if(success)
					break;
			}
			if(success) {
				continue;
			}
			System.out.println("ERROR");
		}
	}
}
