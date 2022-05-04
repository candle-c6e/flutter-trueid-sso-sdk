import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:true_sdk/true_sdk.dart';

void main() {
  const MethodChannel channel = MethodChannel('true_sdk');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });
}
