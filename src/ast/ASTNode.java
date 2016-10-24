package ast;

import java.util.ArrayList;
import java.util.List;

import metrics.MetricContainer;
import util.Util;

public abstract class ASTNode {

	public String nodeInfo;
	public List<ASTNode> children;

	public boolean isImplicit;
	public String sourceFilePath;
	public MetricContainer metricContainer;
	
	public ASTNode(String contents) {
		this.nodeInfo = "";
		this.isImplicit = false;
		this.children = new ArrayList<ASTNode>();
		this.metricContainer = new MetricContainer();
		parseString(contents);
	}

	public List<ASTNode> getChildren() {
		return this.children;
	}
	
	public void parseString(String str) {

		// Remove opening and last parentheses
		String nodeContent = str.substring(1, str.length() - 1);

		List<String> childNodeStrings = Util.getNextNodeStrings(nodeContent);

		// remove child information
		for (String childNodeString : childNodeStrings) {
			
			if (ASTNodeType.isNodeString(childNodeString)) {
				nodeContent = nodeContent.replace(childNodeString, "");
			}
		}

		this.nodeInfo = nodeContent;

		if (nodeInfo.contains(" implicit ")) {
			this.isImplicit = true;
		}
		
		ASTNodeFactory factory = new ASTNodeFactory();

		for (String childNodeString : childNodeStrings) {
			ASTNode node = factory.createNode(childNodeString);
			
			if (node != null) {
				children.add(node);
			}
		}
	}

	public List<ASTNode> containsChildrenOfType (ASTNodeType type, boolean allowsImplicit) {
	        
	    	List<ASTNode> nodes = new ArrayList<ASTNode>();

	    	for (ASTNode child : children) {
	    		if (type.clazz.isInstance(child)){
	    			if (allowsImplicit || !child.isImplicit){
	    				nodes.add(child);
	    			}
	    		}	
	    	}
	    	return nodes;
	    }
	
	public List<ASTNode> containsChildrenOfTypeRecursive(ASTNodeType type) {
		
		List<ASTNode> nodes = new ArrayList<ASTNode>();
		containsChildrenOfTypeRecursive(type, nodes);
		return nodes;
	}
	
	private void containsChildrenOfTypeRecursive (ASTNodeType type, List<ASTNode> nodes) {

    	for (ASTNode child : children) {
    		if (type.clazz.isInstance(child)){
    			nodes.add(child);
    		}
    		
    		child.containsChildrenOfTypeRecursive(type, nodes);
    	}
    }

	public abstract void prettyPrint(int ident);
        
	public List<? extends ASTNode> getSubnodesOfType(Class<? extends ASTNode> chosenClass){

		ArrayList<ASTNode> list = new ArrayList<ASTNode>();

		for (ASTNode node : this.children){

			list.addAll(node.getSubnodesOfType(chosenClass));

			if (node.getClass().equals(chosenClass)){
				list.add(node);
			}
		}

		return list;
	}

	public String getNodeInfo(){
		return nodeInfo;
	}
    
    public void fillMetricContainer() {
        
        for (ASTNode child : children) {
            child.fillMetricContainer();
            this.metricContainer.mergeWith(child.metricContainer);
        }
    }
}
