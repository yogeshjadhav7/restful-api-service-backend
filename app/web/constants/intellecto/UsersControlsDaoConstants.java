package web.constants.intellecto;

public class UsersControlsDaoConstants {

	final public static String NO_USER_FOUND_USERNAME_EMAIL_INTERNAL_EEROR = "Oops, we didn't find user with provided user name and email!";
	final public static String OTP_VALIDATION_FAILED_INTERNAL_EEROR = "Oops, we couln't validate OTP!";
	final public static String OTP_GENERATION_FAILED_INTERNAL_EEROR = "Oops, we couln't send you the OTP!";

	final public static String NO_USER_FOUND_USERNAME_EMAIL = "User with provided user name and email does not exist";
	final public static String OTP_MATCH_FAILED = "OTP provided was incorrect";
	final public static String OTP_INVALID = "OTP provided was used to verify earlier. Generate a new OTP";
	final public static String OTP_EXPIRED = "This OTP has expired. Generate a new OTP";

	final public static int OTP_LENGTH = 6;
}
