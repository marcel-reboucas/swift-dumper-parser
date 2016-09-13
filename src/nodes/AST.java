package nodes;

import java.util.List;

import ast.ASTNodeFactory;
import util.Util;

//Starting point of the ast.
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

	public List<ASTNode> getChildren() {
		return children;
	}

	public void setChildren(List<ASTNode> children) {
		this.children = children;
	}
	
	@Override
	public void prettyPrint(int ident) {
		for (ASTNode child : children) { child.prettyPrint(ident); }
	}
}
