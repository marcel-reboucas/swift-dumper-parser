package nodes;

import util.Util;

public class FuncDeclNode extends ASTNode {
	
	public FuncDeclNode(String contents) {
		super(contents);
		parseString(contents);
	}
	
	public final void parseString(String str) {
		
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		System.out.println(identation + "Func Decl: " + nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}

}
