package nodes;

import util.Util;

public class ParameterNode extends ASTNode {

	public ParameterNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		System.out.println(identation + "Parameter: "+ nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}
}
