import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:true_sdk/true_sdk.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String? _token = 'Unknown';

  Future<void> initPlatformState() async {
    String? token;
    try {
      token = await TrueSdk.login(isStaging: true) ?? 'not auth';
    } on PlatformException {
      token = 'Failed to get platform version.';
    }

    if (!mounted) return;

    setState(() {
      _token = token;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(_token ?? ''),
              GestureDetector(
                onTap: initPlatformState,
                child: Container(
                  color: Colors.amber,
                  width: 200,
                  height: 40,
                  child: const Center(
                    child: Text("Login"),
                  ),
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
