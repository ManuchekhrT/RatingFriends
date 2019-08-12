package tj.manu.ratingfriends.ui.signIn

class SignInPresenter : SignInMVP.Presenter {

    private var view: SignInMVP.View? = null
    private var signInModel: SignInMVP.Model? = null

    init {
        this.signInModel = SignInModel(this)
    }

    override fun bindView(view: SignInMVP.View) {
        this.view = view
    }

    override fun onFacebookSignInBtnClickEvent() {
        signInModel?.configureFacebookLogin()
    }

    override fun onPhoneSignInBtnClickEvent() {
        view?.openPhoneSignInActivity()
    }

}