import java.io.Closeable;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Interface for homework about "fast" scanner.
 *
 * All mentioned methods (as this gist too) is just a recomendation for students.
 * No exact following of it is required but it might make life easier in future.
 * 
 * But there are global requirements for implementation of "fast" scanner:
 * <ul>
 *     <li>Instance of "fast" scanner has to be constructed with any InputStream or Reader.</li>
 *     <li>Reading or any other manipulations with character more than once is prohibited.</li>
 *     <li>No raw expanding buffers of characters sould be used.</li>
 *     <li>
 *         Implemented logic must be strictly bounded with purposes of this type 
 *         (f.e. `sorting` is not purpose of this type).
 *     </li>
 *     <li>Rational approach should be used everywhere.</li>
 * </ul>
 */
public interface IFastScanner extends Closeable {
    
    /**
     * Check if next token (sequence of non whitespace characters) is available.
     * The negative answer should be interpreted as EOF signal.
     *
     * This method can be called several times in a row but the answer should stay
     * the same until some of `next...()` method will be called
     *
     * @return <b>true</b> if one more token is available; <b>false</b> otherwise
     * @throws IOException in case of problems or reading from InputStream or Reader
     */
    boolean hasNext () throws IOException;
    
    /**
     * Check if next token is available before the EOF or \r\n (Windows) or \n (Unix).
     * The negative answer should be interpreted as EOL signal
     *
     * @return <b>true</b> if one more token is available; <b>false</b> otherwise
     * @throws IOException in case of problems or reading from InputStream or Reader
     */
    boolean hasNextInLine () throws IOException;
    
    boolean hasNextInt (int radix) throws IOException;
    
    boolean hasNextIntInLine (int radix) throws IOException;
    
    // and so on ...
    
    /**
     * @return next available token (doesn't matter if it is in new or non-new line)
     * @throws IOException in case of problems or reading from InputStream or Reader
     */
    String next () throws IOException;
    
    /**
     * This method should skip all characters until new line started.
     * It can be useful when {#hasNextInLine ()} returns <code>false</code>
     * and {#hasNext ()} returns <code>true<code> but you don't know how many 
     * empty lines was skipped during the scanning for new token
     */
    void skipLine () throws IOException;
    
    // All trics with
    // <code>
    // try {
    //     Integer.parseInt (token);
    //     return true;
    // } catch (NumberFormatException nfe) { 
    //     return false;
    // }
    // </code>
    // are allowed!
    
    int nextInt (int radix) throws IOException;
    
    // internal logic method
    
    /**
     * This method check if given character is separating character.
     * It can be use in case when not only whitespace characters separaters words,
     * for example here also can be ',' (comma) or ';' (semicolon) etc.
     *
     * @argument codePoint integer code of some character
     * @return <b>true</b> if given character is not a part of word and should be skipped;
     * <b>false</b> otherwise
     */
    boolean isTokensSeparator (int codePoint);
    
}