package com.example.true_sdk

import androidx.appcompat.app.AppCompatActivity
import com.tdcm.truelifelogin.authentication.LoginService
import com.tdcm.truelifelogin.constants.SDKEnvironment
import com.tdcm.truelifelogin.interfaces.LoginServiceListener
import com.tdcm.truelifelogin.models.Events
import com.tdcm.truelifelogin.models.Screens
import java.util.*

class LoginActivity : AppCompatActivity(), LoginServiceListener {
    private val scope = Arrays.asList("public_profile", "mobile", "email", "references")
    private val redirectUrl = "https://www.trueid.net"

    public fun callLogin() {
        val language = "en"
        val latitude = "0.0"
        val longitude = "0.0"
        val isAuto = true
        val environment = SDKEnvironment.STAGING

        val service = LoginService(this, scope, redirectUrl, environment)
        service.login(language, latitude, longitude, isAuto)
    }

    override fun onLoginSuccess(json: String?, expiresSecond: Int) {
        TODO("Not yet implemented")
    }

    override fun onLoginError(msg: String?) {
        TODO("Not yet implemented")
    }

    override fun onLogoutRespond(isSuccess: Boolean, json: String?) {
        TODO("Not yet implemented")
    }

    override fun onRefreshAccessToken(isSuccess: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onCanceled() {
        TODO("Not yet implemented")
    }

    override fun onFindTrueIDApp(isFound: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onMappingAlready(msg: String?) {
        TODO("Not yet implemented")
    }

    override fun onMappingSuccess(msg: String?) {
        TODO("Not yet implemented")
    }

    override fun onMappingFailed(msg: String?) {
        TODO("Not yet implemented")
    }

    override fun onRegisterSuccess(loginCode: String?, clientId: String?) {
        TODO("Not yet implemented")
    }

    override fun onRegisterError(errorMessage: String?) {
        TODO("Not yet implemented")
    }

    override fun onForgotSuccess(loginCode: String?, clientId: String?) {
        TODO("Not yet implemented")
    }

    override fun onForgotError(errorObject: String?) {
        TODO("Not yet implemented")
    }

    override fun onGetInfoSuccess(json: String?, expiresSecond: Int) {
        TODO("Not yet implemented")
    }

    override fun onGetInfoFailed(errorMessage: String?) {
        TODO("Not yet implemented")
    }

    override fun onReceivedScreen(screens: Screens?) {
        TODO("Not yet implemented")
    }

    override fun onReceivedEvent(events: Events?) {
        TODO("Not yet implemented")
    }

    override fun onRefreshAccessTokenFailed(errorMessage: String?) {
        TODO("Not yet implemented")
    }

    override fun onRevokeAlready() {
        TODO("Not yet implemented")
    }
}