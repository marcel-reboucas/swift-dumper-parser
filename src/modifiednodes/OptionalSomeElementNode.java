package modifiednodes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ast.ASTNode;
import util.Util;

public class OptionalSomeElementNode extends ASTNode {
	
	String type;
	
	public OptionalSomeElementNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		String className = this.getClass().getSimpleName();
		System.out.println(identation + className + ": " + nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}

	public void parseString(String str) {

		super.parseString(str);
		
		Pattern pattern = Util.getRegexPatternForType();
		Matcher matcher = pattern.matcher(nodeInfo);
				
		if (matcher.find()){
			this.type = matcher.group(1);
		}
	}
}
