package modifiednodes;

import ast.ASTNode;
import util.Util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClosureExprNode extends ASTNode {
	
	String type;


	public ClosureExprNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		String className = this.getClass().getSimpleName();
		System.out.println(identation + className + ": " + nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}


	public void parseString(String str){

		super.parseString(str);
		Pattern pattern = Util.getRegexPatternForType();
		Matcher matcher = pattern.matcher(nodeInfo);

		if (matcher.find()){
			this.type = matcher.group(1);
		}

	}

}
