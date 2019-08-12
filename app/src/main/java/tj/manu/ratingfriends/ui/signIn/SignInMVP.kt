package tj.manu.ratingfriends.ui.signIn

interface SignInMVP {

    interface Model {
        fun configureFacebookLogin()
    }

    interface View {
        fun openPhoneSignInActivity()
        fun openFacebookSignInActivity()
    }

    interface Presenter {
        fun bindView(view: View)
        fun onPhoneSignInBtnClickEvent()
        fun onFacebookSignInBtnClickEvent()
    }

}