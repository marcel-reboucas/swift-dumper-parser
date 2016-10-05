package modifiednodes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ast.ASTNode;
import metrics.MetricType;
import util.Util;

public class ForcedCheckedCastExprNode extends ASTNode {
	
	String type;
	
	public ForcedCheckedCastExprNode(String contents) {
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
		
		Pattern pattern = Util.getRegexPatternForType();
		Matcher matcher = pattern.matcher(nodeInfo);
				
		if (matcher.find()){
			this.type = matcher.group(1);
		}
	}
	
	public void fillMetricContainer() {
		
		this.metricContainer.setMetric(MetricType.NUMBER_OF_FORCED_TYPE_CASTING, 1);
		super.fillMetricContainer();
	}

}
