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
    Component("component", ComponentNode.class),
    TypeIdent("type_ident", TypeIdentNode.class),
    Result("result", ResultNode.class),
    PatternParen("pattern_paren", PatternParenNode.class),
    PatternTuple("pattern_tuple", PatternTupleNode.class),
    PatternNamed("pattern_named", PatternNamedNode.class),
    PatternAny("pattern_any", PatternAnyNode.class),
    PatternTyped("pattern_typed", PatternTypedNode.class),
    PatternIs("pattern_is", PatternIsNode.class),
    PatternNominal("pattern_nominal", PatternNominalNode.class),
    PatternExpr("pattern_expr", PatternExprNode.class),
    PatternLet("pattern_let", PatternLetNode.class),
    PatternVar("pattern_var", PatternVarNode.class),
    PatternEnumElement("pattern_enum_element", PatternEnumElementNode.class),
    OptionalSomeElement("optional_some_element", OptionalSomeElementNode.class),
    PatternBool("pattern_bool", PatternBoolNode.class),
    PxtensionDecl("extension_decl", ExtensionDeclNode.class),
    Typealias("typealias", TypealiasNode.class),
    VarDecl("var_decl", VarDeclNode.class),
    StructDecl("struct_decl", StructDeclNode.class),
    ClassDecl("class_decl", ClassDeclNode.class),
    PatternBindingDecl("pattern_binding_decl", PatternBindingDeclNode.class),
    SubscriptDecl("subscript_decl", SubscriptDeclNode.class),
    ConstructorDecl("constructor_decl", ConstructorDeclNode.class),
    DestructorDecl("destructor_decl", DestructorDeclNode.class),
    TopLevelCodeDecl("top_level_code_decl", TopLevelCodeDeclNode.class),
    InfixOperatorDecl("infix_operator_decl", InfixOperatorDeclNode.class),
    PrefixOperatorDecl("prefix_operator_decl", PrefixOperatorDeclNode.class),
    PostfixOperatorDecl("postfix_operator_decl", PostfixOperatorDeclNode.class),
    Module("module", ModuleNode.class),
    TupleExpr("tuple_expr", TupleExprNode.class),
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
	
	/*
	ASTDumper prints the following things:
	
		error_expr
		nil_literal_expr
		integer_literal_expr
		code_completion_expr
		float_literal_expr
		boolean_literal_expr
		string_literal_expr
		interpolated_string_literal_expr
		magic_identifier_literal_expr
		object_literal
		discard_assignment_expr
		declref_expr
		super_ref_expr
		type_expr
		other_constructor_ref_expr
		overloaded_decl_ref_expr
		unresolved_specialize_expr
		member_ref_expr
		dynamic_member_ref_expr
		unresolved_member_expr
		dot_self_expr
		paren_expr
		tuple_expr
		array_expr
		dictionary_expr
		subscript_expr
		dynamic_subscript_expr
		unresolved_dot_expr
		tuple_element_expr
		tuple_shuffle_expr
		unresolvedtype_conversion_expr
		function_conversion_expr
		covariant_function_conversion_expr
		covariant_return_conversion_expr
		erasure_expr
		load_expr
		metatype_conversion_expr
		collection_upcast_expr
		derived_to_base_expr
		archetype_to_super_expr
		lvalue_to_pointer
		inject_into_optional
		class_metatype_to_object
		existential_metatype_to_object
		protocol_metatype_to_object
		inout_to_pointer
		string_to_pointer
		pointer_to_pointer
		foreign_object_conversion
		inout_expr
		force_try_expr
		optional_try_expr
		try_expr
		sequence_expr
		capture_list
		closure_expr
		metatype_expr
		opaque_value_expr
		dot_syntax_base_ignored
		rebind_self_in_constructor_expr
		if_expr
		unresolved_pattern_expr
		bind_optional_expr
		optional_evaluation_expr
		force_value_expr
		open_existential_expr
		editor_placeholder_expr
		objc_selector_expr
		type_error
		type_attributed
		type_function
		type_array
		type_dictionary
		type_tuple
		type_named
		type_composite
		type_metatype
		type_inout
		unowned_storage_type
		unmanaged_storage_type
		weak_storage_type
		enum_type
		struct_type
		class_type
		protocol_type
		metatype_type
		existential_metatype_type
		module_type
		dynamic_self_type
		archetype_type
		associated_type_type
		substituted_type
		dependent_member_type
		sil_function_type
		sil_block_storage_type
		sil_box_type
		array_slice_type
		optional_type
		implicitly_unwrapped_optional_type
		dictionary_type
		protocol_composition_type
		lvalue_type
		inout_type
		unbound_generic_type
		bound_generic_class_type
		bound_generic_struct_type
		bound_generic_enum_type
		type_variable_type
		brace_stmt
		return_stmt
		defer_stmt
		if_stmt
		guard_stmt
		do_stmt
		while_stmt
		do_while_stmt
		for_stmt
		for_each_stmt
		break_stmt
		continue_stmt
		fallthrough_stmt
		switch_stmt
		case_stmt
		fail_stmt
		throw_stmt
		do_catch_stmt
		catch
		*/
}
