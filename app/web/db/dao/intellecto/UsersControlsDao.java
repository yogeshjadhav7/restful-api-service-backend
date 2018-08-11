package web.db.dao.intellecto;

import com.avaje.ebean.Ebean;

import utils.general.DateAndTimeUtils;
import utils.general.EmailUtils;
import utils.general.JsonObjectMapper;
import utils.general.StringUtils;
import web.constants.app.AppConstants;
import web.constants.app.DateAndTimeConstants;
import web.constants.intellecto.UsersControlsDaoConstants;
import web.db.dto.intellecto.android.models.User;
import web.db.dto.intellecto.android.wrappers.GameWrapper;
import web.db.dto.intellecto.android.wrappers.OpponentGameWrapper;
import web.db.dto.intellecto.android.wrappers.OpponentWrapper;
import web.db.dto.intellecto.android.wrappers.RobotInfoWrapper;
import web.db.dto.intellecto.android.wrappers.UserGameInfoWrapper;
import web.db.dto.intellecto.android.wrappers.UserWrapper;
import web.db.models.intellecto.Intellecto_Robot_Info;
import web.db.models.intellecto.Intellecto_Users;
import web.db.models.intellecto.Intellecto_Users_Controls;
import web.db.models.intellecto.Intellecto_Users_Game_Info;
import web.response.IntellectoResponse;

public class UsersControlsDao {
	
	public static IntellectoResponse validateOtp(String userName, String email, String otp, String deviceModel, 
			String appVersion) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		
		if(appVersion == null) {
			response.setResponse("Oops, looks like you haven't updated the app. Please update the app from the Play Store to login!");
			response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
			error = true;
			response.setErrorMessage(response.getResponse());
			response.setError(error);
			response.setStatus(status);
			return response;
		}
		
		
		Ebean.beginTransaction();
		try {
			Intellecto_Users user = Intellecto_Users.find.where()
					.eq("username", userName)
					.eq("email", email)
					.findUnique();
			
			if(user == null) {
				response.setResponse(UsersControlsDaoConstants.NO_USER_FOUND_USERNAME_EMAIL);
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
				response.setError(error);
				response.setStatus(status);
				return response;
			}
			
			Intellecto_Users_Controls controls = Intellecto_Users_Controls.find.byId(user.getId());
			if(!otp.equals(controls.getOtp())) {
				response.setResponse(UsersControlsDaoConstants.OTP_MATCH_FAILED);
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
				response.setError(error);
				response.setStatus(status);
				return response;
			}
			
			if(controls.isOtpUsed()) {
				response.setResponse(UsersControlsDaoConstants.OTP_INVALID);
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
				response.setError(error);
				response.setStatus(status);
				return response;
			}
			
			long currentEpoch = DateAndTimeUtils.getCurrentTime();
			if((controls.getLastOtpSentAt() + DateAndTimeConstants.HALF_HOUR_IN_MILLI) <  currentEpoch) {
				response.setResponse(UsersControlsDaoConstants.OTP_EXPIRED);
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
				response.setError(error);
				response.setStatus(status);
				return response;
			}
			
			user.setVerified(true);
			user.setDevice(deviceModel);
			user.update();
			controls.setOtpUsed(true);
			controls.update();
			
			Intellecto_Users_Game_Info userGameInfo = Intellecto_Users_Game_Info.find.where()
					.eq("id", user.getId())
					.findUnique();
			
			if(userGameInfo == null) {
				userGameInfo = new Intellecto_Users_Game_Info();
				userGameInfo.setId(user.getId());
				userGameInfo.save();
			}
			
			
			IntellectoResponse userGameInfoResponse = getUserGameInfo(user.getId());
			if(userGameInfoResponse.isError() || !userGameInfoResponse.isStatus()) {
				UserGameInfoWrapper userGameInfoWrapper = new UserGameInfoWrapper();
				User userTemp = new User();
				userTemp.setUserId(user.getId());
				UserWrapper userWrapper = new UserWrapper();
				userWrapper.setUser(userTemp);
				userGameInfoWrapper.setUserWrapper(userWrapper);
				response.setResponse(JsonObjectMapper.toJsonString(userGameInfoWrapper, false));
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_LONG);
			} else {
				response.setResponse(userGameInfoResponse.getResponse());
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
			}

