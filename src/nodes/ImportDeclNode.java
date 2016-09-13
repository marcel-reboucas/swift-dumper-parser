package nodes;

import util.Util;

public class ImportDeclNode extends ASTNode {

	public ImportDeclNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		System.out.println(identation + "Import Decl: "+ nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}

}
