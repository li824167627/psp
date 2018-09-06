package com.northend.api.go.bean;

public class RequestParam {
	private String name;
	private String type;
	private String demoValue;
	private String notes;
	private String defValue;

	private NotEmpty notEmpty;
	private Size size;
	private Pattern pattern;
	private boolean require;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDemoValue() {
		return demoValue;
	}

	public void setDemoValue(String demoValue) {
		this.demoValue = demoValue;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDefValue() {
		return defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}

	public NotEmpty getNotEmpty() {
		return notEmpty;
	}

	public void setNotEmpty(NotEmpty notEmpty) {
		this.notEmpty = notEmpty;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public boolean isRequire() {
		return require;
	}

	public void setRequire(boolean require) {
		this.require = notEmpty != null;
	}

}
