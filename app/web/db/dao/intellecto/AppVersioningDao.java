package web.db.dao.intellecto;

import java.util.List;

import utils.general.JsonObjectMapper;
import web.constants.app.AppConstants;
import web.constants.intellecto.AndroidAppConstants;
import web.db.models.intellecto.Intellecto_App_Version;
import web.response.IntellectoResponse;

public class AppVersioningDao {

	public static IntellectoResponse checkAppVersion(final String appVersion) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = true;
		boolean error = false;
		response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
		response.setResponse(null);
		
		if(appVersion == null) {
			response.setError(true);
			response.setErrorMessage("No app version was specified");
			return response;
		}
		
		try {
			List<Intellecto_App_Version> mandatoryVersions = null;
			mandatoryVersions = Intellecto_App_Version.find.where()
					.gt("version", appVersion)
					.eq("mandatory", 1)
					.orderBy("version desc")
					.findList();
			
			if(mandatoryVersions != null && !mandatoryVersions.isEmpty()) {
				response.setResponse(JsonObjectMapper.toJsonString(mandatoryVersions.get(mandatoryVersions.size() - 1), false));
				status = false;
			} else {
				List<Intellecto_App_Version> nonMandatoryVersions = null;
				nonMandatoryVersions = Intellecto_App_Version.find.where()
						.gt("version", appVersion)
						.eq("mandatory", 0)
						.orderBy("version desc")
						.findList();
				
				if(nonMandatoryVersions != null && !nonMandatoryVersions.isEmpty()) {
					response.setResponse(JsonObjectMapper.toJsonString(nonMandatoryVersions.get(nonMandatoryVersions.size() - 1), false));
					status = false;
				}
			}

		} catch(Exception e) {
		    response.setResponse(AndroidAppConstants.CHECK_APP_VERSION_FAILED_INTERNAL_ERROR);
		    error = true;
		    response.setErrorMessage(e.getMessage());
		}
		
		response.setError(error);
		response.setStatus(status);
		return response;
	}
	
}
