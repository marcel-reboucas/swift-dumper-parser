package nodes;

import ast.ASTNode;

public class UnknownNode extends ASTNode {

	public UnknownNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		System.out.println("Generic Node to be implemented yet! : "+this.nodeInfo+"");
		for (ASTNode child : children){
			child.prettyPrint(ident+1);
		}
	}

}
