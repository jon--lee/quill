package quill;
import scanner.Scanner;
import ast.Statement;
public class Parser {
	
	private Scanner sqlScanner;
	
	public static void main(String[] args){
		
	}

	public Parser(Scanner scanner){
		this.sqlScanner = scanner;
		parseStatement();
	}
	
	public Statement parseStatement(){
		return null;
	}
	
	/**
	 * assignment defined as "<value> as <name>"
	 * @return
	 */
	public Statement parseAssigment(){
		return null;
	}
	
	/**
	 * 
	 */
	
}
