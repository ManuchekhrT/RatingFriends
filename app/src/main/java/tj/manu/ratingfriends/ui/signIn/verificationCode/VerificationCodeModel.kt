package tj.manu.ratingfriends.ui.signIn.verificationCode

import android.app.Activity
import android.util.Log
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.auth.PhoneAuthProvider
import tj.manu.ratingfriends.utils.CommonUtils
import tj.manu.ratingfriends.utils.SettingsUtils
import java.util.concurrent.TimeUnit
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential



class VerificationCodeModel(var presenter: VerificationCodeMVP.Presenter) : VerificationCodeMVP.Model {

    override fun handleVerificationCode(phoneNumber: String?) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+992$phoneNumber",
            60,
            TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            mCallbacks)
    }

    //the callback to detect the verification status
    private val mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {

            //Getting the code sent by SMS
            val code = phoneAuthCredential.smsCode
            presenter.setVerificationCode(code)

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code

        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.d(this.javaClass.simpleName, " " + e.message)
        }

        override fun onCodeSent(verificationId: String?, forceResendingToken: PhoneAuthProvider.ForceResendingToken?) {
            super.onCodeSent(verificationId, forceResendingToken)

            presenter.setVerificationId(verificationId)
        }
    }


    override fun verifyPhoneNumberWithCode(context: Activity, verificationCode: String) {
        if (!verificationCode.isEmpty() && CommonUtils.isVerificationCodeLength(verificationCode)) {
            val credential = PhoneAuthProvider.getCredential(SettingsUtils.getVerificationId(context), verificationCode)
            presenter.signInWithPhoneCredential(credential)

        } else
            presenter.onVerificationCodeError()

    }


}