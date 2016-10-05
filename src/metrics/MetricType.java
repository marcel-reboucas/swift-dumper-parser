package metrics;

public enum MetricType {
	NUMBER_OF_INSTANCE_VARIABLES(Integer.class, true),
	NUMBER_OF_METHODS(Integer.class, true),
	NUMBER_OF_IFS(Integer.class, false),
	NUMBER_OF_IF_LETS(Integer.class, false),
	NUMBER_OF_FORCED_TRY(Integer.class, false);
	
	@SuppressWarnings("rawtypes")
	public Class typeClass;
	public boolean nodeSpecific;
	
	MetricType(@SuppressWarnings("rawtypes") Class type, boolean nodeSpecific){
		this.typeClass = type;
		this.nodeSpecific = nodeSpecific;
	}
	
	public String toString(){
		return this.name();
	}
	
}
