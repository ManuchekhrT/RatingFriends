package tj.manu.ratingfriends.ui.friends

import android.content.Context

class FriendsPresenter : FriendsMVP.Presenter {

    private var view: FriendsMVP.View? = null

    private var model: FriendsMVP.Model? = null

    init {
        this.model = FriendsModel(this)
    }

    override fun bindView(view: FriendsMVP.View) {
        this.view = view
    }

    override fun loadContacts(context: Context) {
        model?.getContacts(context)
    }

    override fun onContactList(mContactList: MutableList<Friend>) {
        view?.showContacts(mContactList)
    }


}