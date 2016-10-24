package output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ast.ASTNode;
import ast.ASTNodeType;
import modifiednodes.ClassDeclNode;
import modifiednodes.EnumDeclNode;
import modifiednodes.ExtensionDeclNode;
import modifiednodes.FuncDeclNode;
import modifiednodes.ProtocolNode;
import modifiednodes.StructDeclNode;

public class OutputCreator {

	final static String nodeType = "node_type";
	final static String name = "name";
	final static String type = "type";
	final static String sourcePath = "source_path";
	
	
	public ASTNode programAst;

	public OutputCreator(ASTNode programAst) {
		this.programAst = programAst;
	}

	public void generateJson(String outputPath) throws IOException{

		if (this.programAst == null) {
			System.out.println("programAst must be set!");
			return;
		}
	
		HashMap<String, Object> rootMap = new HashMap<>();
		HashMap<String, Object> projectMap = new HashMap<>();
		
		projectMap.putAll(programAst.metricContainer.toStringHashMap());
		
		List<HashMap<String, Object>> classList = new ArrayList<>();
		// for each class declaration:
		for (ASTNode clazz : programAst.containsChildrenOfTypeRecursive(ASTNodeType.ClassDecl)) {
			
			ClassDeclNode node = (ClassDeclNode)clazz;
			
			HashMap<String, Object> classMap = new HashMap<>();
			classMap.put(nodeType, "class");
			classMap.put(name,node.name);
			classMap.put(type, node.type);
			classMap.put("inherits", node.inherits);
			classMap.putAll(clazz.metricContainer.toStringHashMap());
			classMap.put("methods", getMethods(clazz));
			classList.add(classMap);
		}
		projectMap.put("classes", classList);
		
		List<HashMap<String, Object>> structList = new ArrayList<>();
		// for each struct declaration:
		for (ASTNode struct : programAst.containsChildrenOfTypeRecursive(ASTNodeType.StructDecl)) {
			
			StructDeclNode node = (StructDeclNode)struct;
			
			HashMap<String, Object> structMap = new HashMap<>();
			structMap.put(nodeType, "struct");
			structMap.put(name,node.name);
			structMap.put(type, node.type);
			structMap.put(sourcePath, node.sourceFilePath);
			structMap.put("inherits", node.inherits);
			structMap.putAll(struct.metricContainer.toStringHashMap());
			structMap.put("methods", getMethods(struct));
			structList.add(structMap);
		}
		projectMap.put("structs", structList);
		
		
		List<HashMap<String, Object>> extensionsList = new ArrayList<>();
		// for each extension declaration:
		for (ASTNode extension : programAst.containsChildrenOfTypeRecursive(ASTNodeType.PxtensionDecl)) {
			
			ExtensionDeclNode node = (ExtensionDeclNode) extension;
			
			HashMap<String, Object> extensionMap = new HashMap<>();
			extensionMap.put(nodeType, "extension");
			extensionMap.put(name, node.className);
			extensionMap.put(sourcePath, node.sourceFilePath);
			extensionMap.putAll(extension.metricContainer.toStringHashMap());
			extensionMap.put("methods", getMethods(extension));
			extensionsList.add(extensionMap);
		}
		projectMap.put("extensions", extensionsList);
		
		List<HashMap<String, Object>> protocolList = new ArrayList<>();
		// for each protocol declaration:
		for (ASTNode protocol : programAst.containsChildrenOfTypeRecursive(ASTNodeType.Protocol)) {
			
			ProtocolNode node = (ProtocolNode) protocol;
			
			HashMap<String, Object> protocolMap = new HashMap<>();
			protocolMap.put(nodeType, "protocol");
			protocolMap.put(name,node.name);
			protocolMap.put(sourcePath, node.sourceFilePath);
			protocolMap.putAll(protocol.metricContainer.toStringHashMap());
			protocolMap.put("methods", getMethods(protocol));
			protocolList.add(protocolMap);
		}
		projectMap.put("protocols", protocolList);
		
		List<HashMap<String, Object>> enumsList = new ArrayList<>();
		// for each extension declaration:
		for (ASTNode extension : programAst.containsChildrenOfTypeRecursive(ASTNodeType.EnumDecl)) {
			
			EnumDeclNode node = (EnumDeclNode) extension;
			
			HashMap<String, Object> enumMap = new HashMap<>();
			enumMap.put(nodeType, "extension");
			enumMap.put(name, node.name);
			enumMap.put(sourcePath, node.sourceFilePath);
			enumMap.putAll(node.metricContainer.toStringHashMap());
			enumMap.put("methods", getMethods(extension));
			enumsList.add(enumMap);
		}
		projectMap.put("enums", enumsList);
		
		rootMap.put("project", projectMap);
		
		try (Writer writer = new FileWriter(outputPath)) {
		    Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
		    gsonBuilder.toJson(rootMap, writer);
		}
	}

	private static List<HashMap<String, Object>> getMethods(ASTNode parentNode) {
		List<HashMap<String, Object>> methods = new ArrayList<>();
		
		for (ASTNode childFunc : parentNode.containsChildrenOfType(ASTNodeType.FuncDecl, false).stream().
				filter(u -> !((FuncDeclNode)u).anonymous).collect(Collectors.toList())) {
			
			FuncDeclNode node = (FuncDeclNode)childFunc;
			
			HashMap<String, Object> method = new HashMap<>();
			method.put(nodeType, "method");
			method.put(name,node.name);
			method.put("return_type", node.returnType);
			method.put("returns_optional_type", node.returnsOptionalType);
			method.put("parameter_types", node.parameterTypes);
			method.putAll(childFunc.metricContainer.toStringHashMap());
			methods.add(method);
		}
		
		return methods;
	}
}
