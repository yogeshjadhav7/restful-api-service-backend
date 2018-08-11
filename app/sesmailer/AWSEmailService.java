package sesmailer;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class AWSEmailService {

	private String accesskey;
	private String secretkey;
	private String region;

	public AWSEmailService() {
		super();
	}
	
	public AWSEmailService(String accesskey, String secretkey) {
		super();
		this.accesskey = accesskey;
		this.secretkey = secretkey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}
	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	public boolean sendEmail(AWSEmail message) throws Exception{
			 Destination destination = new Destination().withToAddresses(message.getRecipients());
			 
			 // Create the subject and body of the message.
			 Content subject = new Content().withData(message.getSubject());
			 Content textBody = new Content().withData(message.getBody());
			 Body body = new Body().withText(textBody);
			 Message email_message = new Message().withSubject(subject).withBody(body);
			 
			 // Assemble the email.
			 SendEmailRequest request = new SendEmailRequest().withSource(message.getFrom()).withDestination(destination).withMessage(email_message);
			 
			 //Credentials
			 AWSCredentials credentials = new BasicAWSCredentials(accesskey,secretkey);
			 
			 //Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
			 AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);
			 
			 
			 // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your production
			 // access status, sending limits, and Amazon SES identity-related settings are specific to a given
			 // AWS region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using
			 // the US East (N. Virginia) region. Examples of other regions that Amazon SES supports are US_WEST_2
			 // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html
			 
			 //TODO: Remove Hardcoding
			 Region REGION = Region.getRegion(Regions.US_EAST_1);
			 client.setRegion(REGION);
			 // Send the email.
			 client.sendEmail(request);
			 return true;
	}
	
	public boolean sendTemplateEmail(AWSEmail message) throws Exception{
		/*Core Implementation*/
		 Destination destination = new Destination().withToAddresses(message.getRecipients());
		 
		 // Create the subject and body of the message.
		 Content subject = new Content().withData(message.getSubject());
		 Content textBody = new Content().withData(message.getBody());
		 Content htmlContent = new Content().withData(message.getHtmlcontent()).withCharset("UTF-8");
		 Body body = new Body().withText(textBody);
		 body.setHtml(htmlContent);
		 
		 Message email_message = new Message().withSubject(subject).withBody(body);
		 
		 // Assemble the email.
		 SendEmailRequest request = new SendEmailRequest().withSource(message.getFrom()).withDestination(destination).withMessage(email_message);
		 
		 //Credentials
		 AWSCredentials credentials = new BasicAWSCredentials(accesskey,secretkey);
		 
		 //Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
		 AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);
		 
		 // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your production
		 // access status, sending limits, and Amazon SES identity-related settings are specific to a given
		 // AWS region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using
		 // the US East (N. Virginia) region. Examples of other regions that Amazon SES supports are US_WEST_2
		 // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html
		 
		 //TODO: Remove Hardcoding
		 Region REGION = Region.getRegion(Regions.US_EAST_1);
		 client.setRegion(REGION);
		 // Send the email.
		 client.sendEmail(request);
		 return true;
		
		
	}
	
	
	
	

}
