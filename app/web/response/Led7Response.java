package web.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.general.JsonObjectMapper;
import web.db.models.led7.Led7_Data;

public class Led7Response extends Response {
	
	private String api;
	private boolean status;
	private boolean error;
	private String errorMessage;
	private String response;
	private String responseType;
	private String query;
	
	public Led7Response() {}
	
	public Led7Response(String api) {
		this.api = api;
		this.status = true;
	}
	
	public Led7Response(String api, boolean status) {
		this.api = api;
		this.status = status;
	}
	
	
	public Led7Response(String api, boolean status, Led7_Data data, String responseType, String query, boolean prettify) {
		this.api = api;
		this.status = status;
		this.setResponse(data);
		this.responseType = responseType;
		this.query = query;
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
	
	public void setResponse(final Led7_Data data) {
		Led7ResponseStructure led7ResponseStructure = new Led7ResponseStructure(data);
		this.response = led7ResponseStructure.toString();
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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
    public String toString() {
        return JsonObjectMapper.toJsonString(this, true);
    }

	private class Led7ResponseStructure {
		private List<Integer> labels;
		private List<Float> probabilities;
		private Integer prediction;
		
		public Led7ResponseStructure() {}
		
		public Led7ResponseStructure(final Led7_Data data) {
			this.labels = data.getLabels();
			this.probabilities = data.getProbabilities();
			float highestProbability = -1;
			int index = 0;
			for(Float propability : probabilities) {
				if(highestProbability < propability) {
					highestProbability = propability;
					this.prediction = labels.get(index);
				}
				index++;
			}
		}
		
		
	    public List<Integer> getLabels() {
			return labels;
		}

		public void setLabels(List<Integer> labels) {
			this.labels = labels;
		}

		public List<Float> getProbabilities() {
			return probabilities;
		}

		public void setProbabilities(List<Float> probabilities) {
			this.probabilities = probabilities;
		}

		public Integer getPrediction() {
			return prediction;
		}

		public void setPrediction(Integer prediction) {
			this.prediction = prediction;
		}

		@Override
	    public String toString() {
	        return JsonObjectMapper.toJsonString(this, false);
	    }
	}
	
}

