package ast;

import modifiednodes.CatchNode;
import modifiednodes.ClassDeclNode;
import modifiednodes.ClosureExprNode;
import modifiednodes.ConditionalCheckedCastExprNode;
import modifiednodes.DoCatchStmtNode;
import modifiednodes.EnumDeclNode;
import modifiednodes.ExtensionDeclNode;
import modifiednodes.ForceTryExprNode;
import modifiednodes.ForceValueExprNode;
import modifiednodes.ForcedCheckedCastExprNode;
import modifiednodes.FuncDeclNode;
import modifiednodes.GuardStmtNode;
import modifiednodes.IfExprNode;
import modifiednodes.IfStmtNode;
import modifiednodes.OptionalEvaluationExprNode;
import modifiednodes.OptionalSomeElementNode;
import modifiednodes.OptionalTryExprNode;
import modifiednodes.ParameterListNode;
import modifiednodes.ParameterNode;
import modifiednodes.PatternLetNode;
import modifiednodes.ProtocolNode;
import modifiednodes.SourceFileNode;
import modifiednodes.StructDeclNode;
import modifiednodes.ThrowStmtNode;
import modifiednodes.TryExprNode;
import modifiednodes.TupleExprNode;
import modifiednodes.VarDeclNode;
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
    EnumCaseDecl("enum_case_decl", EnumCaseDeclNode.class),
    Component("component", ComponentNode.class),
    TypeIdent("type_ident", TypeIdentNode.class),
    CallExpr("call_expr", CallExprNode.class),
    Result("result", ResultNode.class),
    Pattern("pattern", PatternNode.class),
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
    ErrorExpr("error_expr", ErrorExprNode.class),
    NilLiteralExpr("nil_literal_expr", NilLiteralExprNode.class),
    IntegerLiteralExpr("integer_literal_expr", IntegerLiteralExprNode.class),
    CdeCompletionExpr("code_completion_expr", CdeCompletionExprNode.class),
    FloatLiteralExpr("float_literal_expr", FloatLiteralExprNode.class),
    BooleanLiteralExpr("boolean_literal_expr", BooleanLiteralExprNode.class),
    StringLiteralExpr("string_literal_expr", StringLiteralExprNode.class),
    InterpolatedStringLiteralExpr("interpolated_string_literal_expr", InterpolatedStringLiteralExprNode.class),
    MagicIdentifierLiteralExpr("magic_identifier_literal_expr", MagicIdentifierLiteralExprNode.class),
    ObjectLiteral("object_literal", ObjectLiteralNode.class),
    DiscardAssignmentExpr("discard_assignment_expr", DiscardAssignmentExprNode.class),
    DeclrefExpr("declref_expr", DeclrefExprNode.class),
    SuperRefExpr("super_ref_expr", SuperRefExprNode.class),
    TypeExpr("type_expr", TypeExprNode.class),
    OtherConstructorRefExpr("other_constructor_ref_expr", OtherConstructorRefExprNode.class),
    OverloadedDeclRefExpr("overloaded_decl_ref_expr", OverloadedDeclRefExprNode.class),
    UnresolvedSpecializeExpr("unresolved_specialize_expr", UnresolvedSpecializeExprNode.class),
    MemberRefExpr("member_ref_expr", MemberRefExprNode.class),
    DynamicMemberRefExpr("dynamic_member_ref_expr", DynamicMemberRefExprNode.class),
    UnresolvedMemberExpr("unresolved_member_expr", UnresolvedMemberExprNode.class),
    DotSelfExpr("dot_self_expr", DotSelfExprNode.class),
    ParenExpr("paren_expr", ParenExprNode.class),
    ArrayExpr("array_expr", ArrayExprNode.class),
    DictionaryExpr("dictionary_expr", DictionaryExprNode.class),
    SubscriptExpr("subscript_expr", SubscriptExprNode.class),
    DynamicSubscriptExpr("dynamic_subscript_expr", DynamicSubscriptExprNode.class),
    UnresolvedDotExpr("unresolved_dot_expr", UnresolvedDotExprNode.class),
    TupleElementExpr("tuple_element_expr", TupleElementExprNode.class),
    TupleShuffleExpr("tuple_shuffle_expr", TupleShuffleExprNode.class),
    UnresolvedtypeConversionExpr("unresolvedtype_conversion_expr", UnresolvedtypeConversionExprNode.class),
    FunctionConversionExpr("function_conversion_expr", FunctionConversionExprNode.class),
    CovariantFunctionConversionExpr("covariant_function_conversion_expr", CovariantFunctionConversionExprNode.class),
    CovariantReturnConversionExpr("covariant_return_conversion_expr", CovariantReturnConversionExprNode.class),
    ErasureExpr("erasure_expr", ErasureExprNode.class),
    LoadExpr("load_expr", LoadExprNode.class),
    MetatypeConversionExpr("metatype_conversion_expr", MetatypeConversionExprNode.class),
    CollectionUpcastExpr("collection_upcast_expr", CollectionUpcastExprNode.class),
    DerivedToBaseExpr("derived_to_base_expr", DerivedToBaseExprNode.class),
    ArchetypeToSuperExpr("archetype_to_super_expr", ArchetypeToSuperExprNode.class),
    LvalueToPointer("lvalue_to_pointer", LvalueToPointer.class),
    InjectIntoOptional("inject_into_optional", InjectIntoOptionalNode.class),
    ClassMetatypeToObject("class_metatype_to_object", ClassMetatypeToObjectNode.class),
    ExistentialMetatypeToObject("existential_metatype_to_object", ExistentialMetatypeToObjectNode.class),
    ProtocolMetatypeToObject("protocol_metatype_to_object", ProtocolMetatypeToObjectNode.class),
    InoutToPointer("inout_to_pointer", InoutToPointerNode.class),
    StringToPointer("string_to_pointer", StringToPointerNode.class),
    PointerToPointer("pointer_to_pointer", PointerToPointerNode.class),
    ForeignObjectConversion("foreign_object_conversion", ForeignObjectConversionNode.class),
    InoutExpr("inout_expr", InoutExprNode.class),
    ForceTryExpr("force_try_expr", ForceTryExprNode.class),
    OptionalTryExpr("optional_try_expr", OptionalTryExprNode.class),
    TryExpr("try_expr", TryExprNode.class),
    SequenceExpr("sequence_expr", SequenceExprNode.class),
    CaptureList("capture_list", CaptureListNode.class),
    ClosureExpr("closure_expr", ClosureExprNode.class),
    MetatypeExpr("metatype_expr", MetatypeExprNode.class),
    OpaqueValueExpr("opaque_value_expr", OpaqueValueExprNode.class),
    DotSyntaxBaseIgnored("dot_syntax_base_ignored", DotSyntaxBaseIgnoredNode.class),
    RebindSelfInConstructorExpr("rebind_self_in_constructor_expr", RebindSelfInConstructorExprNode.class),
    IfExpr("if_expr", IfExprNode.class),
    UnresolvedPatternExpr("unresolved_pattern_expr", UnresolvedPatternExprNode.class),
    BindOptionalExpr("bind_optional_expr", BindOptionalExprNode.class),
    OptionalEvaluationExpr("optional_evaluation_expr", OptionalEvaluationExprNode.class),
    ForceValueExpr("force_value_expr", ForceValueExprNode.class),
    OpenExistentialExpr("open_existential_expr", OpenExistentialExprNode.class),
    EditorPlaceholderExpr("editor_placeholder_expr", EditorPlaceholderExprNode.class),
    ObjcSelectorExpr("objc_selector_expr", ObjcSelectorExprNode.class),
    TypeError("type_error", TypeErrorNode.class),
    TypeAttributed("type_attributed", TypeAttributedNode.class),
    TypeFunction("type_function", TypeFunctionNode.class),
    TypeArray("type_array", TypeArrayNode.class),
    TypeDictionary("type_dictionary", TypeDictionaryNode.class),
    TypeTuple("type_tuple", TypeTupleNode.class),
    TypeNamed("type_named", TypeNamedNode.class),
    TypeComposite("type_composite", TypeCompositeNode.class),
    TypeMetatype("type_metatype", TypeMetatypeNode.class),
    TypeInout("type_inout", TypeInoutNode.class),
    UnownedStorageType("unowned_storage_type", UnownedStorageTypeNode.class),
    UnmanagedStorageType("unmanaged_storage_type", UnmanagedStorageTypeNode.class),
    WeakStorageType("weak_storage_type", WeakStorageTypeNode.class),
    EnumType("enum_type", EnumTypeNode.class),
    StructType("struct_type", StructTypeNode.class),
    ClassType("class_type", ClassTypeNode.class),
    ProtocolType("protocol_type", ProtocolTypeNode.class),
    MetatypeType("metatype_type", MetatypeTypeNode.class),
    ExistentialMetatypeType("existential_metatype_type", ExistentialMetatypeTypeNode.class),
    ModuleType("module_type", ModuleTypeNode.class),
    DynamicSelfType("dynamic_self_type", DynamicSelfTypeNode.class),
    ArchetypeType("archetype_type", ArchetypeTypeNode.class),
    AssociatedTypeType("associated_type_type", AssociatedTypeTypeNode.class),
    SubstitutedType("substituted_type", SubstitutedTypeNode.class),
    DependentMemberType("dependent_member_type", DependentMemberTypeNode.class),
    SilFunctionType("sil_function_type", SilFunctionTypeNode.class),
    SilBlockStorageType("sil_block_storage_type", SilBlockStorageTypeNode.class),
    SilBoxType("sil_box_type", SilBoxTypeNode.class),
    ArraySliceType("array_slice_type", ArraySliceTypeNode.class),
    OptionalType("optional_type", OptionalTypeNode.class),
    ImplicitlyUnwrappedOptionalType("implicitly_unwrapped_optional_type", ImplicitlyUnwrappedOptionalTypeNode.class),
    DictionaryType("dictionary_type", DictionaryTypeNode.class),
    ProtocolCompositionType("protocol_composition_type", ProtocolCompositionTypeNode.class),
    LvalueType("lvalue_type", LvalueTypeNode.class),
    InoutType("inout_type", InoutTypeNode.class),
    UnboundGenericType("unbound_generic_type", UnboundGenericTypeNode.class),
    BoundGenericClassType("bound_generic_class_type", BoundGenericClassTypeNode.class),
    BoundGenericStructType("bound_generic_struct_type", BoundGenericStructTypeNode.class),
    BoundGenericEnumType("bound_generic_enum_type", BoundGenericEnumTypeNode.class),
    TypeVariableType("type_variable_type", TypeVariableTypeNode.class),
    BraceStmt("brace_stmt", BraceStmtNode.class),
    ReturnStmt("return_stmt", ReturnStmtNode.class),
    DeferStmt("defer_stmt", DeferStmtNode.class),
    IfStmt("if_stmt", IfStmtNode.class),
    GuardStmt("guard_stmt", GuardStmtNode.class),
    DoStmt("do_stmt", DoStmtNode.class),
    WhileStmt("while_stmt", WhileStmtNode.class),
    DoWhileStmt("do_while_stmt", DoWhileStmtNode.class),
    ForStmt("for_stmt", ForStmtNode.class),
    ForEachStmt("for_each_stmt", ForEachStmtNode.class),
    BreakStmt("break_stmt", BreakStmtNode.class),
    ContinueStmt("continue_stmt", ContinueStmtNode.class),
    FallthroughStmt("fallthrough_stmt", FallthroughStmtNode.class),
    SwitchStmt("switch_stmt", SwitchStmtNode.class),
    CaseStmt("case_stmt", CaseStmtNode.class),
    FailStmt("fail_stmt", FailStmtNode.class),
    ThrowStmt("throw_stmt", ThrowStmtNode.class),
    DoCatchStmt("do_catch_stmt", DoCatchStmtNode.class),
    Catch_("catch", CatchNode.class),
    ForcedCheckedCastExpr("forced_checked_cast_expr", ForcedCheckedCastExprNode.class),
    ConditionalCheckedCastExpr("conditional_checked_cast_expr", ConditionalCheckedCastExprNode.class),
    ConstructorRefCallExpr("constructor_ref_call_expr", ConstructorRefCallExprNode.class),
    AssignExpr("assign_expr", AssignExprNode.class),
    DotSyntaxCallExpr("dot_syntax_call_expr",DotSyntaxCallExprNode.class),
    BinaryExpr("binary_expr",BinaryExprNode.class),
    AutoClosureExpr("autoclosure_expr", AutoClosureExprNode.class),
    ;
	
	public String identifier;
	@SuppressWarnings("rawtypes")
	public Class clazz;
	ASTNodeType(String identifier, @SuppressWarnings("rawtypes") Class clazz) {
		this.identifier = identifier;
		this.clazz = clazz;
	}
	
	public static ASTNodeType typeFromIdentifier(String id) {
		
		for (ASTNodeType type : ASTNodeType.values()) {
			if (type.identifier.equals(id.trim())) {
				return type;
			}
		}
		
		//System.out.println("Not found!!! "+ id);
		return ASTNodeType.Unknown;
	}
	
	public static boolean isNodeString(String str) {
		
		if (str.length() == 0) {
			return false;
		}
		
		for (ASTNodeType type : ASTNodeType.values()) {
			if (str.startsWith(type.identifier)) {
				return true;
			}
		}
	
		return false;
	}
}
