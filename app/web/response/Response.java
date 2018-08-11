package web.response;

import web.constants.app.AppConstants;

public class Response {
	
	private String api;
	private boolean status;
	private boolean error;
	private String errorMessage;
	private String response;
	private String responseType;
	
	public Response() {
		this.api = AppConstants.API_DEFAULT;
	}
	
	public Response(boolean status) {
		this.status = status;
		this.api = AppConstants.API_DEFAULT;
	}
	
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getApi() {
		return api;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

}
