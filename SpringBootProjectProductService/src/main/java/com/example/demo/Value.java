package com.example.demo;

public class Value {
	int valueId;
	String valueName;
	public int getValueId() {
		return valueId;
	}
	public void setValueId(int valueId) {
		this.valueId = valueId;
	}
	public String getValueName() {
		return valueName;
	}
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
	@Override
	public String toString() {
		return "hibernateClass [valueId=" + valueId + ", valueName=" + valueName + "]";
	}
	
	
}
