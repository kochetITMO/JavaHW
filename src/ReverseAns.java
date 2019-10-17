import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Comparator;

class ReverseAns {
	long[][] ans = new long[16][];
	int size = 0;

	void print(Writer out) throws IOException {
		for (int i = size - 1; i > -1; i--) {
			for (int j = (int) (ans[i][0] - 1); j > 1; j--) {
				out.write(Integer.toString((int) ans[i][j]));
				out.write(' ');
			}
			out.write("\n");
		}
	}

	void addLine() {
		if (size == ans.length) {
			ans = Arrays.copyOf(ans, size * 2);
		}
		// in zero -- size, in 1 -- sum
		ans[size] = new long[4];
		ans[size++][0] = 2;
	}

	void addLong(long now) {
		ans[--size][1] += now;
		if (ans[size][0] == ans[size].length) {
			ans[size] = Arrays.copyOf(ans[size], ans[size].length * 2);
		}
		ans[size][(int) ans[size++][0]++] = now;
	}

	void sort() {
		Arrays.parallelSort(ans, 0, size, Comparator.comparingLong(o -> o[1]));

		for (int i = 0; i < size; i++) {
			Arrays.parallelSort(ans[i], 2, (int) ans[i][0]);
		}
	}
}