package tj.manu.ratingfriends.ui.signIn.phone

import android.app.Activity
import com.google.firebase.auth.PhoneAuthCredential

interface PhoneSignInMVP {
    interface Model {
        fun validatePhoneNumber(phoneNumber: String)
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun navigateToVerificationCodeActivity(phoneNumber: String)
        fun setUserNameError()
        fun setVerificationInProgress(b: Boolean)

    }

    interface Presenter {
        fun bindView(view: PhoneSignInMVP.View)
        fun validatePhoneNumber(phoneNumber: String)
        fun onPhoneNumberValidationError()

        fun setVerificationInProgress(b: Boolean)
        fun onPhoneNumberValidationSuccess(phoneNumber: String)
    }
}