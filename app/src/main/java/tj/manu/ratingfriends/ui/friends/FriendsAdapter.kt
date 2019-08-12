package tj.manu.ratingfriends.ui.friends

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_friend.view.*
import tj.manu.ratingfriends.R

class FriendsAdapter(private val friendsList: List<Friend>, val context: Context) : RecyclerView.Adapter<FriendsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FriendsAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false))
    }

    override fun getItemCount(): Int = friendsList.size

    override fun onBindViewHolder(holder: FriendsAdapter.MyViewHolder, position: Int) {
        val friend = friendsList[position]
        holder.itemView.friendNameTv.text = friend.name
        if (holder.itemView.friendNameTv.text.toString().isEmpty()) {
            holder.itemView.friendNameTv.visibility = GONE
            holder.itemView.friendPhoneTv.visibility = VISIBLE
            holder.itemView.friendPhoneTv.text = friend.phone
        }

        if(friend.hasRatingApp)
            holder.itemView.hasAppIv.setImageResource(R.drawable.ic_check_circle)
        else
            holder.itemView.hasAppIv.setImageResource(R.drawable.ic_cancel)

        // Set image if exists
        try {
            if (friend.thumb != null) {
                holder.itemView.friendPhotoIv.setImageBitmap(friend.thumb)
            } else {
                holder.itemView.friendPhotoIv.setImageResource(R.drawable.nopicture)
            }
        } catch (e: OutOfMemoryError) {
            // Add default picture
            holder.itemView.friendPhotoIv.setImageDrawable(this.context.resources.getDrawable(R.drawable.nopicture))
            e.printStackTrace()
        }


    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}