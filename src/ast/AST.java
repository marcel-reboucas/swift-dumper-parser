package ast;

import java.util.List;

import util.Util;

//Starting point of the ast - basically parses source files.
public class AST extends ASTNode {

	public AST(String contents) {
		super(contents);
	}

    public final void parseString(String str) {
    	
    	List<String> childNodeStrings = Util.getNextNodeStrings(str);
    	ASTNodeFactory factory = new ASTNodeFactory();
    	
    	for (String childNodeString : childNodeStrings) {	
    		
    		ASTNode node = factory.createNode(childNodeString);
    		
    		if (node != null) {
    			children.add(node);
    		}
    	}
	}
	
	@Override
	public void prettyPrint(int ident) {
		String identation = Util.getTabsForIdentationAmmount(ident);
		String className = this.getClass().getSimpleName();
		System.out.println(identation + className + ": " + nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident); }
	}
}
