package tj.manu.ratingfriends.ui.signIn.phone

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import tj.manu.ratingfriends.ui.signIn.phone.PhoneSignInMVP
import tj.manu.ratingfriends.utils.CommonUtils
import tj.manu.ratingfriends.utils.SettingsUtils
import java.util.concurrent.TimeUnit

class PhoneSignInModel(var presenter: PhoneSignInMVP.Presenter) : PhoneSignInMVP.Model {

    override fun validatePhoneNumber(phoneNumber: String) {
        if (CommonUtils.isValidPhone(phoneNumber)) {
            presenter.onPhoneNumberValidationSuccess(phoneNumber)
        } else
            presenter.onPhoneNumberValidationError()

    }

//    override fun signInWithPhoneNumber(activity: Activity, phoneNumber: String) {
//        if (CommonUtils.isValidPhone(phoneNumber)) {
//            PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                phoneNumber,
//                60,
//                TimeUnit.SECONDS,
//                activity,
//                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                    override fun onVerificationFailed(e: FirebaseException?) {
//                        // This callback is invoked in an invalid request for verification is made,
//                        // for instance if the the phone number format is not valid.
//                        Log.d(this.javaClass.simpleName, "onVerificationFailed", e)
//                        Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
//
//                    }
//
//                    override fun onVerificationCompleted(credential: PhoneAuthCredential?) {
//                        // This callback will be invoked in two situations:
//                        // 1 - Instant verification. In some cases the phone number can be instantly
//                        //     verified without needing to send or enter a verification code.
//                        // 2 - Auto-retrieval. On some devices Google Play services can automatically
//                        //     detect the incoming verification SMS and perform verification without
//                        //     user action.
//                        presenter.setVerificationInProgress(false)
//                        Toast.makeText(activity, "Successful", Toast.LENGTH_LONG).show()
//                        presenter.onSuccessSignInWithPhone(credential, phoneNumber)
//
//                    }
//
//
//                    override fun onCodeSent(verificationId: String?, token: PhoneAuthProvider.ForceResendingToken?) {
//                        // The SMS verification code has been sent to the provided phone number, we
//                        // now need to ask the user to enter the code and then construct a credential
//                        // by combining the code with a verification ID.
//                        Log.d(this.javaClass.simpleName, "onCodeSent:" + verificationId!!)
//
//                        // Save verification ID and resending token so we can use them later
//                        SettingsUtils.storeVerificationId(activity, verificationId)
//
//                    }
//                }
//            )
//            presenter.setVerificationInProgress(true)
//        } else
//            presenter.onPhoneNumberValidationError()
//    }

}