package modifiednodes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ast.ASTNode;
import metrics.MetricType;
import util.Util;

public class TupleExprNode extends ASTNode {
	
	public String type;
	public boolean isNilComparison; // nil comparison is added at IfStmtNode
	public boolean isNilCoalescing;
	
	public TupleExprNode(String contents) {
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
		
		if (this.type != null) {
			this.isNilComparison = type.equals("(nilLiteral: ())");
		} else {
			this.isNilComparison = false;
		}
		
		// (tuple_expr implicit type='(String?, @autoclosure () throws -> String?)'
		// if it is implicit, containts a nil type and an autoclosure
		if (this.isImplicit && 
				this.type != null &&
				this.type.contains("?,") && 
				this.type.contains("@autoclosure () throws")) {
			isNilCoalescing = true;
		} else {
			isNilCoalescing = false;
		}
	}
	
	public void fillMetricContainer() {
		
		// nil comparison is added at IfStmtNode. nil coalescing is added here. 
		if (this.isNilCoalescing) {
			this.metricContainer.setMetric(MetricType.NUMBER_OF_NIL_COALESCING, 1);
		}
		super.fillMetricContainer();
	}

}
