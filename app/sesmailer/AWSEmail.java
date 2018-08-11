package sesmailer;


public class AWSEmail {
	private String subject;
	private String body;
	private String[] recipients;
	private String from;
	private String htmlcontent;

	public AWSEmail() {
		super();
	}

	public AWSEmail(String subject, String body, String[] recipients,
			String from) {
		super();
		this.subject = subject;
		this.body = body;
		this.recipients = recipients;
		this.from = from;
	}
	
	

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String[] getRecipients() {
		return recipients;
	}
	public void setRecipients(String[] recipients) {
		this.recipients = recipients;
	}
	public void setRecipient(String recipient) {
		this.recipients = new String[10];
		this.recipients[0]=recipient;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getHtmlcontent() {
		return htmlcontent;
	}
	public void setHtmlcontent(String htmlcontent) {
		this.htmlcontent = htmlcontent;
	}

}
