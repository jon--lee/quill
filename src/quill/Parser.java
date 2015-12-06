package quill;

import scanner.SQLScanner;
import scanner.Scannable;
import ast.Program;
import ast.Statement;

public class Parser {
	
	private SQLScanner sqlScanner;
	
	public static void main(String[] args){
		SQLScanner s = new SQLScanner("select * from world;");
		new Parser(s);
	}

	public Parser(SQLScanner scanner){
		this.sqlScanner = scanner;
		Program program = parseProgram();
		program.execute();
	}
	
	public Program parseProgram(){
		Program program = new Program();
		while(!sqlScanner.isEndOfFile()){
			Statement stmt = parseStatement();
			program.append(stmt);
		}
		return program;
	}
	
	public Statement parseStatement(){
		return null;
	}
	
	/**
	 * assignment defined as "<value> as <name>"
	 * @return
	 */
	public Statement parseCreate(){
		return null;
	}
	
	public Statement parseSelect(){
		return null;
	}
	
}
