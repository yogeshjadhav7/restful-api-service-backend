package web.controllers.intellecto;

import play.libs.F.Promise;
import play.mvc.*;


public class DeepLinkManagementController extends IntellectoController {

	public Promise<Result> test() {
		return Promise.pure(ok("Ok!"));
	}
	
}
