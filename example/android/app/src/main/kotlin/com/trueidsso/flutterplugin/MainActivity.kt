package com.trueidsso.flutterplugin

import android.os.Bundle
import com.tdcm.truelifelogin.authentication.TrueIDSDK
import io.flutter.embedding.android.FlutterActivity

class MainActivity: FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        TrueIDSDK.onApplicationCreate()
        super.onCreate(savedInstanceState)
    }
}
