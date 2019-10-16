import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import compactLists.IntList;

public class WordStatIndex {
	
	static boolean notPartOfWord(int c) {
		return !(
                Character.getType(c) == Character.DASH_PUNCTUATION
                        || Character.isLetter(c)
                        || c == '\''
        );
	}
	
	static Map<String, IntList> solve(FastScanner in) throws IOException {
		Map<String, IntList> map = new LinkedHashMap<>();
		int count = 0;

		while (in.hasNextWithout(WordStatIndex::notPartOfWord)) {
			String s = in.nextWithout(WordStatIndex::notPartOfWord).toLowerCase();
			count++;
			IntList list = map.get(s);
			if (list == null) {
				list = new IntList();
				list.add(count);
				map.put(s, list);
			} else {
				list.add(count);
			}
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
		try (
				FastScanner in = new FastScanner(new File(args[0]));
				Writer out = new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream(new File(args[1])),
								StandardCharsets.UTF_8
								)
						)
		) {
			
			print(out, solve(in));
			
		} catch (FileNotFoundException e) {
        	System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input/Output error: " + e.getMessage());
        }
	}
}
