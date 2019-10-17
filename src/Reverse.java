import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;

public class Reverse {

	static ReverseAns solve(FastScanner in) throws IOException {
		var ans = new ReverseAns();
		
		String line = in.nextLine();
		while (line != null) {
			ans.addLine();
			FastScanner lineScanner = new FastScanner(line);
			while (lineScanner.hasNext()) {
				ans.addLong(lineScanner.nextLong());
			}
			line = in.nextLine();
		}

		return ans;
	}

	public static void main(String[] args) {
		try (FastScanner in = new FastScanner(System.in);
				Writer out = new BufferedWriter(new OutputStreamWriter(System.out))) {

			solve(in).print(out);
			;

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Input/Output error: " + e.getMessage());
		}
	}
}
