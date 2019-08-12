package tj.manu.ratingfriends.ui.friends

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_friends.*
import tj.manu.ratingfriends.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FriendsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FriendsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FriendsFragment : Fragment(), FriendsMVP.View {

    private var friendsAdapter: FriendsAdapter? = null
    private lateinit var presenter: FriendsMVP.Presenter

    companion object {
        const val PERMISSIONS_REQUEST_READ_CONTACTS = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = FriendsPresenter()
        presenter.bindView(this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadContacts()
    }

    private fun loadContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            && checkSelfPermission(context!!, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS),
                PERMISSIONS_REQUEST_READ_CONTACTS)
            //callback onRequestPermissionsResult
        } else {
            presenter.loadContacts(context!!)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.loadContacts(context!!)
            } else {
                //  toast("Permission must be granted in order to display contacts information")
            }
        }
    }

    override fun showContacts(contactList: MutableList<Friend>) {
        populateRecyclerView(contactList)
    }

    private fun populateRecyclerView(contactList: MutableList<Friend>) {
        val linearLayoutManager = LinearLayoutManager(context!!)
        friendsRv.layoutManager = linearLayoutManager
        friendsAdapter = FriendsAdapter(contactList, context!!)
        friendsRv.adapter = friendsAdapter
        friendsAdapter?.notifyDataSetChanged()
    }

}
