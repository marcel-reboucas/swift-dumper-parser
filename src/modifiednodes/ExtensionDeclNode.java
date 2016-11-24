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
		
		// ] is the end of the range information
		if (nodeInfo.trim().indexOf("]") != -1) {
			int indexOfSquare = nodeInfo.trim().indexOf("]")+1;
			String substringStartingAtSquare = nodeInfo.trim().substring(indexOfSquare).trim();
			this.className = substringStartingAtSquare.substring(0, substringStartingAtSquare.indexOf(" ")).trim();
		} else {
			this.className = "";
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
