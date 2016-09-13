package nodes;

import util.Util;

public class ParameterListNode extends ASTNode {

	public ParameterListNode(String contents) {
		super(contents);
	}
	
	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		System.out.println(identation + "Parameter List: "+ nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}
}
