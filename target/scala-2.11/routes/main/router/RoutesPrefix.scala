
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/yogesh/apps/restful-api-app/conf/routes
// @DATE:Sun Aug 12 00:30:25 IST 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
