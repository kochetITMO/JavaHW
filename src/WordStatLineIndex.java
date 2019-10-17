import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import compactLists.IntList;

public class WordStatLineIndex {
	static boolean notPartOfWord(int c) {
		return !(Character.getType(c) == Character.DASH_PUNCTUATION || Character.isLetter(c) || c == '\'');
	}

	static Map solve(FastScanner in) throws IOException {
		Map<String, List<IntList>> map = new LinkedHashMap<>();
		int lineCount = 0;

		String line = in.nextLine();
		while (line != null) {
			FastScanner lineScanner = new FastScanner(line);
			
			int wordInLineCount = 0;
			while (lineScanner.hasNextWithout(WordStatIndex::notPartOfWord)) {
				wordInLineCount++;
				String s = lineScanner.nextWithout(WordStatIndex::notPartOfWord).toLowerCase();
				lineCount++;
				List<IntList> list = map.get(s);
				if (list == null) {
					list = List.of(new IntList());
					list.get(0).add(lineCount);
					list.get(0).add(wordInLineCount);
					map.put(s, list);
				} else {
					if (list.get(list.size() - 1).get(0) == lineCount) {
						list.get(list.size() - 1).add(lineCount);
					}
				}
			}
			
			line = in.nextLine();
		}

		return map;
	}

	static void print(Writer out, Map<String, IntList> map) throws IOException {
		for (var entr : map.entrySet()) {
			out.write(entr.getKey());
			out.write(" ");
			out.write(Integer.toString(entr.getValue().getSize()));
			out.write(" ");
			entr.getValue().print(out);
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
