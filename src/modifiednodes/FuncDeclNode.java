package modifiednodes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ast.ASTNode;
import util.Util;

public class FuncDeclNode extends ASTNode {
	
	//'anonname=0x7faed893a090'
	
	String name; 
	String type;
	boolean anonymous;
	
	public FuncDeclNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		String className = this.getClass().getSimpleName();
		System.out.println(identation + className + ": " + 
		" name: "+name +
		" type: "+type);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}

	public void parseString(String str) {

		super.parseString(str);
		
		if (nodeInfo.indexOf("brace_stmt") != -1){
			nodeInfo = nodeInfo.substring(0,nodeInfo.indexOf("brace_stmt"));
			this.isImplicit = nodeInfo.contains(" implicit ");
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
		
		if (this.name != null) {
			this.anonymous = name.contains("anonname");
		} else {
			this.anonymous = true;
		}
		
	}
}
