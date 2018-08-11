
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/yogesh/apps/restful-api-app/conf/routes
// @DATE:Sun Aug 12 00:30:25 IST 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  UsersController_6: web.controllers.intellecto.UsersController,
  // @LINE:15
  UsersBehaviourController_5: web.controllers.intellecto.UsersBehaviourController,
  // @LINE:19
  FriendsController_7: web.controllers.intellecto.FriendsController,
  // @LINE:25
  UserGameInfoController_3: web.controllers.intellecto.UserGameInfoController,
  // @LINE:29
  VersioningController_0: web.controllers.intellecto.VersioningController,
  // @LINE:33
  RobotController_4: web.controllers.intellecto.RobotController,
  // @LINE:39
  PredictorController_1: web.controllers.led7.PredictorController,
  // @LINE:44
  NotificationController_2: web.controllers.intellecto.NotificationController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    UsersController_6: web.controllers.intellecto.UsersController,
    // @LINE:15
    UsersBehaviourController_5: web.controllers.intellecto.UsersBehaviourController,
    // @LINE:19
    FriendsController_7: web.controllers.intellecto.FriendsController,
    // @LINE:25
    UserGameInfoController_3: web.controllers.intellecto.UserGameInfoController,
    // @LINE:29
    VersioningController_0: web.controllers.intellecto.VersioningController,
    // @LINE:33
    RobotController_4: web.controllers.intellecto.RobotController,
    // @LINE:39
    PredictorController_1: web.controllers.led7.PredictorController,
    // @LINE:44
    NotificationController_2: web.controllers.intellecto.NotificationController
  ) = this(errorHandler, UsersController_6, UsersBehaviourController_5, FriendsController_7, UserGameInfoController_3, VersioningController_0, RobotController_4, PredictorController_1, NotificationController_2, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, UsersController_6, UsersBehaviourController_5, FriendsController_7, UserGameInfoController_3, VersioningController_0, RobotController_4, PredictorController_1, NotificationController_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """intellecto/otp/generate""", """web.controllers.intellecto.UsersController.generateOTP()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """intellecto/otp/validate""", """web.controllers.intellecto.UsersController.validateOTP()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """intellecto/fetch/userid""", """web.controllers.intellecto.UsersController.getUserId()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """intellecto/update/behaviour""", """web.controllers.intellecto.UsersBehaviourController.updateUserBehaviour()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """intellecto/fetch/friends""", """web.controllers.intellecto.FriendsController.getFriendsOf()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """intellecto/update/friends""", """web.controllers.intellecto.FriendsController.updateFriendship()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """intellecto/add/friend""", """web.controllers.intellecto.FriendsController.addFriend()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """intellecto/update/user_game_info""", """web.controllers.intellecto.UserGameInfoController.updateUserGameInfo()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """intellecto/check/app_version""", """web.controllers.intellecto.VersioningController.checkAppVersion()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """intellecto/robot/predictions""", """web.controllers.intellecto.RobotController.fetchRobotPredictions()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """intellecto/robot/train""", """web.controllers.intellecto.RobotController.trainRobot()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """intellecto/robot/notify/train/completion""", """web.controllers.intellecto.RobotController.notifyTrainingCompletion()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """intellecto/fetch/robot/info""", """web.controllers.intellecto.RobotController.fetchRobotInfo()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """led7/predict""", """web.controllers.led7.PredictorController.predict()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """led7/db/initializer""", """web.controllers.led7.PredictorController.databaseInitializer()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """intellecto/send/notification/token""", """web.controllers.intellecto.NotificationController.saveFirebaseNotificationToken()"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val web_controllers_intellecto_UsersController_generateOTP0_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("intellecto/otp/generate")))
  )
  private[this] lazy val web_controllers_intellecto_UsersController_generateOTP0_invoker = createInvoker(
    UsersController_6.generateOTP(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.intellecto.UsersController",
      "generateOTP",
      Nil,
      "POST",
      """ INTELLECTO API
 OTP VALIDATION ROUTES""",
      this.prefix + """intellecto/otp/generate"""
    )
  )

  // @LINE:7
  private[this] lazy val web_controllers_intellecto_UsersController_validateOTP1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("intellecto/otp/validate")))
  )
  private[this] lazy val web_controllers_intellecto_UsersController_validateOTP1_invoker = createInvoker(
    UsersController_6.validateOTP(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.intellecto.UsersController",
      "validateOTP",
      Nil,
      "POST",
      """""",
      this.prefix + """intellecto/otp/validate"""
    )
  )

  // @LINE:10
  private[this] lazy val web_controllers_intellecto_UsersController_getUserId2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("intellecto/fetch/userid")))
  )
  private[this] lazy val web_controllers_intellecto_UsersController_getUserId2_invoker = createInvoker(
    UsersController_6.getUserId(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.intellecto.UsersController",
      "getUserId",
      Nil,
      "POST",
      """ USER ROUTES""",
      this.prefix + """intellecto/fetch/userid"""
    )
  )

  // @LINE:15
  private[this] lazy val web_controllers_intellecto_UsersBehaviourController_updateUserBehaviour3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("intellecto/update/behaviour")))
  )
  private[this] lazy val web_controllers_intellecto_UsersBehaviourController_updateUserBehaviour3_invoker = createInvoker(
    UsersBehaviourController_5.updateUserBehaviour(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.intellecto.UsersBehaviourController",
      "updateUserBehaviour",
      Nil,
      "POST",
      """ BEHAVIOUR ROUTES
POST	/intellecto/fetch/behaviour						web.controllers.intellecto.UsersBehaviourController.getUserBehaviour()""",
      this.prefix + """intellecto/update/behaviour"""
    )
  )

  // @LINE:19
  private[this] lazy val web_controllers_intellecto_FriendsController_getFriendsOf4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("intellecto/fetch/friends")))
  )
  private[this] lazy val web_controllers_intellecto_FriendsController_getFriendsOf4_invoker = createInvoker(
    FriendsController_7.getFriendsOf(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.intellecto.FriendsController",
      "getFriendsOf",
      Nil,
      "POST",
      """ FRIENDS ROUTES""",
      this.prefix + """intellecto/fetch/friends"""
    )
  )

  // @LINE:20
  private[this] lazy val web_controllers_intellecto_FriendsController_updateFriendship5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("intellecto/update/friends")))
  )
  private[this] lazy val web_controllers_intellecto_FriendsController_updateFriendship5_invoker = createInvoker(
    FriendsController_7.updateFriendship(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.intellecto.FriendsController",
      "updateFriendship",
      Nil,
      "POST",
      """""",
      this.prefix + """intellecto/update/friends"""
    )
  )

  // @LINE:21
  private[this] lazy val web_controllers_intellecto_FriendsController_addFriend6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("intellecto/add/friend")))
  )
  private[this] lazy val web_controllers_intellecto_FriendsController_addFriend6_invoker = createInvoker(
    FriendsController_7.addFriend(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.intellecto.FriendsController",
      "addFriend",
      Nil,
      "POST",
      """""",
      this.prefix + """intellecto/add/friend"""
    )
  )

  // @LINE:25
  private[this] lazy val web_controllers_intellecto_UserGameInfoController_updateUserGameInfo7_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("intellecto/update/user_game_info")))
  )
  private[this] lazy val web_controllers_intellecto_UserGameInfoController_updateUserGameInfo7_invoker = createInvoker(
    UserGameInfoController_3.updateUserGameInfo(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.intellecto.UserGameInfoController",
      "updateUserGameInfo",
      Nil,
      "POST",
      """ USER GAME INFO ROUTES""",
      this.prefix + """intellecto/update/user_game_info"""
    )
  )

  // @LINE:29
  private[this] lazy val web_controllers_intellecto_VersioningController_checkAppVersion8_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("intellecto/check/app_version")))
  )
  private[this] lazy val web_controllers_intellecto_VersioningController_checkAppVersion8_invoker = createInvoker(
    VersioningController_0.checkAppVersion(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.intellecto.VersioningController",
      "checkAppVersion",
      Nil,
      "POST",
      """ CHECK VERSION ROUTES""",
      this.prefix + """intellecto/check/app_version"""
    )
  )

  // @LINE:33
  private[this] lazy val web_controllers_intellecto_RobotController_fetchRobotPredictions9_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("intellecto/robot/predictions")))
  )
  private[this] lazy val web_controllers_intellecto_RobotController_fetchRobotPredictions9_invoker = createInvoker(
    RobotController_4.fetchRobotPredictions(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.intellecto.RobotController",
      "fetchRobotPredictions",
      Nil,
      "POST",
      """ ROBOT ROUTES""",
      this.prefix + """intellecto/robot/predictions"""
    )
  )

  // @LINE:34
  private[this] lazy val web_controllers_intellecto_RobotController_trainRobot10_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("intellecto/robot/train")))
  )
  private[this] lazy val web_controllers_intellecto_RobotController_trainRobot10_invoker = createInvoker(
    RobotController_4.trainRobot(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.intellecto.RobotController",
      "trainRobot",
      Nil,
      "POST",
      """""",
      this.prefix + """intellecto/robot/train"""
    )
  )

  // @LINE:35
  private[this] lazy val web_controllers_intellecto_RobotController_notifyTrainingCompletion11_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("intellecto/robot/notify/train/completion")))
  )
  private[this] lazy val web_controllers_intellecto_RobotController_notifyTrainingCompletion11_invoker = createInvoker(
    RobotController_4.notifyTrainingCompletion(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.intellecto.RobotController",
      "notifyTrainingCompletion",
      Nil,
      "POST",
      """""",
      this.prefix + """intellecto/robot/notify/train/completion"""
    )
  )

  // @LINE:36
  private[this] lazy val web_controllers_intellecto_RobotController_fetchRobotInfo12_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("intellecto/fetch/robot/info")))
  )
  private[this] lazy val web_controllers_intellecto_RobotController_fetchRobotInfo12_invoker = createInvoker(
    RobotController_4.fetchRobotInfo(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.intellecto.RobotController",
      "fetchRobotInfo",
      Nil,
      "POST",
      """""",
      this.prefix + """intellecto/fetch/robot/info"""
    )
  )

  // @LINE:39
  private[this] lazy val web_controllers_led7_PredictorController_predict13_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("led7/predict")))
  )
  private[this] lazy val web_controllers_led7_PredictorController_predict13_invoker = createInvoker(
    PredictorController_1.predict(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.led7.PredictorController",
      "predict",
      Nil,
      "GET",
      """ FE-SITE API""",
      this.prefix + """led7/predict"""
    )
  )

  // @LINE:40
  private[this] lazy val web_controllers_led7_PredictorController_databaseInitializer14_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("led7/db/initializer")))
  )
  private[this] lazy val web_controllers_led7_PredictorController_databaseInitializer14_invoker = createInvoker(
    PredictorController_1.databaseInitializer(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.led7.PredictorController",
      "databaseInitializer",
      Nil,
      "GET",
      """""",
      this.prefix + """led7/db/initializer"""
    )
  )

  // @LINE:44
  private[this] lazy val web_controllers_intellecto_NotificationController_saveFirebaseNotificationToken15_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("intellecto/send/notification/token")))
  )
  private[this] lazy val web_controllers_intellecto_NotificationController_saveFirebaseNotificationToken15_invoker = createInvoker(
    NotificationController_2.saveFirebaseNotificationToken(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "web.controllers.intellecto.NotificationController",
      "saveFirebaseNotificationToken",
      Nil,
      "POST",
      """ NOTIFICATIONS API""",
      this.prefix + """intellecto/send/notification/token"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case web_controllers_intellecto_UsersController_generateOTP0_route(params) =>
      call { 
        web_controllers_intellecto_UsersController_generateOTP0_invoker.call(UsersController_6.generateOTP())
      }
  
    // @LINE:7
    case web_controllers_intellecto_UsersController_validateOTP1_route(params) =>
      call { 
        web_controllers_intellecto_UsersController_validateOTP1_invoker.call(UsersController_6.validateOTP())
      }
  
    // @LINE:10
    case web_controllers_intellecto_UsersController_getUserId2_route(params) =>
      call { 
        web_controllers_intellecto_UsersController_getUserId2_invoker.call(UsersController_6.getUserId())
      }
  
    // @LINE:15
    case web_controllers_intellecto_UsersBehaviourController_updateUserBehaviour3_route(params) =>
      call { 
        web_controllers_intellecto_UsersBehaviourController_updateUserBehaviour3_invoker.call(UsersBehaviourController_5.updateUserBehaviour())
      }
  
    // @LINE:19
    case web_controllers_intellecto_FriendsController_getFriendsOf4_route(params) =>
      call { 
        web_controllers_intellecto_FriendsController_getFriendsOf4_invoker.call(FriendsController_7.getFriendsOf())
      }
  
    // @LINE:20
    case web_controllers_intellecto_FriendsController_updateFriendship5_route(params) =>
      call { 
        web_controllers_intellecto_FriendsController_updateFriendship5_invoker.call(FriendsController_7.updateFriendship())
      }
  
    // @LINE:21
    case web_controllers_intellecto_FriendsController_addFriend6_route(params) =>
      call { 
        web_controllers_intellecto_FriendsController_addFriend6_invoker.call(FriendsController_7.addFriend())
      }
  
    // @LINE:25
    case web_controllers_intellecto_UserGameInfoController_updateUserGameInfo7_route(params) =>
      call { 
        web_controllers_intellecto_UserGameInfoController_updateUserGameInfo7_invoker.call(UserGameInfoController_3.updateUserGameInfo())
      }
  
    // @LINE:29
    case web_controllers_intellecto_VersioningController_checkAppVersion8_route(params) =>
      call { 
        web_controllers_intellecto_VersioningController_checkAppVersion8_invoker.call(VersioningController_0.checkAppVersion())
      }
  
    // @LINE:33
    case web_controllers_intellecto_RobotController_fetchRobotPredictions9_route(params) =>
      call { 
        web_controllers_intellecto_RobotController_fetchRobotPredictions9_invoker.call(RobotController_4.fetchRobotPredictions())
      }
  
    // @LINE:34
    case web_controllers_intellecto_RobotController_trainRobot10_route(params) =>
      call { 
        web_controllers_intellecto_RobotController_trainRobot10_invoker.call(RobotController_4.trainRobot())
      }
  
    // @LINE:35
    case web_controllers_intellecto_RobotController_notifyTrainingCompletion11_route(params) =>
      call { 
        web_controllers_intellecto_RobotController_notifyTrainingCompletion11_invoker.call(RobotController_4.notifyTrainingCompletion())
      }
  
    // @LINE:36
    case web_controllers_intellecto_RobotController_fetchRobotInfo12_route(params) =>
      call { 
        web_controllers_intellecto_RobotController_fetchRobotInfo12_invoker.call(RobotController_4.fetchRobotInfo())
      }
  
    // @LINE:39
    case web_controllers_led7_PredictorController_predict13_route(params) =>
      call { 
        web_controllers_led7_PredictorController_predict13_invoker.call(PredictorController_1.predict())
      }
  
    // @LINE:40
    case web_controllers_led7_PredictorController_databaseInitializer14_route(params) =>
      call { 
        web_controllers_led7_PredictorController_databaseInitializer14_invoker.call(PredictorController_1.databaseInitializer())
      }
  
    // @LINE:44
    case web_controllers_intellecto_NotificationController_saveFirebaseNotificationToken15_route(params) =>
      call { 
        web_controllers_intellecto_NotificationController_saveFirebaseNotificationToken15_invoker.call(NotificationController_2.saveFirebaseNotificationToken())
      }
  }
}
