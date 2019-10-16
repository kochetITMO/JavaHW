/**
 * @author Kochetov Nikolay M3138
 */

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FastScanner implements IFastScanner {
    private Reader in;
    private String cashNext;
    private FastScanner lineScanner;
    private String additionalSeparators;

    FastScanner(String s) {
        in = new StringReader(s);
    }

    FastScanner(File file) throws FileNotFoundException {
        in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file),
                        StandardCharsets.UTF_8
                )
        );
    }

    FastScanner(Reader in) {
    	this.in = in;
    }
    
    FastScanner(InputStream stream) {
        in = new BufferedReader(new InputStreamReader(stream));
    }
    
    public void close() throws IOException {
        in.close();
    }


    public String nextLine() throws IOException {
        StringBuilder line = new StringBuilder();

        int c = in.read();
        if (c < 0) {
            return null;
        }
        while (c > 0 && c != '\n' && c != '\r') {
            line.append(c);
            c = in.read();
        }

        return line.toString();
    }

    public interface Separators {
        boolean check(int c);
    }

    public String nextWithout(Separators whiteChar) throws IOException {
        var str = new StringBuilder();

        if (cashNext != null) {
            String ans = cashNext; // создание копии
            cashNext = null;
            return ans;
        }
        int c = in.read();
        while (c < 0 || whiteChar.check(c)) {
            c = in.read();
            if (c < 0) {
                return null;
            }
        }

        while (!whiteChar.check(c) && c > 0) {
            str.append(c);
            c = in.read();
        }

        return str.toString();
    }

    public String next() throws IOException {
        return nextWithout(this::isTokensSeparator);
    }

    public boolean hasNext() throws IOException {
        if (cashNext != null) {
            return true;
        }
        cashNext = next();
        return cashNext != null;
    }

    public boolean hasNextInt() throws IOException {
        if (hasNext()) {
            try {
                Integer.parseInt(cashNext);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public int nextInt() throws IOException, IllegalArgumentException {
        return Integer.parseInt(next());
    }

    public boolean hasNextLong() throws IOException {
        if (hasNext()) {
            try {
                Long.parseLong(cashNext);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public long nextLong() throws IOException, IllegalArgumentException {
        return Long.parseLong(next());
    }
    
    private boolean lineScannerCheck() throws IOException {
    	if (lineScanner == null) {
    		String line = nextLine();
    		if (line == null) {
    			return false;
    		}
    		lineScanner = new FastScanner(line);
    	}
    	return true;
    }
    
    public String nextInLine() throws IOException {
    	if (!lineScannerCheck()) {
    		return null;
    	}
    	return lineScanner.next();
    }
    
    public long nextLongInLine() throws IOException {
    	return lineScanner.nextLong();
    }
    
	@Override
	public boolean hasNextInLine() throws IOException {
		if (!lineScannerCheck()) {
    		return false;
    	}
		return lineScanner.hasNext();
	}

	@Override
	public boolean hasNextInt(int radix) throws IOException {
		if (hasNext()) {
            try {
                Integer.parseInt(cashNext, radix);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else {
            return false;
        }
	}

	@Override
	public boolean hasNextIntInLine(int radix) throws IOException {
		if (!lineScannerCheck()) {
    		return false;
    	}
		return lineScanner.hasNextInt();
	}

	@Override
	public void skipLine() throws IOException {
		lineScanner = null;
		nextLine();
	}

	@Override
	public int nextInt(int radix) throws IOException {
		return Integer.parseInt(next(), radix);
	}
	
	public void setAdditionalSeprators(String s) {
		this.additionalSeparators = s;
	}
	
	@Override
	public boolean isTokensSeparator(int codePoint) {
		return Character.isWhitespace(codePoint) || 
				additionalSeparators.indexOf(codePoint) >= 0;
	}
}