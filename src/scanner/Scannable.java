package scanner;
import java.io.IOException;
public interface Scannable {
	
	/**
	 * method: nextToken
	 * @return token	next token in input stream
	 */
	String nextToken() throws IOException;
	
}
