package modifiednodes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ast.ASTNode;
import util.Util;

public class SourceFileNode extends ASTNode {
	
	public SourceFileNode(String contents) {
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
		
		Pattern pattern = Util.getRegexPatternBetweenQuotationMarks();
		Matcher matcher = pattern.matcher(nodeInfo);
				
		if (matcher.find()){
			this.sourceFilePath = matcher.group(1);
		}
		
		setSourceFilePathOfChildren(this, sourceFilePath);
	}
	
	private void setSourceFilePathOfChildren (ASTNode node, String sourcePath) {
		
    	for (ASTNode child : node.children) {
    		child.sourceFilePath = sourcePath;
    		setSourceFilePathOfChildren(child, sourcePath);
    	}
    }
}
