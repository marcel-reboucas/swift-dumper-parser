package nodes;

import util.Util;

public class EnumElementDeclNode extends ASTNode {

	public EnumElementDeclNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		System.out.println(identation + "Enum Element Decl: " + nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}

}
