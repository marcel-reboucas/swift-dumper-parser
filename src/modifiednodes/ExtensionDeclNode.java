package modifiednodes;

import java.util.stream.Collectors;

import ast.ASTNode;
import ast.ASTNodeType;
import metrics.MetricType;
import util.Util;

public class ExtensionDeclNode extends ASTNode {
	
	public String className;
	public String inherits;
	int numberOfMethods;
	
	public ExtensionDeclNode(String contents) {
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
		
		//if the nodeInfo contains more information besides the name
		nodeInfo = nodeInfo.replaceAll(ASTNodeType.PxtensionDecl.identifier, "").trim();
		
		if (nodeInfo.trim().indexOf(" ") != -1) {
			this.className = nodeInfo.trim().substring(0, nodeInfo.trim().indexOf(" ")).trim();
		} else {
			this.className = nodeInfo.trim();
		}
		
		//if inherits
		if (nodeInfo.lastIndexOf("inherits: ") != -1) {
			this.inherits = nodeInfo.substring(nodeInfo.lastIndexOf("inherits: ") + 9).trim();
		}
		
		this.numberOfMethods = this.containsChildrenOfType(ASTNodeType.FuncDecl, false).stream().
				filter(u -> !((FuncDeclNode)u).anonymous).collect(Collectors.toList()).size();
	}

	public void fillMetricContainer() {
		this.metricContainer.setMetric(MetricType.NUMBER_OF_METHODS, this.numberOfMethods);
		super.fillMetricContainer();
	}
}
