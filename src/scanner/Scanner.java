package scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.InputStreamReader;
import java.io.InputStream;

public class Scanner implements Scannable {

	
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner("hello world =");
		System.out.println(s.nextToken());
		System.out.println(s.nextToken());
		System.out.println(s.nextToken());

	}
	
	private String[] operands = {"=", "+", "//", "!", "==", "!="};
	private BufferedReader in;
	private char currentChar;
	private boolean eof;
	
	public Scanner(InputStream inStream){
		setInput(inStream);
	}
	
	
	public Scanner(String inString){
		setInput(inString);
	}
	
	
	public void setInput(String input){
		in = new BufferedReader(new StringReader(input));
		eof = false;
		shift();
	}
	
	
	public void setInput(InputStream input){
		in = new BufferedReader(new InputStreamReader(input));
		eof = false;
		shift();
	}
	
	
	private void shift() {
		try {
			int readChar = in.read();
			if (readChar < 0)
				eof = true;
			currentChar = (char)readChar;
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	
	
	/**
	 * @return whether reached end of file or not
	 */
	public boolean isEndOfFile(){
		return eof;
	}
	
	/**
	 * Return if char is digit
	 * @param c
	 * @return
	 */
	public static boolean isDigit(char c) {
		return (c >= '0' && c <='9');
	}
	
	/**
	 * Return if char is letter
	 * @param c
	 * @return 
	 */
	public static boolean isLetter(char c) {
    	return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
    }
	
	/**
	 * Return if char is whitespace (line break, tab, space...)
	 * @param c
	 * @return
	 */
	public static boolean isWhiteSpace(char c) {
    	return (c == ' ' || c == '\n' || c == '\r' || c == '\t');
    }
	
	
	/**
	 * TODO Create tokenizer interface that should scan for certain tokens
	 * with given conditions. A special tokenizer should handle default cases
	 * such as unidentified operands
	 */
	
	/**
	 * Generates next token input sequence as a string
	 * Does not include whitespace.
	 * All tokenizers should leave currentChar on char
	 * after the token (not the last char in the token).
	 * @return
	 * @throws IOException
	 */
	public String nextToken() throws IOException {
		String token = "";
		
		while(!eof && isWhiteSpace(currentChar)){
			shift();
		}
		if(eof)
			throw new IOException("Scanner has reached end of input stream");
		else {
			if(isDigit(currentChar))
				token = scanNumber();
			else if(isLetter(currentChar))
				token = scanIdentifier();
			else
				token = scanOperand();
		}
		return token;
	}
	
	/**
	 * Tokenize a number which is any digit followed only by digits.
	 * @return
	 * @throws IOException
	 */
	public String scanNumber() throws IOException{
		String token = "" + currentChar;
		shift();
		while(!eof && isDigit(currentChar)){
			token += currentChar;
			shift();
		}
		return token;
	}
	
	/**
	 * Tokenize an identifier which is an letter 
	 * followed by letters or numbers only
	 * @return
	 * @throws IOException
	 */
	public String scanIdentifier() throws IOException{
		String token = "" + currentChar;
		shift();
		while(!eof && (isDigit(currentChar) || isLetter(currentChar))){
			token += currentChar;
			shift();
		}
		return token;
	}
	
	/**
	 * Last resort
	 * Tokenize an operand which is defined in a specified list
	 * Anything not in that list raises an exception.
	 * @return
	 * @throws IOException
	 */
	private String scanOperand() throws IOException {
		String token = "" + currentChar;
		shift();
		while(!eof
				&& !isWhiteSpace(currentChar)
				&& !isLetter(currentChar)
				&& !isDigit(currentChar)){
			token += currentChar;
			shift();
		}
		if (!isOperand(token))
			throw new IOException("Unrecognized operand: " + token);
    	return token;
    }
 	
	/**
	 * Determines if operand is in list of operands.
	 * @param operand
	 * @return
	 */
	private boolean isOperand(String operand) {
		for (int i = 0; i < operands.length; i++)
			if(operand.equals(operands[i]))
				return true;
		return false;
	}
}
