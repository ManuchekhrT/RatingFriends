package tj.manu.ratingfriends.ui.signIn.verificationCode

import android.app.Activity
import com.google.firebase.auth.PhoneAuthCredential

class VerificationCodePresenter : VerificationCodeMVP.Presenter {
    override fun setVerificationCode(code: String?) {
        view?.setVerificationCode(code)
    }

    override fun setVerificationId(verificationId: String?) {
        view?.setVerificationId(verificationId)
    }

    override fun sendVerificationCode(phoneNumber: String?) {
        model?.handleVerificationCode(phoneNumber)
    }


    private var view: VerificationCodeMVP.View? = null
    private var model: VerificationCodeMVP.Model? = null

    override fun signInWithPhoneCredential(credential: PhoneAuthCredential?) {
        view?.signInWithPhoneAndCode(credential)
    }

    override fun validateVerificationCode(context: Activity, verificationCode: String) {
        model?.verifyPhoneNumberWithCode(context, verificationCode)
    }

    override fun bindView(view: VerificationCodeMVP.View) {
        this.view = view
    }

    init {
        this.model = VerificationCodeModel(this)
    }

    override fun onVerificationCodeError() {
        view?.setUserNameError()
    }


}