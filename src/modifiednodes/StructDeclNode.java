package modifiednodes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import ast.ASTNode;
import ast.ASTNodeType;
import metrics.MetricType;
import util.Util;

public class StructDeclNode extends ASTNode {
	
	public String name;
	public String type;
	public String inherits;
	public int numberOfFunctions;
	public int numberOfConstructors;
	public int numberOfAttributes;
	
	public StructDeclNode(String contents) {
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

	public void fillMetricContainer() {
		
		this.metricContainer.setMetric(MetricType.NUMBER_OF_INSTANCE_VARIABLES, this.numberOfAttributes);
		this.metricContainer.setMetric(MetricType.NUMBER_OF_CONSTRUCTORS, this.numberOfConstructors);
		this.metricContainer.setMetric(MetricType.NUMBER_OF_METHODS, this.numberOfFunctions);
		
		super.fillMetricContainer();
	}

}
