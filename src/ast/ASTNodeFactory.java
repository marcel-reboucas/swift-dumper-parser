package ast;

import java.lang.reflect.InvocationTargetException;

public class ASTNodeFactory {

	@SuppressWarnings("unchecked")
	public ASTNode createNode(String nodeString) {

		ASTNode result = null;
	
		// Tries to find (identifier ...) or (identifier)
		int endingIndex = nodeString.indexOf(' ') > 0 ? nodeString.indexOf(' ') : 
			nodeString.indexOf(')');
		
		String identifier = nodeString.substring(1, endingIndex);

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
