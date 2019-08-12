package tj.manu.ratingfriends.ui.signIn

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*
import tj.manu.ratingfriends.R
import tj.manu.ratingfriends.ui.signIn.phone.PhoneSignInActivity

class SignInActivity : AppCompatActivity(), SignInMVP.View {

    private lateinit var presenter: SignInMVP.Presenter
    private var mAuth: FirebaseAuth? = null

    override fun openFacebookSignInActivity() {
    }

    override fun openPhoneSignInActivity() {
        val intent = PhoneSignInActivity.newIntent(this)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        presenter = SignInPresenter()
        presenter.bindView(this)

        phoneSignInBtn.setOnClickListener {
            presenter.onPhoneSignInBtnClickEvent()
        }

        facebookSignInBtn.setOnClickListener {
            presenter.onFacebookSignInBtnClickEvent()
        }

        animationBGGradient()
    }


    private fun animationBGGradient() {
        val constraintLayout = findViewById<ConstraintLayout>(R.id.root_layout)
        val animationDrawable = constraintLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

}