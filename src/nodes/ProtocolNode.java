package nodes;

import util.Util;

public class ProtocolNode extends ASTNode {

	public ProtocolNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		System.out.println(identation + "Protocol: "+ nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}

}
