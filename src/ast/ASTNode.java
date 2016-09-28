package ast;

import java.util.ArrayList;
import java.util.List;

import util.Util;

public abstract class ASTNode {

	protected String nodeInfo;
	protected List<ASTNode> children;

	public boolean isImplicit;
	
	public ASTNode(String contents) {
		this.nodeInfo = "";
		this.isImplicit = false;
		this.children = new ArrayList<ASTNode>();
		parseString(contents);
	}

	public void parseString(String str) {

		// Remove opening and last parentheses
		String nodeContent = str.substring(1, str.length() - 1);

		List<String> childNodeStrings = Util.getNextNodeStrings(nodeContent);

		// remove child information
		for (String childNodeString : childNodeStrings) {
			nodeContent = nodeContent.replace(childNodeString, "");
		}

		this.nodeInfo = nodeContent;

		if (nodeInfo.contains(" implicit ")) {
			this.isImplicit = true;
		}
		
		ASTNodeFactory factory = new ASTNodeFactory();

		for (String childNodeString : childNodeStrings) {
			ASTNode node = factory.createNode(childNodeString);

			if (node != null) {
				children.add(node);
			}
		}
	}

	public List<ASTNode> containsChildrenOfType (ASTNodeType type, boolean allowsImplicit) {
	        
	    	List<ASTNode> nodes = new ArrayList<ASTNode>();

	    	for (ASTNode child : children) {
	    		if (type.clazz.isInstance(child)){
	    			if (allowsImplicit || !child.isImplicit){
	    				nodes.add(child);
	    			}
	    		}	
	    	}
	    	return nodes;
	    }
	
	public List<ASTNode> containsChildrenOfTypeRecursive(ASTNodeType type) {
		
		List<ASTNode> nodes = new ArrayList<ASTNode>();
		containsChildrenOfTypeRecursive(type, nodes);
		return nodes;
	}
	
	private void containsChildrenOfTypeRecursive (ASTNodeType type, List<ASTNode> nodes) {

    	for (ASTNode child : children) {
    		if (type.clazz.isInstance(child)){
    			nodes.add(child);
    		}
    		
    		child.containsChildrenOfTypeRecursive(type, nodes);
    	}
    }

	public abstract void prettyPrint(int ident);

}
