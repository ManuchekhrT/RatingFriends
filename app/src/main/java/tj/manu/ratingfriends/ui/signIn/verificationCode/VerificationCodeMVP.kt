package tj.manu.ratingfriends.ui.signIn.verificationCode

import android.app.Activity
import com.google.firebase.auth.PhoneAuthCredential

interface VerificationCodeMVP {

    interface Model {
        fun verifyPhoneNumberWithCode(context: Activity, verificationCode: String)
        fun handleVerificationCode(phoneNumber: String?)
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun navigateToHomeActivity()
        fun setUserNameError()
        fun signInWithPhoneAndCode(credential: PhoneAuthCredential?)
        fun setVerificationId(verificationId: String?)
        fun setVerificationCode(code: String?)
    }

    interface Presenter {
        fun bindView(view: VerificationCodeMVP.View)
        fun validateVerificationCode(context: Activity, verificationCode: String)
        fun signInWithPhoneCredential(credential: PhoneAuthCredential?)
        fun onVerificationCodeError()
        fun sendVerificationCode(phoneNumber: String?)
        fun setVerificationId(verificationId: String?)
        fun setVerificationCode(code: String?)
    }
}