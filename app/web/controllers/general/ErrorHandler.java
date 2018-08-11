package web.controllers.general;

import play.http.HttpErrorHandler;
import play.mvc.*;
import play.mvc.Http.*;
import utils.general.JsonObjectMapper;
import play.libs.F.*;
import web.response.Response;

public class ErrorHandler implements HttpErrorHandler {

 @Override
 public Promise<Result> onClientError(RequestHeader arg0, int arg1, String arg2) {
	 	Response response = new Response(false);
	 	response.setResponseType("String");
	 	response.setError(true);
	 	response.setResponse("Stop inspecting our calls NOW otherwise we will call 911");
	 	response.setErrorMessage("Invalid request ID");
   return Promise.<Result> pure(
                 Results.ok(JsonObjectMapper.toJsonString(response, true))
         );
 }

 
 @Override
 public Promise<Result> onServerError(RequestHeader arg0, Throwable arg1) {
	 	Response response = new Response(false);
	 	response.setResponseType("String");
	 	response.setResponse("Oops, Something went wrong! Sorry!");
return Promise.<Result> pure(
              Results.ok(JsonObjectMapper.toJsonString(response, true))
      );
 }

}