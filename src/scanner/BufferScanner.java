package scanner;

import java.io.BufferedReader;
import java.io.InputStream;

public abstract class BufferScanner implements Scannable {
	
	protected BufferedReader in;

	
	public BufferScanner(String inString){
		setInput(inString);
	}
	
	public BufferScanner(InputStream inStream){
		setInput(inStream);
	}
	
	public abstract void setInput(String inString);
	public abstract void setInput(InputStream inStream);
	
}
