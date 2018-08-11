
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/yogesh/apps/restful-api-app/conf/routes
// @DATE:Sun Aug 12 00:30:25 IST 2018

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package web.controllers.intellecto.javascript {
  import ReverseRouteContext.empty

  // @LINE:15
  class ReverseUsersBehaviourController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def updateUserBehaviour: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.intellecto.UsersBehaviourController.updateUserBehaviour",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "intellecto/update/behaviour"})
        }
      """
    )
  
  }

  // @LINE:33
  class ReverseRobotController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:33
    def fetchRobotPredictions: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.intellecto.RobotController.fetchRobotPredictions",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "intellecto/robot/predictions"})
        }
      """
    )
  
    // @LINE:35
    def notifyTrainingCompletion: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.intellecto.RobotController.notifyTrainingCompletion",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "intellecto/robot/notify/train/completion"})
        }
      """
    )
  
    // @LINE:36
    def fetchRobotInfo: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.intellecto.RobotController.fetchRobotInfo",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "intellecto/fetch/robot/info"})
        }
      """
    )
  
    // @LINE:34
    def trainRobot: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.intellecto.RobotController.trainRobot",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "intellecto/robot/train"})
        }
      """
    )
  
  }

  // @LINE:19
  class ReverseFriendsController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:19
    def getFriendsOf: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.intellecto.FriendsController.getFriendsOf",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "intellecto/fetch/friends"})
        }
      """
    )
  
    // @LINE:21
    def addFriend: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.intellecto.FriendsController.addFriend",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "intellecto/add/friend"})
        }
      """
    )
  
    // @LINE:20
    def updateFriendship: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.intellecto.FriendsController.updateFriendship",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "intellecto/update/friends"})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseUsersController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def generateOTP: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.intellecto.UsersController.generateOTP",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "intellecto/otp/generate"})
        }
      """
    )
  
    // @LINE:10
    def getUserId: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.intellecto.UsersController.getUserId",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "intellecto/fetch/userid"})
        }
      """
    )
  
    // @LINE:7
    def validateOTP: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.intellecto.UsersController.validateOTP",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "intellecto/otp/validate"})
        }
      """
    )
  
  }

  // @LINE:29
  class ReverseVersioningController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:29
    def checkAppVersion: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.intellecto.VersioningController.checkAppVersion",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "intellecto/check/app_version"})
        }
      """
    )
  
  }

  // @LINE:25
  class ReverseUserGameInfoController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:25
    def updateUserGameInfo: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.intellecto.UserGameInfoController.updateUserGameInfo",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "intellecto/update/user_game_info"})
        }
      """
    )
  
  }

  // @LINE:44
  class ReverseNotificationController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:44
    def saveFirebaseNotificationToken: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "web.controllers.intellecto.NotificationController.saveFirebaseNotificationToken",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "intellecto/send/notification/token"})
        }
      """
    )
  
  }


}
