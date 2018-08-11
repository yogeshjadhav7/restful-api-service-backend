package utils.general;

import sesmailer.AWSEmail;
import sesmailer.AWSEmailService;
import web.constants.app.EmailConstants;

public class EmailUtils {
	
	public static boolean sendEmailTo(final String subject, final String body, String to) {
	
		AWSEmailService emailService = new AWSEmailService();
		emailService.setAccesskey(EmailConstants.ACCESS_KEY);
		emailService.setSecretkey(EmailConstants.SECRET_KEY);
		AWSEmail message = new AWSEmail();
		message.setFrom(EmailConstants.FROM);
		String[] recipients = {to};
		message.setRecipients(recipients);
		message.setSubject(subject);
		message.setBody(body);
		boolean status = true;
		try {
			emailService.sendEmail(message);
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		
		return status;
	}

}
