package de.steven.clitaskmanager.userinterface.dto;


public class CommandParameter {
	private String parameter;
	private String parameterValue;

	public CommandParameter() {
	}

	public CommandParameter(String parameter) {
		this.parameter = parameter;
	}

	public CommandParameter(String parameter, String parameterValue) {
		this.parameter = parameter;
		this.parameterValue = parameterValue;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
}
