import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Judge {
    public static void main(String[] args) throws IOException {
        judge("/Users/michaelhyh/Downloads/2.out", "/Users/michaelhyh/Downloads/2solution.out");
    }

    private static void judge(String outFile, String ansFile) throws IOException {
        BufferedReader out = new BufferedReader(new FileReader(outFile));
        BufferedReader ans = new BufferedReader(new FileReader(ansFile));
        int i = 0;
        while (ans.ready()) {
            i++;
            String thisAns = ans.readLine().trim();
            String thisRes = out.readLine().trim();
            if (!thisAns.equals(thisRes)) {
                System.out.println("Wrong at line: " + i);
                System.out.println("Actual: " + thisAns);
                System.out.println("Yours: " + thisRes);
                return;
            }
        }
        if (out.ready()) {
            System.out.println("answer too long");
            return;
        }
        System.out.println("AC");
        out.close();
        ans.close();
    }

}
