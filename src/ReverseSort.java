import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class ReverseSort {

    static void solve(FastScanner in, BufferedWriter out) throws IOException {
        long[][] ans = new long[16][];
        int size = 0;

        while (in.hasNextLine()) {
        	if (size == ans.length) {
                ans = Arrays.copyOf(ans, size * 2);
            }
            // in zero -- size, in 1 -- sum
            ans[size] = new long[4];
            ans[size][0] = 2;
            
            while (in.hasNextLongInLine()) {
                long nowInt = in.nextLongInLine();
                ans[size][1] += nowInt;
                if (ans[size][0] == ans[size].length) {
                    ans[size] = Arrays.copyOf(ans[size], ans[size].length * 2);
                }
                ans[size][(int)ans[size][0]++] = nowInt;
            }
            size++;
        }

        Arrays.parallelSort(ans, 0, size, Comparator.comparingLong(o -> o[1]));

        for (int i = 0; i < size; i++) {
            Arrays.parallelSort(ans[i], 2, (int) ans[i][0]);
        }

        for (int i = size - 1; i > -1; i--) {
            for (int j = (int) (ans[i][0] - 1); j > 1; j--) {
                out.write(Integer.toString((int) ans[i][j]));
                out.write(' ');
            }
            out.write("\n");
        }
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