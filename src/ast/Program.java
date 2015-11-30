package ast;

import java.util.ArrayList;
import ast.Statement;

public class Program implements Statement {
	
	private ArrayList<Statement> statements;
	
	public Program(){
		statements = new ArrayList<Statement>();
	}
	
	public void append(Statement s){
		statements.add(s);
	}
	
	public void execute(){
		for (int i = 0; i < statements.size(); i++){
			statements.get(i).execute();
		}
	}
}
