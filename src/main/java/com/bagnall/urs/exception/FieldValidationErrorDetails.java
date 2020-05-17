package com.bagnall.urs.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldValidationErrorDetails {

	private String errorTitle;
	private Integer errorStatus;
	private String errorDetail;
	private Long errorTimeStamp;
	private String errorPath;
	private String errorDeveloperMessage;
	private Map<String, List<FieldValidationError>> errors = new HashMap<String, List<FieldValidationError>>();

	public String getErrorTitle() {
		return errorTitle;
	}

	public void setErrorTitle(String errorTitle) {
		this.errorTitle = errorTitle;
	}

	public Integer getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(Integer errorStatus) {
		this.errorStatus = errorStatus;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

	public Long getErrorTimeStamp() {
		return errorTimeStamp;
	}

	public void setErrorTimeStamp(Long errorTimeStamp) {
		this.errorTimeStamp = errorTimeStamp;
	}

	public String getErrorPath() {
		return errorPath;
	}

	public void setErrorPath(String errorPath) {
		this.errorPath = errorPath;
	}

	public String getErrorDeveloperMessage() {
		return errorDeveloperMessage;
	}

	public void setErrorDeveloperMessage(String errorDeveloperMessage) {
		this.errorDeveloperMessage = errorDeveloperMessage;
	}

	public Map<String, List<FieldValidationError>> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, List<FieldValidationError>> errors) {
		this.errors = errors;
	}

}
