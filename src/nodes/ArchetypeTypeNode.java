package nodes;

import ast.ASTNode;
import util.Util;

public class ArchetypeTypeNode extends ASTNode {
	
	public ArchetypeTypeNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		String className = this.getClass().getSimpleName();
		System.out.println(identation + className + ": " + nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}

}
