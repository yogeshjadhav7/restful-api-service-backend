
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/yogesh/apps/restful-api-app/conf/routes
// @DATE:Sun Aug 12 00:30:25 IST 2018

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:39
package web.controllers.led7.javascript {
  import ReverseRouteContext.empty

  // @LINE:39
  class ReversePredictorController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:39
    def predict: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.led7.PredictorController.predict",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "led7/predict"})
        }
      """
    )
  
    // @LINE:40
    def databaseInitializer: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.led7.PredictorController.databaseInitializer",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "led7/db/initializer"})
        }
      """
    )
  
  }


}
