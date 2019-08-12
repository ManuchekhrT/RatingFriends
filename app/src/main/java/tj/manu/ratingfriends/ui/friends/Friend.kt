package tj.manu.ratingfriends.ui.friends

import android.graphics.Bitmap

data class Friend(
    var id: String? = null,
    var name: String? = null,
    var thumb: Bitmap? = null,
    var phone: String? = null,
    var phones: MutableList<String> = mutableListOf(),
    var hasRatingApp: Boolean = false
)