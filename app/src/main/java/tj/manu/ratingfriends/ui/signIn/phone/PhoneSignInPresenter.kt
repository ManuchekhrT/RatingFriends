package tj.manu.ratingfriends.ui.signIn.phone

class PhoneSignInPresenter : PhoneSignInMVP.Presenter {


    override fun setVerificationInProgress(b: Boolean) {
        view?.setVerificationInProgress(b)
    }


    private var view: PhoneSignInMVP.View? = null
    private var model: PhoneSignInMVP.Model? = null

    init {
        this.model = PhoneSignInModel(this)
    }

    override fun bindView(view: PhoneSignInMVP.View) {
        this.view = view
    }

    override fun validatePhoneNumber(phoneNumber: String) {
        if (view != null)
            view?.showProgress()

        model?.validatePhoneNumber(phoneNumber)
    }

    override fun onPhoneNumberValidationError() {
        view?.setUserNameError()
    }



    override fun onPhoneNumberValidationSuccess(phoneNumber: String) {
        view?.navigateToVerificationCodeActivity(phoneNumber)
    }




}