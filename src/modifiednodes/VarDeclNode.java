package modifiednodes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ast.ASTNode;
import metrics.MetricType;
import util.Util;

public class VarDeclNode extends ASTNode {
	
	public String name;
	public String type;
	public boolean isLet;
	
	public VarDeclNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		String className = this.getClass().getSimpleName();
		System.out.println(identation + className + ": " + "Name: "+ this.name +", Type: "+ this.type + ", isLet :"+isLet);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}
	
	public void parseString(String str) {

		super.parseString(str);
		
		if (nodeInfo.contains(" let ")) {
			this.isLet = true;
		}
		
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
	}

	public void fillMetricContainer() {
		
		if (this.isLet) {
			this.metricContainer.setMetric(MetricType.NUMBER_OF_LETS, 1);
			
			if (this.type.endsWith("?")) {
				this.metricContainer.setMetric(MetricType.NUMBER_OF_LETS_WITH_OPTIONAL_TYPE, 1);
			}
			
		} else {
			
			this.metricContainer.setMetric(MetricType.NUMBER_OF_VARS, 1);
			
			if (this.type == null) {
				System.out.println(this.name);
				System.out.println(this.nodeInfo);
			}
			if (this.type.endsWith("?")) {
				this.metricContainer.setMetric(MetricType.NUMBER_OF_VARS_WITH_OPTIONAL_TYPE, 1);
			}
		}
		
		super.fillMetricContainer();
	}
}
