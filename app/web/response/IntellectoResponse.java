package web.response;

import utils.general.JsonObjectMapper;

public class IntellectoResponse extends Response {
	
	private String api;
	private boolean status;
	private boolean error;
	private String errorMessage;
	private String response;
	private String responseType;
	
	
	public IntellectoResponse() {}
	
	public IntellectoResponse(String api) {
		this.api = api;
		this.status = true;
	}
	
	public IntellectoResponse(String api, boolean status) {
		this.api = api;
		this.status = status;
	}
	
	
	public IntellectoResponse(String api, boolean status, String response, String responseType, boolean prettify) {
		this.api = api;
		this.status = status;
		this.response = response;
		this.responseType = responseType;
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
	public void setApi(String api) {
		this.api = api;
	}
	
    @Override
    public String toString() {
        return JsonObjectMapper.toJsonString(this, true);
    }

}
