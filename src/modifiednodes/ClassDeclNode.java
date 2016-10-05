package modifiednodes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import ast.ASTNode;
import ast.ASTNodeType;
import util.Util;

public class ClassDeclNode extends ASTNode {
	
	//class_decl "CVCalendarView" type='CVCalendarView.Type' access=public final @objc @_fixed_layout inherits: UIView
	
	public String name;
	public String type;
	public String inherits;
	public int numberOfFunctions;
	public int numberOfConstructors;
	public int numberOfAttributes;
	
	public ClassDeclNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		String className = this.getClass().getSimpleName();
		System.out.println(identation + className
				 + " name: " + name
				 + " inherits: " + inherits 
				 + " numberOfFunctions: " + numberOfFunctions
				 + " numberOfConstructors:" + numberOfConstructors
				 + " numberOfAttributes:" + numberOfAttributes);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}
	
	public void parseString(String str) {

		super.parseString(str);
		
		Pattern pattern = Util.getRegexPatternBetweenQuotationMarks();
		Matcher matcher = pattern.matcher(nodeInfo);
				
		if (matcher.find()){
			this.name = matcher.group(1);
		}
		
		pattern = Util.getRegexPatternForType();
		matcher = pattern.matcher(nodeInfo);
				
		if (matcher.find()){
			this.type = matcher.group(1);
		}
		
		if (nodeInfo.lastIndexOf("inherits: ") != -1) {
			this.inherits = nodeInfo.substring(nodeInfo.lastIndexOf("inherits: ") + 9).trim();
		}
		
		
		this.numberOfFunctions = this.containsChildrenOfType(ASTNodeType.FuncDecl, false).stream().
				filter(u -> !((FuncDeclNode)u).anonymous).collect(Collectors.toList()).size();
		this.numberOfConstructors = this.containsChildrenOfType(ASTNodeType.ConstructorDecl, true).size();
		this.numberOfAttributes = this.containsChildrenOfType(ASTNodeType.VarDecl, false).size();
	}

}
