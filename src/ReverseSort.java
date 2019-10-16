import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ReverseSort {

    static void solve(FastScanner in, BufferedWriter out) throws IOException {
        ReverseAns ans = Reverse.solve(in);
        ans.sort();
        ans.print(out);
    }

    public static void main(String[] args) {
        try (
                FastScanner in = new FastScanner(System.in);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {

            solve(in, out);

        } catch (IOException e) {
            System.out.println("Input/Output error: " + e.getMessage());
        }
    }
}