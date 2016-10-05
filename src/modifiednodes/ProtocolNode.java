package modifiednodes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import ast.ASTNode;
import ast.ASTNodeType;
import metrics.MetricType;
import util.Util;

public class ProtocolNode extends ASTNode {

	public String name;
	public int numberOfMethods;
	
	public ProtocolNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		String className = this.getClass().getSimpleName();
		System.out.println(identation + className + ": " + nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}


	public void parseString(String str) {

		super.parseString(str);
		
		Pattern pattern = Util.getRegexPatternBetweenQuotationMarks();
		Matcher matcher = pattern.matcher(nodeInfo);
				
		if (matcher.find()){
			this.name = matcher.group(1);
		}
		this.numberOfMethods = this.containsChildrenOfType(ASTNodeType.FuncDecl, false).stream().
				filter(u -> !((FuncDeclNode)u).anonymous).collect(Collectors.toList()).size();
	}

	public void fillMetricContainer() {
		this.metricContainer.setMetric(MetricType.NUMBER_OF_METHODS, this.numberOfMethods);
		super.fillMetricContainer();
	}
}
