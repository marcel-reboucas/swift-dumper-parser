package analysis;

import ast.AST;

public class DumperParser {
	
	String dumperOutput;
	
	public DumperParser(String str){
		this.dumperOutput = str;
	}

	public AST generateAST() {
		return new AST(dumperOutput);
	}
}
