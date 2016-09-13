package ast;

import java.util.ArrayList;
import java.util.List;

import util.Util;

public abstract class ASTNode {

	protected String nodeInfo;
	protected List<ASTNode> children;
	
	public ASTNode(String contents) {
		this.nodeInfo = "";
		this.children = new ArrayList<ASTNode>();
		parseString(contents);
	}

	public void parseString(String str) {
		
		//Remove opening and last parentheses
		String nodeContent = str.substring(1, str.length()-1);
		
		List<String> childNodeStrings = Util.getNextNodeStrings(nodeContent);
		
		//remove child information
		for (String childNodeString : childNodeStrings) {	
			nodeContent = nodeContent.replace(childNodeString, "");
    	}
		
		this.nodeInfo = nodeContent;
		
    	ASTNodeFactory factory = new ASTNodeFactory();
    	
    	for (String childNodeString : childNodeStrings) {	
    		ASTNode node = factory.createNode(childNodeString);
    		
    		if (node != null) {
    			children.add(node);
    		}
    	}
	}
	
	public abstract void prettyPrint(int ident);
}
