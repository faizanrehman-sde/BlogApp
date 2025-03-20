package com.asus.blogapplication.exceptions;

public class ResourceNotFoundException extends RuntimeException { 
	
	String resourceName;
	String fieldName;
	Long value;
	String val;
	
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public ResourceNotFoundException(String resourceName, String fieldName, Long value) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,value));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.value = value;
	}

	public ResourceNotFoundException(String resourceName, String fieldName, String val) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,val));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.val = val;
	}
	
	
	

}
