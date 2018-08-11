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
}
