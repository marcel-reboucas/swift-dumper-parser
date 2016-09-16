package nodes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ast.ASTNode;
import util.Util;

public class VarDeclNode extends ASTNode {
	
	public String name;
	public String type;
	public boolean isLet;
	
	public VarDeclNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		String className = this.getClass().getSimpleName();
		System.out.println(identation + className + ": " + "Name: "+ this.name +", Type: "+ this.type + ", isLet :"+isLet);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}
	
	public void parseString(String str) {

		super.parseString(str);
		
		if (nodeInfo.contains(" let ")) {
			this.isLet = true;
		}
		
		Pattern pattern = Util.getRegexPatternBetweenQuotationMarks();
		Matcher matcher = pattern.matcher(nodeInfo);
				
		if (matcher.find()){
			this.name = matcher.group(1);
		}
		
		pattern = Util.getRegexPatternForType();
		matcher = pattern.matcher(nodeInfo);
				
		if (matcher.find()){
			this.type = matcher.group(1);
		}
	}

}
