import UIKit
import Flutter
import TrueIDFramework

@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate {
  override func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
  ) -> Bool {
      let channel = self.getChannelFlutter()
      
      let trueSDK = TrueIdPlatformAuth.shareInstance
      trueSDK.setSDKfor(sdkFor: TrueSDKFor.staging)
      trueSDK.setClientID(clientId: "clientId")
      trueSDK.setReDirectUrl(urlStr: "redirectUrl")
      trueSDK.initWith(scopes: ["public_profile","email","mobile","references"])
      trueSDK.isLoginAutoAfterForget = true
      trueSDK.isLoginAutoAfterRegister = true
      trueSDK.loginDelegate = self
      trueSDK.isSelfLogin = true
      trueSDK.activeAppDelegate = self
      trueSDK.setLanguage(language: LANGUAGE_SDK.EN)
    
      let eventLog = SDKGALog.eventLog
      eventLog.logDelegate = self
      
      GeneratedPluginRegistrant.register(with: self)
      return super.application(application, didFinishLaunchingWithOptions: launchOptions)
  }

    override func applicationDidBecomeActive(_ application: UIApplication) {
    TrueIdPlatformAuth.activeApp()
  }
}

enum SdkError: Error {
    case cancelLogin
    case error
}

extension AppDelegate : TrueSDKActiveAppDelegate {
    func onActiveAppCompletion(isSuccess: Bool) {}
}

extension AppDelegate : TrueSDKLoginDelegate{
    func onForgetPasswordSuccess(_ reponse: NSDictionary) {}
    
    func onLoginSuccess(_ userProfileData: NSDictionary, expiredTimeInSecond: Int) {
        TrueIdPlatformAuth.shareInstance.selfVerify({ (accessToken) in
            let channel = self.getChannelFlutter()
            var data = [String: String]()
            
            data["sub"] = userProfileData["sub"] as? String
            data["accessToken"] = accessToken
            
            let encoder = JSONEncoder()
            if let json = try? encoder.encode(data) {
                channel.invokeMethod("emit", arguments: String(data: json, encoding: .utf8)!)
            }
            
        }, onVerifyFailed: {(error) in
            
        })
    }
    
    func cancelLoginTrueIDView() {
        let channel = getChannelFlutter()
        channel.invokeMethod("emit", arguments: "CANCEL_LOGIN")
    }
    
    func getChannelFlutter() -> FlutterMethodChannel {
        let controller = self.window?.rootViewController as! FlutterViewController
         return FlutterMethodChannel(
            name: "true_sdk",
            binaryMessenger: controller.binaryMessenger)
    }
    
    func onLoginError(_ error: NSDictionary) {}
    
    func onRevokeAlready() {}
}

struct UserProfile: Decodable {
    let device_id: String
    let sub: String
}

extension AppDelegate : TrueSDKEventLogDelegate{
    
    func onReceivedEvent(event: TrueEventModel) {}
    
    func onReceivedScreen(screenModel: TrueScreenModel) {}
}
