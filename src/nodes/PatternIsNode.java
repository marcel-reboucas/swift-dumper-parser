package nodes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ast.ASTNode;
import util.Util;

public class PatternIsNode extends ASTNode {
	
	public String genericType;
	public String castedType;
	
	public PatternIsNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		String className = this.getClass().getSimpleName();
		System.out.println(identation + className + ": " + nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}
	
	@Override
	public void parseString(String str) {
		// TODO Auto-generated method stub
		super.parseString(str);
		
		Pattern pattern = Util.getRegexPatternForType();
		Matcher matcher = pattern.matcher(nodeInfo);
				
		if (matcher.find()){
			this.genericType = matcher.group(1);
		}
		
		if (this.nodeInfo.contains("coercion")) {
			this.castedType = nodeInfo.substring(nodeInfo.lastIndexOf("coercion ") + 8).trim();
		} else if (this.nodeInfo.contains("value_cast")) {
			this.castedType = nodeInfo.substring(nodeInfo.lastIndexOf("value_cast ") + 10).trim();
		}
	}

}
