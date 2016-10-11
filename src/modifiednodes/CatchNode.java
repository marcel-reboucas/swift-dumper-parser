package modifiednodes;

import java.util.List;

import ast.ASTNode;
import ast.ASTNodeType;
import metrics.MetricType;
import nodes.PatternIsNode;
import util.Util;

public class CatchNode extends ASTNode {

	public boolean hasEmptyBlock;
	public boolean checksType;
	public boolean checksValue;
	public String checkedType;
	public String checkedValue;
	public boolean isGeneric;

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

	public void parseString(String str) {

		super.parseString(str);

		this.hasEmptyBlock = false;
		this.checksType = false;
		this.checksValue = false;
		this.isGeneric = false;


		// catch is generic if doesnt have a condition ie. '} catch {' or '} catch _ {' or '} catch _ as NSError{'
		// has empty block if brace_stmt does not contain any children

		List<ASTNode> patternLets = this.containsChildrenOfType(ASTNodeType.PatternLet, true);
		for (ASTNode patternLetNode : patternLets) {
			if (patternLetNode.isImplicit) { isGeneric = true; }
		}

		// '} catch _ {'
		List<ASTNode> patternAnys = this.containsChildrenOfType(ASTNodeType.PatternAny, true);
		if (!patternAnys.isEmpty()) { isGeneric = true; } 

		//'} catch _ as NSError{'
		List<ASTNode> patternIs = this.containsChildrenOfType(ASTNodeType.PatternIs, true);
		for (ASTNode patternIsNode : patternIs) {
			if (!patternIsNode.containsChildrenOfType(ASTNodeType.PatternAny, true).isEmpty()) {
				isGeneric = true;
			}
		}

		// if not generic, is either by type or value
		if (!isGeneric) {

			//checking if is by type:
			List<ASTNode> patternLet = this.containsChildrenOfType(ASTNodeType.PatternLet, true);
			if (!patternLet.isEmpty()){
				patternIs = patternLet.get(0).containsChildrenOfType(ASTNodeType.PatternIs, true);

				if (!patternIs.isEmpty()){
					this.checksType = true;

					PatternIsNode node = (PatternIsNode) patternIs.get(0);
					this.checkedType = node.castedType;
				}
			}

			//checking if is by value
			patternIs = this.containsChildrenOfType(ASTNodeType.PatternIs, true);
			for (ASTNode patternIsNode : patternIs) {
				if (!patternIsNode.getChildren().isEmpty()) {
					this.checksValue = true;
					PatternIsNode node = (PatternIsNode) patternIs.get(0);
					this.checkedValue = node.castedType;
				}
			}
		}

		// has empty block if brace_stmt does not contain any children
		List<ASTNode> braceStmt = this.containsChildrenOfType(ASTNodeType.BraceStmt, false);
		for (ASTNode braceNode : braceStmt) {
			if (braceNode.getChildren().isEmpty()) {
				hasEmptyBlock = true;
			}
		}
	}
	public void fillMetricContainer() {


		this.metricContainer.setMetric(MetricType.NUMBER_OF_CATCHES, 1);


		if (hasEmptyBlock) {
			this.metricContainer.setMetric(MetricType.NUMBER_OF_EMPTY_CATCHES, 1);
		}

		if (isGeneric) {
			this.metricContainer.setMetric(MetricType.NUMBER_OF_GENERIC_CATCHES, 1);
		}

		if (checksType) {
			this.metricContainer.setMetric(MetricType.NUMBER_OF_CATCHES_BY_TYPE, 1);
		}

		if (checksValue) {
			this.metricContainer.setMetric(MetricType.NUMBER_OF_CATCHES_BY_VALUE, 1);
		}
		
		super.fillMetricContainer();
	}
}
