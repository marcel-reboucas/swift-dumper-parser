package nodes;

import util.Util;

public class EnumDeclNode extends ASTNode {

	public EnumDeclNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		System.out.println(identation + "Enum Decl: " + nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}

}