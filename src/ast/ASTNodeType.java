package ast;

import nodes.*;

public enum ASTNodeType {
	Unknown("unknown", null),
    SourceFile("source_file", SourceFileNode.class),
    ImportDecl("import_decl", ImportDeclNode.class),
    Protocol("protocol", ProtocolNode.class),
    FuncDecl("func_decl", FuncDeclNode.class),
    ParameterList("parameter_list", ParameterListNode.class),
    Parameter("parameter", ParameterNode.class),
    EnumDecl("enum_decl", EnumDeclNode.class),
    EnumElementDecl("enum_element_decl", EnumElementDeclNode.class),
    ;
	
	
	public String identifier;
	public Class clazz;
	ASTNodeType(String identifier, Class clazz) {
		this.identifier = identifier;
		this.clazz = clazz;
	}
	
	public static ASTNodeType typeFromIdentifier(String id) {
		
		for (ASTNodeType type : ASTNodeType.values()) {
			if (type.identifier.equals(id)) {
				return type;
			}
		}
		
		return ASTNodeType.Unknown;
	}
}
