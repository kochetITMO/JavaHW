import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import compactLists.IntList;

public class WordStatLineIndex {

	static class WordStatAns {
		private List<IntList> list;
		private int cnt = 0;
		
		WordStatAns(int lineInd, int allInd) {
			var intList = new IntList();
			intList.add(lineInd);
			intList.add(allInd);
			list = new ArrayList<IntList>();
			list.add(intList);
			cnt = 1;
		}

		void put(int lineInd, int allInd) {
			cnt++;
			if (list.get(list.size() - 1).get(0) == lineInd) {
				list.get(list.size() - 1).add(allInd);
			} else {
				var intList = new IntList();
				intList.add(lineInd);
				intList.add(allInd);
				list.add(intList);
			}
		}
		
		List<IntList> getList() {
			return list;
		}
		
		int getCnt() {
			return cnt;
		}
	}

	static Map<String, WordStatAns> solve(FastScanner in) throws IOException {
		Map<String, WordStatAns> map = new TreeMap<>();
		int lineCount = 0;

		String line = in.nextLine();
		while (line != null) {
			FastScanner lineScanner = new FastScanner(line);
			lineCount++;

			int wordInLineCount = 0;
			while (lineScanner.hasNextWithout(WordStatIndex::notPartOfWord)) {
				wordInLineCount++;
				String s = lineScanner.nextWithout(WordStatIndex::notPartOfWord).toLowerCase();
				WordStatAns val = map.get(s);
				if (val == null) {
					val = new WordStatAns(lineCount, wordInLineCount);
					map.put(s, val);
				} else {
					val.put(lineCount, wordInLineCount);
				}
			}

			line = in.nextLine();
			lineScanner.close();
		}

		return map;
	}

	static void print(Writer out, Map<String, WordStatAns> map) throws IOException {
		for (var entr : map.entrySet()) {
			out.write(entr.getKey());
			out.write(" ");
			out.write(Integer.toString(entr.getValue().getCnt()));
			List<IntList> list = entr.getValue().getList();
			for (IntList intList : list) {
				String lineCnt = " " + intList.get(0) + ":";
				out.write(lineCnt);
				intList.print(out, lineCnt, 1, intList.getSize());
			}
			out.write('\n');
		}
	}

	public static void main(String[] args) {
		try (FastScanner in = new FastScanner(new File(args[0]));
				Writer out = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(new File(args[1])), StandardCharsets.UTF_8))) {

			print(out, solve(in));

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Input/Output error: " + e.getMessage());
		}
	}
}
