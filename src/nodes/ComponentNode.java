package nodes;

import util.Util;

public class ComponentNode extends ASTNode {
	
	public ComponentNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		System.out.println(identation + "Component: " + nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}

}
