package com.example.true_sdk

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.tdcm.truelifelogin.authentication.LoginService
import com.tdcm.truelifelogin.constants.SDKEnvironment
import com.tdcm.truelifelogin.interfaces.LoginServiceListener
import com.tdcm.truelifelogin.models.Events
import com.tdcm.truelifelogin.models.Screens
import android.content.Intent

import android.app.Activity


class LoginActivity : AppCompatActivity(), LoginServiceListener {
    private val scope = listOf("public_profile", "mobile", "email", "references")
    private val redirectUrl = "https://www.trueid.net"
    lateinit var service: LoginService

    private fun callLogin() {
        val language = "en"
        val latitude = "0.0"
        val longitude = "0.0"
        val isAuto = true
        var environment = SDKEnvironment.STAGING

        if((intent.getStringExtra("stagging") ?: "") == "false") {
            environment = SDKEnvironment.PRODUCTION
        }

        try {
            service = LoginService(this, scope, redirectUrl, environment)
            if(service.isLogin) {
                service.logout()
            }
            service.login(language, latitude, longitude, isAuto)
        } catch (e: Exception) {
            finishWithError("CANCEL_LOGIN")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callLogin();
    }

    fun finishWithError(data: String) {
        val intent = Intent()
        intent.putExtra("onLoginSuccess", data)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onLoginSuccess(json: String?, expiresSecond: Int) {
        var sub = ""
        var jsonObj = Gson().fromJson(json ?: "", LoginModel::class.java)
        sub = jsonObj.sub
        var loginModel = LoginModel(sub, accessToken = service.accessToken)
        var encodeJson = Gson().toJson(loginModel)
        finishWithError(encodeJson)
    }

    override fun onLoginError(msg: String?) {
        finishWithError("ERROR")
    }

    override fun onLogoutRespond(isSuccess: Boolean, json: String?) {
    }

    override fun onRefreshAccessToken(isSuccess: Boolean) {
    }

    override fun onCanceled() {
        finishWithError("CANCEL_LOGIN")
    }

    override fun onFindTrueIDApp(isFound: Boolean) {
    }

    override fun onMappingAlready(msg: String?) {
    }

    override fun onMappingSuccess(msg: String?) {
    }

    override fun onMappingFailed(msg: String?) {
    }

    override fun onRegisterSuccess(loginCode: String?, clientId: String?) {
    }

    override fun onRegisterError(errorMessage: String?) {
        finishWithError("ERROR")
    }

    override fun onForgotSuccess(loginCode: String?, clientId: String?) {
    }

    override fun onForgotError(errorObject: String?) {
    }

    override fun onGetInfoSuccess(json: String?, expiresSecond: Int) {
    }

    override fun onGetInfoFailed(errorMessage: String?) {
    }

    override fun onReceivedScreen(screens: Screens?) {
    }

    override fun onReceivedEvent(events: Events?) {
    }

    override fun onRefreshAccessTokenFailed(errorMessage: String?) {
    }

    override fun onRevokeAlready() {
    }
}