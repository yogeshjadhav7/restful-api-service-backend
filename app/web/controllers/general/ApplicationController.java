package web.controllers.general;

import play.libs.F.Promise;
import play.mvc.*;
import utils.general.JsonObjectMapper;
import web.response.Response;

public class ApplicationController extends Controller {
	
	public Promise<Result> invalidRequestHandler(String api, String responseId) {
	 	Response response = new Response(false);
	 	response.setResponseType("String");
	 	response.setError(true);
	 	response.setErrorMessage("Invalid request ID for API = " + api);
	 	response.setResponse("Invalid request ID for API = " + api);
	 	
	 	return Promise.<Result> pure(
              Results.ok(JsonObjectMapper.toJsonString(response, true))
	 	);
	
	}
		


	

}
