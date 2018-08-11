
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/yogesh/apps/restful-api-app/conf/routes
// @DATE:Sun Aug 12 00:30:25 IST 2018

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:39
package web.controllers.led7 {

  // @LINE:39
  class ReversePredictorController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:39
    def predict(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "led7/predict")
    }
  
    // @LINE:40
    def databaseInitializer(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "led7/db/initializer")
    }
  
  }


}
