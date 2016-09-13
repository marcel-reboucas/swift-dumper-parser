package ast;

import java.lang.reflect.InvocationTargetException;

import nodes.ASTNode;

public class ASTNodeFactory {

	@SuppressWarnings("unchecked")
	public ASTNode createNode(String nodeString) {

		ASTNode result = null;
		String identifier = nodeString.substring(1, nodeString.indexOf(' '));

		ASTNodeType type = ASTNodeType.typeFromIdentifier(identifier);

		if (type != ASTNodeType.Unknown) {
			try {
				result = (ASTNode) type.clazz.getConstructor(String.class).newInstance(nodeString);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
