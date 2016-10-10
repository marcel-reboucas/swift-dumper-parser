package metrics;

import java.util.HashMap;

public class MetricContainer {

	private HashMap<MetricType, Object> metrics;

	public MetricContainer(){
		this.metrics = new HashMap<MetricType, Object>();
	}

	public HashMap<MetricType, Object> getMetrics() {
		return metrics;
	}

	public void setMetric(MetricType type, Object value){
		this.metrics.put(type, value);
	}

	public void mergeWith(MetricContainer other){

		for (MetricType type : other.getMetrics().keySet()){

			if (!type.nodeSpecific) {
				if (this.metrics.containsKey(type)){
					Object currentValue = this.metrics.get(type);
					Object otherValue = other.getMetrics().get(type);
					this.metrics.put(type, mergeValues(type, currentValue, otherValue));
				} else {
					this.metrics.put(type, other.getMetrics().get(type));
				}
			}
		}
	}

	private Object mergeValues(MetricType metricType, Object value1, Object value2){

		switch(metricType){
		default:
			return ((Integer) value1) + ((Integer) value2);
		}
	}

	public String toString(){
		return metrics.toString();
	}
}
