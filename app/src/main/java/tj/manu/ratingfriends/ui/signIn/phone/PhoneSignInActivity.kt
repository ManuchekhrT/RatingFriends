package tj.manu.ratingfriends.ui.signIn.phone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_phone_sign_in.*
import tj.manu.ratingfriends.R
import tj.manu.ratingfriends.ui.signIn.verificationCode.VerificationCodeActivity
import tj.manu.ratingfriends.utils.CommonUtils

class PhoneSignInActivity : AppCompatActivity(), PhoneSignInMVP.View {


    private lateinit var presenter: PhoneSignInMVP.Presenter
    private var verificationInProgress = false


    override fun setVerificationInProgress(b: Boolean) {
        verificationInProgress = b
    }


    override fun navigateToVerificationCodeActivity(phoneNumber: String) {
        val intent = VerificationCodeActivity.newIntent(this, phoneNumber)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }


    override fun setUserNameError() {
        phoneSignInEdt.error = getString(R.string.username_error)
    }

    override fun hideProgress() {
    }

    override fun showProgress() {
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, PhoneSignInActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_sign_in)


        presenter = PhoneSignInPresenter()
        presenter.bindView(this)

        phoneSignInStep1Btn.setOnClickListener {
            validatePhoneCredential()
        }

    }


    private fun validatePhoneCredential() {
        val phoneNumber = CommonUtils.convertEdtString(phoneSignInEdt)
        presenter.validatePhoneNumber(phoneNumber)
    }

}