package utils.general;

public class StringUtils {

	private static final String[] NUMBERS = {"1","2","3","4","5","6","7","8","9","0"};
	public static String generateOTP(int otpSize) {
		String  otpString = "";
		for(int i = 0; i < otpSize; i++) {
			int index = (int)(Math.random() * 10);
			index = index % 10;
			otpString += NUMBERS[index];
		}
		return otpString;
	} 
	
	private static final String ALPHANUMERIC = "qwertyuiopasdfghjklzxcvbnm0123456789QWERTYUIOPASDFGHJKLZXCVBNM";
	public static String generateRandomString(final int size) {
		String  string = "";
		for(int i = 0; i < size; i++) {
			int index = (int)(Math.random() * (ALPHANUMERIC.length() - 1));
			string += ALPHANUMERIC.charAt(index);
		}
		return string;
	} 
	
    public static String getAuthKey(final long userId, final String userName, final String apiKey) {
        String authKey = null;
        authKey = MD5.getHashValue(Long.toString(userId) + "#" + userName);
        authKey = MD5.getHashValue(authKey + "@" + apiKey);
        authKey = MD5.getHashValue(userName + "$" + authKey + Long.toString(userId));
        return authKey;
    }
}
