/**
 * @author Kochetov Nikolay M3138
 */

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FastScanner implements AutoCloseable {
    private Reader in;
    private String cashNext;
    private String additionalSeparators = "";
    private final boolean LINE_R = 
    		System.lineSeparator().length() < 2 && 
    		System.lineSeparator().indexOf('\r') > -1;
    
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
        in = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
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
        while (c > 0 && c != '\n') {
        	if (c == '\r') {
        		if (LINE_R) {
        			break;
        		}
        		int nextChar = in.read();
        		if (nextChar == '\n') {
        			break;
        		}
        		line.append(c);
        		c = nextChar;
        	} else {
        		line.append((char) c);
        		c = in.read();
        	}
        }

        return line.toString();
    }

    public interface Separators {
        boolean check(int c);
    }

    public String nextWithout(Separators whiteChar) throws IOException {
        var str = new StringBuilder();

        if (cashNext != null) {
            String ans = cashNext;
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
            str.append((char) c);
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
    
    public boolean hasNextWithout(Separators whiteChar) throws IOException {
    	if (cashNext != null) {
            return true;
        }
        cashNext = nextWithout(whiteChar);
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
	
	public int nextInt(int radix) throws IOException {
		return Integer.parseInt(next(), radix);
	}
	
	public void setAdditionalSeprators(String s) {
		this.additionalSeparators = s;
	}
	
	public boolean isTokensSeparator(int codePoint) {
		return Character.isWhitespace(codePoint) || 
				additionalSeparators.indexOf(codePoint) >= 0;
	}
}