
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/yogesh/apps/restful-api-app/conf/routes
// @DATE:Sun Aug 12 00:30:25 IST 2018

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package web.controllers.intellecto {

  // @LINE:15
  class ReverseUsersBehaviourController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def updateUserBehaviour(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "intellecto/update/behaviour")
    }
  
  }

  // @LINE:33
  class ReverseRobotController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:33
    def fetchRobotPredictions(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "intellecto/robot/predictions")
    }
  
    // @LINE:35
    def notifyTrainingCompletion(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "intellecto/robot/notify/train/completion")
    }
  
    // @LINE:36
    def fetchRobotInfo(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "intellecto/fetch/robot/info")
    }
  
    // @LINE:34
    def trainRobot(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "intellecto/robot/train")
    }
  
  }

  // @LINE:19
  class ReverseFriendsController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:19
    def getFriendsOf(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "intellecto/fetch/friends")
    }
  
    // @LINE:21
    def addFriend(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "intellecto/add/friend")
    }
  
    // @LINE:20
    def updateFriendship(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "intellecto/update/friends")
    }
  
  }

  // @LINE:6
  class ReverseUsersController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def generateOTP(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "intellecto/otp/generate")
    }
  
    // @LINE:10
    def getUserId(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "intellecto/fetch/userid")
    }
  
    // @LINE:7
    def validateOTP(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "intellecto/otp/validate")
    }
  
  }

  // @LINE:29
  class ReverseVersioningController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:29
    def checkAppVersion(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "intellecto/check/app_version")
    }
  
  }

  // @LINE:25
  class ReverseUserGameInfoController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:25
    def updateUserGameInfo(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "intellecto/update/user_game_info")
    }
  
  }

  // @LINE:44
  class ReverseNotificationController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:44
    def saveFirebaseNotificationToken(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "intellecto/send/notification/token")
    }
  
  }


}
