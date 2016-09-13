package nodes;

import util.Util;

public class SourceFileNode extends ASTNode {
	
	public SourceFileNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		System.out.println(identation + "Source File: "+ nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}

}
