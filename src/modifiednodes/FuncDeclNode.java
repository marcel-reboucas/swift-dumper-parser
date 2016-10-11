package modifiednodes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ast.ASTNode;
import ast.ASTNodeType;
import metrics.MetricType;
import util.Util;

public class FuncDeclNode extends ASTNode {
	
	//'anonname=0x7faed893a090'
	
	public String name; 
	public String type;
	public boolean anonymous;
	public int numberOfParameters;
	public List<String> parameterTypes;
	
	public FuncDeclNode(String contents) {
		super(contents);
	}

	@Override
	public void prettyPrint(int ident) {
		
		String identation = Util.getTabsForIdentationAmmount(ident);
		String className = this.getClass().getSimpleName();
		System.out.println(identation + className + ": " + 
		" name: "+name +
		" type: "+type + 
		" numberOfParameters: "+numberOfParameters +
		" parameterTypes: "+parameterTypes);
		for (ASTNode child : children) { child.prettyPrint(ident + 1); }
	}

	public void parseString(String str) {

		super.parseString(str);
		parameterTypes = new ArrayList<String>();
		
		if (nodeInfo.indexOf("brace_stmt") != -1){
			nodeInfo = nodeInfo.substring(0,nodeInfo.indexOf("brace_stmt"));
			this.isImplicit = nodeInfo.contains(" implicit ");
		}
		
		//look for the name between the bginning and the type
		Pattern pattern = Util.getRegexPatternBetweenQuotationMarks();
		Matcher matcher = pattern.matcher(nodeInfo.substring(0,nodeInfo.indexOf("type")));
				
		if (matcher.find()){
			this.name = matcher.group(1);
		} else {
			 pattern = Util.getRegexPatternBetweenSingleQuotationMarks();
			 matcher = pattern.matcher(nodeInfo.substring(0,nodeInfo.indexOf("type")));
			 if (matcher.find()){
				 this.name = matcher.group(1);
			 }
		}
		
		pattern = Util.getRegexPatternForType();
		matcher = pattern.matcher(nodeInfo);
				
		if (matcher.find()){
			this.type = matcher.group(1);
		}
		
		if (this.name != null) {
			this.anonymous = name.contains("anonname");
		} else {
			this.anonymous = true;
		}
		
		//parameters
		List<ASTNode> listNodes = this.containsChildrenOfType(ASTNodeType.ParameterList, false);
		for (ASTNode listNode : listNodes) {
			List<ASTNode> parameters = listNode.containsChildrenOfType(ASTNodeType.Parameter, false);
			
			for (ASTNode parameterNode : parameters){
				ParameterNode parameter = (ParameterNode) parameterNode;
				if (parameter.name != null && !parameter.name.toLowerCase().equals("self")) {
					numberOfParameters += 1;
					parameterTypes.add(parameter.type);
				}
			}
		}
		
	}
	
public void fillMetricContainer() {
		
		this.metricContainer.setMetric(MetricType.NUMBER_OF_PARAMETERS, this.numberOfParameters);
		super.fillMetricContainer();
	}
}
