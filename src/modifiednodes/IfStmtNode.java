package modifiednodes;

import java.util.ArrayList;
import java.util.List;

import ast.ASTNode;
import ast.ASTNodeType;
import metrics.MetricType;
import util.Util;

public class IfStmtNode extends ASTNode {
	
	boolean isIfLet;
    List<String> ifLetTypes;
	
	public IfStmtNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		String className = this.getClass().getSimpleName();
		System.out.println(identation + className + ": " + "isIfLet: "+ isIfLet + ", ifLetTypes = "+ifLetTypes.toString());
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}

	public void parseString(String str) {

		super.parseString(str);
		
		ifLetTypes = new ArrayList<String>();
		isIfLet = false;
		
		//checks if is ifLet
		List<ASTNode> patternNodes = this.containsChildrenOfType(ASTNodeType.Pattern, true);
		for (ASTNode node : patternNodes) {
			List<ASTNode> optionalSomeNodes = node.containsChildrenOfType(ASTNodeType.OptionalSomeElement, true);
			
			if (!optionalSomeNodes.isEmpty()) {
				isIfLet = true;
			}
			
			for (ASTNode optionalSomeNode : optionalSomeNodes) {
				ifLetTypes.add(((OptionalSomeElementNode) optionalSomeNode).type);
			}
		}
	}
	
	public void fillMetricContainer() {
		
		this.metricContainer.setMetric(MetricType.NUMBER_OF_IFS, 1);
		
		if (this.isIfLet) {
			this.metricContainer.setMetric(MetricType.NUMBER_OF_IF_LETS, 1);
		}
		
		super.fillMetricContainer();
	}
}
