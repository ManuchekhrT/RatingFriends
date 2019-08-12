package tj.manu.ratingfriends.ui.friends

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.provider.ContactsContract
import android.provider.MediaStore
import android.text.TextUtils
import tj.manu.ratingfriends.utils.CommonUtils
import java.io.IOException
import java.util.HashSet


class FriendsModel(var presenter: FriendsMVP.Presenter) : FriendsMVP.Model {


    override fun getContacts(context: Context) {
        val mContactList: MutableList<Friend> = mutableListOf()
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI,
            ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER
        )

        val cursor: Cursor?
        val contentResolver = context.contentResolver
        cursor = context.contentResolver
            .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null, null, null)

        if (cursor != null && cursor.count > 0) {
            cursor.use {
                val duplicateGuard = HashSet<String>()
                val indexOfDisplayName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val indexOfDisplayNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val indexOfPhoto = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI)

                while (cursor.moveToNext()) {
                    var bit_thumb: Bitmap? = null

                    var phoneNumber: String? = cursor.getString(indexOfDisplayNumber)

                    if (phoneNumber != null && !phoneNumber.isEmpty()) {
                        phoneNumber = phoneNumber.replace("[^+0-9]".toRegex(), "")
                        if (CommonUtils.isLocalPhoneFormat(phoneNumber)) {
                            phoneNumber = "+992$phoneNumber"
                        }

                        if (duplicateGuard.add(phoneNumber)) {
                            val name = cursor.getString(indexOfDisplayName)
                            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phoneNumber))
                                continue

                            val photo = cursor.getString(indexOfPhoto)
                            try {
                                if (photo != null) {
                                    bit_thumb = MediaStore.Images.Media.getBitmap(contentResolver, Uri.parse(photo))
                                }
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }

                            val contact = Friend()
                            contact.name = name
                            contact.phone = phoneNumber
                            contact.thumb = bit_thumb
                            mContactList.add(contact)
                        }
                    }
                }
            }
        }
        presenter.onContactList(mContactList)
    }

}