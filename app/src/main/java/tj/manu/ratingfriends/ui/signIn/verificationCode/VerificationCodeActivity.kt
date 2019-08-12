package tj.manu.ratingfriends.ui.signIn.verificationCode

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.android.synthetic.main.activity_verification_code.*
import tj.manu.ratingfriends.MainActivity
import tj.manu.ratingfriends.R
import tj.manu.ratingfriends.utils.CommonUtils
import tj.manu.ratingfriends.utils.SettingsUtils


class VerificationCodeActivity : AppCompatActivity(), VerificationCodeMVP.View {

    override fun setVerificationCode(code: String?) {
        if(code != null) {
            verificationCodeEdt.setText(code)
            presenter.validateVerificationCode(this, code)
        }
    }

    override fun setVerificationId(verificationId: String?) {
        SettingsUtils.storeVerificationId(this, verificationId!!)
        mVerificationId = verificationId
    }

    override fun signInWithPhoneAndCode(credential: PhoneAuthCredential?) {
        if (credential != null) {
            loading(true)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        loading(false)
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this@VerificationCodeActivity, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.d(this.javaClass.simpleName, "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            // [START_EXCLUDE silent]
                            verificationCodeEdt.error = "Invalid code."
                            // [END_EXCLUDE]
                        }

                    }
                }
        }
    }

    override fun showProgress() {
        loading(true)
    }

    override fun hideProgress() {
        loading(false)
    }

    override fun navigateToHomeActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUserNameError() {
        verificationCodeEdt.error = "Invalid code error"
    }

    private lateinit var presenter: VerificationCodeMVP.Presenter
    private var mVerificationId: String? = null

    companion object {
        private const val ARG_PHONE_NUMBER = "arg_phone_number"

        fun newIntent(context: Context, phoneNumber: String): Intent {
            val intent = Intent(context, VerificationCodeActivity::class.java)
            intent.putExtra(ARG_PHONE_NUMBER, phoneNumber)
            return intent
        }
    }


    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_code)
        mAuth = FirebaseAuth.getInstance()

        presenter = VerificationCodePresenter()
        presenter.bindView(this)
        val phoneNumber = intent.getStringExtra(ARG_PHONE_NUMBER)
        presenter.sendVerificationCode(phoneNumber)

        phoneSignInWithVerificationCodeStep2Btn.setOnClickListener {
            validateVerificationCode()
        }

    }

    private fun validateVerificationCode() {
        val verificationCode = CommonUtils.convertEdtString(verificationCodeEdt)
        presenter.validateVerificationCode(this, verificationCode)
    }

    // loading show loading while request in progress
    private fun loading(show: Boolean) {
        val shortAnimTime = Integer.parseInt(resources.getString(tj.manu.ratingfriends.R.string.config_anim_time))

        contentViewInVerificationCode.alpha = if (show) .5f else 1F

        loaderView.visibility = if (show) View.VISIBLE else View.GONE
        loaderView.animate().setDuration(shortAnimTime.toLong()).alpha(
            (if (show) 1 else 0).toFloat()
        ).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                loaderView.visibility = if (show) View.VISIBLE else View.GONE
            }
        })
    }
}
