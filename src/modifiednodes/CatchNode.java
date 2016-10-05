package modifiednodes;

import ast.ASTNode;
import metrics.MetricType;
import util.Util;

public class CatchNode extends ASTNode {
	
	public CatchNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		String className = this.getClass().getSimpleName();
		System.out.println(identation + className + ": " + nodeInfo);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}

	public void fillMetricContainer() {
		
		this.metricContainer.setMetric(MetricType.NUMBER_OF_CATCHES, 1);
		
		if (this.children.isEmpty()) {
			this.metricContainer.setMetric(MetricType.NUMBER_OF_EMPTY_CATCHES, 1);
		}
		
		super.fillMetricContainer();
	}
}