			Ebean.commitTransaction();
			status = true;

		} catch(Exception e) {
			response.setResponse(UsersControlsDaoConstants.OTP_VALIDATION_FAILED_INTERNAL_EEROR);
			response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
			error = true;
			response.setErrorMessage(e.getMessage());
		} finally {
			Ebean.endTransaction();
		}
		
		response.setError(error);
		response.setStatus(status);
		return response;
	}
	
	
	public static IntellectoResponse generateOtp(String userName, String email) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		
		Ebean.beginTransaction();
		try {			
			Intellecto_Users userWithSameName = Intellecto_Users.find.where()
					.eq("userName", userName)
					.ne("email", email)
					.findUnique();
			
			if(userWithSameName != null) {
				response.setResponse("Sorry '" + userName + "' already exists with different email address");
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
				response.setStatus(status);
				response.setError(error);
				return response;
			}
			
			
			Intellecto_Users user = Intellecto_Users.find.where()
					.eq("email", email)
					.findUnique();
			
			boolean userExists = true;
			if(user == null) 
				userExists = false;
				
			if(userExists) {

				Intellecto_Users_Controls controls = Intellecto_Users_Controls.find.byId(user.getId());
				if(controls.getBlockedTill() != 0) {
					boolean isBlocked = (DateAndTimeUtils.compareEpochWithCurrentTime(controls.getBlockedTill()) > 0)?true:false;
					if(isBlocked) {
						long blockForInMinutes = DateAndTimeUtils.compareEpochWithCurrentTime(controls.getBlockedTill()) / DateAndTimeConstants.ONE_MIN_IN_MILLI;
						response.setResponse("You have been blocked for " + blockForInMinutes + " minutes due to excessive OTP regeneration requests!");
						response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
						response.setStatus(status);
						response.setError(error);
						return response;
					} else {
						controls.setBlockedTill(0);
						controls.setOtpSent(0);
					}
				} 
				
				if(controls.getOtpSent() == 10) {
					controls.setBlockedTill(DateAndTimeUtils.getCurrentTime() + DateAndTimeConstants.TWELVE_HOURS_IN_MILLI);
					controls.update();
					Ebean.commitTransaction();
					response.setResponse("Request declined. You have been blocked for " + (DateAndTimeConstants.TWELVE_HOURS_IN_MILLI / DateAndTimeConstants.ONE_MIN_IN_MILLI)+ " minutes for sending too many OTP requests!");
					response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
					response.setStatus(status);
					response.setError(error);
					return response;
				}
				
				
				final String userNameStored = user.getUserName();
				if(!userNameStored.equals(userName)) {
					final String emailBody = "Your Intellecto user is " + userNameStored + ". Please sign in using this user name.";
					final String emailSubject = "Intellecto User Reminder";
					if(EmailUtils.sendEmailTo(emailSubject, emailBody, email)) {
						controls.setOtpSent(controls.getOtpSent() + 1);
						controls.update();
						Ebean.commitTransaction();
						response.setResponse("Incorrect user name for " + email + " provided. We have sent the user name you forgot via email :)");
					} else {
						error = true;
						response.setResponse(UsersControlsDaoConstants.OTP_GENERATION_FAILED_INTERNAL_EEROR);
					}
					
					response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
					response.setStatus(status);
					response.setError(error);
					return response;
				}
				
				
				long timeElapsedSinceLastSent = 0 - DateAndTimeUtils.compareEpochWithCurrentTime(controls.getLastOtpSentAt());
				if(timeElapsedSinceLastSent < DateAndTimeConstants.FIVE_MIN_IN_MILLI) {
					long nextOtp = (DateAndTimeConstants.FIVE_MIN_IN_MILLI - timeElapsedSinceLastSent) / 1000;
					response.setResponse("We have already sent OTP at " + email + ". Check spam emails too. You can request for new OTP in " + nextOtp + " seconds");
					response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
					response.setStatus(status);
					response.setError(error);
					return response;
				}
				
				if(timeElapsedSinceLastSent > DateAndTimeConstants.DAY_IN_MILLI) {
					controls.setBlockedTill(0);
					controls.setOtpSent(0);
				}
				
				final String OTP = StringUtils.generateOTP(6);
				long currentEpoch = DateAndTimeUtils.getCurrentTime();
				final String emailBody = "Intellecto OTP for " + userName + " is " + OTP + ". NOTE: OTP will expire in " + (DateAndTimeConstants.HALF_HOUR_IN_MILLI / DateAndTimeConstants.ONE_MIN_IN_MILLI) + " minutes.";
				final String emailSubject = "Intellecto OTP for Email Verification";
				if(EmailUtils.sendEmailTo(emailSubject, emailBody, email)) {
					controls.setOtp(OTP);
					controls.setLastOtpSentAt(currentEpoch);
					controls.setOtpSent(controls.getOtpSent() + 1);
					controls.setOtpUsed(false);
					controls.update();
					Ebean.commitTransaction();
					status = true;
					response.setResponse(Boolean.toString(true));
					response.setResponseType(AppConstants.API_RESPONSE_TYPE_BOOLEAN);
				} else {
					error = true;
					response.setResponse(UsersControlsDaoConstants.OTP_GENERATION_FAILED_INTERNAL_EEROR);
					response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
					response.setStatus(status);
					response.setError(error);
					return response;
				}
				
			} else {
				
				user = new Intellecto_Users();
				user.setEmail(email);
				user.setUserName(userName);
				user.save();
				
				user = Intellecto_Users.find.where()
						.eq("username", userName)
						.eq("email", email)
						.findUnique();
				
				Intellecto_Users_Controls controls = new Intellecto_Users_Controls();
				controls.setBlockedTill(0);
				controls.setOtpSent(0);
				controls.setId(user.getId());
				
				final String OTP = StringUtils.generateOTP(UsersControlsDaoConstants.OTP_LENGTH);
				long currentEpoch = DateAndTimeUtils.getCurrentTime();
				final String emailBody = "Intellecto OTP for " + userName + " is " + OTP + ". NOTE: OTP will expire in " + (DateAndTimeConstants.HALF_HOUR_IN_MILLI / DateAndTimeConstants.ONE_MIN_IN_MILLI) + " minutes.";
				final String emailSubject = "Intellecto OTP for Email Verification";
				if(EmailUtils.sendEmailTo(emailSubject, emailBody, email)) {
					controls.setOtp(OTP);
					controls.setLastOtpSentAt(currentEpoch);
					controls.setOtpSent(controls.getOtpSent() + 1);
					controls.setOtpUsed(false);
					controls.save();
					Ebean.commitTransaction();
					status = true;
					response.setResponse(Boolean.toString(true));
					response.setResponseType(AppConstants.API_RESPONSE_TYPE_BOOLEAN);
				} else {
					error = true;
					response.setResponse(UsersControlsDaoConstants.OTP_GENERATION_FAILED_INTERNAL_EEROR);
					response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
					response.setStatus(status);
					response.setError(error);
					return response;
				}		
			}

		} catch(Exception e) {
			response.setResponse(UsersControlsDaoConstants.OTP_GENERATION_FAILED_INTERNAL_EEROR);
			response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
			error = true;
			response.setErrorMessage(e.getMessage());
			e.printStackTrace();
		} finally {
			Ebean.endTransaction();
		}
		
		response.setStatus(status);
		response.setError(error);
		return response;
	}
	
	
	private static IntellectoResponse getUserGameInfo(final long userId) {
		IntellectoResponse response = new IntellectoResponse(AppConstants.API_INTELLECTO);
		boolean status = false;
		boolean error = false;
		
		try {
			Intellecto_Users_Game_Info userGameInfo = Intellecto_Users_Game_Info.find.where()
					.eq("id", userId)
					.findUnique();
			
			if(userGameInfo == null || userGameInfo.getUserInfo() == null
					|| userGameInfo.getOpponentInfo() == null
					|| userGameInfo.getOpponentGameInfo() == null
					|| userGameInfo.getGameInfo() == null) {
				response.setResponse("No user info found for this user");
				response.setResponseType(AppConstants.API_RESPONSE_TYPE_STRING);
			} else {
				UserWrapper userInfo = (UserWrapper) JsonObjectMapper.toObject(userGameInfo.getUserInfo(), UserWrapper.class);
				OpponentWrapper opponentInfo  = (OpponentWrapper) JsonObjectMapper.toObject(userGameInfo.getOpponentInfo(), OpponentWrapper.class);
				OpponentGameWrapper opponentGameInfo = (OpponentGameWrapper) JsonObjectMapper.toObject(userGameInfo.getOpponentGameInfo(), OpponentGameWrapper.class);
				GameWrapper gameInfo = (GameWrapper) JsonObjectMapper.toObject(userGameInfo.getGameInfo(), GameWrapper.class);
				
				final RobotInfoWrapper robotInfoWrapper = new RobotInfoWrapper();
				robotInfoWrapper.setRobotInfo(Intellecto_Robot_Info.find.where()
						.eq("user_id", userId)
						.findUnique());
				
				UserGameInfoWrapper userGameInfoWrapper = new UserGameInfoWrapper(gameInfo, opponentInfo, opponentGameInfo, userInfo, null, null, robotInfoWrapper);
				
				response.setResponse(JsonObjectMapper.toJsonString(userGameInfoWrapper, false));
				response.setResponseType("JSON Object");
				status = true;
			}
			
		} catch(Exception e) {
			
		}
		
		response.setStatus(status);
		response.setError(error);
		return response;
	}

}
