package modifiednodes;

import ast.ASTNode;
import metrics.MetricType;
import util.Util;

public class TryExprNode extends ASTNode {
	
	public TryExprNode(String contents) {
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
		
		this.metricContainer.setMetric(MetricType.NUMBER_OF_TRY, 1);
		super.fillMetricContainer();
	}
}
