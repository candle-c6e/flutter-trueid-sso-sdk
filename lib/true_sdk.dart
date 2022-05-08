import 'dart:async';

import 'package:flutter/services.dart';

class TrueSdk {
  static const MethodChannel _channel = MethodChannel('true_sdk');

  static Future<dynamic> login({
    isStaging = true,
  }) async {
    Completer _completer = Completer();
    _channel.invokeMethod(
      'login',
      [isStaging],
    );
    _channel.setMethodCallHandler((call) async {
      switch (call.method) {
        case "emit":
          _completer.complete(call.arguments);
          break;
        default:
          break;
      }
    });
    return _completer.future;
  }
}
