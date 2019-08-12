package tj.manu.ratingfriends.ui.friends

import android.content.Context


interface FriendsMVP {

    interface Model {
        fun getContacts(context: Context)
    }

    interface View {
        fun showContacts(friendList: MutableList<Friend>)
    }

    interface Presenter {
        fun bindView(view: View)
        fun loadContacts(context: Context)
        fun onContactList(mContactList: MutableList<Friend>)
    }
}