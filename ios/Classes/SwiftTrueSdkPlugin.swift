import Flutter
import UIKit
import TrueIDFramework

public class SwiftTrueSdkPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "true_sdk", binaryMessenger: registrar.messenger())
    let instance = SwiftTrueSdkPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    if (call.method == "login") {
        TrueIdPlatformAuth.shareInstance.login()
    }
  }
}
