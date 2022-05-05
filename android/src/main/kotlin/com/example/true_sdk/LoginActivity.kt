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
    private var accessToken: String = ""

    private fun callLogin() {
        val language = "en"
        val latitude = "0.0"
        val longitude = "0.0"
        val isAuto = true
        val environment = SDKEnvironment.STAGING

        val service = LoginService(this, scope, redirectUrl, environment)
        service.login(language, latitude, longitude, isAuto)
        accessToken = service.accessToken
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
        var jsonObj = Gson().fromJson(json ?: "" , LoginModel::class.java)
        sub = jsonObj.sub
        var loginModel = LoginModel(sub, token = accessToken)
        var encodeJson = Gson().toJson(loginModel)
        finishWithError(encodeJson)
    }

    override fun onLoginError(msg: String?) {
        Log.d("onLoginError", msg.toString())
    }

    override fun onLogoutRespond(isSuccess: Boolean, json: String?) {
    }

    override fun onRefreshAccessToken(isSuccess: Boolean) {
    }

    override fun onCanceled() {
        finish()
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