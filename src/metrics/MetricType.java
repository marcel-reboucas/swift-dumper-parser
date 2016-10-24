package metrics;

public enum MetricType {
	NUMBER_OF_INSTANCE_VARIABLES(Integer.class, true),
	NUMBER_OF_METHODS(Integer.class, false), 
	NUMBER_OF_CONSTRUCTORS(Integer.class, true),
	NUMBER_OF_PARAMETERS(Integer.class, true),
	NUMBER_OF_IFS(Integer.class, false),
	NUMBER_OF_IF_LETS(Integer.class, false),
	NUMBER_OF_IF_NIL(Integer.class, false),
	NUMBER_OF_GUARDS(Integer.class, false),
	NUMBER_OF_GUARD_LETS(Integer.class, false),
	NUMBER_OF_GUARD_NIL(Integer.class, false),
	NUMBER_OF_CATCHES(Integer.class, false),
	NUMBER_OF_EMPTY_CATCHES(Integer.class, false),
	NUMBER_OF_GENERIC_CATCHES(Integer.class, false),
	NUMBER_OF_CATCHES_BY_TYPE(Integer.class, false),
	NUMBER_OF_CATCHES_BY_VALUE(Integer.class, false),
	NUMBER_OF_FORCED_UNWRAPPINGS(Integer.class, false),
	NUMBER_OF_FORCED_TYPE_CASTING(Integer.class, false),
	NUMBER_OF_OPTIONAL_TYPE_CASTING(Integer.class, false),
	NUMBER_OF_DO_BLOCKS(Integer.class, false),
	NUMBER_OF_TRY(Integer.class, false),
	NUMBER_OF_FORCED_TRY(Integer.class, false),
	NUMBER_OF_OPTIONAL_TRY(Integer.class, false),
	NUMBER_OF_VARS(Integer.class, false),
	NUMBER_OF_VARS_WITH_OPTIONAL_TYPE(Integer.class, false),
	NUMBER_OF_LETS(Integer.class, false),
	NUMBER_OF_LETS_WITH_OPTIONAL_TYPE(Integer.class, false),
	NUMBER_OF_THROWS(Integer.class, false),
	NUMBER_OF_NIL_COALESCING(Integer.class, false);
	
	@SuppressWarnings("rawtypes")
	public Class typeClass;
	public boolean nodeSpecific;
	
	MetricType(@SuppressWarnings("rawtypes") Class type, boolean nodeSpecific){
		this.typeClass = type;
		this.nodeSpecific = nodeSpecific;
	}
	
	public String toString(){
		return this.name().toLowerCase();
	}
	
}
