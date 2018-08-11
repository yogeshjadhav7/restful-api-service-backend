package web.constants.app;

public class EmailConstants {

	public final static String FROM = "intellecto.vertexcover@gmail.com";
	public final static String ACCESS_KEY = play.Play.application().configuration().getString("aws.ses.access.key");
	public final static String SECRET_KEY = play.Play.application().configuration().getString("aws.ses.secret.key");
}
