package modifiednodes;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.ASTNodeType;
import metrics.MetricType;
import util.Util;

public class GuardStmtNode extends ASTNode {
	
	boolean isGuardLet;
    List<String> guardLetTypes;
    
	public GuardStmtNode(String contents) {
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
		
		guardLetTypes = new ArrayList<String>();
		isGuardLet = false;
		
		//checks if is guardLet
		List<ASTNode> patternNodes = this.containsChildrenOfType(ASTNodeType.Pattern, true);
		for (ASTNode node : patternNodes) {
			List<ASTNode> optionalSomeNodes = node.containsChildrenOfType(ASTNodeType.OptionalSomeElement, true);
			
			if (!optionalSomeNodes.isEmpty()) {
				isGuardLet = true;
			}
			
			for (ASTNode optionalSomeNode : optionalSomeNodes) {
				guardLetTypes.add(((OptionalSomeElementNode) optionalSomeNode).type);
			}
		}
	}
	
	public void fillMetricContainer() {
		
		this.metricContainer.setMetric(MetricType.NUMBER_OF_GUARDS, 1);
		
		if (this.isGuardLet) {
			this.metricContainer.setMetric(MetricType.NUMBER_OF_GUARD_LETS, 1);
		}
		
		super.fillMetricContainer();
	}
}
